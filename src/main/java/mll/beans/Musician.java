package mll.beans;

import java.io.Serializable;

public class Musician  implements Serializable
{
	private static final long serialVersionUID = 4461212265125762054L;

	private Integer id;
	private String name;
	private int age;
	private int added_by;
	private String folderId;
	private String gender;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAdded_by() {
		return added_by;
	}
	public void setAdded_by(int added_by) {
		this.added_by = added_by;
	}
	public String getFolderId() {
		return folderId;
	}
	public void setFolderId(String folder_id) {
		this.folderId = folder_id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
