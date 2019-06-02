package com.ptcs.library.dao.impl;

import java.util.List;

import com.ptcs.library.dao.ifac.BookDaoIfac;
import com.ptcs.library.entity.Book;

public class AdminDaoImpl implements BookDaoIfac{
	Boolean queryUserByName(String username) {
		Boolean result = false;
		return result;
	}

	@Override
	public List<Book> queryAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryHotBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryCanLendBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryCanNotLendBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryBookByName(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book queryBookByBookId(Integer bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean lendBook(Integer book_id, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	};
}
