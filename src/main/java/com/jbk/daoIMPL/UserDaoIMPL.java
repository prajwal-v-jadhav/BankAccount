package com.jbk.daoIMPL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.UserDao;
import com.jbk.entity.User;
@Repository
public class UserDaoIMPL implements UserDao {
@Autowired
private SessionFactory sf;
	@Override
	public Boolean addUser(User user) {
		Session session=null;
		Boolean isAdded=false;
		try {
			session=sf.openSession();
			Transaction trr = session.beginTransaction();
			session.save(user);
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
	public List<User> getAllUser() {
		Session session=null;
		List<User> list=null;
		try {
			session=sf.openSession();
			Criteria crr = session.createCriteria(User.class);
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
	public User getUserByUserId(Long userId) {
		Session session=null;
		User user=null;
		try {
			session=sf.openSession();
			 user = session.get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public Boolean updateUser(User user) {
		Session session=null;
		Boolean isUpdated=false;
		try {
			session=sf.openSession();
			Transaction trr = session.beginTransaction();
			session.update(user);
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
	public Boolean deleteUserByUserId(Long userId) {
		Session session=null;
		Boolean isDeleted=false;
		try {
			session=sf.openSession();
			Transaction trr = session.beginTransaction();
			User user = session.get(User.class, userId);
			session.delete(user);
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
	public List<User> sortUser(String sortBy, String fieldName) {
	Session session=null;
	List<User> list=null;
		try {
			session=sf.openSession();
			Criteria crr=session.createCriteria(User.class);
			if(sortBy.equalsIgnoreCase("asc")) {
				
				crr.addOrder(Order.asc(fieldName));
				
			}
			else {
				crr.addOrder(Order.desc(fieldName));
			}
			
		list=	crr.list();
			
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(session!=null) {
			session.close();
		}
	}
		return list;
	}

	@Override
	public Integer getTotalCountOfUsers() {
		Session session=null;
		long count=0;
		try {
			session=sf.openSession();
			Criteria crr=session.createCriteria(User.class);
			 crr.setProjection(Projections.count("userId"));
			count=(long) crr.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		
		}
		
		return (int) count;
}
//	@Override
//	public int[] uploadUsers(List<User> list) {
//		String msg=null;
//		int addCount=0;
//		int excludedCount=0;
//		int[] arr=new int[2];
//		  
//		for (User user : list) {
//			Boolean isAdded=addUser(user);
//			if(isAdded) {
//				addCount=addCount + 1;
//			}else {
//				excludedCount=excludedCount + 1;
//			}
//		}
//		arr[0]=addCount;
//		arr[1]=excludedCount;
//		msg ="Added count "+addCount+"  &   excludedCount  "+excludedCount;
//		return arr;
//}

	@Override
	public User getUserByUserName(String userName) {
		Session session=null;
		User user=null;
		try {
			session=sf.openSession();
			Criteria crr = session.createCriteria(User.class);
		 crr.add(Restrictions.eq("userName", userName));
		user= (User) crr.uniqueResult();
			 //user = session.get(User.class, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public String uploadusers(List<User> list) {
		String msg=null;
		int count=0;
		for (User user : list) {
			addUser(user);
			count=count+1;
			
		}
		msg="Added count=" +count;
		return msg;
	}
}
