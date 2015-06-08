package com.mywx.common.util;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class WeixinLogger {

	
	
	public static  Logger getLogger(Object obj){
		
		  
		 Logger logger = Logger.getLogger(Object.class.getName());
		 
		
		return logger;
	}
	
	
}
