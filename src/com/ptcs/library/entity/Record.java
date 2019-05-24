package com.ptcs.library.entity;

import java.util.Date;

public class Record {
	/**
	 * recordId 编号
	 * user user对象
	 * book book对象
	 * lendTime  借出时间
	 * returnTime 归还时间
	 */
	private Integer recordId;
	private User user;
	private Book book;
	private Date lendTime;
	private Date returnTime;
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getLendTime() {
		return lendTime;
	}
	public void setLendTime(Date lendTime) {
		this.lendTime = lendTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public Record(Integer recordId, User user, Book book, Date lendTime, Date returnTime) {
		super();
		this.recordId = recordId;
		this.user = user;
		this.book = book;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	public Record() {
		super();
	}
	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", user=" + user + ", book=" + book + ", lendTime=" + lendTime
				+ ", returnTime=" + returnTime + "]";
	}
	
	
}
