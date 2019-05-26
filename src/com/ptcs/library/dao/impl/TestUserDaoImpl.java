package com.ptcs.library.dao.impl;

public class TestUserDaoImpl {

	public static void main(String[] args) {
		System.out.println(new UserDaoImpl().queryUserByNameAndPassword("1234", "123456",2));

	}

}
