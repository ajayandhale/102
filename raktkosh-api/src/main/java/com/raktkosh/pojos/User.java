package com.raktkosh.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.raktkosh.core.Antigens;
import com.raktkosh.core.BloodTypes;
import com.raktkosh.core.Role;
import com.raktkosh.dto.requests.SignupDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {
  
  @Column(unique = true, length = 20, nullable = false)
  private String username;
  
  @Column(nullable = false)
  private String password;
  
  @Column(length = 50, nullable = false)
  private String fullname;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(nullable = false)
  private LocalDate dob;
  
  @Column(length = 50, unique = true, nullable = false)
  private String email;
  
  @Pattern(regexp = "^[6789]\\d{9}$")
  @Column(length = 10)
  private String mobile;
  
  @Enumerated(value = EnumType.STRING)
  @Column(length = 10)
  private Role role;
  
  @Enumerated(value = EnumType.STRING)
  @Column(name = "blood_type", length = 2)
  private BloodTypes bloodType;
  
  @Enumerated(value = EnumType.STRING)
  @Column(length = 10)
  private Antigens antigen;
  
  @Column(insertable = false, updatable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP", name = "registered_on")
  private LocalDateTime registerdOn;
  
  @OneToOne(mappedBy = "user")
  private UserAddress address;
  
  @Column(insertable = false, columnDefinition = "TINYINT NOT NULL DEFAULT 0")
  private boolean activated;

  
  @OneToMany(mappedBy = "userId")
  private List<Post> userId;

  public static User build(@Valid SignupDTO signupRequest) {
    User user = new User();
    user.setUsername(signupRequest.getUsername());
    user.setPassword(signupRequest.getPassword());
    user.setFullname(signupRequest.getFullname());
    user.setEmail(signupRequest.getEmail());
    user.setDob(signupRequest.getDob());
    return user;
  }

}
