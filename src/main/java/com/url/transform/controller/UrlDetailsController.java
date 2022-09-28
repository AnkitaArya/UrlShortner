package com.url.transform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.url.transform.domain.UrlShortnerResource;
import com.url.transform.service.UrlShortnerServiceImpl;

@RestController
@RequestMapping(value = "/rest/url")
public class UrlDetailsController {
	Logger log = LoggerFactory.getLogger(UrlDetailsController.class);

	@Autowired
	UrlShortnerServiceImpl shortnerService;

	@PutMapping
	ResponseEntity<UrlShortnerResource> shortenUrl(@RequestBody final String longUrl) {
		log.debug("Generating short url for {}", longUrl);
		return ResponseEntity.ok(shortnerService.shortenUrl(longUrl));
	}

	@GetMapping
	ResponseEntity<UrlShortnerResource> getLongUrl(@RequestParam("url") String url) {
		log.debug("Fetching long url for {}", url);
		return ResponseEntity.ok(shortnerService.getFullurl(url));
	}

}
