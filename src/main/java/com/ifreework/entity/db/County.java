package com.ifreework.entity.db;




public class County {
	
	//columns START
	private Integer countyId;
	private String countyName;
	private String municipalityId;
	//columns END

	public void setCountyId(Integer value) {
		this.countyId = value;
	}
	
	public Integer getCountyId() {
		return this.countyId;
	}
	
	
	
	public void setCountyName(String value) {
		this.countyName = value;
	}
	public String getCountyName() {
		return this.countyName;
	}

	public String getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}	
	
	
}


