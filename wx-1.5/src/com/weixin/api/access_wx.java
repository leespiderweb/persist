package com.weixin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.service.CoreService;


/**
 * Servlet implementation class access_wx
 */
public class access_wx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public access_wx() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 *  确认请求来自微信服务器      
	 *   
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("验证签名!"); 
		  System.out.println(" access_wx的get方法第一次      getContentLength  line 37  " +request.getContentLength());
				
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
		   		
		if(signature==null || timestamp==null || nonce==null || echostr==null){  
            //这几个参数为空时，排序会报错。  
			System.out.println("parameter is null! 参数为空 ");
            out.write("参数为空null");  
        }  else {
		
		if(wx_utility.checkweixinsignature(signature, timestamp, nonce, echostr)){
			
			//System.out.println("签名合法!"); 
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
		
		        System.out.println(" access_wx的post方法 第一次   getContentLength  line 90  " +request.getContentLength());
							
		        System.out.println("post is active ");
		        
                request.setCharacterEncoding("UTF-8");
		
		        response.setCharacterEncoding("UTF-8");
		       // 微信加密签名
				String signature = request.getParameter("signature");
				
				// 时间戳 
				String timestamp = request.getParameter("timestamp");
				
				// 随机数  
				String nonce = request.getParameter("nonce");
				
				// 随机字符串  
				String echostr = request.getParameter("echostr");
				
				PrintWriter out = response.getWriter();  
				
				//out.println("SUCCESS");
					
				
				//System.out.println(signature+"  , "+timestamp+"  , "+nonce+"  , "+echostr);
				  		 
				
				if(signature==null || timestamp==null || nonce==null){  
		            //这几个参数为空时，排序会报错。  
					//System.out.println("parameter is null! on no !! ");
		           // out.write("parameter is null! ");  
		            
		            System.out.println("post下收到的 信息 ：为空 " );
		        	
		        }  else {
				
		        	//System.out.println("post下收到的 信息 ：signature : "+signature+"  ,  timestamp : "+timestamp+"  , nonce :   "+nonce );
		        	
				if(wx_utility.checkweixinsignature(signature, timestamp, nonce, echostr)){
					

					// 调用核心业务类接收消息、处理消息  
			        // String respMessage = CoreService.processRequest(request);  
					        //respMessage
					String respMessage = "";
					
					  System.out.println(" access_wx的post方法第二次      getContentLength  line 139  " +request.getContentLength());
						
					 
							respMessage = CoreService.processRequest(request);
						 
						  
						
						// System.out.println("返回信息 ： "+respMessage );
					        
					        // 响应消息  
					       // PrintWriter out2 = response.getWriter(); 
					        
					        out.print(respMessage); 
					        
					 
					       // out2.close();  
				    
			        
				}
					
					
				//else {
					//out.print("check signature from weixin  is failed !! ");
					//System.out.println("check signature from weixin  is failed !!： "  );
				//}
				
				out.close();  
				
		        out = null; 
				
		        }
				
		//打印微信发过来的URL信息，方法一		
		System.out.println("getRequestURL()   :  "+request.getRequestURL());			
		System.out.println("getQueryString()   :  "+request.getQueryString());
		
		
		
//		//打印微信发过来的URL信息，方法二		
//		Map<String, String[]> params = request.getParameterMap();  
//		
//		System.out.println("Map得到的参数大小是  ：  "+params.size()+"    ----");
//        String queryString = "";  
//        for (String key : params.keySet()) {  
//            String[] values = params.get(key);  
//            for (int i = 0; i < values.length; i++) {  
//                String value = values[i];  
//                queryString += key + "=" + value + "&";  
//            }  
//        }  
//        // 去掉最后一个空格  
//        queryString = queryString.substring(0, queryString.length() - 1);  
//        System.out.println("POST   " + request.getRequestURL() + " " + queryString);  
//    	//打印微信发过来的URL信息，方法二	，结束
		
		
		//将请求、响应的编码均设置为UTF-8（解决中文乱码）
		
		
		
		
	}

}
