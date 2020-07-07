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
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;

public class FrmChangemerchat extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelName = new JLabel("���ƣ�");
	private JLabel labelStar = new JLabel("�Ǽ���");
	
	private JTextField edtName = new JTextField(20);
	private JTextField edtStar = new JTextField(20);
	
	public FrmChangemerchat (Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelStar);
		workPane.add(edtStar);
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
				if(merchatManager.currentmerchat==null) throw new BusinessException("δָ���̼�");
				
				if(!("".equals(edtName.getText()))) {
					String name=this.edtName.getText();
					wm_assistantUtil.merchatmanager.changeMerchatname(merchatManager.currentmerchat, name);
				}
				if(!("".equals(edtStar.getText()))) {
					if(wm_assistantUtil.isNumericZidai(edtStar.getText())==false) {
						throw  new BusinessException("�Ǽ�ֻ�������� ");
					}
					int star =Integer.parseInt(this.edtStar.getText());
					wm_assistantUtil.merchatmanager.changeMerchatstar(merchatManager.currentmerchat, star);
					
				}
				
				merchatManager.currentmerchat=null;
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
	
}
	
	



