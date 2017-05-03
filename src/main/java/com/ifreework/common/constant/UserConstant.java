package com.ifreework.common.constant;

public enum UserConstant {
	USER_CACHE_NAME("sys-userCache"),
	USERNAME_KEY_PREFIX("username-"),
	
	
	RESOURCE_PERMISSIONS_PREFIX("resource-permissions-"),
	USER_PERMISSIONS_PREFIX("user-permissions-");
	
	
	private String value;
	private UserConstant(String value){
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
}
