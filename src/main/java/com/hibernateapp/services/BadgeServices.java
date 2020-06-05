package com.hibernateapp.services;

import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernateapp.dao.BadgeRepository;
import com.hibernateapp.pojos.Badge;

@Service
public class BadgeServices {

	static final Logger logger = LogManager.getLogger(BadgeServices.class.getName());

	@Autowired
	private BadgeRepository badgeRepository;

	public Badge addBadge(Badge badge) {

		try {
			logger.info("badge insertion in progress");
			badge = badgeRepository.save(badge);
			return badge;
		} catch (Exception ex) {
			logger.debug("exception -  {}", ex);
			throw ex;
		}
	}

	public Badge getBadge(Long badgeId) {
		try {
			logger.info("get badge for badgeId - {}", badgeId);
			Optional<Badge> badge = badgeRepository.findById(badgeId);
			return badge.get();

		} catch (Exception ex) {
			logger.debug("exception -  {}", ex);
			throw ex;
		}
	}

	public List<Badge> findBadges(Optional<Boolean> status, Optional<String> badgeName) {
		try {
			List<Badge> badges = null;
			if (badgeName.isPresent() && status.isPresent()) {
				logger.info("get badges for  {} badge name and {} status", badgeName.get(), status.get());
				badges = badgeRepository.findBadgesByNameAndStatus1(badgeName.get(), status.get());

			} else if (badgeName.isPresent()) {
				logger.info("get badges for  {} badge name", badgeName.get());
				badges = badgeRepository.findBadgesByName(badgeName.get());
			} else if (status.isPresent()) {
				logger.info("get badges for  {} status", status.get());
				badges = badgeRepository.findBadgesByStatus(status.get());
			}

			return badges;

		} catch (Exception ex) {
			logger.debug("exception -  {}", ex);
			throw ex;
		}
	}

}
