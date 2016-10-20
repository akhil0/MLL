package mll.beans;

import java.io.Serializable;

public class Genre implements Serializable
{
	private static final long serialVersionUID = -7054001681433650751L;
	
	private Integer id;
	private Integer songId;
	private String genre;
	
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
