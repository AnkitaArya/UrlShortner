package com.url.transform.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.transform.entity.UrlEntity;
import com.url.transform.repository.UrlDetailsRepository;

@Service
public class UrlShortnerService {
	@Autowired
	UrlDetailsRepository detailsRepository;
	
	public String shortenUrl(String fullUrl){
		String shortUrl = null;
		String domain = "http://shortUrl/";
		if(fullUrl!=null) {
			//check if the url already exists in DB
			UrlEntity entity = detailsRepository.findByFullUrl(fullUrl);
			if(entity!=null && (entity.getFullUrl()!=null) &&  (entity.getShortUrl()!=null)) {
				shortUrl = entity.getShortUrl();
			}else {
				UrlEntity entityCreated =new UrlEntity();
				entityCreated.setFullUrl(fullUrl);
				//first save to get generated ID
				entityCreated =save(fullUrl);
				//now encrypt the id 
				String encyId = encryptShortUrlId(entityCreated.getUrlId());
				shortUrl = domain+encyId;
				//save the encryptedId to Db
				entityCreated.setShortUrl(shortUrl);
				//update
				detailsRepository.save(entityCreated);
			}
		}
		
		return shortUrl;
		
		
	}
	
	private String encryptShortUrlId(Long id) {
		Map<Integer, String> idEncyptMap = new HashMap<Integer, String>();
		char possibleChars[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int base = 62;
		StringBuilder strBldr = new StringBuilder();
        while (id > 0) {
        	strBldr.insert(0, possibleChars[(int) (id % base)]);
            id = id / base;
        }
        return strBldr.toString();
		
	}

	private UrlEntity save(String fullUrl) {
        return detailsRepository.save(new UrlEntity(fullUrl));
    }
	
	public String getFullurl(String shortUrl){
		String fullUrl = null;
		if(shortUrl!=null) {
			//check if the url already exists in DB
			UrlEntity entity = detailsRepository.findByShortUrl(shortUrl);
			if(entity!=null && (entity.getFullUrl()!=null) &&  (entity.getShortUrl()!=null)) {
				fullUrl = entity.getFullUrl();
			}else {
				//the URl is not available in system;
				
			}
		}
		
		return fullUrl;
		
		
	}

}
