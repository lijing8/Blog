package springTest.web.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springTest.web.dao.AdminDAO;
import springTest.web.entity.AdminEntity;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	

	public AdminEntity checkLogin(String name,String password) throws NoSuchAlgorithmException{
		return dao.getAdmin(name, MD5(password));
	}
	
	public boolean updatepw(String pw) throws NoSuchAlgorithmException{
		return dao.setpw(MD5(pw));
	}
	
	private String MD5 (String in) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		char[] charArray = in.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for(int i = 0; i < charArray.length; i++){
			 byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();  
		for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16) {
                hexValue.append("0");  
            }
            hexValue.append(Integer.toHexString(val));  
        } 
		return hexValue.toString();
	}
}