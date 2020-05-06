package zy.model;
import java.sql.*;
import java.util.*;
public class OrderMBeanCl {
	private Statement sm=null;//PreparedStatement
	private ResultSet rs=null;
	private Connection ct=null;
	
	private int pageSize = 6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来

	public boolean updateOrdM2(String O_ID,String O_M_ID,String O_M_Name,String O_M_JhPrice,String O_Number) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("update orderm set O_Number='"+O_Number+"' where O_M_ID='"+O_M_ID+"'");

																																																		
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
	
	//4.返回分页的总页数
	public int getOrdMPageCount(String O_ID) {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from orderM where O_ID='"+O_ID+"'" );
	
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
	public ArrayList getOrdMByPage(int pageNow,String O_ID) {	
		ArrayList OMal = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
				rs=sm.executeQuery("select * from orderM where O_ID='"+O_ID+"' limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				OrderMBean omb = new OrderMBean();				
				omb.setO_ID(rs.getString(1));			
				omb.setO_M_ID(rs.getString(2));			
				omb.setO_M_Name(rs.getString(3));
				omb.setO_M_JhPrice(rs.getString(4));
				omb.setO_Number(rs.getString(5));
				//将omb放入到ArrayList中去
				OMal.add(omb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return OMal;		
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
