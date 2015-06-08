package com.weixin.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class accexx_wx2
 */
public class accexx_wx2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accexx_wx2() {
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
            out.write("parameter is null! ");  
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
			   response.setContentType("text/html; charset=UTF-8");
			   PrintWriter out = response.getWriter();
			   out.println("SUCCESS");
			   ServletInputStream in = request.getInputStream();
			   String str = readLine(in);// 这里是前台发起的所有参数的值，包括二进制形式的文件和其它形式的文件
			   out.println(str);
			   System.out.println(str);
			   out.flush();
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
			  return;
			 }

			 /**
			  * Read the next line of input.
			  * 
			  * @return a String containing the next line of input from the stream, or
			  *         null to indicate the end of the stream.
			  * @exception IOException
			  *                if an input or output exception has occurred.
			  */
			 private String readLine(ServletInputStream in) throws IOException {
			  byte[] buf = new byte[8 * 1024];
			  StringBuffer sbuf = new StringBuffer();
			  int result;
			  // String line;

			  do {
			   result = in.readLine(buf, 0, buf.length); // does +=
			   if (result != -1) {
			    sbuf.append(new String(buf, 0, result, "UTF-8"));
			   }
			  } while (result == buf.length); // loop only if the buffer was filled

			  if (sbuf.length() == 0) {
			   return null; // nothing read, must be at the end of stream
			  }

			  // Cut off the trailing \n or \r\n
			  // It should always be \r\n but IE5 sometimes does just \n
			  int len = sbuf.length();
			  if (sbuf.charAt(len - 2) == '\r') {
			   sbuf.setLength(len - 2); // cut \r\n
			  } else {
			   sbuf.setLength(len - 1); // cut \n
			  }
			  return sbuf.toString();
			 }
			


	}


