package com.jduncan.bookclubfinal.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jduncan.bookclubfinal.models.LoginUser;
import com.jduncan.bookclubfinal.models.User;
import com.jduncan.bookclubfinal.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	// Register new user
	public User register(User newUser, BindingResult binding) {
		if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
			binding.rejectValue("email", "Unique", "This email is already in use.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			binding.rejectValue("confirmPassword", "Matches", "Confirmed password does not match.");
		}
		if(binding.hasErrors()) {
			return null;
		} else {
			String who_hash = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(who_hash);
			return userRepo.save(newUser);
		}
	}
	
	// Login existing user
	public User login(LoginUser newLogin, BindingResult binding) {
		if(binding.hasErrors()) {
			return null;
		}
		
		Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
		if(!potentialUser.isPresent()) {
			binding.rejectValue("email", "Unique", "Unknown email - please confirm or register.");
			return null;
		}
		
		User user = potentialUser.get();
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			binding.rejectValue("password", "Matches", "Password does not match our records.");
		}
		if(binding.hasErrors()) {
			return null;
		} else {
			return user;
		}
	}
	
	// Retrieve one user
	public User getOneUser(Long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

}
