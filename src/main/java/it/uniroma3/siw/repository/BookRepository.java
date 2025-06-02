package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

	public List<Book> findAll();
	
	public Book findByTitle(String title);
	
	public boolean existsByTitle(String title);
	
	boolean existsByTitleAndYear(String title, Integer year);
	
	public List<Book> findByGenre(String genre);
	
	public List<Book> findByAuthors(Author author);
	
	public void deleteById(Long id);
	
	public List<Book> findAllByOrderByYearDesc();
	
	/* @Query("SELECT b FROM Book b JOIN b.reviews r GROUP BY b HAVING AVG(r.rating) >= :minAverage")
    List<Book> findByAverageRatingGreaterThanEqual(@Param("minAverage") Double minAverage); */
}
