package zy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zy.model.*;

@WebServlet("/OrderMClServlet")
public class OrderMClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderMClServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//中问乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获得标志值
		String flag = request.getParameter("flag");
		
		//如果要分页显示用户信息
		if(flag.equals("OrdM")) {
			String O_ID = request.getParameter("O_ID");
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("OrdMpageNow"));
				//调用OrdBeanCl
				OrderMBeanCl omc = new OrderMBeanCl();
				
				//在跳转到Main.jsp页面时，就把要显示的数据准备好
				ArrayList OMal = omc.getOrdMByPage(pageNow,O_ID);
				int pageCount = omc.getOrdMPageCount(O_ID);
				//将OMal，pageCount,pageNow放入request中
				request.setAttribute("OMal", OMal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("OrdM.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
		//采购员 如果要分页显示用户信息
		if(flag.equals("OrdM2")) {
			String O_ID = request.getParameter("O_ID");
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("OrdMpageNow"));
				//调用OrdBeanCl
				OrderMBeanCl omc = new OrderMBeanCl();
				
				//在跳转到Main.jsp页面时，就把要显示的数据准备好
				ArrayList OMal = omc.getOrdMByPage(pageNow,O_ID);
				int pageCount = omc.getOrdMPageCount(O_ID);
				//将OMal，pageCount,pageNow放入request中
				request.setAttribute("OMal", OMal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				
				
				
				OrderPBeanCl opc = new OrderPBeanCl();
				String O_S = opc.OrdPyj2(O_ID);
				System.out.println(O_S);
				request.setAttribute("O_S", O_S);
				
				request.getRequestDispatcher("OrdM2.jsp").forward(request, response);
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}	
		if(flag.equals("OrdM2tg")) {
			String O_ID = request.getParameter("O_ID");
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("OrdMpageNow"));
				//调用OrdBeanCl
				OrderMBeanCl omc = new OrderMBeanCl();
				
				//在跳转到Main.jsp页面时，就把要显示的数据准备好
				ArrayList OMal = omc.getOrdMByPage(pageNow,O_ID);
				int pageCount = omc.getOrdMPageCount(O_ID);
				//将OMal，pageCount,pageNow放入request中
				request.setAttribute("OMal", OMal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				
				
				
				OrderPBeanCl opc = new OrderPBeanCl();
				String O_S = opc.OrdPyj2(O_ID);
				System.out.println(O_S);
				request.setAttribute("O_S", O_S);
				
				request.getRequestDispatcher("OrdM2tg.jsp").forward(request, response);
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}			
		
		else if(flag.equals("updateOrdM2")) {
			//完成修改
			//得到用户输入的信息
			String O_ID = request.getParameter("O_ID");
			String O_M_ID = request.getParameter("O_M_ID");
			String O_M_Name = request.getParameter("O_M_Name");
			String O_M_JhPrice = request.getParameter("O_M_JhPrice");
			String O_Number = request.getParameter("O_Number");

			
			//创建OrdBeanCl
			OrderMBeanCl omc = new OrderMBeanCl();
			
			if(omc.updateOrdM2(O_ID, O_M_ID,O_M_Name,O_M_JhPrice,O_Number)){
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
