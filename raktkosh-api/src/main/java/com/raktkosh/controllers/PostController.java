package com.raktkosh.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktkosh.core.Antigens;
import com.raktkosh.core.BloodTypes;
import com.raktkosh.core.PostCategory;
import com.raktkosh.pojos.Post;
import com.raktkosh.pojos.User;
import com.raktkosh.repositories.UserRepository;
import com.raktkosh.services.IPostService;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

	@Autowired
	private IPostService postService;
	
	@Autowired
	private UserRepository userRepo;
	
	public PostController()
	{
		System.out.println("In Constructor Of"+getClass().getName());
	}
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<?> addPost(@RequestBody @Valid Post post , @PathVariable Long userId){
	User user	=  userRepo.findById(userId).orElseThrow();
		post.setUserId(user);
		return new ResponseEntity<>(postService.addPost(post), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<Boolean> deletePostById(@PathVariable Long postId){
		return ResponseEntity.ok(postService.deletePostById(postId));
	}
	
	@GetMapping("/{postCategory}")
	public List<?> findByPostCategory(@PathVariable PostCategory postCategory) {
		return postService.findByPostCategory(postCategory);
		//return ResponseEntity.status(HttpStatus.OK).body(postService.findByPostCategory(postCategory));

	}
	
	@GetMapping("/{bloodType}/{antigen}")
	public ResponseEntity<?> findByTypeAndAntigen(@PathVariable BloodTypes bloodType,@PathVariable Antigens antigen){
	return ResponseEntity.status(HttpStatus.OK).body(postService.findByTypeAndAntigen(bloodType, antigen));
	}
	
	@GetMapping("/{bloodType}/{antigen}/dateBy")
	public ResponseEntity<?> findByTypeAndSort(@PathVariable BloodTypes bloodType,@PathVariable Antigens antigen) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findByTypeAndSort(bloodType, antigen, Sort.by("createdOn")));
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
	}
} 
