package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.BookValidator;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Genre;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookValidator bookValidator;
	
	@GetMapping("/book")
	public String getBooks(Model model) {
		model.addAttribute("books", this.bookService.findAllBooks());
		return "books.html";
	}
	
	@GetMapping("/book/{id}")
	public String getBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", this.bookService.getById(id).get());
		return "book.html";
	}
	
	@GetMapping(value="/admin/manageBooks")
	public String manageBooks(Model model) {
		model.addAttribute("books", this.bookService.findAllBooks());
		return "admin/manageBooks.html";
	}
	
	@GetMapping(value="/admin/formUpdateBook/{id}")
	public String formUpdateBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", this.bookService.getById(id).get());
		model.addAttribute("authors", authorService.getAllInOrder());
		model.addAttribute("genres", Genre.values());
		return "admin/formUpdateBook.html";
	}
	
	@PostMapping("/admin/updateBook/{id}")
	public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult,
	                         @RequestParam("authorIds") List<Long> authorIds, @RequestParam("image") MultipartFile multipartFile,
	                         Model model) throws IOException {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("authors", authorService.getAllInOrder());
	        model.addAttribute("genres", Genre.values());
	        return "admin/formUpdateBook.html";
	    }
	    
	    Book existingBook = bookService.getById(id).get();

	    existingBook.setTitle(book.getTitle());
	    existingBook.setYear(book.getYear());
	    existingBook.setPlot(book.getPlot());
	    existingBook.setGenre(book.getGenre());
	    existingBook.setAuthors(authorService.findAllByIds(authorIds));

	    if (!multipartFile.isEmpty()) {
	        String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());
	        existingBook.setPhoto(base64Image);
	    }

	    bookService.save(existingBook);

	    model.addAttribute("book", existingBook);
	    return "book.html";
	}

	@GetMapping(value = "/admin/formNewBook")
	public String addNewBook(Model model) {
		//model.addAttribute("authors", this.authorService.getAll());
		model.addAttribute("genres", Genre.values());
		model.addAttribute("authors", this.authorService.getAllInOrder());
		model.addAttribute("book", new Book());
		return "/admin/formNewBook.html";
	}
	
	@PostMapping(value = "/admin/book")
	public String newBook(@Valid @ModelAttribute("book") Book book, @RequestParam("authorIds") List<Long> authorIds,
			BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		
		
		// Author author = this.authorService.getById(authorId).get();
		List<Author> selectedAuthors = new ArrayList<>();
		if(authorIds == null || authorIds.isEmpty()) {
			bindingResult.reject("book.authors.required");
		} else {
			for(Long id : authorIds) {
				Optional<Author> authorOptional = this.authorService.getById(id);
				if(authorOptional.isEmpty()) {
					bindingResult.reject("book.authors.invalidId");
					break;
				} else {
					selectedAuthors.add(authorOptional.get());
				}
			}
		}
		book.setAuthors(selectedAuthors);
		
		this.bookValidator.validate(book, bindingResult);
		
		this.bookValidator.validate(book, bindingResult);
		if(!bindingResult.hasErrors()) {
			String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());;
			book.setPhoto(base64Image);
			this.bookService.save(book);
			model.addAttribute("book", book);
			return "book.html";
		} else {
			return "admin/formNewBook.html";
		} 
		
		/* if(!this.bookService.existsByTitle(book.getTitle())) {
			String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());;
			book.setPhoto(base64Image);
			book.setAuthor(author);
			this.bookService.save(book);
			model.addAttribute("book", book);
			return "book.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo libro esiste già!");
			return "admin/formNewBook.html";
		}
			this.bookValidator.validate(book, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.bookService.save(book);
			model.addAttribute("book", book);
			return "book.html";
		} else {
			return "admin/formNewBook.html";
		} */
	}
	
	@GetMapping(value="/admin/indexBook")
	public String indexBook() {
		return "admin/indexBook.html";
	}

	
	@GetMapping("/admin/book/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		this.bookService.deleteById(id);
		model.addAttribute("books", this.bookService.findAllBooks());
		return "redirect:/book";
	}
}
