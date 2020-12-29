package com.tts.PositiveVibes.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tts.PositiveVibes.model.Role;
import com.tts.PositiveVibes.model.User;
import com.tts.PositiveVibes.model.Vibe;
import com.tts.PositiveVibes.repository.RoleRepository;
import com.tts.PositiveVibes.repository.UserRepository;


@Service
public class Vibeservice {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public Vibeservice(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
		}

	public void save(@Valid Vibe vibe) {
		userRepository.save(vibe);
	}
	public User saveNewUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setActive(1);
	    Role userRole = roleRepository.findByRole("USER");
	    user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	    return userRepository.save(user);
	}
	public User getLoggedInUser() {
	    String loggedInUsername = SecurityContextHolder.
	      getContext().getAuthentication().getName();
	    
	    return findByUsername(loggedInUsername);
	}
}