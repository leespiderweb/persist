package com.mywx.manager.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mywx.weixin.respon.bean.Article;




public class Article_Process {

	
	private Connection 		    ct=null;
	private PreparedStatement   ps=null;
	private ResultSet 			rs=null;
	
 
	
	public boolean Add(Article art){
		
		boolean Article_Process_is_Done = false;
		
	 
	      String Title = art.getTitle();  	    
	 
	      String Description = art.getDescription();  	    
	     
	      String PicUrl = art.getPicUrl();  	    
	    
	      String Url = art.getUrl();
	      
	      String str_sql = "insert into t_article(title,description,picurl,url) values( "+
	   		  		
		  		 " ' " +Title+
		  		
		  		 "' ,  ' " + Description+
		  		 " ',  ' " +PicUrl+
		  		  " ' , ' " + Url +
		  		  " ') ;"
		  		
		  		  ;
		
	      System.out.println(str_sql);
	      
	      
	      ConnDB conn = new ConnDB();
			
	      
	      try {
	    	  
	    	ct =new ConnDB().getConn();
	 		 
	  		ps=ct.prepareStatement(str_sql);
	  		
	  		if(ps.executeUpdate()==1){
	  			
	  			Article_Process_is_Done = true ;	
	  			
	  		}
	  		 
	  	} catch (SQLException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}finally{
	  		
	  		conn.myclose(ct, ps, rs);
	  		
	  	}
		
		return Article_Process_is_Done;
				
	}
	
	
	public boolean Update(Article art){
		
		boolean Article_Process_is_Done = false;
				
		return Article_Process_is_Done;
		
	}
	
	
	public boolean Delete(Article art){
		
		boolean Article_Process_is_Done = false;
		
		
		
		
		
		
		return Article_Process_is_Done;
		
		
		
	}
	
	
    public Article Get(int id){
		
    	Article art = new Article();
    	
		boolean Article_Process_is_Done = false;
		
		
		
		
		
		
		return art;
					
	}

    public Vector Get_Articles( ){
		
    	Vector vce_Art = new Vector();
    	
    	
    	ConnDB conn = new ConnDB();
		
	     String str_sql = "select * from t_article ";
			
	     ct =new ConnDB().getConn();
	     
		 try {
			 
			ps=ct.prepareStatement(str_sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()){	
				
				Article art = new Article();
			   
				 art.setId(rs.getInt(1));
			     art.setTitle(rs.getString(2));
			     art.setDescription(rs.getString(3));
			     art.setUrl(rs.getString(4));
			     art.setPicUrl(rs.getString(5));
							
				
				vce_Art.add(art);
				
			
			}
			
			return vce_Art;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
	  		
			conn.myclose(ct, ps, rs);
	  		
	  	}
		
		
		
		
		return vce_Art;
					
	}

    
//    public List<Article> Get(){
//		
//    	//List<Article> list_art = new List()<>;
//    	
//    	List list_art;
//    	Article art = new Article();
//    	
//		boolean Article_Process_is_Done = false;
//		
//		
//		
//		
//		
//		
//		return list_art;
//					
//	}
    

}
