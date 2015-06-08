package com.weixin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;  

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.weixin.bean.message.resp.TextMessage;
import com.weixin.util.*;
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.core.util.QuickWriter;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;  
import com.thoughtworks.xstream.io.xml.XppDriver; 

public class CoreService {

	/**
	 * 处理微信发来的请求 
	 * 
	 */
	
	public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
            
            System.out.println("processRequest开始信息处理   , request.getInputStream()" +request.getInputStream());
            
            System.out.println("getContentLength  " +request.getContentLength());
            // xml请求解析  
           // MessageUtil myMessageUtil = new MessageUtil();
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            
            System.out.println("  发送方帐号（open_id） : "+fromUserName);
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            
            System.out.println("  公众帐号   : "+toUserName );
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            
            System.out.println("   消息类型    : "+msgType);
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                respContent = "xy_您发送的是文本消息！";  
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "xy_您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您关注xyforce！";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }  
            }  
  
            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
 
	
	
	
	
	public static String processResponse(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
            System.out.println("  processResponse开始信息处理 " );
            // xml请求解析  
            MessageUtil myMessageUtil = new MessageUtil();
            Map<String, String> requestMap = myMessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            
            System.out.println("  发送方帐号（open_id） : "+fromUserName);
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            
            System.out.println("  公众帐号   : "+toUserName );
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            
            System.out.println("   消息类型    : "+msgType);
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                respContent = "xy_您发送的是文本消息！";  
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "xy_您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }  
            }  
  
            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);              
          //respMessage
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }





	public static String processRequest2(HttpServletRequest request ) throws Exception {
		
		String respMessage ="";
		 String respContent = "请求处理异常，请稍候尝试！";   
		
		try {
			   
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
			   
			ServletInputStream in = request.getInputStream();
			
			String line = null;
			 
			StringBuilder sb = new StringBuilder();
			   
			   
			while((line = br.readLine())!=null){ 
            
				sb.append(line); 
            
			}
			
			   System.out.println("ServletInputStream流的信息是：  "+sb);
			   
			 
			//   InputStream inputStream = request.getInputStream();  
		        // 读取输入流  
		        SAXReader reader = new SAXReader();  
		         Document document = reader.read(in);  
			   
		    	// 将解析结果存储在HashMap中  
		        Map<String, String> map = new HashMap<String, String>();  
		        
		      
		        // 得到xml根元素  
		        Element root = document.getRootElement();  
		        // 得到根元素的所有子节点  
		        List<Element> elementList = root.elements(); 
		        
		        
		        
		        // 遍历所有子节点  
		        for (Element e : elementList)  
		            map.put(e.getName(), e.getText());  
		        
		        
		  
		        // 释放资源  
		        in.close();  
		        in = null;  
		        
		        // 发送方帐号（open_id）  
	            String fromUserName = map.get("FromUserName");  
	            
	            System.out.println("  发送方帐号（open_id） : "+fromUserName);
	            // 公众帐号  
	            String toUserName = map.get("ToUserName");  
	            
	            System.out.println("  公众帐号   : "+toUserName );
	            // 消息类型  
	            String msgType = map.get("MsgType");  
	  
	            
	            System.out.println("   消息类型    : "+msgType);
	            
	            
	      	 
	            // 回复文本消息  
	            TextMessage textMessage = new TextMessage();  
	            textMessage.setToUserName(fromUserName);  
	            textMessage.setFromUserName(toUserName);  
	            textMessage.setCreateTime(new Date().getTime());  
	            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
	            textMessage.setFuncFlag(0);  
	  
	            // 文本消息  
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
	                respContent = "xy_您发送的是文本消息！";  
	            }  
	            // 图片消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
	                respContent = "xy_您发送的是图片消息！";  
	            }  
	            // 地理位置消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
	                respContent = "您发送的是地理位置消息！";  
	            }  
	            // 链接消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
	                respContent = "您发送的是链接消息！";  
	            }  
	            // 音频消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
	                respContent = "您发送的是音频消息！";  
	            }  
	            // 事件推送  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
	                // 事件类型  
	                String eventType = map.get("Event");  
	                // 订阅  
	                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
	                    respContent = "谢谢您的关注！";  
	                }  
	                // 取消订阅  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
	                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
	                }  
	                // 自定义菜单点击事件  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
	                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
	                }  
	            }  
	  
	            
	            textMessage.setContent(respContent);  
	            
	            
	            
	              xstream.alias("xml", textMessage.getClass());  
	            
	              respMessage = xstream.toXML(textMessage);  
	            
	          //2回送xml信息给信公众平台
	            
	            
			    
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
		//1解析微信公众平台发过来的xml信息
		return respContent;
		
		
		
		
		
		
		
		//return respMessage;
		
		
	}  
 
	
	public static String processRequest3(HttpServletRequest request ) throws IOException   {
		
		    String respMessage ="";
		    
		    String respContent = "请求处理异常，请稍候尝试！";   
		
		    System.out.println("  processRequest3  getContentLength  " +request.getContentLength());
			   
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
			   
			ServletInputStream in = request.getInputStream();
			
			String line = null;
			 
			StringBuilder sb = new StringBuilder();
			   
			   
			while((line = br.readLine())!=null){ 
            
				sb.append(line); 
            
			}
			
			   System.out.println("ServletInputStream流的信息是：  "+sb);
			   
			 
			    
			  
		//1解析微信公众平台发过来的xml信息
		  return respContent;		
		//return respMessage;
		
		
	}  
 
	
	public static String processRequest4(HttpServletRequest request ) throws IOException   {
		
	    String respMessage ="";
	    
	    String respContent = "请求处理异常，请稍候尝试！";   
	
	    System.out.println("  processRequest3  getContentLength  " +request.getContentLength());
		   
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
		   
		ServletInputStream in = request.getInputStream();
		
		String line = null;
		 
		StringBuilder sb = new StringBuilder();
		   
		   
		while((line = br.readLine())!=null){ 
        
			sb.append(line); 
        
		}
		
		   System.out.println("ServletInputStream流的信息是：  "+sb);
		   
		 
		    
		  
	//1解析微信公众平台发过来的xml信息
	  return respContent;		
	//return respMessage;
	
	
}  


	
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                @SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });
	
}
