package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import wm_assistant.begin.wm_assistantUtil;
import wm_assistant.contorl.GMManager;
import wm_assistant.contorl.merchatManager;
import wm_assistant.contorl.productManager;
import wm_assistant.contorl.productsortManager;
import wm_assistant.model.merchat;
import wm_assistant.model.product;
import wm_assistant.model.productsort;
import wm_assistant.util.BaseException;

public class FrmMerchatforGM  extends JDialog implements ActionListener{
	
	private JMenuBar menubar=new JMenuBar(); ;
	private JMenu menu_merchat=new JMenu("�̼�");
	private JMenu menu_productsort=new JMenu("����");
	private JMenu menu_product=new JMenu("��Ʒ");
    private JMenu menu_youhui=new JMenu("�Ż���Ϣ");
    
    private JMenuItem  menuItem_Addmerchat=new JMenuItem("�½��̼�");
    private JMenuItem  menuItem_Changemerchat=new JMenuItem("�޸��̼�");
    private JMenuItem  menuItem_Deletemerchat=new JMenuItem("ɾ���̼�");
    private JMenuItem  menuItem_Addps=new JMenuItem("�½�����");
    private JMenuItem  menuItem_Changeps=new JMenuItem("�޸�����");
    private JMenuItem  menuItem_Deleteps=new JMenuItem("ɾ������");
    private JMenuItem  menuItem_Addproduct=new JMenuItem("�½���Ʒ");
    private JMenuItem  menuItem_Changeproduct=new JMenuItem("�޸���Ʒ");
    private JMenuItem  menuItem_Deleteproduct=new JMenuItem("ɾ����Ʒ");
    private JMenuItem  menuItem_Fresh=new JMenuItem("ˢ��");
    
    private JPanel statusBar = new JPanel();
    
    private Object merchatTitle[]=merchat.tableTitles;
	private Object merchatData[][];
   	DefaultTableModel merchatModel=new DefaultTableModel();
   	private JTable dataTablemerchat=new JTable(merchatModel);
   	
   	private Object tblproductsortTitle[]=productsort.tblproductsortTitle;
   	private Object tblproductsort[][];
   	DefaultTableModel tabproductsortModel=new DefaultTableModel();
   	private JTable dataproductsort=new JTable(tabproductsortModel);

   	private Object tblproductTitle[]=product.tblproductTitle;
   	private Object tblproduct[][];
   	DefaultTableModel tabproductModel=new DefaultTableModel();
   	private JTable dataproduct=new JTable(tabproductModel);
   	
