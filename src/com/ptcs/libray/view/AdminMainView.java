package com.ptcs.libray.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ptcs.library.entity.User;

public class AdminMainView extends JFrame{
	/** 大容器 */
	private JPanel panel_common;
	/** 欢迎容器 */
	private JPanel panel_welcome;
	/** 左容器 */
	private JDesktopPane panel_left;
	/** 右容器 */
	private JPanel panel_right;
	/** 欢迎标签 */
	private JLabel label_welcome;
	/** 图片标签 */
	private JLabel label_img;
	/** 图书管理功能按钮 */
	private JButton btn_book_mgr;
	/** 借阅记录功能按钮 */
	private JButton btn_quy_record;
	/** 用户管理功能按钮**/
	private JButton btn_user_mgr;
	/** 退出窗口按钮 */
	private JButton btn_exit;
	/**
	 * 定义一个属性保存用户信息：编号，姓名，密码，用户类型
	 */
	private User user;
    //	管理窗体：图书管理
	private AdminManagerBookView mbv = null;
	//管理窗体：借阅记录查询
	private AdminQueryRecordView qrv = null;
	//管理窗体：用户管理
	private AdminManagerUserView muv = null;
	/**
	 * 初始化各个控件并拼装
	 */
	private void init()
	{
		//初始化组件
		panel_common = new JPanel(new BorderLayout());// 东西南北中的布局管理器
		panel_welcome = new JPanel();
		panel_left = new JDesktopPane();
		panel_right = new JPanel(new GridLayout(7, 1, 0, 30));

		label_welcome = new JLabel(
				"欢    迎    "+this.user.getUserName()+"   使   用   图   书   借   阅   管   理   系   统");

		Icon icon = new ImageIcon("config\\images\\baxianhua.jpg");
		label_img = new JLabel(icon);
		label_img.setBounds(0, 0, 680, 600);

		btn_book_mgr = new JButton("图书管理");
		btn_quy_record = new JButton("借阅记录查询");
		btn_user_mgr = new JButton("用户管理");
		btn_exit = new JButton("退出窗口");

		// 拼装组件
		panel_welcome.add(label_welcome);

		panel_left.add(label_img);

		panel_right.add(new JLabel());
	
		panel_right.add(btn_book_mgr);
		panel_right.add(btn_quy_record);
		panel_right.add(btn_user_mgr);
		panel_right.add(btn_exit);
		panel_right.add(new JLabel());
		panel_right.add(new JLabel());

		panel_common.add(panel_welcome, BorderLayout.NORTH);
		panel_common.add(panel_left, BorderLayout.CENTER);// 注意这里不能是west,否则图片出不来
		panel_common.add(panel_right, BorderLayout.EAST);

		this.add(panel_common);
	}
	/**
	 * 给三个按钮注册侦听器
	 */
	private void registerListener() {
		//图书查询
		btn_book_mgr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_book_mgr");
				//显示图书管理嵌入式窗体
				if(mbv != null) {
					panel_left.remove(mbv);
				}
				if(qrv != null) {
					panel_left.remove(qrv);
				}
				if(muv != null) {
					panel_left.remove(muv);
				}
				mbv = new AdminManagerBookView();
				panel_left.add(mbv);
				mbv.toFront();//将此窗体放到最前面
			}
		});
		//借阅记录查询
		btn_quy_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_quy_record");
				//显示借阅记录查询嵌入式窗体
				if(mbv != null) {
					panel_left.remove(mbv);
				}
				if(qrv != null) {
					panel_left.remove(qrv);
				}
				if(muv != null) {
					panel_left.remove(muv);
				}
				qrv = new AdminQueryRecordView();
				panel_left.add(qrv);
				qrv.toFront();//将此窗体放到最前面
			}
		});
		//用户管理
		btn_user_mgr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_user_mgr");
				//显示用户管理查询嵌入式窗体
				if(mbv != null) {
					panel_left.remove(mbv);
				}
				if(qrv != null) {
					panel_left.remove(qrv);
				}
				if(muv != null) {
					panel_left.remove(muv);
				}
				muv = new AdminManagerUserView();
				panel_left.add(muv);
				muv.toFront();//将此窗体放到最前面
			}
		});
		
		
		//退出窗口
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_exit");
				new UserLoginView();
				AdminMainView.this.dispose();//关闭当前窗口
			}
		});
	}

	/**
	 * 利用构造方法设置窗体属性
	 */
	public AdminMainView(User user) {
		this.user = user;//给user属性赋值
		init();
		registerListener();
		this.setTitle("管理主窗体");
		this.setSize(800,600);
		this.setResizable(false);//不能收缩
		this.setLocationRelativeTo(null);//居中
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//点击x之后程序退出
		this.setVisible(true);//显示
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
