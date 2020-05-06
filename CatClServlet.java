package zy.controller;

import zy.model.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CatClServlet")
public class CatClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CatClServlet() {
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
				//调用CatBeanCl
				CatBeanCl cbc = new CatBeanCl();
				
				//在跳转到Wel.jsp页面时，就把要显示的数据准备好
				ArrayList Cal = cbc.getCatByPage(pageNow);
				int pageCount = cbc.getCatPageCount();
				//将Cal，pageCount,pageNow放入request中
				request.setAttribute("Cal", Cal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("Cat.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
		
		
		else if(flag.equals("addCat")) {
			//完成添加
			//得到用户输入的信息
			String C_ID = request.getParameter("C_ID");
			String C_Name = request.getParameter("C_Name");


			
			//创建CatloyeesBeanCl
			CatBeanCl cbc = new CatBeanCl();
			
			
			
			if(cbc.addCat(C_ID,C_Name)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("updateCat")) {
			//完成更新
			//得到用户输入的信息
			String C_ID = request.getParameter("C_ID");
			String C_Name = request.getParameter("C_Name");


			//创建CatBeanCl
			CatBeanCl cbc = new CatBeanCl();
			
			if(cbc.updateCat(C_ID,C_Name)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("delCat")) {
			//完成删除
			//1.得到要删除用户的id
			String C_ID = request.getParameter("C_ID");
			
			//创建CatBeanCl
			CatBeanCl cbc = new CatBeanCl();
			if(cbc.delCatById(C_ID)) {
				//删除成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//删除失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
	
	
		
		
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
