package com.ptcs.libray.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminManagerBookView extends JInternalFrame {
	//窗体中功能的实现依赖底层的dao，所以属性依赖
	/**
	 * 窗体中最外层的面板
	 */
	private JPanel panel_common;
	/**
	 * 左面板
	 */
	private JScrollPane panel_left;
	/**
	 * 右面板
	 */
	private JPanel panel_right;
	/** 存放数据的表格控件 */
	private JTable table;
	/** 查询类型标签 */
	private JLabel lb_query_type;
	/** 查询类型下拉框 */
	private JComboBox<String> cb_query_type;
	/**输入框用来输入图书编号或者图书的书名**/
	private JTextField txt_book_id_or_name;
	/** 查询按钮 */
	private JButton btn_query;
	/**添加按钮**/
	private JButton btn_add;
	/**添加按钮**/
	private JButton btn_delete;
	/**修改按钮**/
	private JButton btn_update;
	/**修改按钮**/
	private JButton btn_lend;
	/** 退出按钮 */
	private JButton btn_exit;
	/** 初始化组件装配组件的方法 */ 
	private void init() {
		lb_query_type = new JLabel("查询类型：");
		cb_query_type = new JComboBox<String>(new String[] { "所有图书信息", "指定编号图书",
				"指定书名图书"});
		btn_query = new JButton("查    询");
		btn_add = new JButton("添    加");
		btn_delete = new JButton("删    除");
		btn_update = new JButton("修    改");
		btn_lend = new JButton("借    书");
		txt_book_id_or_name = new JTextField();//输入框
		btn_exit = new JButton("退     出");

		table = new JTable();
		panel_left = new JScrollPane(table);

		panel_right = new JPanel(new GridLayout(10, 1, 0, 20));
		panel_right.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "查询条件"));
		panel_right.add(lb_query_type);
		panel_right.add(cb_query_type);
		panel_right.add(txt_book_id_or_name);
		panel_right.add(btn_query);
		panel_right.add(btn_add);
		panel_right.add(btn_delete);
		panel_right.add(btn_update);
		panel_right.add(btn_lend);
		panel_right.add(new JLabel());
		panel_right.add(btn_exit);

		panel_common = new JPanel(new BorderLayout());
		panel_common.add(panel_left, BorderLayout.CENTER);
		panel_common.add(panel_right, BorderLayout.EAST);

		this.add(panel_common);
	}
	/** 构造方法 */
	public AdminManagerBookView() {
		init();
//		registerListener();
		this.setTitle("图书管理窗体");
		this.setSize(679,540);
		//设置窗体可以关闭
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置默认的关闭操作，释放内存空间
		this.setIconifiable(true);// 窗体能否最小化
		this.setVisible(true);//显示
	}
}
