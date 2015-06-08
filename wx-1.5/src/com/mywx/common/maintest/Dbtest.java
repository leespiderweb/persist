package com.mywx.common.maintest;

import java.util.Vector;

import com.mywx.manager.db.Article_Process;
import com.mywx.weixin.respon.bean.Article;

public class Dbtest {

	
   public void add_articel(){
	  
	   Article_Process ap = new Article_Process();
				
		Article art = new Article();
		
		art.setTitle("标题1");
		art.setDescription("测试信息，描述一，描述二，描述三");
		art.setUrl("url1");
		art.setPicUrl("picUrl111picUrl");
		
		ap.Add(art);
	   
   }
	
   public void get_articel(){
		  
	   Article_Process ap2 = new Article_Process();
				
		Vector vec_art = ap2.Get_Articles();
		
		 for(int i = 0 ; i  < vec_art.size() ; i++){
			 
			 Article art = (Article)vec_art.get(i);
			 
			    System.out.println("   : " +art.getId() );
				
				System.out.println("   : " + art.getTitle());
				
				System.out.println("   : " + art.getDescription());
				
				System.out.println("   : " + art.getUrl());
				
				System.out.println("   : " + art.getPicUrl());
				
		 }
		
		
		 
	   
   }
   
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dbtest dbt = new Dbtest();
		
		
		
		dbt.add_articel();
		
		dbt.get_articel();
	}

}
