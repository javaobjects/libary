package com.ptcs.libray.view;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ptcs.library.dao.DAOFactory;
import com.ptcs.library.dao.ifac.UserDaoIfac;
import com.ptcs.library.entity.User;

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
		//点击退出按钮退回注册窗体回到登陆窗体
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_exit");
				UserRegisterView.this.dispose();
				new UserLoginView();
			}
		});
		btn_commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_commit");
				System.out.println("你点击咯登录");
				/**
				 *点击登录按钮的目的：登录，来到主 窗体
				 *1、获取用户名和密码 还有用户类型 
				 */
				/**
				 * 点击确定提交按钮目的：
				 * 1、获取用户姓名、密码、确认密码
				 * 2、验证用户姓名合法（无特殊字符）
				 * 3、验证密码与确认密码一致并无特列字符
				 * 4、通过验证后向数据库插入数据并返回是否插入成功 成功失败都弹出提示框
				 * 5、成功后关闭当前窗体打开登陆窗体
				 * 6、若失败则留在当前窗体
				 */
				String username = txt_username.getText();
				String password = txt_password.getText();
				String type = (String)cb_type.getSelectedItem();
				int user_type = cb_type.getSelectedIndex() + 1;//1是管理员2 是普通用户
				
//				System.out.println(username);
//				System.out.println(password);
//				System.out.println(user_type);
				
				//2、对数据进行非空判断
				//对用户名进行非空判断
				if(username == null || "".equals(username.trim())) {
					JOptionPane.showMessageDialog(null,"用户名为空,请重新输入");
					return;
				}
				//对密码进行非空判断
				if(password == null || "".equals(password.trim())) {
					JOptionPane.showMessageDialog(null, "密码为空,请重新输入");
					return;
				}
				//3、判断用户是否存在
				User user = userDao.queryUserByNameAndPassword(username,password,user_type);
				if(user == null) {
					JOptionPane.showMessageDialog(null, "用户名或密码或类型错误,请重新输入或选择");
					return;
				}
				//4、判断用户类型:如果是普通用户则弹出用户主窗体，如果是管理员则弹出管理员主窗体
				if(user.getUserType() == 1) {//1是管理员
					System.out.println("弹出管理主窗体");
					new AdminMainView(user);//弹出管理主窗体 	
					UserLoginView.this.dispose();////释放窗体占用的内存
				}else {//2是普通用户
					System.out.println("弹出普通用户窗体");
					new UserMainView(user);//弹出普通用户窗体
					UserLoginView.this.dispose();//释放窗体占用的内存
				}
			}
		});
	}
	public UserRegisterView() {
		init();
		registetActionListener();
	};
}
