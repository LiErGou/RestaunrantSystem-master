package guiPackage.administrator;

import guiPackage.CustomerMainPageGUI;
import guiPackage.DBUtil;

import java.awt.*;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DishView {
	JButton deleteBt = new JButton();
	JButton addBt = new JButton();
	
	public DishView() {
		String[] s = { "", "", "", "", "" };
		int width = 400;
		int height = 300;
		final JFrame f = new JFrame("菜品清单");
		JPanel p = new JPanel();
		Container contentPane = f.getContentPane();
		JList list1 = new JList(s);
		list1.setBorder(BorderFactory.createTitledBorder("菜品清单"));
		/*
		 * 设置在JList中画上图像。在此参数中，我们产生一个CellRenderer对象，此对象实作了ListCellRenderer
		 * interface,因此可以返回一个ListCellRenderer对象当作setCellRenderer()方法的参数.
		 */
		final CellRenderer cr = new CellRenderer();
		list1.setCellRenderer(cr);
		deleteBt.setText("删除菜品");
		deleteBt.setSize(50, 20);
		addBt.setText("增加菜品");
		addBt.setSize(50, 20);
		p.add(deleteBt);
		p.add(addBt);
		contentPane.add(new JScrollPane(list1));
		contentPane.add(p, BorderLayout.SOUTH);
		deleteBt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt, cr);
			}
		});
		addBt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}

			
		});

		List<DishMenu> dishList = getMenuList();
		// System.out.println(getPath());
		DefaultListModel dlm = new DefaultListModel();
		String picPath = getPath();
		for (DishMenu dish : dishList) {
			dlm.addElement(new CellInfo(
					picPath + "\\" + dish.getPic() + ".png", dish.getName(),
					dish.getId()));
		}

		list1.setModel(dlm);

		f.pack();
		f.setBounds(10, 10, width, height);
		f.show();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
			}
		});
	}
	private void jButton2ActionPerformed(ActionEvent evt) {
		  ModifyFoodItem adminPage = new ModifyFoodItem();
	        adminPage.setVisible(true);
	        
		
	}
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,
			CellRenderer cr) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		
		Object[] options ={ "确定", "取消" };  
		int response=JOptionPane.showOptionDialog(null, "确认删除", "标题",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(response==0){
			if (cr.dishItem != null) {
				int id = cr.dishItem.id;
				System.out.println(cr.dishItem.iconName);
				delete(id);
			}
		}
		if(response==1){
			System.out.println("取消");
		}
	}

	private boolean delete(int id) {
		String sql = " delete from menutbl where id = ? ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, id);
			// 执行更新
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return false;
	}

	private String getPath() {
		String sqlWork = " select topath " + " from topathtbl " + " where id=?";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sqlWork);
			// 设置查询参数
			pstmt.setInt(1, 1);
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
			// 判断用户是否存在
			if (rs.next()) {
				// 获得用户信息
				String topath = rs.getString(1)+"\\WebRoot\\WEB-INF\\classes\\image";
				// 封装用户信息
				return topath;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

	public List<DishMenu> getMenuList() {
		// 查询SQL语句
		String sql = " select id,typeId,name,price,pic,remark,discribe,grade"
				+ " from menutbl order by grade desc";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			List<DishMenu> list = new ArrayList<DishMenu>();
			DishMenu menu;
			while (rs.next()) {
				int id = rs.getInt(1);
				int typeId = rs.getInt(2);
				String name = rs.getString(3);
				int price = rs.getInt(4);
				String pic = rs.getString(5);
				String remark = rs.getString(6);
				String discribe = rs.getString(7);
				int graded = rs.getInt(8);
				menu = new DishMenu(id, price, typeId, name, pic, remark,
						discribe, graded);
				list.add(menu);

			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

	public static void main(String args[]) {
		new DishView();
	}

}

class CellRenderer extends JLabel implements ListCellRenderer {
	/*
	 * 类CellRenderer继承JLabel并实作ListCellRenderer.由于我们利用JLabel易于插图的特性，
	 * 因此CellRenderer继承了JLabel可让JList中的每个项目都视为是一个JLabel.
	 */
	CellInfo dishItem = null;

	CellRenderer() {
		setOpaque(true);
	}

	/* 从这里到结束：实作getListCellRendererComponent()方法 */
	public Component getListCellRendererComponent(JList list, Object Ovalue,
			int index, boolean isSelected, boolean cellHasFocus) {
		/*
		 * 我们判断list.getModel().getElementAt(index)所返回的值是否为null,例如上个例子中，若JList的标题是
		 * "你玩过哪些数据库软件"，则index>=4的项目值我们全都设为null.而在这个例子中，因为不会有null值，因此有没有加上这个判
		 * 断并没有关系.
		 */
		CellInfo value = (CellInfo) Ovalue;
		if (value != null) {
			setText(value.iconName);
			ImageIcon image = new ImageIcon(value.svgPath);
			image.setImage(image.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			setIcon(image);
			setSize(20, 20);
		}
		if (isSelected) {
			dishItem = value;
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			// 设置选取与取消选取的前景与背景颜色.
			// System.out.println("取消选中");
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}
}
