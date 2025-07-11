package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.Base64;

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

import it.uniroma3.siw.controller.validator.AuthorValidator;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.service.AuthorService;
import jakarta.validation.Valid;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AuthorValidator authorValidator;
	
	@GetMapping(value = "/admin/formNewAuthor")
	public String addAuthor(Model model) {
		model.addAttribute("author", new Author());
		return "admin/formNewAuthor.html";
	}
	
	@PostMapping("/admin/author")
	public String newAuthor(@Valid @ModelAttribute("author") Author author, BindingResult bindingResult,
			@RequestParam("image") MultipartFile multipartFile,
			Model model) throws IOException {
		/* if(!this.authorService.existsByNameAndSurname(author.getName(), author.getSurname())) {
			String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());;
			author.setPhoto(base64Image);
			this.authorService.save(author);
			model.addAttribute("author", author);
			return "author.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo autore esiste già");
			return "admin/formNewAuthor.html";
		} */
		// this.authorValidator.validate(author, bindingResult);
		if(!this.authorService.existsByNameAndSurname(author.getName(), author.getSurname())) {
			String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());;
			author.setPhoto(base64Image);
			this.authorService.save(author);
			model.addAttribute("author", author);
			return "author.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo autore esiste già");
			return "admin/formNewAuthor.html";
		}
	}
	
	@GetMapping(value="/admin/indexAuthor")
	public String indexAuthor() {
		return "admin/indexAuthor.html";
	}
	
	@GetMapping("author/{id}")
	public String getAuthor(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.authorService.getById(id).get());
		return "author.html";
	}
	
	@GetMapping("/author")
	public String getAuthor(Model model) {
		model.addAttribute("authors", this.authorService.getAll());
		return "authors.html";
	}
	
	@GetMapping("/admin/formUpdateAuthor/{id}")
	public String formUpdateAuthor(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.authorService.getById(id).get());
		return "admin/formUpdateAuthor.html";
	}
	
	@PostMapping("/admin/updateAuthor/{id}")
	public String updateAuthor(@PathVariable("id") Long id, @Valid @ModelAttribute("author") Author author,
			BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		if(bindingResult.hasErrors()) {
			return "admin/formUpdateAuthor.html";
		}
		
		Author existingAuthor = authorService.getById(id).get();
		if (!multipartFile.isEmpty()) {
	        String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());
	        existingAuthor.setPhoto(base64Image);
	    }
		this.authorService.save(existingAuthor);
		return "redirect:/author/" + id;	
	}
	
	@GetMapping(value="/admin/manageAuthors")
	public String manageAuthors(Model model) {
		model.addAttribute("authors", this.authorService.getAll());
		return "admin/manageAuthors.html";
	}
	
	@GetMapping("/admin/author/delete/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
		this.authorService.deleteById(id);
		model.addAttribute("authors", this.authorService.getAll());
		return "redirect:/author";
	}
}