package zy.model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
public class OrderPBeanCl {
	private Statement sm=null;//PreparedStatement
	private ResultSet rs=null;
	private Connection ct=null;
	
	private int pageSize = 6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来
	
	//9.采购员通过订单实现无法删除
	public boolean delOrderPById2(String O_ID) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("delete from orderP where O_TB='"+"驳回"+"' and  O_ID='"+O_ID+"' ");
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

	//8.删除订单	
	public boolean delOrderPById(String O_ID) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("delete from orderP where O_ID='"+O_ID+"'");
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
	//显示意见
	public String OrdPyj2(String O_ID) {
		String O_S =null;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			SimpleDateFormat ddff = new SimpleDateFormat("yyyyMMdd-HHmm");//设置日期格式
			//执行 a很重要 代表受影响的行数 返回1
			rs= sm.executeQuery("select O_S from orderP  where O_ID='"+O_ID+"'");
			OrderPBean opb = new OrderPBean();
			while(rs.next()) {
						
			O_S = opb.setO_S(rs.getString(1));	
			
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return O_S;	
	}	
	//7.添加意见
public boolean OrdPyj(String O_ID,String O_S) {
	boolean b = false;
	try {
		//得到连接
		ct = new ConDB().getConn();
		sm = ct.createStatement();
		SimpleDateFormat ddff = new SimpleDateFormat("yyyyMMdd-HHmm");//设置日期格式
		//执行 a很重要 代表受影响的行数 返回1
		int a = sm.executeUpdate("update orderP set O_S='"+O_S+"' where O_ID='"+O_ID+"'");

																																																	
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
	
	
		//6.通过订单
	public boolean OrdPtg(String O_ID,String O_Utime,String O_P_ID,String O_M_ID,String O_M_Name,String O_Number,String O_M_JhPrice,String O_E_ID,String O_Dtime) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			SimpleDateFormat ddff = new SimpleDateFormat("yyyyMMdd-HHmm");//设置日期格式
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("update orderP set O_Dtime='"+ddff.format(new Date())+"',O_TB='"+"通过"+"' where O_ID='"+O_ID+"'");

																																																		
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
	//5.驳回订单
public boolean OrdPbh(String O_ID,String O_Utime,String O_P_ID,String O_M_ID,String O_M_Name,String O_Number,String O_M_JhPrice,String O_E_ID,String O_Dtime) {
	boolean b = false;
	try {
		//得到连接
		ct = new ConDB().getConn();
		sm = ct.createStatement();
		SimpleDateFormat ddff = new SimpleDateFormat("yyyyMMdd-HHmm");//设置日期格式
		//执行 a很重要 代表受影响的行数 返回1
		int a = sm.executeUpdate("update orderP set O_Dtime='"+ddff.format(new Date())+"',O_TB='"+"驳回"+"' where O_ID='"+O_ID+"'");

																																																	
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
	public int getOrdPPageCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from orderP " );
	
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
	public ArrayList getOrdPByPage(int pageNow) {	
		ArrayList OPal = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
				rs=sm.executeQuery("select * from orderP limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				OrderPBean opb = new OrderPBean();				
				opb.setO_ID(rs.getString(1));			
				opb.setO_P_ID(rs.getString(2));			
				opb.setO_Utime(rs.getString(3));
				opb.setO_Dtime(rs.getString(4));
				opb.setO_TB(rs.getString(5));
				opb.setO_E_ID(rs.getString(6));
				//将opb放入到ArrayList中去
				OPal.add(opb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return OPal;		
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
