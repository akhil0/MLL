package mll.beans;

import java.util.Date;

public class SongMetadata {
 
	private String title;
	private long beats_per_rate;
	private String copyright_number;
	private Date copyright_date;
	private String publishing_company;
	private String pro;
	private String lyrics;
	private String primary_genre;
	private String secondary_genre;
	private String fileName;
	private byte[] content;
	private String contentURL;
	private String sourceOfContent;
	private String artists;
	private long userId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getBeats_per_rate() {
		return beats_per_rate;
	}
	public void setBeats_per_rate(Long beats_per_rate) {
		this.beats_per_rate = beats_per_rate;
	}
	public String getCopyright_number() {
		return copyright_number;
	}
	public void setCopyright_number(String copyright_number) {
		this.copyright_number = copyright_number;
	}
	public Date getCopyright_date() {
		return copyright_date;
	}
	public void setCopyright_date(Date copyright_date) {
		this.copyright_date = copyright_date;
	}
	public String getPublishing_company() {
		return publishing_company;
	}
	public void setPublishing_company(String publishing_company) {
		this.publishing_company = publishing_company;
	}
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	public String getPrimary_genre() {
		return primary_genre;
	}
	public void setPrimary_genre(String primary_genre) {
		this.primary_genre = primary_genre;
	}
	public String getSecondary_genre() {
		return secondary_genre;
	}
	public void setSecondary_genre(String secondary_genre) {
		this.secondary_genre = secondary_genre;
	}
	public void setFileName(String fileName) {
		this.fileName=fileName;
		
	}
	public String getFileName()
	{
		return fileName;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getContentURL() {
		return contentURL;
	}
	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}
	public String getSourceOfContent() {
		return sourceOfContent;
	}
	public void setSourceOfContent(String sourceOfContent) {
		this.sourceOfContent = sourceOfContent;
	}
	public String getArtists() {
		return artists;
	}
	public void setArtists(String artists) {
		this.artists = artists;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long i) {
		this.userId = i;
	}
	
}