   	private merchat curmerchat=null;
   	private productsort curproductsort=null;
	private product curproduct=null;
	List<merchat> allmerchat=null;
	List<productsort> allproductsort=null;
	List<product> allproduct=null;
   	private void reloadmerchatTable(){//���ǲ������ݣ���Ҫ��ʵ�����滻
		try {
			allmerchat=merchatManager.loadMerchat();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		merchatData =  new Object[allmerchat.size()][merchat.tableTitles.length];
		for(int i=0;i<allmerchat.size();i++){
			for(int j=0;j<merchat.tableTitles.length;j++)
				merchatData[i][j]=allmerchat.get(i).getCell(j);
		}
		merchatModel.setDataVector(merchatData,merchatTitle);
		this.dataTablemerchat.validate();
		this.dataTablemerchat.repaint();
	}
   	private void reloadproductsortTabel(int merchatIdx){
		if(merchatIdx<0) return;
		curmerchat=allmerchat.get(merchatIdx);
		merchatManager.currentmerchat=curmerchat;
		try {
			allproductsort=wm_assistantUtil.productsortmanager.loadproductsort(curmerchat);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblproductsort =new Object[allproductsort.size()][productsort.tblproductsortTitle.length];
		for(int i=0;i<allproductsort.size();i++){
			for(int j=0;j<productsort.tblproductsortTitle.length;j++)
				tblproductsort[i][j]=allproductsort.get(i).getCell(j);
		}
		
		tabproductsortModel.setDataVector(tblproductsort,tblproductsortTitle);
		this.dataproductsort.validate();
		this.dataproductsort.repaint();
	}
	private void reloadproductTabel(int psIdx){
		if(psIdx<0) return;
		curproductsort=allproductsort.get(psIdx);
		productsortManager.currentproductsort=curproductsort;
		try {
			allproduct=wm_assistantUtil.productmanager.loadproduct(curproductsort);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblproduct =new Object[allproduct.size()][product.tblproductTitle.length];
		for(int i=0;i<allproduct.size();i++){
			for(int j=0;j<product.tblproductTitle.length;j++)
				tblproduct[i][j]=allproduct.get(i).getCell(j);
		}
		
		tabproductModel.setDataVector(tblproduct,tblproductTitle);
		this.dataproduct.validate();
		this.dataproduct.repaint();
	}
	public FrmMerchatforGM(Frame f, String s, boolean b) {
		this.setSize(1500, 800);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		menubar.add(menu_merchat);this.menu_merchat.addActionListener(this);
		menubar.add(menu_productsort);this.menu_productsort.addActionListener(this);
		menubar.add(menu_product);this.menu_product.addActionListener(this);
		this.menu_merchat.add(this.menuItem_Addmerchat); this.menuItem_Addmerchat.addActionListener(this);
		this.menu_merchat.add(this.menuItem_Changemerchat); this.menuItem_Changemerchat.addActionListener(this);
		this.menu_merchat.add(this.menuItem_Deletemerchat); this.menuItem_Deletemerchat.addActionListener(this);
		this.menu_productsort.add(this.menuItem_Addps); this.menuItem_Addps.addActionListener(this);
		this.menu_productsort.add(this.menuItem_Changeps); this.menuItem_Changeps.addActionListener(this);
		this.menu_productsort.add(this.menuItem_Deleteps); this.menuItem_Deleteps.addActionListener(this);
		this.menu_product.add(this.menuItem_Addproduct); this.menuItem_Addproduct.addActionListener(this);
		this.menu_product.add(this.menuItem_Changeproduct); this.menuItem_Changeproduct.addActionListener(this);
		this.menu_product.add(this.menuItem_Deleteproduct); this.menuItem_Deleteproduct.addActionListener(this);
		this.menu_merchat.add(this.menuItem_Fresh); this.menuItem_Fresh.addActionListener(this);
		
		menubar.add(menu_youhui);this.menu_youhui.addActionListener(this);
		this.setJMenuBar(menubar);
		
		this.getContentPane().add(new JScrollPane(this.dataTablemerchat), BorderLayout.WEST);
		this.reloadmerchatTable();
		this.dataTablemerchat.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMerchatforGM.this.dataTablemerchat.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMerchatforGM.this.reloadproductsortTabel(i);
			}
	    	
	    });
		this.getContentPane().add(new JScrollPane(this.dataproductsort), BorderLayout.CENTER);
		this.dataproductsort.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMerchatforGM.this.dataproductsort.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMerchatforGM.this.reloadproductTabel(i);
			}
	    	
	    });
		
	    this.getContentPane().add(new JScrollPane(this.dataproduct), BorderLayout.EAST);
	    
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("����!"+GMManager.currentGM.getGm_name());//�޸ĳ�   ���ã�+��½�û���
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_Fresh) {
			this.reloadmerchatTable();
		}
		
		
		else if(e.getSource()==this.menuItem_Addmerchat){
			FrmAddmerchat dlg=new FrmAddmerchat(this,"����̼�",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Changemerchat){
			FrmChangemerchat dlg=new FrmChangemerchat(this,"�޸��̼���Ϣ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Deletemerchat){
			if(this.curmerchat==null) {
				JOptionPane.showMessageDialog(null, "��ѡ���̼�", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				wm_assistantUtil.merchatmanager.deleteMerchat(curmerchat);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		
		else if(e.getSource()==this.menuItem_Addps){
			FrmAddproductsort dlg=new FrmAddproductsort(this,"�������",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Changeps){
			FrmChangeproductsort dlg=new FrmChangeproductsort(this,"�޸�����",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Deleteps){
			if(this.curproductsort==null) {
				JOptionPane.showMessageDialog(null, "��ѡ������", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				wm_assistantUtil.productsortmanager.deletesort(curproductsort);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		else if(e.getSource()==this.menuItem_Addproduct){
			FrmAddproduct dlg=new FrmAddproduct(this,"�����Ʒ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Changeproduct){
			int i=FrmMerchatforGM.this.dataproduct.getSelectedRow();
			productManager.currentproduct=allproduct.get(i);
			FrmChangeproduct dlg=new FrmChangeproduct(this,"�޸���Ʒ",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Deleteproduct){
			int i=FrmMerchatforGM.this.dataproduct.getSelectedRow();
			productManager.currentproduct=allproduct.get(i);
			if(this.curproduct==null) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				wm_assistantUtil.productmanager.deleteproduct(this.allproduct.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		
		
		
		
	}


}
