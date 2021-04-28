package com.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.auth.model.DAOBook;
import com.auth.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;

	public boolean save(DAOBook book) {
		try {
			if (book.getISBN().length() != 13)
				throw new Exception("Invalid ISBN " + book.getISBN());

			DAOBook foundBook = bookRepo.findByISBN(book.getISBN()).orElse(null);
			if (foundBook != null)
				throw new Exception("Book with ISBN " + book.getISBN() + " already exists.");
			bookRepo.save(book);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public List<DAOBook> sortBooks(List<DAOBook> books, String sortBy) {

		switch (sortBy) {
		case "PRICE":
			Collections.sort(books, new Comparator<DAOBook>() {
				@Override
				public int compare(DAOBook o1, DAOBook o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}
			});
			break;
		case "ISBN":
			Collections.sort(books, new Comparator<DAOBook>() {
				@Override
				public int compare(DAOBook o1, DAOBook o2) {
					return Long.parseLong(o1.getISBN()) > Long.parseLong(o2.getISBN()) ? 1 : -1;
				}
			});
			break;
		}

		return books;
	}

	public Iterable<DAOBook> getAll() {
		List<DAOBook> books = new ArrayList<DAOBook>();
		Iterator<DAOBook> iterator = bookRepo.findAll().iterator();
		while (iterator.hasNext()) {
			books.add(iterator.next());
		}
		return books;
	}

	public boolean update(DAOBook book, Long bookId) {
		try {
			DAOBook foundBook = bookRepo.findById(bookId).orElse(null);
			if (foundBook == null)
				throw new Exception("No book found with ID:\t" + bookId);
			foundBook.setTitle(book.getTitle());
			foundBook.setISBN(book.getISBN());
			foundBook.setPrice(book.getPrice());
			foundBook.setAuthorName(book.getAuthorName());
			bookRepo.save(foundBook);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Autowired
	private JdbcTemplate jdbc;

	public Collection<DAOBook> search(String q, String sortBy) {
		List<DAOBook> books = (List<DAOBook>) jdbc.query("select * from books where title like '%" + q
					+ "%' or isbn like '%" + q + "%' or author_name like '%" + q + "%' ",
					new BeanPropertyRowMapper(DAOBook.class));
		return this.sortBooks(books, sortBy);
	}

	public DAOBook getBookById(Long id) {
		return bookRepo.findById(id).orElse(null);
	}

	public boolean delete(Long id) {
		try {
			bookRepo.deleteById(id);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}