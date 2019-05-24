package com.ptcs.library.util;

public class TestDBUtils {

	public static void main(String[] args) {
		new DBUtils();
		System.out.println(DBUtils.getConnection());
	}
}
