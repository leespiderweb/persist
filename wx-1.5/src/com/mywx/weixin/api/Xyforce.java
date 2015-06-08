package com.mywx.weixin.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mywx.common.util.WeixinLogger;
import com.mywx.weixin.coreservice.XyforceService;
import com.mywx.weixin.util.validate;

/**
 * Servlet implementation class Xyforce
 */
public class Xyforce extends HttpServlet {
	
	 Logger logger = WeixinLogger.getLogger(Xyforce.class);
	
	 
	 
	 private static final long serialVersionUID = 1L;
       
	private static String token = "spiderwx";

	private static String EncodingAESKey = "IvCM1gr69p8NVYjBxbgeaJZWzMjC4UgRZ9z0KieljQp";

	private static String AppID = "wx29fb1d7dde619ae3";

	private static String AppSecret = "7c1e194b511980018431448b923ac1c4";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xyforce() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//注册开发者模式是用于验证签名信息是否来自微信
				
		logger.info(" 验证开始，doGet： ");	
		
		
		validate myvalidate_get = new validate();
		
		try {
			
			// 随机字符串  
			String echostr = request.getParameter("echostr");
			
			//System.out.println(this.getClass()+" line   53"); 
			//System.out.println( "  echostr "+ echostr ); 
			
			PrintWriter out = response.getWriter();  
			
			//验证签名的合法性（是否来自微信）
			if(myvalidate_get.check_signature(request, token)&&(null!=echostr)){
				
				out.write(echostr);
				logger.info(" 验证成功，echostr为： "+echostr);	
								
			}
			
			out.close();  
			
	        out = null; 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		validate myvalidate_post = new validate();
		
		
		//回复给用户的信息
		String respMessage = "";
				
		try {
				
			PrintWriter out = response.getWriter();  
			
			//验证签名的合法性（是否来自微信）
			if(myvalidate_post.check_signature(request, token)){
				
				// System.out.println(" Xyforce 的post方法    getContentLength  line 139  " +request.getContentLength());
					
				 XyforceService myXyforceService = new XyforceService();				 				 
					
				 respMessage = myXyforceService.processWelcom(request);				
					
				 out.write(respMessage);
				 logger.info(" 回复信息成功 ");	
					
								
			}
			
			out.close();  
			
	        out = null; 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
