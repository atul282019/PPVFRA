package com.ppvfra.common;

public enum OtpSalt {

	SALT("bf77e39ef7194fa2806b53ed07465fd0");

	private String salt;
	private OtpSalt(String salt) {
	        this.salt = salt;
	}
	public String getSalt() {
	        return salt;
	}
 
	@Override
	public String toString() {
	        return salt;
	}
	
}
