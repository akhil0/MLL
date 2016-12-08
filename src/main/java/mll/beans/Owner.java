package mll.beans;

import java.io.Serializable;

public class Owner implements Serializable
{
	private static final long serialVersionUID = -8241862681652667500L;

	private Integer id;
	private String songId;
	private String name;
	private String divisonOfOwnership;	 
	private String ownerType;
	private String primaryPhone;
	private String secondaryPhone;	 
	private String primaryEmail;
	private String secondaryEmail;
	private Long owner_percent;
	private String contribution;
	private String role;
	private Integer band_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
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
	public Long getOwner_percent() {
		return owner_percent;
	}
	public void setOwner_percent(Long owner_percent) {
		this.owner_percent = owner_percent;
	}
	
	public String getContribution() {
		return contribution;
	}
	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getBand_id() {
		return band_id;
	}
	public void setBand_id(Integer band_id) {
		this.band_id = band_id;
	}
}
