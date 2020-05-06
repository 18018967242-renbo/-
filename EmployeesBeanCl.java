//这是一个处理类，有些人把它叫做bo，主要是封装对employees表的各种操作
//操作（增删改查）
package zy.model;

import java.sql.*;
import java.util.*;

public class EmployeesBeanCl {
	
	private Statement sm=null;//PreparedStatement
	private ResultSet rs=null;
	private Connection ct=null;
	
	private int pageSize =6;	
	private int rowCount=0;//该值从数据库查询
	private int pageCount=0;//该值计算出来
	

	
	
	
	//7.修改用户
	public boolean updateEmp(String E_ID,String E_Name,String E_LoginName,String E_LoginPwd,String E_Title) {
		boolean b = false;
		try {
			
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("update employees set E_Name='"+E_Name+"', E_LoginName='"+E_LoginName+"',E_LoginPwd='"+E_LoginPwd+"',E_Title='"+E_Title+"' where E_ID='"+E_ID+"'");

																																																		
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
	
	//6.添加用户
	/**
	 * @author 张月
	 * @param E_ID
	 * @param E_Name
	 * @param E_LoginName
	 * @param E_LoginPwd
	 * @param E_Title
	 * @return
	 */
	public boolean addEmp(String E_ID,String E_Name,String E_LoginName,String E_LoginPwd,String E_Title) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("insert into employees values('"+E_ID+"','"+E_Name+"','"+E_LoginName+"','"+E_LoginPwd+"','"+E_Title+"')");
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
	
	
	
	//5.删除用户
	public boolean delEmpById(String E_ID) {
		boolean b = false;
		try {
			//得到连接
			ct = new ConDB().getConn();
			sm = ct.createStatement();
			//执行 a很重要 代表受影响的行数 返回1
			int a = sm.executeUpdate("delete from employees where E_ID='"+E_ID+"'");
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
	
	//4.返回分页的总页数
	public int getPageCount() {
		try {
			//1 得到链接
			ct = new ConDB().getConn();
			
			sm=ct.createStatement();
			//4.查询
			
			 rs = sm.executeQuery("select count(*) from employees " );
	
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
	public ArrayList getByPage(int pageNow) {	
		ArrayList al = new ArrayList();		
		try {			
			ct = new ConDB().getConn();			
			//3.创建
			 sm = ct.createStatement();		
			//查询出需要显示的记录
			rs=sm.executeQuery("select * from employees limit "+pageSize*(pageNow-1) + "," + pageSize);
			//开始将rs封装到ArrayList
			while(rs.next()) {
				EmployeesBean eb = new EmployeesBean();
				eb.setE_ID(rs.getString(1));
				eb.setE_Name(rs.getString(2));
				eb.setE_LoginName(rs.getString(3));
				eb.setE_LoginPwd(rs.getString(4));
				eb.setE_Title(rs.getString(5));
				//将eb放入到ArrayList中去
				al.add(eb);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return al;		
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
	

	//1.LoginCl中验证用户是否存在函数
	public boolean checkEmployees(String E_LoginName,String E_LoginPwd) {
		boolean b = false;
		
		try {
			
			ct = new ConDB().getConn();

			//3.创建
			 sm = ct.createStatement();
			//4.查询
			 rs = sm.executeQuery("select E_LoginPwd , E_Name from employees where E_LoginName='"+E_LoginName+"'" );
			
			
			 //5.执行
			//根据结果做判断
			if(rs.next()){
				//说明用户存在
				if(rs.getString(1).equals(E_LoginPwd)){
					//一定合法
					b=true;
				}
			}		
		}
		catch(Exception ex) {
			//一定要写上
			ex.printStackTrace();
		}finally {
			
			//关闭打开的各种资源,重要！！！
			//函数
			this.close();

	
		}
		return b;
	}
	
	

	
}
