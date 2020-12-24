package com.marve.entity;

public class TestDetails {
	private String testid,testname,active;
	


public String getTestid() {
		return testid;
	}
    
	public String getActive() {
	return active;
}

public void setActive(String active) {
	this.active = active;
}

	public TestDetails(String testid, String testname,String active) {
	super();
	this.testid = testid;
	this.testname = testname;
	this.active=active;
}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}


}
