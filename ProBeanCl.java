package zy.model;
import java.sql.*;
import java.util.*;
public class ProBeanCl {
	private Statement sm=null;//PreparedStatement
	private ResultSet rs=null;
	private Connection ct=null;
	
	private int pageSize = 6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来

	//7.删除供应商
	
		public boolean delProById(String P_ID) {
			boolean b = false;
			try {
				//得到连接
				ct = new ConDB().getConn();
				sm = ct.createStatement();
				//执行 a很重要 代表受影响的行数 返回1
				int a = sm.executeUpdate("delete from provider where P_ID='"+P_ID+"'");
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
		//6.修改供应商
		public boolean updatePro(String P_ID,String P_Name,String P_Direvtor,String P_Tel,String P_Phone,String P_Address,String P_PostalCode,String P_BnkAccount,String P_BankNumber,String P_C_ID) {
			boolean b = false;
			try {
				//得到连接
				ct = new ConDB().getConn();
				sm = ct.createStatement();
				//执行 a很重要 代表受影响的行数 返回1
				int a = sm.executeUpdate("update provider set P_Name='"+P_Name+"',  P_Direvtor='"+P_Direvtor+"', P_Tel='"+P_Tel+"' , P_Phone='"+P_Phone+"', P_Address='"+P_Address+"', P_PostalCode='"+P_PostalCode+"', P_BnkAccount='"+P_BnkAccount+"', P_BankNumber='"+P_BankNumber+"', P_C_ID='"+P_C_ID+"' where P_ID='"+P_ID+"'");

																																																			
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
		
	
	//5.添加供应商
	public boolean addPro(String P_ID,String P_Name,String P_Direvtor,String P_Tel,String P_Phone,String P_Address,String P_PostalCode,String P_BnkAccount,String P_BankNumber,String P_C_ID) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("insert into provider values('"+P_ID+"','"+P_Name+"','"+P_Direvtor+"','"+P_Tel+"','"+P_Phone+"','"+P_Address+"','"+P_PostalCode+"','"+P_BnkAccount+"','"+P_BankNumber+"','"+P_C_ID+"')");
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
	public int getProPageCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from provider " );
	
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
	public ArrayList getProByPage(int pageNow) {	
		ArrayList Pal = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
			rs=sm.executeQuery("select * from provider limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				ProBean pb = new ProBean();
				pb.setP_ID(rs.getString(1));
				pb.setP_Name(rs.getString(2));
				pb.setP_Direvtor(rs.getString(3));
				pb.setP_Tel(rs.getString(4));
				pb.setP_Phone(rs.getString(5));
				pb.setP_Address(rs.getString(6));
				pb.setP_PostalCode(rs.getString(7));
				pb.setP_BnkAccount(rs.getString(8));
				pb.setP_BankNumber(rs.getString(9));
				pb.setP_C_ID(rs.getString(10));
				
				
				//将eb放入到ArrayList中去
				Pal.add(pb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return Pal;		
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
