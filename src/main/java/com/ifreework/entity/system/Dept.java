package com.ifreework.entity.system;

import java.util.List;

public class Dept {
	
	//columns START
	private java.lang.String deptId; //部门ID
	private java.lang.String name; //部门名称
	private java.lang.String nameEn; //英文简称
	private java.lang.String bianma; //部门编码
	private java.lang.String parentId; //父级部门ID
	private java.lang.Integer depth;
	private java.lang.Integer leaf;
	private java.lang.String bz; //
	private java.lang.String headman;  //部门领导
	private java.lang.String tel; //联系电话
	private java.lang.String functions;
	private java.lang.String address;
	private List<User> children ;
	
	private Integer onLineNumber;
	//columns END

	
	
	
	public void setDeptId(java.lang.String value) {
		this.deptId = value;
	}
	
	public Integer getOnLineNumber() {
		return onLineNumber;
	}

	public void setOnLineNumber(Integer onLineNumber) {
		this.onLineNumber = onLineNumber;
	}

	public java.lang.Integer getDepth() {
		return depth;
	}

	public void setDepth(java.lang.Integer depth) {
		this.depth = depth;
	}

	public java.lang.Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(java.lang.Integer leaf) {
		this.leaf = leaf;
	}

	public List<User> getChildren() {
		return children;
	}

	public void setChildren(List<User> children) {
		this.children = children;
	}

	public java.lang.String getDeptId() {
		return this.deptId;
	}
	
	
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.String getName() {
		return this.name;
	}	
	
	public void setNameEn(java.lang.String value) {
		this.nameEn = value;
	}
	public java.lang.String getNameEn() {
		return this.nameEn;
	}	
	
	public void setBianma(java.lang.String value) {
		this.bianma = value;
	}
	public java.lang.String getBianma() {
		return this.bianma;
	}	
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	public java.lang.String getParentId() {
		return this.parentId;
	}	
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}	
	
	public void setHeadman(java.lang.String value) {
		this.headman = value;
	}
	public java.lang.String getHeadman() {
		return this.headman;
	}	
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	public java.lang.String getTel() {
		return this.tel;
	}	
	
	public void setFunctions(java.lang.String value) {
		this.functions = value;
	}
	public java.lang.String getFunctions() {
		return this.functions;
	}	
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}	
}


