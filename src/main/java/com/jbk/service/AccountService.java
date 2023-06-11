package com.jbk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jbk.entity.Account;
@Service
public interface AccountService {

 public	Boolean addAccount(Account account);

public List<Account> getAllAccount();

public Account getAccountByAccountNo(int accountNo);

public Boolean updateAccount(Account account);

public Boolean deleteAccountByAccountNo(int accountNo);



public List<Account> getMaxBalanceAccount();



}
