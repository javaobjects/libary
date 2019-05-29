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
//import com.ptcs.libray.view.UserQueryRecordView.RecordModel;

public class AdminQueryRecordView extends JInternalFrame {
	//窗体中功能的实现依赖底层的dao，所以属性依赖
//		private RecordDaoIfac recordDao = DAOFactory.getRecordDaoInstance();
//		private User user;
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
		/** 查询按钮 */
		private JButton btn_query;
		/** 还书按钮 */
		private JButton btn_return;
		/** 退出按钮 */
		private JButton btn_exit;
		/**定义全局变量*/
		/**record_id待还书的记录编号**/
		private int record_id;
		/**book_id待还书的编号**/
		private int book_id;
		/**
		 * 存放选定图书ID属性
		 */
//		private Integer book_id = 0;
		/** 初始化组件装配组件的方法 */ 
		private void init() {
			lb_query_type = new JLabel("查询类型：");
			cb_query_type = new JComboBox<String>(new String[] { "所有借书记录", "未还借书记录",
					"已还借书记录"});
			btn_query = new JButton("查    询");
			btn_return = new JButton("还     书");
			btn_exit = new JButton("退     出");

			table = new JTable();
			panel_left = new JScrollPane(table);

			panel_right = new JPanel(new GridLayout(7, 1, 0, 20));
			panel_right.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createRaisedBevelBorder(), "查询条件"));
			panel_right.add(lb_query_type);
			panel_right.add(cb_query_type);
			panel_right.add(btn_query);
			panel_right.add(btn_return);
			panel_right.add(new JLabel());
			panel_right.add(new JLabel());
			panel_right.add(btn_exit);

			panel_common = new JPanel(new BorderLayout());
			panel_common.add(panel_left, BorderLayout.CENTER);
			panel_common.add(panel_right, BorderLayout.EAST);

			this.add(panel_common);
		}
		
		
		/** 构造方法 */
		public AdminQueryRecordView() {
			init();
//			registerListener();
			this.setTitle("管理员查询借阅记录窗体");
			this.setSize(679,540);
			//设置窗体可以关闭
			this.setClosable(true);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置默认的关闭操作，释放内存空间
			this.setIconifiable(true);// 窗体能否最小化
			this.setVisible(true);//显示
		}
}
