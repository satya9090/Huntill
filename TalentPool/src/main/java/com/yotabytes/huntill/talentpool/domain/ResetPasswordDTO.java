package com.yotabytes.huntill.talentpool.domain;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class ResetPasswordDTO {
	

private String resetPasswordToken;
 
private String password;

public String getResetPasswordToken() {
	return resetPasswordToken;
}

public void setResetPasswordToken(String resetPasswordToken) {
	this.resetPasswordToken = resetPasswordToken;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}
