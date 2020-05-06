//这个控制器，将处理用户的分页显示，查找删除修改添加。
package zy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zy.model.*;



@WebServlet("/EmpClServlet")
public class EmpClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpClServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//中问乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获得标志值
		String flag = request.getParameter("flag");
		
		//如果要分页显示用户信息
		if(flag.equals("fenye")) {
		
			//得到用户希望显示的pageNow
	
			try {	
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				//调用EmployeesBeanCl
				EmployeesBeanCl ebc = new EmployeesBeanCl();
				
				//在跳转到Emp.jsp页面时，就把要显示的数据准备好
				ArrayList al = ebc.getByPage(pageNow);
				int pageCount = ebc.getPageCount();
				//将al，pageCount,pageNow放入request中
				request.setAttribute("al", al);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Emp.jsp
				request.getRequestDispatcher("Emp.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
		else if(flag.equals("delEmp")) {
			//完成删除
			//1.得到要删除用户的id
			String empId = request.getParameter("empId");
			
			//创建EmployeesBeanCl
			EmployeesBeanCl ebc = new EmployeesBeanCl();
			if(ebc.delEmpById(empId)) {
				//删除成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//删除失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("addEmp")) {
			//完成添加
			//得到用户输入的信息
			String E_ID = request.getParameter("E_ID");
			String E_Name = request.getParameter("E_Name");
			String E_LoginName = request.getParameter("E_LoginName");
			String E_LoginPwd = request.getParameter("E_LoginPwd");
			String E_Title = request.getParameter("E_Title");

			
			//创建EmployeesBeanCl
			EmployeesBeanCl ebc = new EmployeesBeanCl();
			
			
			
			if(ebc.addEmp(E_ID, E_Name, E_LoginName, E_LoginPwd, E_Title)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
	
		else if(flag.equals("updateEmp")) {
			//完成修改
			//得到用户输入的信息
			String E_ID = request.getParameter("E_ID");
			String E_Name = request.getParameter("E_Name");
			String E_LoginName = request.getParameter("E_LoginName");
			String E_LoginPwd = request.getParameter("E_LoginPwd");
			String E_Title = request.getParameter("E_Title");

			
			//创建EmployeesBeanCl
			EmployeesBeanCl ebc = new EmployeesBeanCl();
			
			
			
			if(ebc.updateEmp(E_ID, E_Name, E_LoginName, E_LoginPwd, E_Title)){
				//修改成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//修改失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}	
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
