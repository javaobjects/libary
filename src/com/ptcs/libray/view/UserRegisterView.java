package com.ptcs.libray.view;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ptcs.library.dao.DAOFactory;
import com.ptcs.library.dao.ifac.UserDaoIfac;

public class UserRegisterView extends JFrame{
	//大到窗体中的窗口 小到各个组件如标签等 都定义成窗体的属性
	private JPanel panel_common;//最底层的公共容器
	

	private JLabel lab_username;//用户姓名标签
	private JLabel lab_password;//密码标签
	private JLabel lab_pw_again;//再次输入密码标签
	
	private JTextField txt_username;//用户姓名文本框 
	private JTextField txt_password;//密码文框
	private JTextField txt_pw_again;//再次输入密码文框
	
	private JButton btn_exit;//退出按钮
	private JButton btn_commit;//确定按钮
	/**
	 * 窗体属性依赖UserDaoIfac接口
	 */
	private UserDaoIfac userDao = DAOFactory.getUserDaoInstance();//声明依赖并初始化,避免空指针异常
	
	/**
	 * 初始化窗体的方法
	 */
	public void init() {
		//1、把组件全部实例化
		panel_common = new JPanel(new GridLayout(4,2,0,10));//实例化最底层的公共容器并传参 4行2列
		
		lab_username = new JLabel("用户姓名");//用户名块
		lab_password = new JLabel("密       码");//密码块
		lab_pw_again = new JLabel("确认密码");//密码块
		txt_username = new JTextField();//用户名输入文本框
		txt_password = new JTextField();//密码文本输入框
		txt_pw_again = new JTextField();//再次输入密码文本输入框
		btn_exit = new JButton("退出");//登录按钮
		btn_commit = new JButton("确认提交");//注册按钮
		
		panel_common.add(lab_username);
		panel_common.add(txt_username);
		panel_common.add(lab_password);
		panel_common.add(txt_password);
		panel_common.add(lab_pw_again);
		panel_common.add(txt_pw_again);
		panel_common.add(btn_exit);
		panel_common.add(btn_commit);
		
		
		//3、把组件都装入窗体中
		this.add(panel_common);//将总容器全部存入进容器JFrame
		this.setTitle("用户注册窗体");//设置窗体的标题
		this.setSize(360,210);//设置窗体大小
		this.setResizable(false);//不能收缩
		this.setLocationRelativeTo(null);//居中
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭窗体时结束进程
		this.setVisible(true);//让窗体可见
	}
	/**
	 * 给所有按钮注册倾听器 方法
	 */
	private void registetActionListener() {
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_exit");
			}
		});
		btn_commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_commit");
				
			}
		});
	}
	public UserRegisterView() {
		init();
		registetActionListener();
	};
}
