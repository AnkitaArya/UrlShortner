package com.url.transform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.url.transform.service.UrlShortnerService;

@RestController
@RequestMapping(value = "/rest/url")
public class UrlDetailsController {
@Autowired
UrlShortnerService shortnerService;
	
	@PutMapping
	 ResponseEntity<Object> shortenUrl(@RequestBody final String longUrl){
		return ResponseEntity.ok(shortnerService.shortenUrl(longUrl));
	}
	
	@GetMapping
	 ResponseEntity<Object> getLongUrl(@RequestParam ("url") String url){
		return ResponseEntity.ok(shortnerService.getFullurl(url));
	}

}
