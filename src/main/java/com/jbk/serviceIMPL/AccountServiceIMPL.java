package com.jbk.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.AccountDao;
import com.jbk.entity.Account;
import com.jbk.service.AccountService;
@Service
public class AccountServiceIMPL implements AccountService {
@Autowired
private AccountDao accountdao;
	@Override
	public Boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		return accountdao.addAccount(account);
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accountdao.getAllAccount();
	}

	@Override
	public Account getAccountByAccountNo(int accountNo) {
		// TODO Auto-generated method stub
		return accountdao.getAccountByAccountNo(accountNo);
	}

	@Override
	public Boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountdao.updateAccount(account);
	}


	@Override
	public Boolean deleteAccountByAccountNo(int accountNo) {
		// TODO Auto-generated method stub
		return accountdao.deleteAccountByAccountNo(accountNo);
	}

	@Override
	public List<Account> getMaxBalanceAccount() {
		// TODO Auto-generated method stub
		return accountdao.getMaxBalanceAccount();
	}

}
