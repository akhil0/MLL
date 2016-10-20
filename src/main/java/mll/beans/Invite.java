package mll.beans;

import java.io.Serializable;

public class Invite implements Serializable
{
	private static final long serialVersionUID = -5078382714401223485L;
	Token token = new Token();
	private Boolean isGenerated = false;
	private Boolean isValid = false;
	private String url;
	private String message;
	private String actiontype;
	
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public Boolean getIsGenerated() {
		return isGenerated;
	}
	public void setIsGenerated(Boolean isGenerated) {
		this.isGenerated = isGenerated;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
}
