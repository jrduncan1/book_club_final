package com.jduncan.bookclubfinal.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jduncan.bookclubfinal.models.LoginUser;
import com.jduncan.bookclubfinal.models.User;
import com.jduncan.bookclubfinal.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// **** DISPLAY ROUTES ****
	
	// Index (login & reg page)
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("uuid") != null) {
			return "redirect:/dashboard";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	// Logs user out and clears session, redirecting to index.jsp
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// **** ACTION ROUTES ****
	
	// Processes registration of new user
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult binding, Model model, HttpSession session) {
		userService.register(newUser, binding);
		if(binding.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("uuid", newUser.getId());
		return "redirect:/dashboard";
	}
	
	// Processes logging in of existing user
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult binding, Model model, HttpSession session) {
		User user = userService.login(newLogin, binding);
		if(binding.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		session.setAttribute("uuid", user.getId());
		return "redirect:/dashboard";
	}

}
