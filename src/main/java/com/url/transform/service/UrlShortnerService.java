package com.url.transform.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.url.transform.domain.UrlShortnerResource;

@Transactional
@Service
public interface UrlShortnerService {
	
	public UrlShortnerResource shortenUrl(String fullUrl);
	public UrlShortnerResource getFullurl(String shortUrl);
	
}
