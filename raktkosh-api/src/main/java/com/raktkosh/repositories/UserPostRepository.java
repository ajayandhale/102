package com.raktkosh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raktkosh.core.Antigens;
import com.raktkosh.core.BloodTypes;
import com.raktkosh.core.PostCategory;
import com.raktkosh.pojos.Post;

@Repository
public interface UserPostRepository extends JpaRepository<Post, Long> {

	List<Post> findByTypeAndAntigen(BloodTypes type, Antigens antigen);

	List<Post> findByPostCategory(PostCategory postCategory);

	@Query("select p from Post p left outer join fetch p.userId where p.type = ?1 and p.antigen = ?2")
	//@Query("select p , u.id from Post p join User u fetch u.userId where p.type = ?1 and p.antigen = ?2")
	List<Post> findByTypeAndSort(BloodTypes type, Antigens antigen, Sort sort);
	
	List<Post> findAll();
	
	Optional<Post> findById(Long id);

}
