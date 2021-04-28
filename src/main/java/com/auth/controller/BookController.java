package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.DAOBook;
import com.auth.service.BookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/book")
public class BookController {

	
	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<?> saveBook(@RequestBody DAOBook book) throws Exception {
		if(bookService.save(book))
			return ResponseEntity.ok("BOOK CREATED");
		return ResponseEntity.ok("FAILED");
	}
	@GetMapping
	public ResponseEntity<?> getBooks(@RequestParam(required = false) String sortBy) {
		return ResponseEntity.ok(bookService.getAll());
	}
	@GetMapping("/{bookId}")
	public ResponseEntity<?> getBooks(@PathVariable Long bookId) {
		DAOBook book = bookService.getBookById(bookId);
		if(book == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(book);
	}
	@GetMapping("/search")
	public ResponseEntity<?> searchBooks(@RequestParam String q,@RequestParam String sortBy) {
		if(q.trim().length() < 3) return ResponseEntity.ok("query string needs atleast 3 characters");
		return ResponseEntity.ok(bookService.search(q, sortBy));
	}
	@PutMapping(value = "/{bookId}")
	public ResponseEntity<String> updateBook(@RequestBody DAOBook book, @PathVariable Long bookId) {
		if(bookService.update(book, bookId))
			return ResponseEntity.ok("UPDATED");
		return ResponseEntity.ok("FAILED");
	}
	
	@DeleteMapping(value = "/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
		if(bookService.delete(bookId))	return ResponseEntity.ok("DELETED");
		return ResponseEntity.ok("FAILED");
	}
}