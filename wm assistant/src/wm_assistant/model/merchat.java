package wm_assistant.model;

public class merchat {
	private int merchat_no;
	private String merchat_name;
	private int merchat_star;
	private int merchat_conmus;
	private int merchat_sell;
	public int getMerchat_no() {
		return merchat_no;
	}
	public void setMerchat_no(int metchat_no) {
		this.merchat_no = metchat_no;
	}
	public String getMerchat_name() {
		return merchat_name;
	}
	public void setMerchat_name(String merchat_name) {
		this.merchat_name = merchat_name;
	}
	public int getMerchat_star() {
		return merchat_star;
	}
	public void setMerchat_star(int merchat_star) {
		this.merchat_star = merchat_star;
	}
	public int getMerchat_conmus() {
		return merchat_conmus;
	}
	public void setMerchat_conmus(int merchat_conmus) {
		this.merchat_conmus = merchat_conmus;
	}
	public int getMerchat_sell() {
		return merchat_sell;
	}
	public void setMerchat_sell(int merchat_sell) {
		this.merchat_sell = merchat_sell;
	}
	
	public static final String[] tableTitles={"���","����","�Ǽ�","�˾�����","������"};
	/**
	 * �����и���javabean������޸ı��������룬col��ʾ�������е�����ţ�0��ʼ
	 */
	public String getCell(int col){
		if(col==0) return String.valueOf(merchat_no);
		else if(col==1) return merchat_name;
		else if(col==2) return String.valueOf(merchat_star);
		else if(col==3) return String.valueOf(merchat_conmus);
		else if(col==4) return String.valueOf(merchat_sell);
		else return "";
	}
	
	public static final String[] tableTitlesforuser={"����","�Ǽ�","�˾�����","������"};
	/**
	 * �����и���javabean������޸ı��������룬col��ʾ�������е�����ţ�0��ʼ
	 */
	public String getCelluser(int col){
		if(col==0) return merchat_name;
		else if(col==1) return String.valueOf(merchat_star);
		else if(col==2) return String.valueOf(merchat_conmus);
		else if(col==3) return String.valueOf(merchat_sell);
		else return "";
	}
	

}
