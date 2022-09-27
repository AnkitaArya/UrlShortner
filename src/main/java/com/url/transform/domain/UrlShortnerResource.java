package com.url.transform.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UrlShortnerResource {
	
	@JsonInclude(Include.NON_NULL)
	private String fullurl;
	@JsonInclude(Include.NON_NULL)
	private String shortUrl;
	
	public String getFullurl() {
		return fullurl;
	}
	public void setFullurl(String fullurl) {
		this.fullurl = fullurl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}
