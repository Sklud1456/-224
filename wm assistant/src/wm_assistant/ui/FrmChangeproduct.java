package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wm_assistant.begin.wm_assistantUtil;
import wm_assistant.contorl.merchatManager;
import wm_assistant.contorl.productManager;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;

public class FrmChangeproduct extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelName = new JLabel("���ƣ�");
	private JLabel labelPrice = new JLabel("�۸�");
	private JLabel labelSellprice = new JLabel("��Ա�ۣ�");
	
	private JTextField edtName = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private JTextField edtSellprice = new JTextField(20);
	
	public FrmChangeproduct (Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		workPane.add(labelSellprice);
		workPane.add(edtSellprice);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			try {
				if(productManager.currentproduct==null) throw new BusinessException("δָ����Ʒ");
				
				if(!("".equals(edtName.getText()))) {
					String name=edtName.getText();
					wm_assistantUtil.productmanager.changePname(productManager.currentproduct, name);
				}
				if(!("".equals(edtPrice.getText()))) {
					double price=Double.parseDouble(edtPrice.getText());
					if(wm_assistantUtil.isNumericZidai(edtPrice.getText())==false) {
						throw  new BusinessException("�Ǽ�ֻ�������� ");
					}
					wm_assistantUtil.productmanager.changePprice1(productManager.currentproduct, price);
				}
				if(!("".equals(edtSellprice.getText()))) {
					double price=Double.parseDouble(edtSellprice.getText());
					if(wm_assistantUtil.isNumericZidai(edtSellprice.getText())==false) {
						throw  new BusinessException("�Ǽ�ֻ�������� ");
					}
					wm_assistantUtil.productmanager.changePprice2(productManager.currentproduct, price);
				}
				
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}

}
