package com.weixin.api;
/* 对接微信基础功能接口
 * 功能1：验证签名，2015.5.17实现
 * 功能2：
 */
import java.security.MessageDigest;
import java.util.Arrays;

public class wx_utility {

private static String token = "spiderwx";

private static String EncodingAESKey = "IvCM1gr69p8NVYjBxbgeaJZWzMjC4UgRZ9z0KieljQp";

private static String AppID = "wx29fb1d7dde619ae3";

private static String AppSecret = "7c1e194b511980018431448b923ac1c4";

	
	public   static boolean checkweixinsignature(String signature  ,  String   timestamp,String nonce  ,String  echostr ){
		
		boolean signature_is_valiad = false;
		
//		System.out.println("signature from weixin   :  "+signature+"  , ");		
//		System.out.println("timestamp from weixin  :   "+timestamp+"  , ");
//		System.out.println("nonce from weixin   :  "+nonce+"  , ");
//		System.out.println("echostr from weixin   :  "+echostr+"  .");
		String[] arr = new String[]{token,timestamp,nonce};
		
		 // 将token、timestamp、nonce三个参数进行字典序排序 
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
			
			 
		content = null;  
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
       
		if( tmpStr.equals(signature.toUpperCase())){
	    	   		
			System.out.println("checkweixinsignature---签名验证成功 ");
			
			System.out.println("signature : "+signature+"  ,  timestamp : "+timestamp+"  , nonce :   "+nonce+ "   , echostr  :"  + echostr);
        	
			
	    	   signature_is_valiad = true;
	    	   
	       }  
        
        return signature_is_valiad;
		
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
