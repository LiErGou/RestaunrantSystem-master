package guiPackage.administrator;

import guiPackage.DBUtil;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuSale extends JFrame {

	String sql = "select menuname,sum(num*price) as zonghe from orderdetailtbl group by menuId order by zonghe desc";
	DBUtil util = new DBUtil();
	Connection con = util.openConnection();
	JPanel panel = (JPanel) getContentPane();
	JLabel label = new JLabel();
	JLabel label1;
	ImageIcon background = new ImageIcon("image\\d.jpg");
	JLabel label2;

	MenuSale() {
		int width = 700;
		int height = 700;
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);

			}

		});
		setSize(width, height);
		label2 = new JLabel(background);
		label2.setBounds(0, 0, width, height);
		panel.setOpaque(false);
		getLayeredPane().setLayout(null);
		getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));
		setLayout(null);
		try {
			List<MenuBean> menuList = new ArrayList<MenuBean>();
			// Map <String,Object>map=new Map<String,Object>();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			panel.setLayout(null);
			panel.setSize(width, height);
			while (rs.next()) {
				MenuBean menuBean = new MenuBean();
				menuBean.setname(rs.getString(1));
				menuBean.setprice(rs.getInt(2));
				menuList.add(menuBean);
			}
			MenuBean d = new MenuBean();
			JLabel l1 = new JLabel("菜名");
			JLabel l2 = new JLabel("销售额(元)");
			l1.setFont(new Font("", 1, 20));
			l2.setFont(new Font("", 1, 20));
			l1.setBounds(220, 50, 120, 30);
			l2.setBounds(340, 50, 120, 30);
			panel.add(l1);
			panel.add(l2);
			for (int i = 0; i < menuList.size(); i++) {
				String menuName = menuList.get(i).getname();
				String menuPrice = String.valueOf(menuList.get(i).getprice());
				label = new JLabel(menuName);
				label1 = new JLabel(menuPrice);
				label.setFont(new Font("", 1, 20));
				label1.setFont(new Font("", 1, 20));
				label.setBounds(220, 90 + i * 30, 120, 30);
				label1.setBounds(340, 90 + i * 30, 120, 30);
				panel.add(label);
				panel.add(label1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(con);
		}
		setVisible(true);
		setResizable(false);
	}

}
