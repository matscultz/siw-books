package it.uniroma3.siw.controller.validator;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.service.AuthorService;

@Component
public class AuthorValidator implements Validator{

	@Autowired
	private AuthorService authorService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Author.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Author author = (Author) o;
		
		LocalDate dateOfBirth = author.getDateOfBirth();
        LocalDate dateOfDeath = author.getDateOfDeath();
		
		if (dateOfBirth != null && dateOfDeath != null && dateOfDeath.isBefore(dateOfBirth)) {
            errors.reject("author.dateOfDeath.invalid");
        }

		if (author.getName() != null && author.getSurname() != null) {
            if (authorService.existsByNameAndSurname(author.getName(), author.getSurname())) {
                errors.reject("author.duplicate");
            }
        }
	}

}
