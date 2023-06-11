package com.jbk.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jbk.entity.User;

@Repository
public interface UserDao {

	public Boolean addUser(User user);

	public List<User> getAllUser();

	public User getUserByUserId(Long userId);

	public Boolean updateUser(User user);

	public Boolean deleteUserByUserId(Long userId);

	public List<User> sortUser(String sortBy, String fieldName);

	public Integer getTotalCountOfUsers();

	//public int[] uploadUsers(List<User> list);

	public User getUserByUserName(String userName);

	public String uploadusers(List<User> list);

	

	

	
}
