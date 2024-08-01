package com.stock.controller;

import com.stock.entity.MyUser;
import com.stock.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.stock.service.IUserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService service;

	@PostConstruct
	public void createAdmin() {
		MyUser myUser = new MyUser();
		myUser.setUserId(1);
		myUser.setFullName("Admin");
		myUser.setUsername("admin@gmail.com");
		myUser.setUserContact("9037492402");
		myUser.setPassword(new BCryptPasswordEncoder().encode("123"));
		myUser.setRole(UserRole.ADMIN);
		service.saveUser(myUser);
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody MyUser myUser) {
		myUser.setRole(UserRole.NORMAL);
		return ResponseEntity.ok(service.saveUser(myUser));
	}

	@GetMapping("/getUser/{uid}")
	public ResponseEntity<?> getUser(@PathVariable("uid") Integer uid) {

		MyUser myUser = service.fetchUserById(uid);
		return new ResponseEntity<MyUser>(myUser, HttpStatus.OK);

	}

	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody MyUser myUser){
		return ResponseEntity.ok(service.loginUser(myUser));
	}
}
