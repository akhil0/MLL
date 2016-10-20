package mll.beans;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 7161080394385403697L;
	
	private Integer id;
	private String userName;
	private String password;
	private String emailId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
