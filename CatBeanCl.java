package zy.model;

import java.sql.*;
import java.util.*;

public class CatBeanCl {
	private Statement sm=null;//PreparedStatement
	private ResultSet rs=null;
	private Connection ct=null;
	
	private int pageSize = 6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来

	//7.删除商品
	
		public boolean delCatById(String C_ID) {
			boolean b = false;
			try {
				//得到连接
				ct = new ConDB().getConn();
				sm = ct.createStatement();
				//执行 a很重要 代表受影响的行数 返回1
				int a = sm.executeUpdate("delete from category where C_ID='"+C_ID+"'");
				if(a==1) {
					//删除成功
					b=true;
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				this.close();
			}
			return b;
		
			
			
		}
		//6.修改商品
		public boolean updateCat(String C_ID,String C_Name) {
			boolean b = false;
			try {
				//得到连接
				ct = new ConDB().getConn();
				sm = ct.createStatement();
				//执行 a很重要 代表受影响的行数 返回1
				int a = sm.executeUpdate("update category set C_Name='"+C_Name+"' where C_ID='"+C_ID+"'");

																																																			
				if(a==1) {
					//更新成功
					b=true;
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				this.close();
			}
			return b;	
		}
		
	
	//5.添加商品
		public boolean addCat(String C_ID,String C_Name) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("insert into category values('"+C_ID+"','"+C_Name+"')");
			if(a==1) {
				//添加成功
				b=true;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return b;

	}
		

	
	//4.返回分页的总页数
	public int getCatPageCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from category " );
	
			//请注意，一定要rs.nest
			if(rs.next()){
				rowCount = rs.getInt(1);
			}
			//计算pageCount,这里的算法很多，可以自己设计
			if(rowCount%pageSize==0){
				pageCount = rowCount/pageSize;
			}
			else{
				pageCount = rowCount/pageSize+1;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		
		return pageCount;
	}
	
	
	//3.得到用户需要显示的用户信息函数（分页）
	public ArrayList getCatByPage(int pageNow) {	
		ArrayList Cal = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
			rs=sm.executeQuery("select * from category limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				CatBean cb = new CatBean();
				cb.setC_ID(rs.getString(1));
				cb.setC_Name(rs.getString(2));

				
				//将eb放入到ArrayList中去
				Cal.add(cb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return Cal;		
	}
	

	
	//2.关闭资源函数
	public void close() {
		try {
			if(rs!=null) {
				rs.close();
				rs=null;
			}
			if(sm!=null) {
				sm.close();
				sm=null;
			}
			if(ct!=null) {
				ct.close();
				ct=null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	
	
	
}
