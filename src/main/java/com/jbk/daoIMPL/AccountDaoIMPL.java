package com.jbk.daoIMPL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.AccountDao;
import com.jbk.entity.Account;


@Repository
public class AccountDaoIMPL implements AccountDao {
@Autowired
private SessionFactory sf;
	@Override
	public Boolean addAccount(Account account) {
		Session session=null;
		Boolean isAdded=false;
		try {
			session=sf.openSession();
			Transaction trr = session.beginTransaction();
			session.createCriteria(Account.class);
			session.save(account);
			trr.commit();
			isAdded=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isAdded;
	}

	@Override
	public List<Account> getAllAccount() {
		Session session=null;
		List<Account> list=null;
		try {
			session=sf.openSession();
			Criteria crr = session.createCriteria(Account.class);
			list=crr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public Account getAccountByAccountNo(int accountNo) {
		Session session=null;
		Account account=null;
		try {
			session=sf.openSession();
			 account = session.get(Account.class, accountNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return account;
	}

	@Override
	public Boolean updateAccount(Account account) {
		Session session=null;
		Boolean isUpdated=false;
		try {
			session=sf.openSession();
			Transaction trr = session.beginTransaction();
			session.update(account);
			trr.commit();
			isUpdated=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public Boolean deleteAccountByAccountNo(int accountNo) {
		Session session=null;
		Boolean isDeleted=false;
		try {
			session=sf.openSession();
			Account account=session.get(Account.class,accountNo);
			Transaction trr = session.beginTransaction();
			session.delete(account);
			trr.commit();
			isDeleted=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isDeleted;
	}

	@Override
	public List<Account> getMaxBalanceAccount() {
		
			Session session=null;
			List<Account> list=null;
			try {
				session=sf.openSession();
				Criteria crr = session.createCriteria(Account.class);
				crr.setProjection(Projections.max("balance"));
			list=	crr.list();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(session!=null) {
					session.close();
				}
			}
			return list;
		
	}
	
}
