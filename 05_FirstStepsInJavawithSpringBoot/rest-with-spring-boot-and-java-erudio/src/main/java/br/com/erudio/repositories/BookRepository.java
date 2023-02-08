package br.com.erudio.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.erudio.model.Book;
//Utilizar o @Service ou @Repository é para identificar mais facil que tipo de classe estamos lidando,a funcionalidade é a mesma para ambos
public interface BookRepository extends JpaRepository<Book, Long>{
	
	//%and%
		@Query("SELECT b FROM Book b WHERE b.author LIKE LOWER(CONCAT ('%',:author,'%'))")
		Page<Book> findBookByAuthor(@Param("author") String author, Pageable pageable);
}
