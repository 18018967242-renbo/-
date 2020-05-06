package zy.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class MerBeanCl {
	private Statement sm=null;
	private Statement ssmm=null;
	private Statement sssmmm=null;//PreparedStatement
	private ResultSet rs=null;

	private Connection ct=null;
	private Connection cctt=null;
	private Connection cccttt=null;
	private int pageSize = 6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来


	
	
	//11.返回分页的总页数
	public int MCCount(String MC) {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from merchandise where M_ID like  ('%"+MC+"%') or M_Name like  ('%"+MC+"%') or M_C_ID like  ('%"+MC+"%') or M_C_Name like  ('%"+MC+"%') or M_Specification like  ('%"+MC+"%') or M_Model like  ('%"+MC+"%') or M_Size like  ('%"+MC+"%') or M_JhPrice like  ('%"+MC+"%') or M_SellPrice like  ('%"+MC+"%') or M_MPrice like  ('%"+MC+"%') or M_Number like  ('%"+MC+"%') or M_P_ID like  ('%"+MC+"%') " );
	
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
	
	//10.得到用户需要显示的用户信息函数（分页）

	public ArrayList getMCByPage(int pageNow ,String MC) {	
		ArrayList MCal = new ArrayList();
		try {			
			ct = new ConDB().getConn();	
			cctt = new ConDB().getConn();	
			cccttt = new ConDB().getConn();	
			//3.创建
			 sm = ct.createStatement();	
			 ssmm = cctt.createStatement();
			 sssmmm = cctt.createStatement();
			//查询出需要显示的记录
			 rs = sm.executeQuery("select * from merchandise where M_ID like  ('%"+MC+"%') or M_Name like  ('%"+MC+"%') or M_C_ID like  ('%"+MC+"%') or M_C_Name like  ('%"+MC+"%') or M_Specification like  ('%"+MC+"%') or M_Model like  ('%"+MC+"%') or M_Size like  ('%"+MC+"%') or M_JhPrice like  ('%"+MC+"%') or M_SellPrice like  ('%"+MC+"%') or M_MPrice like  ('%"+MC+"%') or M_Number like  ('%"+MC+"%') or M_P_ID like  ('%"+MC+"%')  limit "+pageSize*(pageNow-1) + "," + pageSize);
			
			SimpleDateFormat ddff = new SimpleDateFormat("yyyyMMdd-HHmm");//设置日期格式
			// int pageNow = Integer.parseInt(request.getParameter("OrdMpageNow"));

			
			//开始将rs封装到ArrayList
			while(rs.next()) {
				MerBean mb = new MerBean();
				mb.setM_ID(rs.getString(1));
				mb.setM_Name(rs.getString(2));
				mb.setM_C_ID(rs.getString(3));
				mb.setM_C_Name(rs.getString(4));
				mb.setM_Specification(rs.getString(5));
				mb.setM_Model(rs.getString(6));
				mb.setM_Size(rs.getString(7));
				mb.setM_JhPrice(rs.getString(8));
				mb.setM_SellPrice(rs.getString(9));
				mb.setM_MPrice(rs.getString(10));
				mb.setM_Number(rs.getString(11));			
				mb.setM_P_ID(rs.getString(12));

				//将eb放入到ArrayList中去
				MCal.add(mb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return MCal;		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	//9.返回分页的总页数
	public int allOrdCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from merchandise where M_Number < 10 " );
	
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
	
	//8.得到用户需要显示的用户信息函数（分页）

	public ArrayList allOrd(int pageNow,String E_LoginName) {	
		ArrayList all = new ArrayList();
		try {			
			ct = new ConDB().getConn();	
			cctt = new ConDB().getConn();	
			cccttt = new ConDB().getConn();	
			//3.创建
			 sm = ct.createStatement();	
			 ssmm = cctt.createStatement();
			 sssmmm = cctt.createStatement();
			//查询出需要显示的记录
			rs=sm.executeQuery("select * from merchandise where M_Number < 10 limit "+pageSize*(pageNow-1) + "," + pageSize);
			
			SimpleDateFormat ddff = new SimpleDateFormat("yyyyMMdd-HHmm");//设置日期格式
			// int pageNow = Integer.parseInt(request.getParameter("OrdMpageNow"));

			
			//开始将rs封装到ArrayList
			while(rs.next()) {
				MerBean mb = new MerBean();
				mb.setM_ID(rs.getString(1));
				mb.setM_Name(rs.getString(2));
				mb.setM_C_ID(rs.getString(3));
				mb.setM_C_Name(rs.getString(4));
				mb.setM_Specification(rs.getString(5));
				mb.setM_Model(rs.getString(6));
				mb.setM_Size(rs.getString(7));
				mb.setM_JhPrice(rs.getString(8));
				mb.setM_SellPrice(rs.getString(9));
				mb.setM_MPrice(rs.getString(10));
				int a = Integer.parseInt(mb.setM_Number(rs.getString(11)))*10;
				
				mb.setM_P_ID(rs.getString(12));

				int rrss=ssmm.executeUpdate("insert into orderM(O_ID,O_M_ID,O_M_Name,O_M_JhPrice,O_Number) values('"+rs.getString(12)+"-"+ddff.format(new Date())+"','"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(8)+"','"+a+"')");
			
				

				try {
					int rrrsss=sssmmm.executeUpdate("insert into orderP(O_ID,O_P_ID,O_Utime,O_Dtime,O_TB,O_E_ID) values('"+rs.getString(12)+"-"+ddff.format(new Date())+"','"+rs.getString(12)+"','"+ddff.format(new Date())+"','"+"未审核"+"','"+"未审核"+"','"+E_LoginName+"')");
				}
				catch(Exception ex) {
					//注意不可少
					ex.printStackTrace();
				}
				
				//将eb放入到ArrayList中去
				all.add(mb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return all;		
	}
	
	//7.删除商品
	
		public boolean delMerById(String M_ID) {
			boolean b = false;
			try {
				//得到连接
				ct = new ConDB().getConn();
				sm = ct.createStatement();
				//执行 a很重要 代表受影响的行数 返回1
				int a = sm.executeUpdate("delete from merchandise where M_ID='"+M_ID+"'");
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
		public boolean updateMer(String M_ID,String M_Name,String M_C_ID,String M_C_Name,String M_Specification,String M_Model,String M_Size,String M_JhPrice,String M_SellPrice,String M_MPrice,String M_Number,String M_P_ID) {
			boolean b = false;
			try {
				//得到连接
				ct = new ConDB().getConn();
				sm = ct.createStatement();
				//执行 a很重要 代表受影响的行数 返回1
				int a = sm.executeUpdate("update merchandise set M_Name='"+M_Name+"',  M_C_ID='"+M_C_ID+"', M_C_Name='"+M_C_Name+"' , M_Specification='"+M_Specification+"', M_Model='"+M_Model+"', M_Size='"+M_Size+"', M_JhPrice='"+M_JhPrice+"', M_SellPrice='"+M_SellPrice+"', M_MPrice='"+M_MPrice+"', M_Number='"+M_Number+"', M_P_ID='"+M_P_ID+"' where M_ID='"+M_ID+"'");

																																																			
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
		public boolean addMer(String M_ID,String M_Name,String M_C_ID,String M_C_Name,String M_Specification,String M_Model,String M_Size,String M_JhPrice,String M_SellPrice,String M_MPrice,String M_Number,String M_P_ID) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("insert into merchandise values('"+M_ID+"','"+M_Name+"','"+M_C_ID+"','"+M_C_Name+"','"+M_Specification+"','"+M_Model+"','"+M_Size+"','"+M_JhPrice+"','"+M_SellPrice+"','"+M_MPrice+"','"+M_Number+"','"+M_P_ID+"')");
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
	public int getMerPageCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from merchandise " );
	
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
	public ArrayList getMerByPage(int pageNow) {	
		ArrayList all = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
			rs=sm.executeQuery("select * from merchandise limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				MerBean mb = new MerBean();
				mb.setM_ID(rs.getString(1));
				mb.setM_Name(rs.getString(2));
				mb.setM_C_ID(rs.getString(3));
				mb.setM_C_Name(rs.getString(4));
				mb.setM_Specification(rs.getString(5));
				mb.setM_Model(rs.getString(6));
				mb.setM_Size(rs.getString(7));
				mb.setM_JhPrice(rs.getString(8));
				mb.setM_SellPrice(rs.getString(9));
				mb.setM_MPrice(rs.getString(10));
				mb.setM_Number(rs.getString(11));
				mb.setM_P_ID(rs.getString(12));
				
				//将eb放入到ArrayList中去
				all.add(mb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return all;		
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
