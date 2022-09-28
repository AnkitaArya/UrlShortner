package com.url.transform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.transform.constants.UrlShortnerConstants;
import com.url.transform.domain.UrlShortnerResource;
import com.url.transform.entity.UrlEntity;
import com.url.transform.exceptions.ResourceNotFoundException;
import com.url.transform.exceptions.UrlShortnerServiceException;
import com.url.transform.repository.UrlDetailsRepository;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService{
	
	Logger log = LoggerFactory.getLogger(UrlShortnerServiceImpl.class);
	
	@Autowired
	UrlDetailsRepository detailsRepository;
	
	@Override
	public UrlShortnerResource shortenUrl(String fullUrl){
		UrlShortnerResource resource = new UrlShortnerResource();
		String shortUrl = null;
		String domain = UrlShortnerConstants.DOMAIN;
		try {
			if(fullUrl!=null) {
				//check if the url already exists in DB
				UrlEntity entity = detailsRepository.findByFullUrl(fullUrl);
				if(entity!=null && (entity.getFullUrl()!=null) &&  (entity.getShortUrl()!=null)) {
					shortUrl = entity.getShortUrl();
				}else {
					log.debug("{} does not exist in Db. Thus creating new entry.", fullUrl);
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
			resource.setShortUrl(shortUrl);
		}catch(UrlShortnerServiceException serviceEx) {		
			log.error(UrlShortnerConstants.SERVICE_EXCEPTION, serviceEx);
			throw new UrlShortnerServiceException(serviceEx.getStatusCode(), serviceEx.getMessage());
		}
		
		log.info("{} url is shortened to {}", fullUrl, shortUrl);
		return resource;
		
		
	}
	
	private String encryptShortUrlId(Long id) {
		char possibleChars[] = UrlShortnerConstants.POSSIBLE_CHAR_STRING.toCharArray();
		int base = UrlShortnerConstants.BASE;
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
	@Override
	public UrlShortnerResource getFullurl(String shortUrl){
		UrlShortnerResource resource = new UrlShortnerResource();
		String fullUrl = null;
		if(shortUrl!=null) {
			//check if the url already exists in DB
			UrlEntity entity = detailsRepository.findByShortUrl(shortUrl);
			if(entity!=null && (entity.getFullUrl()!=null) &&  (entity.getShortUrl()!=null)) {
				fullUrl = entity.getFullUrl();
				resource.setFullurl(fullUrl);
			}else {
				//the URl is not available in system;
				log.error("{} url is not found in the Db.",shortUrl);
				throw new ResourceNotFoundException(UrlShortnerConstants.SHORT_URL_NOT_FOUND);
			}
		}
		return resource;
	}

}
