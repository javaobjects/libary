package com.ptcs.library.dao.ifac;

import com.ptcs.library.entity.User;

public interface UserDaoIfac {
	/**
	 * 添加用户的方法：
	 * 用户注册功能会调用该方法
	 */
	int addUser(User user);
	/**
	 * 根据用户名和密码查询用户的方法
	 * 用户登录时会调用该方法
	 */
	User queryUserByNameAndPassword(String username, String password, Integer usertype);
}