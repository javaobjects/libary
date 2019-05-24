package com.ptcs.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ptcs.library.entity.Book;
import com.ptcs.library.util.DBUtils;

public class BookDaoImpl {

	/** 查询所有图书的sql语句 */
	private static final String QUERY_ALL_BOOKS="select book_id,book_name,lend_count,status from tab_book";
	/** 查看热门图书信息 */
	private static final String QUERY_HOT_BOOKS="select b.* "
			+ "from (select book_id,book_name,lend_count,status from tab_book order by lend_count desc) b"
			+ " where rownum<=5";
	/** 查询可借图书 */
	private static final String QUERY_CAN_LEND_BOOKS = "select book_id,book_name,lend_count,status from tab_book"
			+ " where status=1";
	/** 查询不可借图书*/
	private static final String QUERY_NOT_LEND_BOOKS = "select book_id,book_name,lend_count,status from tab_book"
			+ " where status=0";

	/**
	 * 3、查看所有图书信息
	 */
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
				book.setLendCount(rs.getInt("lend_count"));
				book.setStatus(rs.getInt("status"));
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
				book.setLendCount(rs.getInt("lend_count"));
				book.setStatus(rs.getInt("status"));
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
				book.setLendCount(rs.getInt("lend_count"));
				book.setStatus(rs.getInt("status"));
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
				book.setLendCount(rs.getInt("lend_count"));
				book.setStatus(rs.getInt("status"));
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
	 * 查看指定书名的图书信息
	 */
//	public List<Book> queryBookByName(String book){
//		
//	}
	
	
	
	/**
	 * 查看指定编号的图书信息
	 * @param bookId
	 * @return
	 */
	public Book queryBookByBookId(Integer bookId) {
		Book book = null;
		return book;
	}
}
