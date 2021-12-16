package com.jduncan.bookclubfinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jduncan.bookclubfinal.models.Book;
import com.jduncan.bookclubfinal.repositories.BookRepository;

@Service
public class MainService {
	
	@Autowired
	private BookRepository bookRepo;
	
	// Find all books
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}
	
	// Create a book
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	// Edit a book
	public Book editBook(Book book) {
		return bookRepo.save(book);
	}
	
	// View one book
	public Book showOneBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	// Delete one book
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}

}
