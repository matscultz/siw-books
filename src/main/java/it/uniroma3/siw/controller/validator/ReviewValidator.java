package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.service.ReviewService;

@Component
public class ReviewValidator implements Validator{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Review.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Review review = (Review) target;
		
		if (review.getComment() == null || review.getComment().trim().isEmpty()) {
			errors.reject("review.text.empty");
        }
		
		if (review.getComment() != null && review.getComment().trim().length() < 10) {
	        errors.reject("review.text.tooShort");
	    }
		
		if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
			errors.reject("review.rating.invalid");
		}
		
		if (review.getReviewer() != null && review.getBook() != null) {
            boolean exists = reviewRepository.existsByBookAndReviewer(review.getBook(), review.getReviewer());
            if (exists) {
                errors.reject("review.duplicate");
            }
        }

	}

}
