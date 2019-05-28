package com.ptcs.libray.view;

import javax.swing.JFrame;

import com.ptcs.library.entity.User;

public class AdminMainView extends JFrame{
	private User user;

	/**
	 * 利用构造方法设置窗体属性
	 */
	public AdminMainView(User user) {
		this.user = user;//给user属性赋值
//		init();
//		registerListener();
		this.setTitle("管理主窗体");
		this.setSize(800,600);
		this.setResizable(false);//不能收缩
		this.setLocationRelativeTo(null);//居中
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//点击x之后程序退出
		this.setVisible(true);//显示
	}
}
