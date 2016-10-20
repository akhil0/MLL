package mll.beans;

import java.io.Serializable;

public class Musician  implements Serializable
{
	private static final long serialVersionUID = 4461212265125762054L;

	private Integer id;
	private String name;
	
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
	
	
}
