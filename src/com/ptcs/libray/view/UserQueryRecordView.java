package com.ptcs.libray.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.ptcs.library.dao.DAOFactory;
import com.ptcs.library.dao.ifac.RecordDaoIfac;
import com.ptcs.library.entity.Record;
import com.ptcs.library.entity.User;
/**
 * 用户查询借阅记录
 * @author xianxian 
 *
 */
public class UserQueryRecordView extends JInternalFrame{
	//窗体中功能的实现依赖底层的dao，所以属性依赖
	private RecordDaoIfac recordDao = DAOFactory.getRecordDaoInstance();
	private User user;
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
//	private Integer book_id = 0;
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
	
	
	private void registerListener() { 
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击
				//1、获取用户选定的图书的id，记录id
				int rowIndex = table.getSelectedRow();
				record_id = (int) table.getValueAt(rowIndex, 0);
				book_id = (int) table.getValueAt(rowIndex, 1);
				System.out.println("record_id:"+record_id+",book_id:"+book_id);
			}
		});
		btn_query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("111");
				/**
				 * 思路：1、记录是什么样的查什么样的，这样没错的
				 *     2、照顾用户体验 user_id不需要显示，因为是自己借的书，
				 *     book最好把书名显示出来;归还时间最好改居是否还书
				 *     3、考虑后面的还书功能实现 起来方便，所以表中最好把
				 *     record_id book_id都显示出来
			     * user_id book_id lend_time return_time 4 13 5 2019/5/27
				 * 14:18:31 5 13 4 2019/5/27 15:14:34 111 13 6 2019/5/2 2019/5/5
				 * 110 12 4 2019/5/14 2019/5/16 109 11 3 2019/5/13 108 14 2
				 * 2019/5/6
				 * 
	             * record_id user_id book_id book_name lend_time 是否归还 4 13 5
				 * 2019/5/27 已还 5 13 4 2019/5/27 已还 111 13 6 2019/5/2 已还 110 12
				 * 4 2019/5/14 已还 109 11 3 2019/5/13 未还 108 14 2 2019/5/6 未还
				 */
				
				/**
				 * 查询功能实现
				 * 1、获取查询类型
				 * 2、调用底层dao的查询方法查询记录集合
				 * 3、将集合数据显示到table控件中
				 *    3.1 需要先定义数据模型
				 * 
				 */
				//把刚才选的借阅记录编号清空
				record_id = 0;
				book_id = 0;
				int type = cb_query_type.getSelectedIndex();//值从0开始
				List<Record> records = null;
				
				switch (type) {
					case 0:
						records = recordDao.queryAllRecord(user);
						break;
					case 1:
						records = recordDao.queryAllNotReturnRecord(user);
						break;
					case 2:
						records = recordDao.queryAllReturnRecord(user);
						break;
					default:
						break;
				}
				System.out.println("records:"+records.toString());
				RecordModel model = new RecordModel(records);
				table.setModel(model);
				
			}
		});
		btn_return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("222");
				//1、获取用户选定的图书的id，记录id能过给table控件注册侦听器获取
				
				//2、对id进行非空检验
				if(record_id == 0) {
					JOptionPane.showMessageDialog(null, "请选择要还的书");
					return;
				}
				
				//3、调用底层dao完成还书功能并提示信息
				boolean result = recordDao.returnBook(record_id,book_id);
				if(result) {
					JOptionPane.showMessageDialog(null, "还书成功");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "还书失败");
					return;
				}
			}
		});
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("333");
				UserQueryRecordView.this.dispose();//关闭当前窗口
			}
		});
		
	}
	/** 构造方法 */
	public UserQueryRecordView(User user) {
		this.user = user;
		init();
		registerListener();
		this.setTitle("用户查询借阅记录窗体");
		this.setSize(679,540);
		//设置窗体可以关闭
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置默认的关闭操作，释放内存空间
		this.setIconifiable(true);// 窗体能否最小化
		this.setVisible(true);//显示
	}
	//定义显示图书数据表格模型，也是一个内部类
	private class RecordModel implements TableModel{

		private List<Record> records;
		  
		public RecordModel(List<Record> records)
		{
			this.records = records;
		}
		@Override
		public int getRowCount() {
			return records.size();
		}

		@Override
		public int getColumnCount() {
			return 5;//5列：record_id book_id book_name lend_time 是否归还 
		}

		@Override//3
		public String getColumnName(int columnIndex) {
			if(columnIndex == 0) {
				return "记录编号";
			}else if(columnIndex == 1) {
				return "图书编号";
			}else if(columnIndex == 2) {
				return "图书名称";
			}else if(columnIndex == 3){
				return "借书时间";
			}else {
				return "是否已经归还";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;//第一列的数据类型
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override//4
		public Object getValueAt(int rowIndex, int columnIndex) {
			//首先获取当前行的数据：record
			Record record = records.get(rowIndex);//rowIndex从0开始 相当于集合中元素索引
			if(columnIndex == 0) {
				return record.getRecordId();
			}else if(columnIndex == 1) {
				return record.getBook().getBookId();
			}else if(columnIndex == 2) {
				return record.getBook().getBookName();
			}else if(columnIndex == 3){
				return record.getLendTime();
			}else {
				return record.getReturnTime() == null?"未还":"已还";
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
		}
		
	}
	
	
}
