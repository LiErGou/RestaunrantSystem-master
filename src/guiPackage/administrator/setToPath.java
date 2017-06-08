package guiPackage.administrator;

import guiPackage.DBUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class setToPath {
    public static void main(String[] args) {
        ToPathFrame f=new ToPathFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
 
class ToPathFrame extends JFrame{
    public ToPathFrame(){
        setSize(500,300);
        setLocation(150,200);
        setTitle("图片路径修改");
        setResizable(false);
         
        ToPathPanel p=new ToPathPanel();
        this.add(p);
    }
}
 
class ToPathPanel extends JPanel{
    private JLabel label1=new JLabel("目的路径");
    private JTextField text1=new JTextField(30);
    private JButton b1=new JButton("确定");
    public ToPathPanel(){
        setLayout(null);
        label1.setBounds(60, 50, 100, 30);
        text1.setBounds(190, 50, 200, 30);
        b1.setBounds(150,100,100,40);
        add(label1);
        add(text1);
        add(b1);
        b1.addActionListener(new ActionListener() {
     

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success=writeToDb(text1.getText());
				if(success=true){
					JOptionPane.showMessageDialog(null, "设置图片目的路径成功！");
				}else{
					JOptionPane.showMessageDialog(null, "设置图片目的路径失败！");
				}
				
			}
        });
    }
    public boolean writeToDb(String path){
    	// 更新SQL语句
    			String sql2 = " update topathtbl set topath = ? where id = 1 ";
    			// 数据库连接工具类
    			DBUtil util = new DBUtil();
    			// 获得连接
    			Connection conn = util.openConnection();
//    			System.out.println("id="+id);
    			try {
    				
    				// 获得预定义语句
    				PreparedStatement pstmt = conn.prepareStatement(sql2);

    				// 设置参数
    				pstmt.setString(1, path);
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
}