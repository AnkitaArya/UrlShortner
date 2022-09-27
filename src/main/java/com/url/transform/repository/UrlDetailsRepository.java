package com.url.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import com.url.transform.entity.UrlEntity;
@EnableJpaRepositories
public interface UrlDetailsRepository extends JpaRepository<UrlEntity, Integer> {
	
	public UrlEntity findByFullUrl(String fullUrl);
	public UrlEntity save(UrlEntity urlEntity);
	public UrlEntity findByShortUrl(String shortUrl);
	

}
