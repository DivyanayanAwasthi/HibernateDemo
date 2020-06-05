package com.hibernateapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibernateapp.pojos.Badge;
import com.hibernateapp.services.BadgeServices;

@RestController
public class BadgeController {

	private static final Logger logger = LogManager.getLogger(BadgeController.class.getName());
	@Autowired
	private BadgeServices badgeServices;

	@PostMapping(value = "/badge")
	public ResponseEntity<Badge> insertBadge(@RequestBody Badge badge) {
		try {
			badge = badgeServices.addBadge(badge);
			return new ResponseEntity<Badge>(badge, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Badge>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/badge/{badgeid}" })
	public ResponseEntity<Badge> findBadgeById(@PathVariable("badgeid") Optional<Long> badgeId) {
		try {
			Badge badge = badgeServices.getBadge(badgeId.get());
			return new ResponseEntity<Badge>(badge, HttpStatus.OK);

		} catch (Exception ex) {
			logger.error("Exception  - {} ", ex);
			return new ResponseEntity<Badge>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping(value = "/badge")
	public Response findBadges(@RequestParam("status") Optional<Boolean> status,
			@RequestParam("badgeName") Optional<String> badgeName) {
		CacheControl cc = new CacheControl();
		ResponseBuilder builder = null;
		try {
			List<Badge> badges = badgeServices.findBadges(status, badgeName);
			cc.setMaxAge(10);
			cc.setPrivate(true);
			builder = Response.ok(badges);
			

			return builder.build();
		} catch (Exception ex) {
			logger.error("Exception - {}", ex);
			return Response.serverError().build();

		}
	}
}
