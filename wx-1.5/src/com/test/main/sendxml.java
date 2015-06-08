package com.test.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.bean.message.resp.TextMessage;
import com.weixin.util.MessageUtil;

/**
 * Servlet implementation class sendxml
 */
public class sendxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendxml() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TextMessage textMessage = new TextMessage();
		
		textMessage.setToUserName("xyforce");

		textMessage.setFromUserName("13829918230");

		textMessage.setContent("hard to say hi");

		textMessage.setFuncFlag(0);

		textMessage.setMsgType("text");
		
		textMessage.setCreateTime(20150520);
		
		MessageUtil.textMessageToXml(textMessage);
		
		//request.setAttribute(arg0, arg1);
		
		request.getRequestDispatcher("").forward(request, response);
		

	}

}
