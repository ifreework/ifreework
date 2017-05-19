package com.ifreework.entity.system;

import java.io.Serializable;

public class LoginLog implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -7815272296449329695L;
	//columns START
	private java.lang.String loginLogId;
	private java.lang.String username;
	private java.util.Date loginTime;
	private java.util.Date logoutTime;
	private java.lang.String ip;
	private java.lang.String browser;
	private java.lang.String browserVersion;
	private java.lang.String mac;
	private java.lang.String os;
	//columns END

	public void setLoginLogId(java.lang.String value) {
		this.loginLogId = value;
	}
	
	public java.lang.String getLoginLogId() {
		return this.loginLogId;
	}
	
	
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	public java.lang.String getUsername() {
		return this.username;
	}	
	
	public void setLoginTime(java.util.Date value) {
		this.loginTime = value;
	}
	public java.util.Date getLoginTime() {
		return this.loginTime;
	}	
	
	public void setLogoutTime(java.util.Date value) {
		this.logoutTime = value;
	}
	public java.util.Date getLogoutTime() {
		return this.logoutTime;
	}	
	
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	public java.lang.String getIp() {
		return this.ip;
	}	
	
	public void setBrowser(java.lang.String value) {
		this.browser = value;
	}
	public java.lang.String getBrowser() {
		return this.browser;
	}	
	
	public void setBrowserVersion(java.lang.String value) {
		this.browserVersion = value;
	}
	public java.lang.String getBrowserVersion() {
		return this.browserVersion;
	}	
	
	public void setMac(java.lang.String value) {
		this.mac = value;
	}
	public java.lang.String getMac() {
		return this.mac;
	}	
	
	public void setOs(java.lang.String value) {
		this.os = value;
	}
	public java.lang.String getOs() {
		return this.os;
	}	
}


