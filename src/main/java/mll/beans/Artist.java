package mll.beans;

import java.io.Serializable;

public class Artist implements Serializable
{
	private static final long serialVersionUID = -152269388788828458L;
	
	private Integer id;
	private Integer songId;
	private String name;
	private String skills;
	
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
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
}
