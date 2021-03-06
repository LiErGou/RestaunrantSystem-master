/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guiPackage.administrator;

import guiPackage.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classPackage.User;
import classPackage.RestaurantSystem;
import javax.swing.JOptionPane;

/**
 * 
 * @author Meng
 */
public class ChangeCustomerInfo extends javax.swing.JFrame {
	User c;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Creates new form ChangeCustomerInfo
	 */
	public ChangeCustomerInfo() {
		initComponents();
		c = null;
	}

	public void setCustomer(User c) {
		this.c = c;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		
		jTextField2 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

//		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("用户姓名");

//		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("密         码");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		

//		jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton1.setText("设         置");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

//		jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton2.setText("设          置");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jButton1.setVisible(false);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(27, 27, 27)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														130,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(jLabel3)
												.addComponent(
														jTextField2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														134, Short.MAX_VALUE))
								.addGap(38, 38, 38)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jButton1)
												.addComponent(jButton2))
								.addContainerGap(80, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(93, 93, 93)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														36,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jLabel3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														41,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton1))
								.addGap(29, 29, 29)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														jTextField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton2))
								.addContainerGap(133, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
//		if (jTextField1.getText().getBytes().length > 0) {
//			c.setName(jTextField1.getText());
//			RestaurantSystem.writeCustomerListToFile();
//			JOptionPane.showMessageDialog(null, "Set successfully");
//		} else {
//			JOptionPane.showMessageDialog(null, "Input wrong");
//		}

	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
		if (jTextField2.getText().getBytes().length > 0) {
			boolean isSuccess=setPassword(jTextField2.getText(), id);
			if(isSuccess){
				JOptionPane.showMessageDialog(null, "修改成功");
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}// GEN-LAST:event_jButton2ActionPerformed

	private boolean setPassword(String password, int id) {
		// 更新SQL语句
		String sql2 = " update guesttbl set password = ? where id = ? ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		System.out.println("id="+id);
		try {
			
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql2);

			// 设置参数
			pstmt.setString(1, password);
			pstmt.setInt(2, id);
			// 更新订单表
			pstmt.executeUpdate();

			
			return true;
		} catch (SQLException e) {// 当try语句中出现异常是时，会执行catch中的语句，java运行时系统会自动将catch括号中的Exception
									// e
									// 初始化，也就是实例化Exception类型的对象。e是此对象引用名称。然后e（引用）会自动调用Exception类中指定的方法，也就出现了e.printStackTrace()
									// ;。
									// ,printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因
			e.printStackTrace();
			try {
				conn.rollback();// 回滚，当conn.commit()失败时回滚从而保证数据库的完整性，避免表在没有提交也没有回滚的情况下锁死
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.closeConn(conn);
		}
		return false;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					ChangeCustomerInfo.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					ChangeCustomerInfo.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					ChangeCustomerInfo.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					ChangeCustomerInfo.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ChangeCustomerInfo().setVisible(true);
			}
		});
	}

	public void setJTextField1(String text) {
		jLabel3.setText(c.getAccount());
	}

	public void setJTextField2(String text) {
		jTextField2.setText(text);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField jTextField2;
	// End of variables declaration//GEN-END:variables
}
