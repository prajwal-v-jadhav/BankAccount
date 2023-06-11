package com.jbk.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Account;
import com.jbk.exception.AccountAlreadyExistsException;
import com.jbk.exception.UserAlreadyExistsException;
import com.jbk.service.AccountService;
import com.jbk.service.UserService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountservice;
	@PostMapping("/add-Account")
	public ResponseEntity<Boolean> addAccount(@RequestBody Account account){
		Boolean isAdded=accountservice.addAccount(account);
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.CONFLICT);
		}
	}
		@GetMapping("/get-all-account")
		public ResponseEntity<List<Account>> getAllAccount(){
			List<Account> list=accountservice.getAllAccount();
			if(list!=null) {
				return new ResponseEntity<List<Account>>(list,HttpStatus.OK);
			}else
			{
				throw new AccountAlreadyExistsException("Account Already Exists");
			}
		}
		
		@GetMapping("/get-account-by-accountno/{accountNo}")
		public ResponseEntity<Account> getAccountByAccountNo(@PathVariable int accountNo){
			Account account=accountservice.getAccountByAccountNo(accountNo);
			if(account!=null) {
				return new ResponseEntity<Account>(account, HttpStatus.OK);
			}else {
				return new ResponseEntity<Account>(account,HttpStatus.NO_CONTENT);
			}
		}
		@PutMapping("/update-account")
		public ResponseEntity<Boolean> updateAccount (@RequestBody Account account){
			Boolean isUpdated=accountservice.updateAccount(account);
			
			if(isUpdated) {
				return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(isUpdated, HttpStatus.CONFLICT);
			}
		}
		@DeleteMapping("/delete-account-by-accountno/{accountNo}")
		public ResponseEntity<Boolean> deleteAccountByAccountNo (@PathVariable int accountNo){
			Boolean isDeleted=accountservice.deleteAccountByAccountNo(accountNo);
			
			if(isDeleted) {
				return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(isDeleted, HttpStatus.CONFLICT);
			}
		}
		
		@GetMapping("/get-maxbalance-account")
		public ResponseEntity<List<Account>> getMaxBalanceAccount(){
			List<Account> list=accountservice.getMaxBalanceAccount();
			if(list!=null) {
				return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Account>>(list,HttpStatus.NO_CONTENT);
			}
		}
		
	}

