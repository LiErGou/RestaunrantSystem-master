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
        setTitle("ͼƬ·���޸�");
        setResizable(false);
         
        ToPathPanel p=new ToPathPanel();
        this.add(p);
    }
}
 
class ToPathPanel extends JPanel{
    private JLabel label1=new JLabel("Ŀ��·��");
    private JTextField text1=new JTextField(30);
    private JButton b1=new JButton("ȷ��");
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
					JOptionPane.showMessageDialog(null, "����ͼƬĿ��·���ɹ���");
				}else{
					JOptionPane.showMessageDialog(null, "����ͼƬĿ��·��ʧ�ܣ�");
				}
				
			}
        });
    }
    public boolean writeToDb(String path){
    	// ����SQL���
    			String sql2 = " update topathtbl set topath = ? where id = 1 ";
    			// ���ݿ����ӹ�����
    			DBUtil util = new DBUtil();
    			// �������
    			Connection conn = util.openConnection();
//    			System.out.println("id="+id);
    			try {
    				
    				// ���Ԥ�������
    				PreparedStatement pstmt = conn.prepareStatement(sql2);

    				// ���ò���
    				pstmt.setString(1, path);
    				// ���¶�����
    				pstmt.executeUpdate();
    				return true;
    			} catch (SQLException e) {// ��try����г����쳣��ʱ����ִ��catch�е���䣬java����ʱϵͳ���Զ���catch�����е�Exception
    										// e
    										// ��ʼ����Ҳ����ʵ����Exception���͵Ķ���e�Ǵ˶����������ơ�Ȼ��e�����ã����Զ�����Exception����ָ���ķ�����Ҳ�ͳ�����e.printStackTrace()
    										// ;��
    										// ,printStackTrace()��������˼�ǣ��������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
    				e.printStackTrace();
    				try {
    					conn.rollback();// �ع�����conn.commit()ʧ��ʱ�ع��Ӷ���֤���ݿ�������ԣ��������û���ύҲû�лع������������
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
    			} finally {
    				util.closeConn(conn);
    			}
    			return false;
    }
}