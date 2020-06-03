package com.hibernateapp.configs;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
public class CacheCofiguration  extends CachingConfigurerSupport{

//	@Bean
//	@Primary
//	public CacheManager ehCacheMamager() {
//		
//	}
}
