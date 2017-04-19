package com.ifreework.entity.db;




public class Municipality{
	
	//columns START
	private String municipalityId;
	private String municipalityName;
	private String provinceId;
	private String firstLetter;
	private String isHot;
	private String state;
	//columns END
	public String getMunicipalityId() {
		return municipalityId;
	}
	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}
	public String getMunicipalityName() {
		return municipalityName;
	}
	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	public String getIsHot() {
		return isHot;
	}
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}


