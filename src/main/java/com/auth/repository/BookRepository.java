package com.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.auth.model.DAOBook;


public interface BookRepository extends CrudRepository<DAOBook, Long> {
	
	@Query(value = "select * from books where isbn = :isbn", nativeQuery = true)
	Optional<DAOBook> findByISBN(@Param(value = "isbn") String isbn);
	
}
