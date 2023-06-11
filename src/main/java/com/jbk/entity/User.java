package com.jbk.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.jbk.annotation.CharOnly;



@Entity
public class User {
	@Id
	
	@Column(nullable = false, unique=true)
	private Long userId;
	@NotEmpty
	@CharOnly
	private String userName;
	@NotEmpty
	@CharOnly
	private String address;
	@NotEmpty
	@Min(1)
	@Column(unique=true)
	private String mobileNo;
	@OneToOne
	Account account;
	public User() {
		
	}
	public User(Long userId, String userName, String address, String mobileNo, Account account) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
		this.mobileNo = mobileNo;
		this.account = account;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	 
}
