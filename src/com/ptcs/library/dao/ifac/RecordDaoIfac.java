package com.ptcs.library.dao.ifac;

import java.util.List;

import com.ptcs.library.entity.Record;
import com.ptcs.library.entity.User;

public interface RecordDaoIfac {
	
	/**
	 * 还书功能
	 * @param record_id
	 * @param book_id
	 * @return
	 */
	public boolean returnBook(int record_id,int book_id);

	/**
	 * 查询本人的所有借阅记录
	 * select record_id,user_id,book_id,lend_time,return_time from tab_record where user_id = ?
	 */
	List<Record> queryAllRecord(User user);

	/**
	 * 查询本人所有未归还借阅记录
	 * @param user
	 * @return
	 */
	List<Record> queryAllNotReturnRecord(User user);

	/**
	 * 查询本人的所有已归还借阅记录
	 * @param user
	 * @return
	 */
	List<Record> queryAllReturnRecord(User user);

}