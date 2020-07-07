package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.Button;
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
import wm_assistant.contorl.productsortManager;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;

public class FrmAddproduct  extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("名称：");
	private JLabel labelprice = new JLabel("价格：");
	private JLabel labelsellprice = new JLabel("会员价：");
	
	private JTextField edtName = new JTextField(20);
	private JTextField edtprice = new JTextField(20);
	private JTextField edtsellprice = new JTextField(20);
	
	public FrmAddproduct(FrmMerchatforGM frmMerchatforGM, String s, boolean b) {
		super(frmMerchatforGM, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelprice);
		workPane.add(edtprice);
		workPane.add(labelsellprice);
		workPane.add(edtsellprice);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			double price1= Double.parseDouble(this.edtprice.getText());
			double price2= Double.parseDouble(this.edtsellprice.getText());
			
			try {
				if(merchatManager.currentmerchat==null) throw  new BusinessException("请选择种类");
				else {
					wm_assistantUtil.productmanager.addproduct(productsortManager.currentproductsort, name, price1, price2);
					this.setVisible(false);
				}
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}

}
