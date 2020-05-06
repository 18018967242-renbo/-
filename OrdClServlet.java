package zy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zy.model.*;
@WebServlet("/OrdClServlet")
public class OrdClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrdClServlet() {
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
				//调用OrdBeanCl
				OrdBeanCl obc = new OrdBeanCl();
				
				//在跳转到Main.jsp页面时，就把要显示的数据准备好
				ArrayList Oal = obc.getOrdByPage(pageNow);
				int pageCount = obc.getOrdPageCount();
				//将Oal，pageCount,pageNow放入request中
				request.setAttribute("Oal", Oal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("Ord.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
		
		else if(flag.equals("addOrd")) {
			//完成添加
			//得到用户输入的信息
			String O_ID = request.getParameter("O_ID");
			String O_Utime = request.getParameter("O_Utime");
			String O_P_ID = request.getParameter("O_P_ID");
			String O_M_ID = request.getParameter("O_M_ID");
			String O_M_Name = request.getParameter("O_M_Name");
			String O_Number = request.getParameter("O_Number");
			String O_M_JhPrice = request.getParameter("O_M_JhPrice");
			String O_E_ID = request.getParameter("O_E_ID");
			String O_Dtime = request.getParameter("O_Dtime");

			
			//创建EmployeesBeanCl
			OrdBeanCl obc = new OrdBeanCl();
			
			
			
			if(obc.addOrd(O_ID, O_Utime, O_P_ID, O_M_ID,O_M_Name, O_Number,O_M_JhPrice ,O_E_ID , O_Dtime)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("updateOrd")) {
			//完成修改
			//得到用户输入的信息
			String O_ID = request.getParameter("O_ID");
			String O_Utime = request.getParameter("O_Utime");
			String O_P_ID = request.getParameter("O_P_ID");
			String O_M_ID = request.getParameter("O_M_ID");
			String O_M_Name = request.getParameter("O_M_Name");
			String O_Number = request.getParameter("O_Number");
			String O_M_JhPrice = request.getParameter("O_M_JhPrice");
			String O_E_ID = request.getParameter("O_E_ID");
			String O_Dtime = request.getParameter("O_Dtime");

			
			//创建OrdBeanCl
			OrdBeanCl obc = new OrdBeanCl();
			
			if(obc.updateOrd(O_ID, O_Utime, O_P_ID, O_M_ID,O_M_Name,O_Number,O_M_JhPrice ,O_E_ID , O_Dtime)){
				//修改成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//修改失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("delOrd")) {
			//完成删除
			//1.得到要删除用户的id
			String O_ID = request.getParameter("O_ID");
			
			//创建OrdBeanCl
			OrdBeanCl obc = new OrdBeanCl();
			if(obc.delOrdById(O_ID)) {
				//删除成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//删除失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		
		else if(flag.equals("allOrd")) {
			//完成添加
			//得到用户输入的信息
			String O_ID = request.getParameter("O_ID");
			String O_Utime = request.getParameter("O_Utime");
			String O_P_ID = request.getParameter("O_P_ID");
			String O_M_ID = request.getParameter("O_M_ID");
			String O_M_Name = request.getParameter("O_M_Name");
			String O_Number = request.getParameter("O_Number");
			String O_M_JhPrice = request.getParameter("O_M_JhPrice");
			String O_E_ID = request.getParameter("O_E_ID");
			String O_Dtime = request.getParameter("O_Dtime");

			
			//创建EmployeesBeanCl
			OrdBeanCl obc = new OrdBeanCl();
			
			
			
			if(obc.allOrd(O_ID, O_Utime, O_P_ID, O_M_ID,O_M_Name, O_Number,O_M_JhPrice ,O_E_ID , O_Dtime)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}	

		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
