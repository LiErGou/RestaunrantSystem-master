package guiPackage.administrator;

import java.awt.*;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;



import java.util.Vector;

public class test2 {
	JButton bt=new JButton();
	
	public test2() {
		String[] s = { "����", "ƻ��", "��ݮ", "�㽶", "����" };
		int width=400;
		int height=300;
		JFrame f = new JFrame("JList");
		JPanel p=new JPanel();
		Container contentPane = f.getContentPane();
		JList list1 = new JList(s);
		list1.setBorder(BorderFactory.createTitledBorder("��ϲ������Щˮ����"));
		/*
		 * ������JList�л���ͼ���ڴ˲����У����ǲ���һ��CellRenderer���󣬴˶���ʵ����ListCellRenderer
		 * interface,��˿��Է���һ��ListCellRenderer������setCellRenderer()�����Ĳ���.
		 */
		list1.setCellRenderer(new CellRenderer());
		bt.setText("ɾ����Ʒ");
		bt.setSize(50, 20);
		p.add(bt);
		contentPane.add(new JScrollPane(list1));
		contentPane.add(p,BorderLayout.SOUTH);
		
		 DefaultListModel dlm = new DefaultListModel();
		 for (int i = 0; i <10; i++) {  
	            dlm.addElement(new CellInfo("C:\\Users\\Administrator\\Desktop\\1494924351(1).png", "i"));  
	        }  
	        list1.setModel(dlm);  
		
		f.pack();
		f.setBounds(10, 10, width, height);
		f.show();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String args[]) {
		new test2();
	}
	 
}  


class CellRenderer extends JLabel implements ListCellRenderer {
	/*
	 * ��CellRenderer�̳�JLabel��ʵ��ListCellRenderer.������������JLabel���ڲ�ͼ�����ԣ�
	 * ���CellRenderer�̳���JLabel����JList�е�ÿ����Ŀ����Ϊ��һ��JLabel.
	 */
	CellRenderer() {
		setOpaque(true);
	}

	/* �����ﵽ������ʵ��getListCellRendererComponent()���� */
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		/*
		 * �����ж�list.getModel().getElementAt(index)�����ص�ֵ�Ƿ�Ϊnull,�����ϸ������У���JList�ı�����
		 * "�������Щ���ݿ����"����index>=4����Ŀֵ����ȫ����Ϊnull.������������У���Ϊ������nullֵ�������û�м��������
		 * �ϲ�û�й�ϵ.
		 */
		if (value != null) {
			setText(value.toString());
			ImageIcon image=new ImageIcon("C:\\Users\\Administrator\\Desktop\\1494924351(1).png");
			image.setImage(image.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
			setIcon(image);
			setSize(20,20);
		}
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			// ����ѡȡ��ȡ��ѡȡ��ǰ���뱳����ɫ.
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}
}


