
package com.company.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="USER")
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotBlank
	@Size(min=6,max=30,message="size of name should min 2 char and max 10 char!!")
    @Column(length=30)
	private String name;
	 @Column( unique=true  )
	private String email;
	private String password;
	private String role;
	private boolean enabled;
    private String imageUrl;
    @Column(length=500)
    private String about;
    
    
     @OneToMany(  mappedBy="user" ,cascade = CascadeType.ALL)
	private List<Contact> contacts =new ArrayList<>();			;
    


	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
    
	public List<Contact> getContact() {
		return contacts;
	}

	public void setContact(List<Contact> contact) {
		this.contacts = contact;
	
	}
	}

