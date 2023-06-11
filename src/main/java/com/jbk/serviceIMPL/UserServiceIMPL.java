package com.jbk.serviceIMPL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.UserDao;
import com.jbk.entity.Account;

import com.jbk.entity.User;
import com.jbk.service.UserService;
import com.jbk.validation.ValidateObject;


@Service
public class UserServiceIMPL implements UserService {
	@Autowired
	private UserDao userdao;
	String excludedRows = "";
	int totalRecordCount = 0;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<Integer, Map<String, String>> errorMap = new HashMap<Integer, Map<String, String>>();

	Map<String, String> validateError = new HashMap<String, String>();

	@Override
	public Boolean addUser(User user) {
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		user.setUserId(Long.parseLong(id));
		return userdao.addUser(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userdao.getAllUser();
	}

	@Override
	public User getUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userdao.getUserByUserId(userId);
	}

	@Override
	public Boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userdao.updateUser(user);
	}

	@Override
	public Boolean deleteUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userdao.deleteUserByUserId(userId);
	}

	@Override
	public List<User> sortUser(String sortBy, String fieldName) {
		// TODO Auto-generated method stub
		return userdao.sortUser(sortBy, fieldName);
	}

	@Override
	public Integer getTotalCountOfUsers() {

		return userdao.getTotalCountOfUsers();
	}

	

	@Override
	public User getUserByUserName(String userName) {
		
		return userdao.getUserByUserName(userName);
	}

	@Override
	public String uploadRecordsFromExcel(MultipartFile file) {
		String fileName=file.getOriginalFilename();
		String filePath="src/main/resources";
		List<User> list=new ArrayList<User>();
		String msg=null;
		try {
				
			byte[] data = file.getBytes();
			FileOutputStream fos= new FileOutputStream(new File(filePath+File.separator+fileName));
			fos.write(data);
			
//			list=readExcel(filePath + File.separator+fileName);
//			
//			msg=dao.uploadUser(list);
//			
//			//for readfile
		
			FileInputStream fis=new FileInputStream(new File(filePath+File.separator+fileName));
			
			Workbook workbook=new XSSFWorkbook(fis);
			
			Sheet sheet = workbook.getSheet("Bankaccount");
			
			Iterator<Row> rows = sheet.rowIterator();
			User user=null;
			int count=1;
			//List<User> list=new ArrayList<User>();
			while(rows.hasNext()) {
				Row row=(Row) rows.next();
				if(row.getRowNum() == 0) {
					continue;
				}
				
				user=new User();
				Long id = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date()));
				id=id+count;
				user.setUserId(id);
				count=count+1;
				Iterator<Cell> cells = row.cellIterator();
				
				while(cells.hasNext()) {
					Cell cell =(Cell) cells.next();
					int columnIndex = cell.getColumnIndex();
					
					
					switch(columnIndex) {
					case 0:{
						user.setUserName(cell.getStringCellValue());
						
						break;
					}
					
					case 1:{
						
						user.setAddress(cell.getStringCellValue());
						break;
					}
					
					case 2:{
						Account account=new Account();
						account.setAccountNo((int) (cell.getNumericCellValue()));
						break;
					}
					
					case 3:{
						
						user.setMobileNo(cell.getStringCellValue());
						break;
					}
					}
					//user.setUserName(cell.getStringCellValue());
//					CellType cellType = cell.getCellType();
//					
//					if(cellType==cellType.STRING) {
//						System.out.print(cell.getStringCellValue()+"\t");
//					}else {
//						System.out.print(cell.getNumericCellValue()+"\t");
//					}
				
				
				}

				list.add(user);
			}
			
//			for (User use : list) {
//				System.out.println(use);
	//		}
			
		 msg=	userdao.uploadusers(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}


}
