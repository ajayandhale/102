package com.raktkosh.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.raktkosh.core.Antigens;
import com.raktkosh.core.BloodTypes;
import com.raktkosh.core.PostCategory;
import com.raktkosh.pojos.Post;

public interface IPostService {

	Post addPost(Post post);
	
	boolean deletePostById(Long id);
	
	List<Post> findByTypeAndAntigen(BloodTypes type,Antigens antigen);
	
	List<Post> findByPostCategory(PostCategory postcategory);
	
	List<Post> findByTypeAndSort(BloodTypes type, Antigens antigen,Sort sort);
	
	Post findById(Long id);

}
