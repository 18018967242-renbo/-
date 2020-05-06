package zy.model;
import java.sql.*;
import java.util.*;
public class OrdBeanCl {
	private Statement sm=null;//PreparedStatement
	private ResultSet rs=null;
	private Connection ct=null;
	
	private int pageSize = 6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来
	
	//5.添加订单
	
	public boolean allOrd(String O_ID,String O_Utime,String O_P_ID,String O_M_ID,String O_M_Name,String O_Number,String O_M_JhPrice,String O_E_ID,String O_Dtime) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("insert into orders values('"+O_ID+"','"+O_M_ID+"','"+O_M_Name+"','"+O_P_ID+"','"+O_M_JhPrice+"','"+O_Number+"','"+O_Utime+"','"+O_Dtime+"','"+O_E_ID+"')");
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
	
	
	
	
	
	
	
	//7.删除订单
	
	public boolean delOrdById(String O_ID) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("delete from orders where O_ID='"+O_ID+"'");
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
	//6.修改订单
	public boolean updateOrd(String O_ID,String O_Utime,String O_P_ID,String O_M_ID,String O_M_Name,String O_Number,String O_M_JhPrice,String O_E_ID,String O_Dtime) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("update orders set  O_Number='"+O_Number+"', O_Utime='"+O_Utime+"', O_Dtime='"+O_Dtime+"' where O_ID='"+O_ID+"'");

																																																		
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
	
	
	
	
	//5.添加订单
	
	public boolean addOrd(String O_ID,String O_Utime,String O_P_ID,String O_M_ID,String O_M_Name,String O_Number,String O_M_JhPrice,String O_E_ID,String O_Dtime) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("insert into orders values('"+O_ID+"','"+O_M_ID+"','"+O_M_Name+"','"+O_P_ID+"','"+O_M_JhPrice+"','"+O_Number+"','"+O_Utime+"','"+O_Dtime+"','"+O_E_ID+"')");
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
	public int getOrdPageCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from orders " );
	
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
	public ArrayList getOrdByPage(int pageNow) {	
		ArrayList Oal = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
				rs=sm.executeQuery("select * from orders limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				OrdBean ob = new OrdBean();
				
				ob.setO_ID(rs.getString(1));
				ob.setO_M_Name(rs.getString(2));
				ob.setO_M_ID(rs.getString(3));				
				ob.setO_P_ID(rs.getString(4));
				ob.setO_M_JhPrice(rs.getString(5));				
				ob.setO_Number(rs.getString(6));				
				ob.setO_Utime(rs.getString(7));
				ob.setO_Dtime(rs.getString(8));
				ob.setO_E_ID(rs.getString(9));
				//将eb放入到ArrayList中去
				Oal.add(ob);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return Oal;		
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
