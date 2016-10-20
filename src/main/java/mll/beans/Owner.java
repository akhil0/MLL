package mll.beans;

import java.io.Serializable;

public class Owner implements Serializable
{
	private static final long serialVersionUID = -8241862681652667500L;

	private Integer id;
	private Integer songId;
	private String name;
	private String divisonOfOwnership;	 
	private String ownerType;
	private String primaryPhone;
	private String secondaryPhone;	 
	private String primaryEmail;
	private String secondaryEmail;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDivisonOfOwnership() {
		return divisonOfOwnership;
	}
	public void setDivisonOfOwnership(String divisonOfOwnership) {
		this.divisonOfOwnership = divisonOfOwnership;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	public String getSecondaryPhone() {
		return secondaryPhone;
	}
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getSecondaryEmail() {
		return secondaryEmail;
	}
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}
}
