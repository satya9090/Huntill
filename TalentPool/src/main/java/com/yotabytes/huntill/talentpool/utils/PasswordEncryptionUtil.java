package com.yotabytes.huntill.talentpool.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Service
public class PasswordEncryptionUtil {
	private static int workload = 12;
	
	  public static String hashPassword(String password_plaintext) { String salt =
	  BCrypt.gensalt(workload); String hashed_password =
	  BCrypt.hashpw(password_plaintext, salt);
	  
	  return(hashed_password); }
	  
	  
	/*
	 * public String getEncriptedPassword(String password) { String generatedKey =
	 * null; try { if (password != null) { MessageDigest algorithm =
	 * MessageDigest.getInstance("MD5"); algorithm.update(password.getBytes());
	 * byte[] output = algorithm.digest(); generatedKey =
	 * bytesToHex(output).toLowerCase(); } } catch (Exception e) {
	 * e.printStackTrace(); } return generatedKey; }
	 * 
	 * public static String bytesToHex(byte[] b) { char hexDigit[] = { '0', '1',
	 * '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	 * StringBuffer buf = new StringBuffer(); for (int j = 0; j < b.length; j++) {
	 * buf.append(hexDigit[(b[j] >> 4) & 0x0f]); buf.append(hexDigit[b[j] & 0x0f]);
	 * } return buf.toString(); }
	 */
	  
	/*
	 * // String test_passwd = "abcdefghijklmnopqrstuvwxyz"; public String
	 * getEncriptedPassword(String password) { String computed_hash=null;
	 * System.out.println("Password"+password); try { if (password != null) {
	 * MessageDigest algorithm = MessageDigest.getInstance("MD5");
	 * algorithm.update(password.getBytes()); byte[] output = algorithm.digest();
	 * computed_hash = hashPassword(output);
	 * System.out.println("Password"+computed_hash); } catch(Exception e){
	 * e.printStackTrace(); } return computed_hash; }
	 */
	
		public String getEncriptedPassword(String password) {
			 String generatedSecuredPasswordHash=null;
			try {
   generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
   System.out.println(generatedSecuredPasswordHash);
   boolean matched = BCrypt.checkpw(password, generatedSecuredPasswordHash);
   System.out.println(matched);
			}
		catch(Exception e){
			e.printStackTrace();
		}
			return generatedSecuredPasswordHash;
		}
		
		
	/*
	 * public static void main(String[] args) throws NoSuchAlgorithmException {
	 * String originalPassword = "password"; String generatedSecuredPasswordHash =
	 * BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
	 * System.out.println(generatedSecuredPasswordHash);
	 * 
	 * boolean matched = BCrypt.checkpw(originalPassword,
	 * generatedSecuredPasswordHash); System.out.println(matched); }
	 */
}
