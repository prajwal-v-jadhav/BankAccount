package com.jbk.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.User;

@Service
public interface UserService {

	public Boolean addUser(User user);

	public List<User> getAllUser();

	public User getUserByUserId(Long userId);

	public Boolean updateUser(User user);

	public Boolean deleteUserByUserId(Long userId);

	public List<User> sortUser(String sortBy, String fieldName);
	
	public Integer getTotalCountOfUsers();
	

	public User getUserByUserName(String userName);
	
	public String uploadRecordsFromExcel(MultipartFile file);
	

	
}
