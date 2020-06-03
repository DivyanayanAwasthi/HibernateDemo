package com.hibernateapp.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hibernateapp.pojos.Badge;



public interface BadgeRepository  extends JpaRepository<Badge,Long>{

	 @Cacheable(cacheNames = "badges")
	 @Query("FROM Badge WHERE badgestatus = ?1")
	    List<Badge> findBadgesByStatus(Boolean status);
	 
	 
	 @Query("FROM Badge WHERE badgename like %?1%")
	    List<Badge> findBadgesByName(String badgeName);
}
