package com.weixin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class acc
 */
public class acc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public acc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    System.out.println("doget is actived !"); 
		
		// 微信加密签名
		String signature = request.getParameter("signature");
		
		// 时间戳 
		String timestamp = request.getParameter("timestamp");
		
		// 随机数  
		String nonce = request.getParameter("nonce");
		
		// 随机字符串  
		String echostr = request.getParameter("echostr");			
		
		PrintWriter out = response.getWriter();  
		//System.out.println(signature+"  , "+timestamp+"  , "+nonce+"  , "+echostr);			  	
		
		  wx_utility wx_util = new wx_utility();
		
		if(signature==null || timestamp==null || nonce==null || echostr==null){  
            //这几个参数为空时，排序会报错。  
			//System.out.println("parameter is null! on no !! ");
            out.write("acc  parameter is null! ");  
        }  else {
		
		if(wx_util.checkweixinsignature(signature, timestamp, nonce, echostr)){
							
			 out.print(echostr);				
			
		}else {
			
			out.print("check signature from weixin  is failed !! ");
			
		}
		
		out.close();  
		
        out = null; 
		
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			   PrintWriter out = response.getWriter(); 
			
			 out.write("access_wx3   parameter is null! ");  
			
			  System.out.println(" access_wx3 的post方法第一 次      getContentLength  line 86  " +request.getContentLength());
				
			  System.out.println(" access_wx3 的post方法,  request.getMethod(); line 88  " +  request.getMethod());
				
			  
			
			   response.setContentType("text/html; charset=UTF-8");
			   
		
			   
			  
			   
			   BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
			   
				ServletInputStream in = request.getInputStream();
				
				String line = null;
				 
				StringBuilder sb = new StringBuilder();
				   
				   
				while((line = br.readLine())!=null){ 
	            
					sb.append(line); 
	            
				}
				
				   System.out.println("acce的Post方法直接打印：ServletInputStream流的信息是：  "+sb);
				   out.println("access_wx3d的Post方法直接打印"+sb);
				   
				   out.close();  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	 

	}

}
