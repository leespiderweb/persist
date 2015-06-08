package com.weixin.localtest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.weixin.bean.message.resp.TextMessage;
import com.weixin.util.MessageUtil;

public class testsendxml {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub

		
		String urlString="http://wx.hzdywxqsx.com/access_wx";
		  URL connectURL = new URL(urlString);
		  HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
		  conn.setReadTimeout(100000);
		        conn.setConnectTimeout(100000);
		  conn.setDoInput(true);
		  conn.setDoOutput(true);
		  conn.setUseCaches(false);
		  conn.setRequestMethod("POST");
		  conn.setRequestProperty("Connection", "Keep-Alive");
		
		  TextMessage textMessage = new TextMessage();
			
			textMessage.setToUserName("xyforce");

			textMessage.setFromUserName("13829918230");

			textMessage.setContent("hard to say hi");

			textMessage.setFuncFlag(0);

			textMessage.setMsgType("text");
			
			textMessage.setCreateTime(20150520);
			
		 
		  
		   // 得到请求的输出流对象  
	        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());  
	        
	        out.write(MessageUtil.textMessageToXml(textMessage));  
	  	  out.flush();
	  	  out.close();
	  	  
	  	int read;
		  StringBuffer inputb = new StringBuffer();
		  InputStream is = conn.getInputStream();
		  InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
		   while ((read=inputStreamReader.read())>=0) {
		             inputb.append( (char) read);
		         }
		   System.out.println(inputb.toString());
	}

}
