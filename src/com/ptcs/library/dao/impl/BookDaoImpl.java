package com.ptcs.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ptcs.library.dao.ifac.BookDaoIfac;
import com.ptcs.library.entity.Book;
import com.ptcs.library.util.DBUtils;
/**
 * 图书表的数据访问对象类
 * @author xianxian
 *
 */
public class BookDaoImpl implements BookDaoIfac {

	/** 查询所有图书的sql语句 */
	private static final String QUERY_ALL_BOOKS="select book_id,book_name,book_count,book_status from tab_book";
	/** 查看热门图书信息 */
	private static final String QUERY_HOT_BOOKS="select b.* "
			+ "from (select book_id,book_name,book_count,book_status from tab_book order by book_count desc) b"
			+ " where rownum<=5";
	/** 查询可借图书 */
	private static final String QUERY_CAN_LEND_BOOKS = "select book_id,book_name,book_count,book_status from tab_book"
			+ " where book_status=1";
	/** 查询不可借图书*/
	private static final String QUERY_NOT_LEND_BOOKS = "select book_id,book_name,book_count,book_status from tab_book"
			+ " where book_status=0";
	/**插入一条借书记录**/
	private static final String INSERT_LENT_BOOK_TO_TAB_RECORD = "insert into tab_record(record_id,book_id,user_id,lend_time) "
			+ "values(seq_record_id.nextval,?,?,sysdate)";
	/**修改书的状态为**/
	private static final String UPDATE_BOOK_STATUS = "update tab_book set book_status = 0 where book_id = ?";
	
//	5.添加图书
//	6.删除图书
//	7.修改图书
	
	/**
	 * 10.借书
	 */
	public Boolean lendBook(Integer book_id,Integer user_id) {
		Boolean result = false;
		/*
		 * 思路：先设置事务手动提交，查询书的状态，如果可借继续 如果不可借返回，，
		 * 如果可借那么插入一条记录同时修改书的状态为0
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
			int status = 0;
			if(rs.next()) {
				status = rs.getInt("book_status");
			}
			//如果不可借返回
			if(status == 0) {
				return result;
			}else {
				//如果可借继续
				//1、插入一条借书记录
				stmt = conn.prepareStatement(INSERT_LENT_BOOK_TO_TAB_RECORD);
				stmt.setInt(1,book_id);
				stmt.setInt(2,user_id);
				int rows_insert = stmt.executeUpdate();
				//2、修改书的状态为0
				stmt = conn.prepareStatement(UPDATE_BOOK_STATUS);
				stmt.setInt(1,book_id);
				int rows_update = stmt.executeUpdate();
				
				if(rows_insert > 0 && rows_update > 0) {
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
	 * 3、查看所有图书信息
	 */
	@Override
	public List<Book> queryAllBooks(){
		List<Book> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_ALL_BOOKS);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setLendCount(rs.getInt("book_count"));
				book.setStatus(rs.getInt("book_status"));
				list.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return list;
	}
	
	/**
	 * 4.查看热门图书信息
	 */
	@Override
	public List<Book> queryHotBooks()
	{
		List<Book> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_HOT_BOOKS);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setLendCount(rs.getInt("book_count"));
				book.setStatus(rs.getInt("book_status"));
				list.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return list;
	}
	
	/**
	 * 5.查看可借图书信息
	 */
	@Override
	public List<Book> queryCanLendBooks(){
		List<Book> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_CAN_LEND_BOOKS);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setLendCount(rs.getInt("book_count"));
				book.setStatus(rs.getInt("book_status"));
				list.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return list;
	}
	
	/**
	 * 6.查看已借图书信息
	 */
	@Override
	public List<Book> queryCanNotLendBooks()
	{
		List<Book> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(QUERY_NOT_LEND_BOOKS);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setLendCount(rs.getInt("book_count"));
				book.setStatus(rs.getInt("book_status"));
				list.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(conn, stmt, rs);
		}
		return list;
	}
	
	
	/**
	 * 4.查看指定书名的图书信息
	 */
	@Override
	public List<Book> queryBookByName(String bookName){
		//需要模糊查询
		List<Book> list = new ArrayList<>();
		return list;
	}
	
	
	
	/**
	 * 3.查看指定编号的图书信息
	 * @param bookId
	 * @return
	 */
	@Override
	public Book queryBookByBookId(Integer bookId) {
		Book book = null;
		return book;
	}
}
