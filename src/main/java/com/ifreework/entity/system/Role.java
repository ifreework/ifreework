package com.ifreework.entity.system;




public class Role{
	
	//columns START
	private java.lang.String roleId;
	private java.lang.String roleName;
	private java.lang.String parentId;
	private java.lang.String isLeaf;
	private java.lang.String remarks;
	//columns END

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	
	
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	public java.lang.String getRoleName() {
		return this.roleName;
	}	
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	public java.lang.String getParentId() {
		return this.parentId;
	}	
	
	public void setIsLeaf(java.lang.String value) {
		this.isLeaf = value;
	}
	public java.lang.String getIsLeaf() {
		return this.isLeaf;
	}	
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	public java.lang.String getRemarks() {
		return this.remarks;
	}	
}


