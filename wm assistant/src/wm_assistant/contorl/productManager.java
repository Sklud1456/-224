package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wm_assistant.model.merchat;
import wm_assistant.model.product;
import wm_assistant.model.productsort;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;

public class productManager {
	public static product currentproduct=null;
	public void addproduct(productsort ps, String name,double price1,double price2) throws BaseException {
		if("".equals(name)||name==null) throw new BusinessException("��Ʒ������Ϊ��");
		if(price1<0||price2<0) throw new BusinessException("�۸񲻿�Ϊ����");
		if(price1<price2) throw new BusinessException("�Żݼ۸񲻿��Ա���ͨ�۸��");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product where productsort_no=? and product_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,ps.getProductsort_no());
			pst.setString(2,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("��Ʒ���Ѵ���");
			}
			
			sql="insert into product(productsort_no,product_name,product_price,product_sellprice) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,ps.getProductsort_no());
			pst.setString(2, name);
			pst.setDouble(3, price1);
			pst.setDouble(4, price2);
			pst.executeUpdate();
			pst.close();
			
			sql="update productsort set productsort_number=productsort_number+1 where productsort_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, ps.getProductsort_no());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
	}
	
	public List<product> loadproduct(productsort ps) throws BaseException {
		List<product> result=new ArrayList<product>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product where productsort_no=? order by product_no asc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, ps.getProductsort_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				product p=new product();
				p.setProduct_no(rs.getInt(1));
				p.setProductsort_no(rs.getInt(2));
				p.setProduct_name(rs.getString(3));
				p.setProduct_price(rs.getDouble(4));
				p.setProduct_sellprice(rs.getDouble(5));
				result.add(p);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	public void changePname(product p,String name)throws BaseException{
		if("".equals(name)||name==null) throw new BusinessException("��Ʒ������Ϊ��");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product where product_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getProduct_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("����Ʒ������");
			else {
				rs.close();
				pst.close();
				sql="update product set product_name=? where product_no=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, p.getProduct_no());
				pst.executeUpdate();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void changePprice1(product p,double price1)throws BaseException{
		if(price1<0) throw new BusinessException("�۸񲻿�Ϊ����");

		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product where product_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getProduct_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("����Ʒ������");
			else {
				double p2=rs.getDouble(5);
				if(price1<p2) throw new BusinessException("��ͨ�۸񲻿��Ա��Żݼ۸��");
				rs.close();
				pst.close();
				sql="update product set product_price=? where product_no=?";
				pst=conn.prepareStatement(sql);
				pst.setDouble(1, price1);
				pst.setInt(2, p.getProduct_no());
				pst.executeUpdate();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void changePprice2(product p,double price)throws BaseException{
		if(price<0) throw new BusinessException("�۸񲻿�Ϊ����");

		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product where product_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getProduct_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("����Ʒ������");
			else {
				double p2=rs.getDouble(4);
				if(price>p2) throw new BusinessException("�Żݼ۸񲻿��Ա���ͨ�۸��");
				rs.close();
				pst.close();
				sql="update product set product_sellprice=? where product_no=?";
				pst=conn.prepareStatement(sql);
				pst.setDouble(1, price);
				pst.setInt(2, p.getProduct_no());
				pst.executeUpdate();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void deleteproduct(product p) throws BaseException {
		Connection conn=null;
		try {
	
			conn=DBUtil.getConnection();
			String sql="select * from product where product_no=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,p.getProduct_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("����Ʒ������");
			}
			rs.close();
			sql="delete from product where product_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,p.getProduct_no());
			pst.executeUpdate();
			pst.close();
			
			sql="update productsort set productsort_number=productsort_number-1 where productsort_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,p.getProductsort_no());
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
	}

	
	public static void main(String[] args) {
		productManager a=new productManager();
		List<product> b=new ArrayList<product>();
	}

	

}
