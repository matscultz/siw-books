package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public void save(Review review) {
		/* Book book = review.getBook();
		User user = review.getReviewer();
		
		if(book != null && !book.getReviews().contains(review))  {
			book.getReviews().add(review);
		}
		
		if(user != null && !user.getReviews().contains(review)) {
			user.getReviews().add(review);
		} */
		
		this.reviewRepository.save(review);
	}

	public Optional<Review> getById(Long id) {
	        return this.reviewRepository.findById(id);
	}
	
	public List<Review> getReviewByBook(Book book) {
		return this.reviewRepository.findByBook(book);
	}
	
	public List<Review> getReviewsByReviewer(User user) {
		return this.reviewRepository.findByReviewer(user);
	}
	
	public boolean alreadyReviewed(Book book, User user) {
		return this.reviewRepository.existsByBookAndReviewer(book, user);
	}
	
	public void deleteById(Long id) {
		this.reviewRepository.deleteById(id);
	}
	
	
	
}
