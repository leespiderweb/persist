package com.mywx.manager.db;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;



public class ConnDB {

	private Connection 		    conn=null;
	private PreparedStatement   ps=null;
	private ResultSet 			rs=null;
	
	public  Connection getConn(){
		
		try{
			
			Class.forName("org.postgresql.Driver").newInstance();
	     // String sqlurl="jdbc:microsoft:sqlserver://192.168.1.100:1433;databaseName=OA_4";
	     // String sqlurl="jdbc:microsoft:sqlserver://192.168.1.100:1433;databaseName=OA_4";
	        String sqlurl="";
	       // sqlurl="jdbc:postgresql://localhost/mymenu";
	        sqlurl="jdbc:postgresql://localhost/Xyforce";
	        //sqlurl="jdbc:postgresql://localhost/ex";
	        //sqlurl="jdbc:postgresql://localhost/sx";
	        //sqlurl="jdbc:postgresql://localhost/hsdya";
	        //121.40.70.47
	          //sqlurl="jdbc:postgresql://121.40.70.47/dywsx";
	        
	        conn=DriverManager.getConnection(sqlurl,"postgres","spider@2010");				
			}
		catch(Exception ex){				
							ex.printStackTrace();
						    }
		return conn;			
		}	
	

	
//未实现
	public void myclose ( Connection   conn ,	 PreparedStatement  ps,  ResultSet rs){
		
		try {
	     	 if(rs!=null){
	  		    rs.close();
	             rs=null;
	         }
	        if(ps!=null){
	  		    ps.close();
	          	ps=null;
	         }
	        if(conn!=null){
	        	conn.close();
	        	conn=null;
	        }  	  		   
	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		
		
		
	}
	
	
	
}
