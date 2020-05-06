package zy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zy.model.*;
@WebServlet("/ProClServlet")
public class ProClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProClServlet() {
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
				//调用ProBeanCl
				ProBeanCl pbc = new ProBeanCl();
				
				//在跳转到Wel.jsp页面时，就把要显示的数据准备好
				ArrayList Pal = pbc.getProByPage(pageNow);
				int pageCount = pbc.getProPageCount();
				//将Pal，pageCount,pageNow放入request中
				request.setAttribute("Pal", Pal);
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("pageNow", pageNow+"");
				//重新跳转回 Wel.jsp
				request.getRequestDispatcher("Pro.jsp").forward(request, response);
				
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	
		}
		else if(flag.equals("addPro")) {
			//完成添加
			//得到用户输入的信息
			String P_ID = request.getParameter("P_ID");
			String P_Name = request.getParameter("P_Name");
			String P_Direvtor = request.getParameter("P_Direvtor");
			String P_Tel = request.getParameter("P_Tel");
			String P_Phone = request.getParameter("P_Phone");
			String P_Address = request.getParameter("P_Address");
			String P_PostalCode = request.getParameter("P_PostalCode");
			String P_BnkAccount = request.getParameter("P_BnkAccount");
			String P_BankNumber = request.getParameter("P_BankNumber");
			String P_C_ID = request.getParameter("P_C_ID");

			
			//创建ProloyeesBeanCl
			ProBeanCl pbc = new ProBeanCl();
			
			
			
			if(pbc.addPro(P_ID, P_Name, P_Direvtor, P_Tel, P_Phone, P_Address, P_PostalCode, P_BnkAccount, P_BankNumber, P_C_ID)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("updatePro")) {
			//完成添加
			//得到用户输入的信息
			String P_ID = request.getParameter("P_ID");
			String P_Name = request.getParameter("P_Name");
			String P_Direvtor = request.getParameter("P_Direvtor");
			String P_Tel = request.getParameter("P_Tel");
			String P_Phone = request.getParameter("P_Phone");
			String P_Address = request.getParameter("P_Address");
			String P_PostalCode = request.getParameter("P_PostalCode");
			String P_BnkAccount = request.getParameter("P_BnkAccount");
			String P_BankNumber = request.getParameter("P_BankNumber");
			String P_C_ID = request.getParameter("P_C_ID");

			
			//创建ProBeanCl
			ProBeanCl pbc = new ProBeanCl();
			
			if(pbc.updatePro(P_ID, P_Name, P_Direvtor, P_Tel, P_Phone, P_Address, P_PostalCode, P_BnkAccount, P_BankNumber, P_C_ID)){
				//添加成功
				request.getRequestDispatcher("Suc.jsp").forward(request, response);
			}
			else {
				//添加失败
				request.getRequestDispatcher("Err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("delPro")) {
			//完成删除
			//1.得到要删除用户的id
			String P_ID = request.getParameter("P_ID");
			
			//创建ProBeanCl
			ProBeanCl pbc = new ProBeanCl();
			if(pbc.delProById(P_ID)) {
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
