package Gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test implements ActionListener {
	JFrame f;// ��Ҫ�Ŀ��
	JPanel da;// �����ֲ�
	JPanel g1;// �Ϸ������
	JScrollPane g2;// �����й����������
	JLabel b1;// �ĸ���ǩ
	JLabel b2;
	JLabel b3;
	JLabel b4;
	JTextField text;// �����������ֵ��ı���
	JPanel bu;// ������������ѡ��
	JRadioButton button1;// ������ѡ��
	JRadioButton button2;
	JPanel bo;// ������������ѡ��
	JCheckBox box1;// ������ѡ��
	JCheckBox box2;
	JCheckBox box3;
	JComboBox xia;// һ��������
	String a[];
	JButton ok;// ȷ����ť
	JTextArea o;

	public Test() {
		// ʵ������
		JFrame f = new JFrame("��һ�¿���ô��");
		JPanel g1 = new JPanel();
		JScrollPane g2 = new JScrollPane();
		JLabel b1 = new JLabel("������");
		JLabel b2 = new JLabel("�Ա�");
		JLabel b3 = new JLabel("���ã�");
		JLabel b4 = new JLabel("����");
		JTextField text = new JTextField();
		JPanel bu = new JPanel();
		JRadioButton button1 = new JRadioButton("��");
		JRadioButton button2 = new JRadioButton("Ů");
		bu.add(button1);
		bu.add(button2);
		JPanel bo = new JPanel();
		JCheckBox box1 = new JCheckBox("����");
		JCheckBox box2 = new JCheckBox("����");
		JCheckBox box3 = new JCheckBox("����");
		bo.add(box1);
		bo.add(box2);
		bo.add(box3);
		String a[] = { "ok?", "0000" };
		JComboBox xia = new JComboBox(a);
		g1.setLayout(new GridLayout(4, 2));
		g1.add(b1);
		g1.add(text);
		g1.add(b2);
		g1.add(bu);
		g1.add(b3);
		g1.add(bo);
		g1.add(b4);
		g1.add(xia);
		JTextArea o = new JTextArea();
		g2.add(o);
		JButton ok = new JButton("ȷ��");
		// g2.add(ok);
		JPanel da = new JPanel();
		da.setLayout(new GridLayout(3, 1));
		da.add(g1);
		da.add(g2);
		da.add(ok);
		ok.addActionListener(this);
		// �������
		f.add(da);
		f.setLocation(500, 500);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		Test r = new Test();
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(text.getText());
	}
}
