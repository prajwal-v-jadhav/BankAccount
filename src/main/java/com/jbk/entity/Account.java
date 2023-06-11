package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import com.jbk.annotation.CharOnly;

@Entity
public class Account {
	@Id
	@Column(nullable = false)
	private int accountNo;
	@Column(nullable = false)
	@CharOnly
	private String accountType;
	@Column(nullable = false)
	private double balance;
	@Column(nullable = false)
	private int openingYear;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountNo, String accountType, double balance, int openingYear) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.balance = balance;
		this.openingYear = openingYear;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getOpeningYear() {
		return openingYear;
	}
	public void setOpeningYear(int openingYear) {
		this.openingYear = openingYear;
	}
	
	
}
