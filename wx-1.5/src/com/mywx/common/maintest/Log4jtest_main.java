package com.mywx.common.maintest;

//import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class Log4jtest_main {

	static Logger logger = Logger.getLogger(Log4jtest_main.class.getName());
   
	
	public static void main(String[] args) {


		logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message."); 
		
		
		
    }

}
