package guiPackage.administrator;

import java.awt.*;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;



import java.util.Vector;

public class test2 {
	JButton bt=new JButton();
	
	public test2() {
		String[] s = { "西瓜", "苹果", "草莓", "香蕉", "葡萄" };
		int width=400;
		int height=300;
		JFrame f = new JFrame("JList");
		JPanel p=new JPanel();
		Container contentPane = f.getContentPane();
		JList list1 = new JList(s);
		list1.setBorder(BorderFactory.createTitledBorder("您喜欢吃哪些水果？"));
		/*
		 * 设置在JList中画上图像。在此参数中，我们产生一个CellRenderer对象，此对象实作了ListCellRenderer
		 * interface,因此可以返回一个ListCellRenderer对象当作setCellRenderer()方法的参数.
		 */
		list1.setCellRenderer(new CellRenderer());
		bt.setText("删除菜品");
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
	 * 类CellRenderer继承JLabel并实作ListCellRenderer.由于我们利用JLabel易于插图的特性，
	 * 因此CellRenderer继承了JLabel可让JList中的每个项目都视为是一个JLabel.
	 */
	CellRenderer() {
		setOpaque(true);
	}

	/* 从这里到结束：实作getListCellRendererComponent()方法 */
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		/*
		 * 我们判断list.getModel().getElementAt(index)所返回的值是否为null,例如上个例子中，若JList的标题是
		 * "你玩过哪些数据库软件"，则index>=4的项目值我们全都设为null.而在这个例子中，因为不会有null值，因此有没有加上这个判
		 * 断并没有关系.
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
			// 设置选取与取消选取的前景与背景颜色.
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}
}


