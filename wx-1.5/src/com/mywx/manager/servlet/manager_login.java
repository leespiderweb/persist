package com.mywx.manager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class manager_login
 */
public class manager_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manager_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setCharacterEncoding("UTF-8");
//		
//       response.setCharacterEncoding("UTF-8");
//		
		// int user_id = Integer.parseInt(request.getParameter("user_id"));	
		
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");	
		 
		String user_name = request.getParameter("user_id");		
		
		 String psd   = request.getParameter("user_id") ;		
		// request.getRequestDispatcher("mywxmanager.html").forward(request, response);
			
			
		//用户信息为空
		if((""==user_id||null==user_id)&&(""==user_name||null==user_name)||(""==psd||null==psd)){
			
			//System.out.println("user_id : "+user_id);
			
			out.write("1111系统错误，请稍后重试");
			
		}else{
				
			//System.out.println("user_id : "+user_id);
			 
			request.getRequestDispatcher("mywxmanager.html").forward(request, response);
				
			
			
		}
		 
	}
}
