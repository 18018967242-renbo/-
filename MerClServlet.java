package zy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zy.model.*;
@WebServlet("/MerClServlet")
public class MerClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MerClServlet() {
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
				//调用MerBeanCl
				MerBeanCl mbc = new MerBeanCl();
				
				//在跳转到Wel.jsp页面时，就把要显示的数据准备好
				ArrayList all = mbc.getMerByPage(pageNow);
				int pageCount = mbc.getMerPageCount();
				//将all，pageCount,pageNow放入request中
				request.setAttribute("all", all);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("Mer.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
		
		
		else if(flag.equals("addMer")) {
			//完成添加
			//得到用户输入的信息
			String M_ID = request.getParameter("M_ID");
			String M_Name = request.getParameter("M_Name");
			String M_C_ID = request.getParameter("M_C_ID");
			String M_C_Name = request.getParameter("M_C_Name");
			String M_Specification = request.getParameter("M_Specification");
			String M_Model = request.getParameter("M_Model");
			String M_Size = request.getParameter("M_Size");
			String M_JhPrice = request.getParameter("M_JhPrice");
			String M_SellPrice = request.getParameter("M_SellPrice");
			String M_MPrice = request.getParameter("M_MPrice");
			String M_Number = request.getParameter("M_Number");
			String M_P_ID = request.getParameter("M_P_ID");

			
			//创建MerloyeesBeanCl
			MerBeanCl mbc = new MerBeanCl();
			
			
			
			if(mbc.addMer(M_ID,M_Name,M_C_ID, M_C_Name, M_Specification, M_Model, M_Size, M_JhPrice, M_SellPrice, M_MPrice, M_Number, M_P_ID)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("updateMer")) {
			//完成更新
			//得到用户输入的信息
			String M_ID = request.getParameter("M_ID");
			String M_Name = request.getParameter("M_Name");
			String M_C_ID = request.getParameter("M_C_ID");
			String M_C_Name = request.getParameter("M_C_Name");
			String M_Specification = request.getParameter("M_Specification");
			String M_Model = request.getParameter("M_Model");
			String M_Size = request.getParameter("M_Size");
			String M_JhPrice = request.getParameter("M_JhPrice");
			String M_SellPrice = request.getParameter("M_SellPrice");
			String M_MPrice = request.getParameter("M_MPrice");
			String M_Number = request.getParameter("M_Number");
			String M_P_ID = request.getParameter("M_P_ID");

			//创建MerBeanCl
			MerBeanCl mbc = new MerBeanCl();
			
			if(mbc.updateMer(M_ID,M_Name,M_C_ID, M_C_Name, M_Specification, M_Model, M_Size, M_JhPrice, M_SellPrice, M_MPrice, M_Number, M_P_ID)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("delMer")) {
			//完成删除
			//1.得到要删除用户的id
			String M_ID = request.getParameter("M_ID");
			
			//创建MerBeanCl
			MerBeanCl mbc = new MerBeanCl();
			if(mbc.delMerById(M_ID)) {
				//删除成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//删除失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
	

		else if(flag.equals("allOrd")) {
			
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("OrdZpageNow"));
				//调用MerBeanCl
				
				String E_LoginName = request.getParameter("E_LoginName");
				
				System.out.println(E_LoginName);
				MerBeanCl mbc = new MerBeanCl();
				
				//在跳转到Wel.jsp页面时，就把要显示的数据准备好
				ArrayList all = mbc.allOrd(pageNow,E_LoginName);
				int pageCount = mbc.allOrdCount();
				
				
				//将all，pageCount,pageNow放入request中
				request.setAttribute("all", all);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("OrdZ2.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
		}
		
		else if(flag.equals("MC")) {
			
			//得到用户希望显示的pageNow
	
			try {
				int pageNow = Integer.parseInt(request.getParameter("MCpageNow"));
				//调用MerBeanCl
				
				
				String MC = request.getParameter("MC");
	
				System.out.println(MC);
				MerBeanCl mbc = new MerBeanCl();
				
				//在跳转到Wel.jsp页面时，就把要显示的数据准备好
				ArrayList MCal = mbc.getMCByPage(pageNow,MC);
				int pageCount = mbc.MCCount(MC);
				
				
				//将MCal，pageCount,pageNow放入request中
				request.setAttribute("MCal", MCal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("MC.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
