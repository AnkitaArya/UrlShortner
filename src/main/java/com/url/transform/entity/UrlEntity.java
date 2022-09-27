package com.url.transform.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "URL_ENTITY")
public class UrlEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long urlId;
	
	@Column(name="SHORT_URL")
    private String shortUrl;
	
	@Column(name="FULL_URL")
    private String fullUrl;
    
	public UrlEntity() {
		
	}

	public UrlEntity(Long urlId, String shortUrl, String fullUrl) {
		super();
		this.urlId = urlId;
		this.shortUrl = shortUrl;
		this.fullUrl = fullUrl;
	}

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public UrlEntity(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

}
