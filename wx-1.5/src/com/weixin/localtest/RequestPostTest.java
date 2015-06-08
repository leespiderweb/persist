package com.weixin.localtest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestPostTest {

	public static void main(String[] args) throws Exception{
		
		// TODO Auto-generated method stub
		
		String urlString="http://wx.hzdywxqsx.com/access_wx";
		
		//urlString="http://localhost:8080/wx-1.5/accexx_wx2";
		
		 urlString="http://wx.hzdywxqsx.com/acc";		
		
		  URL connectURL = new URL(urlString);
		  HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection();
		  conn.setReadTimeout(100000);
		  conn.setConnectTimeout(100000);
		  conn.setDoInput(true);
		  conn.setDoOutput(true);
		  conn.setUseCaches(false);
		  conn.setRequestMethod("POST");
		  conn.setRequestProperty("Connection", "Keep-Alive");
		  conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
		   // 得到请求的输出流对象  
	      OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());  
	        // 把数据写入请求的Body  
	     // out.write("<?xml version='1.0' encoding='UTF-8'?><trans><tran><requestId>111111</requestId><orderNo>22222</orderNo></tran><tran><requestId>aaaa</requestId><orderNo>bbbb</orderNo></tran><tran><requestId>cccccc</requestId><orderNo>ddddd</orderNo></tran></trans>");  
String stt="<xml><ToUserName><![CDATA[gh_c2b078badbdf]]></ToUserName><FromUserName>"
		+ "<![CDATA[oLjvcs1pvhYD5lvyAWlDOyN-iKeY]]></FromUserName><CreateTime>1432822793</CreateTime><MsgType>"
		+ "<![CDATA[text]]></MsgType><Content><![CDATA[有空要的哥俩我也]]></Content><MsgId>6153927037104720164</MsgId></xml>";
	      out.write(stt);
	      out.flush();	  
	      out.close();

	  //接收发起请求后由服务端返回的结果
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

 
