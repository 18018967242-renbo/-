package zy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zy.model.*;

@WebServlet("/OrderPClServlet")
public class OrderPClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderPClServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//中问乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获得标志值
		String flag = request.getParameter("flag");
		
		//如果要分页显示用户信息
		if(flag.equals("OrdP")) {
		
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("OrdPpageNow"));
				//调用OrderPBeanCl
				OrderPBeanCl opc = new OrderPBeanCl();
				
				//在跳转到Main.jsp页面时，就把要显示的数据准备好
				ArrayList OPal = opc.getOrdPByPage(pageNow);
				int pageCount = opc.getOrdPPageCount();
				//将OPal，pageCount,pageNow放入request中
				request.setAttribute("OPal", OPal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("OrdP.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
//采购员订单列表
		else if(flag.equals("OrdP2")) {
			
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("OrdPpageNow"));
				//调用OrderPBeanCl
				OrderPBeanCl opc = new OrderPBeanCl();
				
				//在跳转到Main.jsp页面时，就把要显示的数据准备好
				ArrayList OPal = opc.getOrdPByPage(pageNow);
				int pageCount = opc.getOrdPPageCount();
				//将OPal，pageCount,pageNow放入request中
				request.setAttribute("OPal", OPal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("OrdP2.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}		
		

		else if(flag.equals("OrdPtg")) {
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
			OrderPBeanCl opc = new OrderPBeanCl();
			
			if(opc.OrdPtg(O_ID, O_Utime, O_P_ID, O_M_ID,O_M_Name,O_Number,O_M_JhPrice ,O_E_ID , O_Dtime)){
				//修改成功
				request.getRequestDispatcher("OrdSh.jsp").forward(request, response);
			}
			else {
				//修改失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		else if(flag.equals("OrdPbh")) {
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
			OrderPBeanCl opc = new OrderPBeanCl();
			
			if(opc.OrdPbh(O_ID, O_Utime, O_P_ID, O_M_ID,O_M_Name,O_Number,O_M_JhPrice ,O_E_ID , O_Dtime)){
				//修改成功
				request.getRequestDispatcher("OrdSh.jsp").forward(request, response);
			}
			else {
				//修改失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("OrdPyj")) {
			//完成修改
			//得到用户输入的信息
			String O_ID = request.getParameter("O_ID");
			String O_S = request.getParameter("O_S");
			
			//创建OrdBeanCl
			OrderPBeanCl opc = new OrderPBeanCl();
			
			if(opc.OrdPyj(O_ID,O_S)){
				//修改成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//修改失败
				//request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}			
		
		else if(flag.equals("OrdPdel")) {
			//完成删除
			//1.得到要删除用户的id
			String O_ID = request.getParameter("O_ID");
			
			//创建OrderPBeanCl
			OrderPBeanCl opc = new OrderPBeanCl();
			if(opc.delOrderPById(O_ID)) {
				//删除成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//删除失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		//采购员通过订单实现无法删除
		else if(flag.equals("OrdPdel2")) {
			//完成删除
			//1.得到要删除用户的id
			String O_ID = request.getParameter("O_ID");
			
			//创建OrderPBeanCl
			OrderPBeanCl opc = new OrderPBeanCl();
			if(opc.delOrderPById2(O_ID)) {
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
