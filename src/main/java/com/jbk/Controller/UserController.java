package com.jbk.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.User;
import com.jbk.exception.UserAlreadyExistsException;
import com.jbk.exception.UserNotFoundException;
import com.jbk.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userservice;
	@PostMapping("/add-user")
	public ResponseEntity<Boolean> addUser(@RequestBody @Valid User user){
		Boolean isAdded=userservice.addUser(user);
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.OK);
		}else {
			throw new UserAlreadyExistsException("User Already Exists");
		}
	}
		@GetMapping("/get-all-user")
		public ResponseEntity<List<User>> getAllUser(){
			List<User> list=userservice.getAllUser();
			if(list!=null) {
				return new ResponseEntity<List<User>>(list,HttpStatus.OK);
			}else
			{
				throw new UserNotFoundException("User Not Fount");
			}
		}
		
		@GetMapping("/get-user-by-userid/{userId}")
		public ResponseEntity<User> getUserByUserId(@PathVariable Long userId){
			User user=userservice.getUserByUserId(userId);
			if(user!=null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}else {
				throw new UserNotFoundException("User Not Fount");			}
		}
		@PutMapping("/update-user")
		public ResponseEntity<Boolean> updateUser (@RequestBody User user){
			Boolean isUpdated=userservice.updateUser(user);
			
			if(isUpdated) {
				return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(isUpdated, HttpStatus.CONFLICT);
			}
		}
		@DeleteMapping("/delete-user-by-userid/{userId}")
		public ResponseEntity<Boolean> deleteUserByUserId (@PathVariable Long userId){
			Boolean isDeleted=userservice.deleteUserByUserId(userId);
			if(isDeleted) {
				return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(isDeleted, HttpStatus.CONFLICT);
			}
		}
			@GetMapping("/sort-user/{sortBy}/{fieldName}")
			public ResponseEntity<List<User>> sortUser(@PathVariable String sortBy, @PathVariable String fieldName){
				List<User> user=userservice.sortUser(sortBy, fieldName);
				if(user!= null) {
					return new ResponseEntity<List<User>>(user, HttpStatus.OK);
					
				}else {
					return new ResponseEntity<List<User>>(user,HttpStatus.NO_CONTENT);
				}
			}
		@GetMapping("/get-total-count-user")
		public ResponseEntity<Integer> getTotalCountOfUsers(){
			Integer count=userservice.getTotalCountOfUsers();
			if(count!=0) {
				return new ResponseEntity<Integer>(count, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<Integer>(count, HttpStatus.NO_CONTENT);
			}
		}
			@PostMapping("/upload-file")
			public ResponseEntity<String> uploadRecordsFromExcel(@RequestParam MultipartFile file){
				String msg= userservice.uploadRecordsFromExcel(file);
				
				return new ResponseEntity<String>(msg, HttpStatus.OK);
			}
			
			@GetMapping("/get-user-by-username/{userName}")
			public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
				User user=userservice.getUserByUserName(userName);
				if(user!=null) {
					return new ResponseEntity<User>(user, HttpStatus.OK);
				}else {
					throw new UserNotFoundException("User Not Fount");			}
			}
			
			

				
				
			}
		
		
		
	
