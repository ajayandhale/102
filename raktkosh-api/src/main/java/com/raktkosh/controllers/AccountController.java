package com.raktkosh.controllers;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktkosh.core.Role;
import com.raktkosh.dto.requests.SigninDTO;
import com.raktkosh.dto.requests.SignupDTO;
import com.raktkosh.dto.responses.MessageResponse;
import com.raktkosh.pojos.User;
import com.raktkosh.repositories.UserRepository;
import com.raktkosh.security.UserDetailsImpl;
import com.raktkosh.services.IVerificationService;
import com.raktkosh.utils.JWTUtils;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = { "${com.raktkosh.ORIGINS}" })
public class AccountController {
  
  private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private IVerificationService verification;

  @Autowired
  private JWTUtils jwtUtils;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody @Valid SignupDTO signupRequest) {
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken."));
    }

    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use."));
    }

    signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    User user = User.build(signupRequest);
    user.setRole(Role.USER);

    userRepository.save(user);
    try {
      verification.sendVerificationMail(user.getEmail());
    } catch (MessagingException e) {
      logger.error("Failed to send verification email. " + e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Registeration successfull."));
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signup(@RequestBody @Valid SigninDTO signinRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJWTToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    userDetails.setToken(jwt);

    return ResponseEntity.ok(userDetails);
  }
  
  @GetMapping("/verify/{token}")
  public ResponseEntity<?> verify(@PathVariable String token) {
    return ResponseEntity.ok(verification.verifyEmail(token));
  }
}
