package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BadgeRepository;
import com.example.demo.pojos.Badge;

@RestController
public class BadgeController {
	
	@Autowired
	private BadgeRepository badgeRepository;
	
	@PostMapping(value = "/badges")
	public ResponseEntity<Badge> insertBadge(@RequestBody Badge badge) {
		try {
		Badge badgeResponse =badgeRepository.save(badge);
		return new ResponseEntity<Badge>(badgeResponse,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Badge>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/badge/{badgeid}")
	public ResponseEntity<Badge> findBadgeById(@PathVariable("badgeid") String badgeId) {
		
		Optional<Badge> badge = badgeRepository.findById(Long.valueOf(badgeId));
		return new ResponseEntity<Badge>(badge.get(),HttpStatus.OK);
	}
}
