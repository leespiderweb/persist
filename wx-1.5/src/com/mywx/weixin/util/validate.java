package com.mywx.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mywx.common.util.WeixinLogger;
import com.mywx.weixin.api.Xyforce;

 



public class validate {

	//static Logger logger = Logger.getLogger(validate.class.getName());
	
	 Logger logger = WeixinLogger.getLogger(Xyforce.class);
	 
	public   boolean check_signature(HttpServletRequest request,String token){
		
		boolean signature_is_valid = false;
		
		
		
		//System.out.println(this.getClass()+" line   18     验证签名!"); 
		//System.out.println(this.getClass()+"     getContentLength  line 19  " +request.getContentLength());
				
		// 微信加密签名
		String signature = request.getParameter("signature");
		
		// 时间戳 
		String timestamp = request.getParameter("timestamp");
		
		// 随机数  
		String nonce = request.getParameter("nonce");
		

		//System.out.println( "  signature  "+ signature); 
		//System.out.println( " timestamp  "+ timestamp); 
		//System.out.println( "  nonce "+ nonce ); 

		//if(signature==null || timestamp==null || nonce==null || echostr==null){  
			
			if(signature==null || timestamp==null || nonce==null ){  
			       
            //这几个参数为空时，排序会报错。  
				
				logger.info("parameter is null! 参数为空 ,验证失败");	
		 
             
        }  else {
        	
        	//logger.info("signature  : "+signature);	   		  
        	//logger.info(" timestamp  : "+timestamp);        	
        	//logger.info(" nonce  :   "+nonce);
        	
        	String[] arr = new String[]{token,timestamp,nonce};
        	
        	Arrays.sort(arr);
        	
        	StringBuilder content = new StringBuilder();
    		
    		for(int i=0; i < arr.length; i++){
    			
    			content.append(arr[i]);
    			
    		}
    			
    			MessageDigest md = null;
    			
    			String tmpStr = null;
    			
    			
    			try {
    				
    				md = MessageDigest.getInstance("SHA-1");
    				// 将三个参数字符串拼接成一个字符串进行sha1加密  
    				byte[] digest = md.digest(content.toString().getBytes());
    				
    				tmpStr = byteToStr(digest);
    				//System.out.println("local-sha :  "+tmpStr+"  .");
    				
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			 
    			//System.out.println("checkweixinsignature  72  ---tmpStr ：           " +tmpStr);
    			//System.out.println("checkweixinsignature  73  ---signature.  "+signature.toUpperCase());
    		content = null;  
            // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
           
    		if( tmpStr.equals(signature.toUpperCase())){
    	    	   	
    			logger.info("---签名验证成功");	
    			// System.out.println(this.getClass()+ " ---签名验证成功 ");
    			
    			//System.out.println("signature : "+signature+"  ,  timestamp : "+timestamp+"  , nonce :   "+nonce);
            	
    			
    			signature_is_valid = true;
    	    	   
    	       }  else{
    	    	   
    	    	   logger.info("签名验证失败了");	
    	    	   //System.out.println(this.getClass()+" ---签名验证失败了 ");  
    	    	   
    	       }
    		
        
        }
		
		
		return signature_is_valid;
	}
	
	
	


	
	private static boolean check_signature(String signature  ,  String   timestamp,String nonce ){
		
		boolean signature_is_valiad = false;
		

    	
		
		return  signature_is_valiad  ;
		
	}
	
	
	//将字节数组转换为十六进制字符串 
	private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
	
	
	//将字节转换为十六进制字符串
	private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    } 
	
}
