package com.marve.entity;

public class Feedback {
	private String name,email,mobile,code,message;
	public Feedback()
	{}
	public Feedback(String name,String email,String mobile,String code,String message)
	{
		this.name=name;
		this.email=email;
		this.mobile=mobile;
		this.code=code;
		this.message=message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
