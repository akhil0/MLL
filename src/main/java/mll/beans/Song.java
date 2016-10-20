package mll.beans;

import java.io.Serializable;
import java.util.Date;

public class Song implements Serializable
{
	private static final long serialVersionUID = -6457237205216286747L;
	
	private Integer id;
	private String title;
	private Long beatsPerMin;
	private String copyrightNo;
	private Date copyRightDate;
	private String publishingCompany;
	private String pro;
	private byte[] content;
	private String sourceOfContent;
	private String contentURL;
	private Integer userId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getBeatsPerMin() {
		return beatsPerMin;
	}
	public void setBeatsPerMin(Long beatsPerMin) {
		this.beatsPerMin = beatsPerMin;
	}
	public String getCopyrightNo() {
		return copyrightNo;
	}
	public void setCopyrightNo(String copyrightNo) {
		this.copyrightNo = copyrightNo;
	}
	public Date getCopyRightDate() {
		return copyRightDate;
	}
	public void setCopyRightDate(Date copyRightDate) {
		this.copyRightDate = copyRightDate;
	}
	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getSourceOfContent() {
		return sourceOfContent;
	}
	public void setSourceOfContent(String sourceOfContent) {
		this.sourceOfContent = sourceOfContent;
	}
	public String getContentURL() {
		return contentURL;
	}
	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
