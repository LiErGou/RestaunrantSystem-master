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
		final JFrame f = new JFrame("��Ʒ�嵥");
		JPanel p = new JPanel();
		Container contentPane = f.getContentPane();
		JList list1 = new JList(s);
		list1.setBorder(BorderFactory.createTitledBorder("��Ʒ�嵥"));
		/*
		 * ������JList�л���ͼ���ڴ˲����У����ǲ���һ��CellRenderer���󣬴˶���ʵ����ListCellRenderer
		 * interface,��˿��Է���һ��ListCellRenderer������setCellRenderer()�����Ĳ���.
		 */
		final CellRenderer cr = new CellRenderer();
		list1.setCellRenderer(cr);
		deleteBt.setText("ɾ����Ʒ");
		deleteBt.setSize(50, 20);
		addBt.setText("���Ӳ�Ʒ");
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
		
		Object[] options ={ "ȷ��", "ȡ��" };  
		int response=JOptionPane.showOptionDialog(null, "ȷ��ɾ��", "����",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(response==0){
			if (cr.dishItem != null) {
				int id = cr.dishItem.id;
				System.out.println(cr.dishItem.iconName);
				delete(id);
			}
		}
		if(response==1){
			System.out.println("ȡ��");
		}
	}

	private boolean delete(int id) {
		String sql = " delete from menutbl where id = ? ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, id);
			// ִ�и���
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
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sqlWork);
			// ���ò�ѯ����
			pstmt.setInt(1, 1);
			// ִ�в�ѯ
			ResultSet rs = pstmt.executeQuery();
			// �ж��û��Ƿ����
			if (rs.next()) {
				// ����û���Ϣ
				String topath = rs.getString(1)+"\\WebRoot\\WEB-INF\\classes\\image";
				// ��װ�û���Ϣ
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
		// ��ѯSQL���
		String sql = " select id,typeId,name,price,pic,remark,discribe,grade"
				+ " from menutbl order by grade desc";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
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
	 * ��CellRenderer�̳�JLabel��ʵ��ListCellRenderer.������������JLabel���ڲ�ͼ�����ԣ�
	 * ���CellRenderer�̳���JLabel����JList�е�ÿ����Ŀ����Ϊ��һ��JLabel.
	 */
	CellInfo dishItem = null;

	CellRenderer() {
		setOpaque(true);
	}

	/* �����ﵽ������ʵ��getListCellRendererComponent()���� */
	public Component getListCellRendererComponent(JList list, Object Ovalue,
			int index, boolean isSelected, boolean cellHasFocus) {
		/*
		 * �����ж�list.getModel().getElementAt(index)�����ص�ֵ�Ƿ�Ϊnull,�����ϸ������У���JList�ı�����
		 * "�������Щ���ݿ����"����index>=4����Ŀֵ����ȫ����Ϊnull.������������У���Ϊ������nullֵ�������û�м��������
		 * �ϲ�û�й�ϵ.
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
			// ����ѡȡ��ȡ��ѡȡ��ǰ���뱳����ɫ.
			// System.out.println("ȡ��ѡ��");
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}
}
