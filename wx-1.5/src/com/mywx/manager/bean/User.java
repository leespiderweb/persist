package com.mywx.manager.bean;

/**
 * @author spider
 *  本管理系统用户信息，用于验证身份信息，
 */
public class User {

	//用户ID，身份识别
	private int user_id;	
	
	//用户公司信息，实现多账号，分权限管理
	private int group_id;	
	
	//用户权限级别
	private int user_level;
	
	//用户名称
	private String user_name;
	
	//密码
	private String psd;		
	
	//用户名全称，企业名称，个人身份证名称等，注册主题
	private String fullname;	
	
	//用户状态，是否欠费，等等
	private String user_status;
		
	 
	//用户权限
	private int phone_number;
	
}
