package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;

	public List<Author> getAll() {
		return (List<Author>) this.authorRepository.findAll();
	}

	public Optional<Author> getById(Long id) {
		return this.authorRepository.findById(id);
	}

	public boolean existsByNameAndSurname(String name, String surname) {
		return this.authorRepository.existsByNameAndSurname(name, surname);
	}

	public void save(Author author) {
		this.authorRepository.save(author);
	}

	public List<Author> getAllInOrder() {
		return this.authorRepository.findAllAuthorsOrderedBySurname();
	}
	
	public List<Author> getBySurname(String surname) {
		return this.authorRepository.findBySurname(surname);
	}

	public List<Author> findAllByIds(List<Long> authorIds) {
		return (List<Author>) this.authorRepository.findAllById(authorIds);
	}

    @Transactional
    public void deleteById(Long authorId) {
        
        Author authorToDelete = authorRepository.findById(authorId)
                                                .orElseThrow(() -> new EntityNotFoundException("Autore non trovato con ID: " + authorId));

        for(Book book : authorToDelete.getBooks()) {
        	book.getAuthors().remove(authorToDelete);
        	this.bookRepository.save(book);
        }
        
        authorToDelete.getBooks().clear();

        this.authorRepository.delete(authorToDelete);
    }
}
