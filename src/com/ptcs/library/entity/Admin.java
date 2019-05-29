package com.ptcs.library.entity;

public class Admin {
	/**
	 * AdminId 用户编号
	 * AdminName 用户名
	 * AdminPassword 用户密码
	 * AdminType 用户类型
	 */
	private Integer adminId;
	private String adminName;
	private String adminPassword;
	private Integer adminType;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Integer getAdminType() {
		return adminType;
	}
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}
	public Admin(Integer adminId, String adminName, String adminPassword, Integer adminType) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminType = adminType;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword + ", adminType="
				+ adminType + "]";
	}
}
