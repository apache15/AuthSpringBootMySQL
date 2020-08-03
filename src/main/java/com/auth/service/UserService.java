package com.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.model.DAOUser;
import com.auth.model.UserDTO;
import com.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setHome_addr(user.getHome_addr());
		newUser.setOffice_addr(user.getOffice_addr());
		newUser.setContact(user.getContact());
		return userRepo.save(newUser);
	}

	public DAOUser updateUser(UserDTO user) {
		DAOUser upuser = userRepo.findByUsername(getUserName());
		upuser.setEmail(user.getEmail());
		upuser.setContact(user.getContact());
		upuser.setHome_addr(user.getHome_addr());
		upuser.setOffice_addr(user.getOffice_addr());
		return userRepo.save(upuser);
	}

	public DAOUser getUser() {
		return userRepo.findByUsername(getUserName());
	}
	public long getUserId() {
		return getUser().getId();
	}
}