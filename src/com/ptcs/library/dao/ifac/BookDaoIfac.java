package com.ptcs.library.dao.ifac;

import java.util.List;

import com.ptcs.library.entity.Book;

public interface BookDaoIfac {

	/**
	 * 3、查看所有图书信息
	 */
	List<Book> queryAllBooks();

	/**
	 * 4.查看热门图书信息
	 */
	List<Book> queryHotBooks();

	/**
	 * 5.查看可借图书信息
	 */
	List<Book> queryCanLendBooks();

	/**
	 * 6.查看已借图书信息
	 */
	List<Book> queryCanNotLendBooks();

	/**
	 * 4.查看指定书名的图书信息
	 */
	List<Book> queryBookByName(String bookName);

	/**
	 * 3.查看指定编号的图书信息
	 * @param bookId
	 * @return
	 */
	Book queryBookByBookId(Integer bookId);

	/**
	 * 借书的方法
	 * @param book_id
	 * @param userId
	 * @return
	 */
	Boolean lendBook(Integer book_id, Integer userId);

}