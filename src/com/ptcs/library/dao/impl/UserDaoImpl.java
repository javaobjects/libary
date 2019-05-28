package com.ptcs.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	 * 根据用户名查询用户名是否存在的sql
	 */
	private static final String QUERY_USER_BY_NAME = "select * from tab_user"
			+ " where user_name = ?";
	/**向用户表里插入数据**/
	private static final String INSERT_USER_TO_TAB_USER = "insert into tab_user(user_id,user_name,user_password,user_type)"
			+ " values((select max(user_id) from tab_user)+1,?,?,?);";

	/**
	 * 添加用户的方法：
	 * 用户注册功能会调用该方法
	 * insert into tab_user(user_id,user_name,user_password,user_type)
 values((select max(user_id) from tab_user)+1,157,123456,1);
	 */
	@Override
	public Integer addUser(String userName, String userPassword, Integer userType) {
		int rows = 0;
		Connection conn = null;//连接声明
		PreparedStatement stmt = null;//预编译声明
		ResultSet rs = null;//结果声明
		
		try {
			conn = DBUtils.getConnection();//获取连接
			conn.setAutoCommit(false);//设置事务手动提交
			stmt = conn.prepareStatement(INSERT_USER_TO_TAB_USER);//执行预编译
			//给点位符赋值
			stmt.setString(1,userName);
			stmt.setString(2,userPassword);
			stmt.setInt(3,userType);
			rs = stmt.executeQuery();//执行sql语句
			if(rs.next()){
				rows++;
				conn.commit();//事务提交
			}else {
				conn.rollback();//事务回滚
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();//事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return rows;
	}

	/**
	 * 根据用户名查询此用户是否存在 存在则返回true
	 */
	@Override
	public Boolean queryUserByName(String username) {
		Boolean result = false;
		Connection conn = null;//连接声明
		PreparedStatement stmt = null;//预编译声明
		ResultSet rs = null;//结果声明
		
		try {
			conn = DBUtils.getConnection();//获取连接
			stmt = conn.prepareStatement(QUERY_USER_BY_NAME);//执行预编译
			//给点位符赋值
			stmt.setString(1,username);
			rs = stmt.executeQuery();//执行sql
			if(rs.next()) {//如果有数据则将result赋值为true
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return result;
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
