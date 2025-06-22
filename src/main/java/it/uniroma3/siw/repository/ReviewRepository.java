package it.uniroma3.siw.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	
	public List<Review> findAll();
	
	public List<Review> findByBook(Book book);
	
	public List<Review> findByReviewer(User reviewer);
	
	public boolean existsByBookAndReviewer(Book book, User reviewer);
	
	public List<Review> findByRatingGreaterThanEqual(Integer rating);
	
}
