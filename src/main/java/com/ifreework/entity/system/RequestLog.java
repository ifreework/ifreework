package com.ifreework.entity.system;


import java.io.Serializable;

public class RequestLog implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -7211684431445523719L;
	//columns START
	private java.lang.String requestLogId;
	private java.lang.String sessionId;
	private java.lang.String username;
	private java.lang.String resourceId;
	private java.util.Date requestTime;
	private Long timeLength;
	private java.lang.String ip;
	private java.lang.String browser;
	private java.lang.String browserVersion;
	private java.lang.String os;
	private java.lang.String deviceType;
	private java.lang.String osInfo;
	
	private Resource resource;
	//columns END
	
	
	public java.lang.String getRequestLogId() {
		return requestLogId;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public void setRequestLogId(java.lang.String requestLogId) {
		this.requestLogId = requestLogId;
	}
	public java.lang.String getSessionId() {
		return sessionId;
	}
	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}
	public java.lang.String getUsername() {
		return username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	public java.lang.String getResourceId() {
		return resourceId;
	}
	public void setResourceId(java.lang.String resourceId) {
		this.resourceId = resourceId;
	}
	public java.util.Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(java.util.Date requestTime) {
		this.requestTime = requestTime;
	}
	public Long getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(Long timeLength) {
		this.timeLength = timeLength;
	}
	public java.lang.String getIp() {
		return ip;
	}
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	public java.lang.String getBrowser() {
		return browser;
	}
	public void setBrowser(java.lang.String browser) {
		this.browser = browser;
	}
	public java.lang.String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(java.lang.String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public java.lang.String getOs() {
		return os;
	}
	public void setOs(java.lang.String os) {
		this.os = os;
	}
	public java.lang.String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(java.lang.String deviceType) {
		this.deviceType = deviceType;
	}
	public java.lang.String getOsInfo() {
		return osInfo;
	}
	public void setOsInfo(java.lang.String osInfo) {
		this.osInfo = osInfo;
	}

}


