//这是一个控制器，主要完成对用户身份的验证
//控制器本身是不会去完成业务逻辑的。他主要是去调用Model模型完成对数据的处理任务
package zy.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zy.model.*;

@WebServlet("/LoginClServlet")
public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginClServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//中问乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//得到用户名和密码
		
		String E_LoginName = request.getParameter("E_LoginName");
		String E_LoginPwd = request.getParameter("E_LoginPwd");
		
		//request.setAttribute("E_LoginName", E_LoginName);
		//使用模型（EmployeesBeanCl)完成对用户的验证
		//1.创建一个 EmployeesBeanCl 对象
		
		EmployeesBeanCl ebc = new EmployeesBeanCl();
		

		
		//2.调用方法，检查是否合法
		
		if(ebc.checkEmployees(E_LoginName, E_LoginPwd)) {
			

            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("E_LoginName", E_LoginName);			
			
			
			//在跳转到Main.jsp页面时，就把要显示的数据准备好


			ArrayList al = ebc.getByPage(1);
			int pageCount = ebc.getPageCount();
			
			//将al，pageCount,pageNow放入request中
			
			request.setAttribute("al", al);
			request.setAttribute("pageCount", pageCount+"");
			request.setAttribute("pageNow", "1");
			
			//合法
			//转向	跳转的效率不高
			//response.sendRedirect("Main.jsp");
			
			//所以常常使用转发的方法
			//这种方法效率高，同时request中的对象可以在下一页面使用
			//request.getSession().setAttribute("E_LoginName",E_LoginName);
			//管理员
			if(E_LoginName.equals("001")||E_LoginName.equals("002")||E_LoginName.equals("003")) {
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			}
			//部门经理
			if(E_LoginName.equals("101")||E_LoginName.equals("102")||E_LoginName.equals("103")) {
			request.getRequestDispatcher("Index1.jsp").forward(request, response);
			}
			//采购员
			if(E_LoginName.equals("201")||E_LoginName.equals("202")||E_LoginName.equals("203")) {
			request.getRequestDispatcher("Index2.jsp").forward(request, response);
			}
			
		}
		
		else {
			
			//不合法
			//response.sendRedirect("Login.jsp");
			
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
