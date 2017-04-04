package com.ifreework.entity.system;

import com.ifreework.common.entity.Page;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年8月16日 下午1:38:36    
 * @修改人：wangyh    
 * @修改时间：2016年8月16日 下午1:38:36    
 * @version 1.0
 */
public class User {
	private java.lang.String userId;
	private java.lang.String userName;
	private java.lang.String password;
	private java.lang.String name;
	private java.lang.String rights;
	private java.lang.String roleId;
	private java.lang.String lastLogin;
	private java.lang.String IP;
	private java.lang.String status;
	private java.lang.String bz;
	private java.lang.String skin;
	private java.lang.String email;
	private java.lang.String number;
	private java.lang.String phone;
	private java.lang.String cityCode;
	private java.lang.String imgPath;
	private java.lang.String isOnline;
	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getNumber() {
		return number;
	}

	public void setNumber(java.lang.String number) {
		this.number = number;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(java.lang.String isOnline) {
		this.isOnline = isOnline;
	}

	private Page page; // 分页对象
	private Role role;


	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * userId
	 * 
	 * @return the userId
	 */

	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userName
	 * 
	 * @return the userName
	 */

	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * password
	 * 
	 * @return the password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * name
	 * 
	 * @return the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * rights
	 * 
	 * @return the rights
	 */

	public String getRights() {
		return rights;
	}

	/**
	 * @param rights
	 *            the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}

	/**
	 * roleId
	 * 
	 * @return the roleId
	 */

	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * lastLogin
	 * 
	 * @return the lastLogin
	 */

	public String getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * iP
	 * 
	 * @return the iP
	 */

	public String getIP() {
		return IP;
	}

	/**
	 * @param iP
	 *            the iP to set
	 */
	public void setIP(String iP) {
		IP = iP;
	}

	/**
	 * status
	 * 
	 * @return the status
	 */

	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * role
	 * 
	 * @return the role
	 */

	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * page
	 * 
	 * @return the page
	 */

	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * skin
	 * 
	 * @return the skin
	 */

	public String getSkin() {
		return skin;
	}

	/**
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}

	
	/**    
	 * cityCode    
	 *    
	 * @return  the cityCode    
	*/
	
	public String getCityCode() {
		return cityCode;
	}

	/**    
	 * @param cityCode the cityCode to set    
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
