package mll.beans;

import java.util.Date;

public class PlaylistReference {
	
	private Integer id;
	private int userId;
	private String playlistName;
	private Date creationDate;
	private Boolean isShared = false;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int user_id) {
		this.userId = user_id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Boolean getIsShared() {
		return isShared;
	}
	public void setIsShared(Boolean isShared) {
		this.isShared = isShared;
	}
}