package com.jbk.validation;

import java.util.HashMap;
import java.util.Map;


import com.jbk.entity.User;

public class ValidateObject {
	public static Map<String, String> map=null;
	public static Map<String, String> validateUser(User user){
		map=new HashMap<>();
		if(user.getUserName()!=null) {
			boolean isValidated = true;
			try {
				Double.parseDouble(user.getUserName());
				
			} catch (NumberFormatException e) {
				isValidated=false;	
			}
			if(isValidated) { 
				map.put("userName", "Invalid User Name");	
			}
		}
			else {
				map.put("userName", "User is required");
				
			}
		
			if(user.getMobileNo().length() < 10){
				map.put("mobileNo", "Invalid Mobile No");
			}
		
		return map;
	}
		
}

