package com.jduncan.bookclubfinal.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	// **** CONSTRUCTORS ****
	
	public User() {
		
	}
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	// **** PRIMARY KEY ****
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// **** MEMBER VARIABLES ****
	
	@NotEmpty(message="Name is required!")
	@Size(min=1, message="Name cannot be blank.")
	private String name;
	
	@NotEmpty(message="Email is required!")
	@Email(message="Please enter a valid email.")
	private String email;
	
	@NotEmpty(message="Password is required!")
	@Size(min=8, max=128, message="Password must be between {min} and {max} characters.")
	private String password;
	
	@Transient
	@NotEmpty(message="Confirm password is required!")
	@Size(min=8, max=128, message="Confirm password must be between {min} and {max} characters.")
	private String confirmPassword;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	// **** ONE TO MANY ****
	
	@OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
	private List<Book> createdBooks;
	
	// **** GETTERS & SETTERS
	
	@PrePersist
	protected void onCreate() {
	    this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
	    this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Book> getCreatedBooks() {
		return createdBooks;
	}

	public void setCreatedBooks(List<Book> createdBooks) {
		this.createdBooks = createdBooks;
	}
	
	
	
}
