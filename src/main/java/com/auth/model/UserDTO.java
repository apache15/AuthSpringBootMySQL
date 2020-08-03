package com.auth.model;

public class UserDTO {
	private String username;
	private String password;
	private String email;
	private String contact;
	private String home_addr;
	private String office_addr;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHome_addr() {
		return home_addr;
	}

	public void setHome_addr(String home_addr) {
		this.home_addr = home_addr;
	}

	public String getOffice_addr() {
		return office_addr;
	}

	public void setOffice_addr(String office_addr) {
		this.office_addr = office_addr;
	}
}