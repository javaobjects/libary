package com.ptcs.library.entity;

public class Book {

	/**
	 * bookId 书籍编号 
	 * bookName 书名
	 * lendCount 借出次数
	 * status 书籍状态
	 */
	private Integer bookId;
	private String bookName;
	private Integer lendCount;
	private Integer status;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getLendCount() {
		return lendCount;
	}
	public void setLendCount(Integer lendCount) {
		this.lendCount = lendCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Book(Integer bookId, String bookName, Integer lendCount, Integer status) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.lendCount = lendCount;
		this.status = status;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", lendCount=" + lendCount + ", status=" + status
				+ "]";
	}
	
}
