package com.ptcs.library.dao;

import com.ptcs.library.dao.ifac.BookDaoIfac;
import com.ptcs.library.dao.ifac.RecordDaoIfac;
import com.ptcs.library.dao.ifac.UserDaoIfac;
import com.ptcs.library.dao.impl.BookDaoImpl;
import com.ptcs.library.dao.impl.RecordDaoImpl;
import com.ptcs.library.dao.impl.UserDaoImpl;

/**
 * Dao的工厂,专门生产各类dao的实例
 * @author xianxian
 *
 */
public class DAOFactory {
	/**
	 * 获取 UserDaoIfac接口实例的方法
	 */
	public static UserDaoIfac getUserDaoInstance() {
		return new UserDaoImpl();
	}

	public static BookDaoIfac getBookDaoInstance() {
		return new BookDaoImpl();
	}
	public static RecordDaoIfac getRecordDaoInstance()
	{
		return new RecordDaoImpl();
	}
}
