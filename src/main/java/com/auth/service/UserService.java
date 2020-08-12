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

	public DAOUser save(DAOUser user) {
		DAOUser findUser = userRepo.findByUsername(user.getUsername());

		if(findUser == null) {
			DAOUser newUser = userRepo.save(new DAOUser(
					user.getUsername(),
					user.getFirstName(),
					user.getLastName(),
					bcryptEncoder.encode(user.getPassword()),
					user.getEmail(),
					user.getContact(),
					user.getHomeAddress(),
					user.getOfficeAddress()
			));
			newUser.setPassword("");
			return newUser;
		}else {
			return new DAOUser("User already exists");
		}
	}

	public DAOUser updateUser(DAOUser user) {		
		DAOUser upuser = userRepo.findByUsername(getUserName());
		if(upuser == null) {
			return new DAOUser("User does not exists");
		}else {
			DAOUser updtUser = userRepo.save(new DAOUser(
					user.getUsername(),
					user.getFirstName(),
					user.getLastName(),
					bcryptEncoder.encode(user.getPassword()),
					user.getEmail(),
					user.getContact(),
					user.getHomeAddress(),
					user.getOfficeAddress()
			));
			updtUser.setPassword("");
			return updtUser;
		}
	}

	public DAOUser getUser() {
		return userRepo.findByUsername(getUserName());
	}
	public long getUserId() {
		return getUser().getId();
	}
}