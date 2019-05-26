package com.ptcs.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ptcs.library.dao.ifac.UserDaoIfac;
import com.ptcs.library.entity.User;
import com.ptcs.library.util.DBUtils;

/**
 * 用户表的数据访问对象类
 * @author xianxian
 *
 */
public class UserDaoImpl implements UserDaoIfac {
	
	/** 
	 * 根据用户名和密码查询用户的sql
	 */
	private static final String QUERY_USER_BY_NAME_AND_PASSWORD = "select * from tab_user "
			+ "where user_name = ? and user_password = ? and user_type = ?";

	/**
	 * 添加用户的方法：
	 * 用户注册功能会调用该方法
	 */
	@Override
	public int addUser(User user) {
		int rows = 0;
		return rows;
	}

	/**
	 * 根据用户名和密码查询用户的方法
	 * 用户登录时会调用该方法
	 */
	@Override
	public User queryUserByNameAndPassword(String username,String password,Integer usertype) {
		User user = null;
		Connection conn = null;//连接声明
		PreparedStatement stmt = null;//预编译声明
		ResultSet rs = null;//结果声明
		
		try {
			conn = DBUtils.getConnection();//获取连接
			stmt = conn.prepareStatement(QUERY_USER_BY_NAME_AND_PASSWORD);//执行预编译
			//给占位符赋值
			stmt.setString(1,username);
			stmt.setString(2,password);
			stmt.setInt(3,usertype);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				user = new User();//有数据则在堆区里开个房间
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserType(rs.getInt("user_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return user;
	}
}
