package it.uniroma3.siw.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Genre;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.validation.Valid;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAllBooks() {
		return this.bookRepository.findAll();
	}

	public Optional<Book> getById(Long id) {
		return this.bookRepository.findById(id);
	}
	
	public void save(@Valid Book book) {
		this.bookRepository.save(book);
	}
	
	public boolean existsByTitle(String title) {
		return this.bookRepository.existsByTitle(title);
	}
	
	public Optional<Book> findByTitle(String title) {
		return this.bookRepository.findByTitle(title);
	}

	public List<Book> findByGenre(String genreString) {
	    Genre genre;
	    try {
	        genre = Genre.valueOf(genreString.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        return Collections.emptyList();
	    }
	    return this.bookRepository.findByGenre(genre);
	}
	
	public void deleteById(Long id) {
		this.bookRepository.deleteById(id);
	}

	
}
