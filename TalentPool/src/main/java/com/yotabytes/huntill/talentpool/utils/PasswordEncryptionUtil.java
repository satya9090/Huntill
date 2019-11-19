package com.yotabytes.huntill.talentpool.utils;

import java.security.MessageDigest;

import org.springframework.stereotype.Service;
@Service
public class PasswordEncryptionUtil {

	public String getEncriptedPassword(String password) {
		String generatedKey=null;
		try{
			if(password!=null){
				MessageDigest algorithm = MessageDigest.getInstance("MD5"); 
				algorithm.update(password.getBytes());
		      	byte[] output = algorithm.digest();	      	
				generatedKey = bytesToHex(output).toLowerCase();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return generatedKey;
	}
	
	public static String bytesToHex(byte[] b) {
		char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	    StringBuffer buf = new StringBuffer();
	    for (int j=0; j<b.length; j++) {
	    	buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
	        buf.append(hexDigit[b[j] & 0x0f]);
	    }
	    return buf.toString();
	}
}
