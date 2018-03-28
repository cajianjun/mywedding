package com.cjj.wedding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "photopath")
public class PhotoPathProperties {

	private String withIdCard;
	private String idCardFront;
	private String idCardBack;
	private String businessLicense;
	private String users;
	private String photoPrefix;

	
	
	public String getPhotoPrefix() {
		return photoPrefix;
	}

	public void setPhotoPrefix(String photoPrefix) {
		this.photoPrefix = photoPrefix;
	}

	public String getWithIdCard() {
		return withIdCard;
	}

	public void setWithIdCard(String withIdCard) {
		this.withIdCard = withIdCard;
	}

	public String getIdCardFront() {
		return idCardFront;
	}

	public void setIdCardFront(String idCardFront) {
		this.idCardFront = idCardFront;
	}

	public String getIdCardBack() {
		return idCardBack;
	}

	public void setIdCardBack(String idCardBack) {
		this.idCardBack = idCardBack;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}
	
}
