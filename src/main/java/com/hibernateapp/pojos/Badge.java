package com.hibernateapp.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "badge")
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long badgeId;

	@Column(name = "badgename")
	private String badgeName;
	
	@Column(name  = "badgestatus")
	private Boolean badgeStatus;
	
	@Column(name  = "technology")
	private String technology;

	public Boolean getBadgeStatus() {
		return badgeStatus;
	}

	public void setBadgeStatus(Boolean badgeStatus) {
		this.badgeStatus = badgeStatus;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

}
