package com.mywx.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextInitListener
 *
 */
public class ContextInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	Properties props = new Properties(); 
        InputStream inputStream = null; 
        try { 
            inputStream = getClass().getResourceAsStream("/base.Properties"); 
            props.load(inputStream); 
            String sql_url = (String) props.get("sql_url"); 
            String abc = (String) props.get("abc"); 
            
            System.out.println("sql_url  "+sql_url);
            
            System.out.println("abc  "+abc);
            
        } catch (IOException ex) { 
            ex.printStackTrace(); 
        } 
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
