package com.jduncan.bookclubfinal.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jduncan.bookclubfinal.models.Book;
import com.jduncan.bookclubfinal.models.User;
import com.jduncan.bookclubfinal.services.MainService;
import com.jduncan.bookclubfinal.services.UserService;

@Controller
public class BookController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private UserService userService;
	
	// **** DISPLAY ROUTES ****
	
	// Dashboard for users that have registered or successfully logged in
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		List<Book> books = mainService.allBooks();
		model.addAttribute("books", books);
		model.addAttribute("loggedUser", userService.getOneUser((Long) session.getAttribute("uuid")));
		return "dashboard.jsp";
	}
	
	// Form for adding new book
	@GetMapping("/books/new")
	public String newBookForm(HttpSession session, Model model) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		model.addAttribute("newBook", new Book());
		model.addAttribute("loggedUser", userService.getOneUser((Long) session.getAttribute("uuid")));
		return "newBook.jsp";
	}
	
	// View one book
	@GetMapping("books/{id}")
	public String viewOneBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		Book book = mainService.showOneBook(id);
		model.addAttribute("book", book);
		return "showOneBook.jsp";
	}
	
	// Form for editing existing book
	@GetMapping("/books/{id}/edit")
	public String editBookForm(@PathVariable("id") Long id, HttpSession session, Model model) {
		Book book = mainService.showOneBook(id);
		Long uuid = (Long) session.getAttribute("uuid");
		if(!book.getCreator().getId().equals(uuid)) {
			return "redirect:/dashboard";
		}
		model.addAttribute("book", book);
		return "editBook.jsp";
	}
	
	// **** ACTION ROUTES ****
	
	// Processes creation of new book
	@PostMapping("/books/new")
	public String createNewBook(@Valid @ModelAttribute("newBook") Book newBook, BindingResult binding, HttpSession session) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		if(binding.hasErrors()) {
			return "newBook.jsp";
		} else {
			User user = userService.getOneUser((Long) session.getAttribute("uuid"));
			newBook.setCreator(user);
			mainService.addBook(newBook);
			return "redirect:/dashboard";
		}
		
	}
	
	// Processes updating/editing of existing book
	@PutMapping("/books/{id}/edit")
	public String processEditBook(@Valid @ModelAttribute("book") Book book, BindingResult binding, HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		if(binding.hasErrors()) {
			model.addAttribute("loggedUser", userService.getOneUser((Long) session.getAttribute("uuid")));
			return "editBook.jsp";
		} else {
			Book bookUpdate = mainService.showOneBook(id);
			if(!session.getAttribute("uuid").equals(bookUpdate.getCreator().getId())) {
				return "redirect:/dashboard";
			}
			
			bookUpdate.setTitle(book.getTitle());
			bookUpdate.setAuthor(book.getAuthor());
			bookUpdate.setThoughts(book.getThoughts());
			
			mainService.editBook(bookUpdate);
			return "redirect:/dashboard";
			
		}
		
	}
	
	// Deletes book
	@DeleteMapping("/books/{id}/delete")
	public String processDelete(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		mainService.deleteBook(id);
		return "redirect:/dashboard";
	}

}
