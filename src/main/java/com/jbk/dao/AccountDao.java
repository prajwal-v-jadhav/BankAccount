package com.jbk.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jbk.entity.Account;
@Repository
public interface AccountDao {

public	Boolean addAccount(Account account);

public List<Account> getAllAccount();

public Account getAccountByAccountNo(int accountNo);

public Boolean updateAccount(Account account);

public Boolean deleteAccountByAccountNo(int accountNo);

public List<Account> getMaxBalanceAccount();



}
