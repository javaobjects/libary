package com.ptcs.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ptcs.library.dao.ifac.RecordDaoIfac;
import com.ptcs.library.entity.Book;
import com.ptcs.library.entity.Record;
import com.ptcs.library.entity.User;
import com.ptcs.library.util.DBUtils;

public class RecordDaoImpl implements RecordDaoIfac {

	private static final String QUERY_ALL_RECORD_BY_SELF = "select r.record_id,r.user_id,"
			+ "r.book_id,r.lend_time,r.return_time,"
			+ "b.book_name from libary_tab_record r,libary_tab_book b where r.book_id = b.book_id and user_id = ?";
	private static final String QUERY_ALL_NOT_RETURN_RECORD_BY_SELF = "select r.record_id,r.user_id,"
			+ "r.book_id,r.lend_time,r.return_time,"
			+ "b.book_name from libary_tab_record r,libary_tab_book b where r.book_id = b.book_id and user_id = ?"
			+ " and r.return_time is null";
	private static final String QUERY_ALL_RETURN_RECORD_BY_SELF = "select r.record_id,r.user_id,"
			+ "r.book_id,r.lend_time,r.return_time,"
			+ "b.book_name from libary_tab_record r,libary_tab_book b where r.book_id = b.book_id and user_id = ?"
			+ " and r.return_time is not null";

	
	
	/**
	 * 11、还书功能
	 */
	public boolean returnBook(int record_id,int book_id)
	{
		Boolean result = false;
		/*
		 * 思路：先设置事务手动提交，查询书的状态，如果可还继续 如果不可还返回，，
		 * 如果可还那么
		 * 1、修改借书记录的归还时间
		 * 2、同时修改书的状态为1
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("select book_status from tab_book where book_id=?");
			stmt.setInt(1,book_id);
			rs = stmt.executeQuery();
			int status = 1;
			if(rs.next()) { 
				status = rs.getInt("book_status");
			}
			System.out.println(status);
			//如果不可还返回
			if(status != 0) {
				return result;
			}else {
				//如果可还继续
				//1、修改借书记录的归还时间
				stmt = conn.prepareStatement("update tab_record set return_time = sysdate where record_id = ?");
				stmt.setInt(1,record_id);
				int rows1 = stmt.executeUpdate();
				//2、同时修改书的状态为1
				stmt = conn.prepareStatement("update tab_book set book_status = 1 where book_id=?");
				stmt.setInt(1,book_id);
				int rows2 = stmt.executeUpdate();
				
				if(rows1 > 0 && rows2 > 0) {
					conn.commit();//事务提交
					result = true;//借书成功
				}else {
					conn.rollback();//事务回滚
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return result;
	}
	/**
	 * 查询本人的所有借阅记录
	 * select record_id,user_id,book_id,lend_time,return_time from tab_record where user_id = ?
	 */
	@Override
	public List<Record> queryAllRecord(User user)
	{
		List<Record> records = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_ALL_RECORD_BY_SELF);
			stmt.setInt(1,user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				record.setBook(book);
				User users  = new User();
				user.setUserId(rs.getInt("user_id"));
				record.setUser(users);
				record.setLendTime(rs.getDate("lend_time"));
				record.setReturnTime(rs.getDate("return_time"));
				record.setRecordId(rs.getInt("record_id"));
				records.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return records;
	}
	/**
	 * 查询本人所有未归还借阅记录
	 * @param user
	 * @return
	 */
	@Override
	public List<Record> queryAllNotReturnRecord(User user)
	{
		List<Record> records = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_ALL_NOT_RETURN_RECORD_BY_SELF);
			stmt.setInt(1,user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				record.setBook(book);
				User users  = new User();
				user.setUserId(rs.getInt("user_id"));
				record.setUser(users);
				record.setLendTime(rs.getDate("lend_time"));
				record.setReturnTime(rs.getDate("return_time"));
				record.setRecordId(rs.getInt("record_id"));
				records.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return records;
	}
	/**
	 * 查询本人的所有已归还借阅记录
	 * @param user
	 * @return
	 */
	@Override
	public List<Record> queryAllReturnRecord(User user)
	{
		List<Record> records = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_ALL_RETURN_RECORD_BY_SELF);
			stmt.setInt(1,user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				record.setBook(book);
				User users  = new User();
				user.setUserId(rs.getInt("user_id"));
				record.setUser(users);
				record.setLendTime(rs.getDate("lend_time"));
				record.setReturnTime(rs.getDate("return_time"));
				record.setRecordId(rs.getInt("record_id"));
				records.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return records;
	}
}
