package com.hibernateapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hibernateapp.pojos.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

	@Query("FROM Badge WHERE badgestatus = ?1")
	List<Badge> findBadgesByStatus(Boolean status);

	@Query("FROM Badge WHERE badgename like %?1%")
	List<Badge> findBadgesByName(String badgeName);

	@Query("FROM Badge WHERE badgename like %?1% and badgestatus = ?2")
	List<Badge> findBadgesByNameAndStatus(String badgeName, Boolean status);

	@Query(value = "SELECT * FROM Badge badge WHERE badgeName like '%1%' and badge.badgestatus = 2  ", nativeQuery = true) //TODO fix it
	List<Badge> findBadgesByNameAndStatus1(String badgeName, Boolean status);
}
