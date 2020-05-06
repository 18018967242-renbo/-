//从数据库中得到连接

package zy.model;

import java.sql.*;

public class ConDB {

	private Connection ct=null;
		
		public Connection getConn() {
			try {
				//1.加载驱动
				Class.forName("com.mysql.jdbc.Driver");
				//2.得到连接
				ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/systemdb?useUnicode=true&characterEncoding=utf-8", "bczdyh", "bczdyh");	
			
				
			}
			catch(Exception ex) {
				//注意不可少
				ex.printStackTrace();
			}
			return ct;
		}
	
	
}
