package com.ptcs.library.entity;

public class User {

	/**
	 * UserId 用户编号
	 * userName 用户名
	 * userPassword 用户密码
	 * userType 用户类型
	 */
	private Integer UserId;
	private String userName;
	private String userPassword;
	private Integer userType;
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public User(Integer userId, String userName, String userPassword, Integer userType) {
		super();
		UserId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", userName=" + userName + ", userPassword=" + userPassword + ", userType="
				+ userType + "]";
	}
	
}
