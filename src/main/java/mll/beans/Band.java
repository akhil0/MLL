package mll.beans;

import java.io.Serializable;

public class Band implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer musician_id;
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
	public Integer getmusician_id() {
		return musician_id;
	}
	public void setmusician_id(Integer musician_id) {
		this.musician_id = musician_id;
	}

}
