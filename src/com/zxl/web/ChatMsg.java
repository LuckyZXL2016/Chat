package com.zxl.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxl.domain.ChatItem;

public class ChatMsg extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext context = this.getServletContext();
		
		//获取历史信息
		Integer count = (Integer)context.getAttribute("count") == null 
										? 0 : (Integer)context.getAttribute("count") ;
		Map<Integer,ChatItem> chatmap = (Map<Integer, ChatItem>) context.getAttribute("chatmap") == null 
										?  Collections.synchronizedMap(new LinkedHashMap<Integer, ChatItem>()) 
												:(Map<Integer, ChatItem>) context.getAttribute("chatmap");
		//组织javabean信息
		ChatItem item = new ChatItem();
		count = count + 1;
		item.setPos(count+"");
		item.setTime(new Date().toLocaleString());
		item.setUname(filter(
							((String)request.getSession().getAttribute("name")==null 
								? "ip:"+request.getRemoteAddr() 
										: "uname:"+(String) request.getSession().getAttribute("name")))
						);
		item.setMsg(filter(request.getParameter("msg")));
		
		//将信息加入chatmap
		synchronized (chatmap) {
			chatmap.put(count, item);
		}
		
		//存回ServletContext域
		context.setAttribute("count", count);
		context.setAttribute("chatmap", chatmap);
	}
	
	/**
	 * 对json格式的特殊字符进行转义
	 */
	  private static String filter(String message) {

	        if (message == null)
	            return (null);

	        char content[] = new char[message.length()];
	        message.getChars(0, message.length(), content, 0);
	        StringBuffer result = new StringBuffer(content.length + 50);
	        for (int i = 0; i < content.length; i++) {
	            switch (content[i]) {
	            case '<':
	                result.append("&lt;");
	                break;
	            case '>':
	                result.append("&gt;");
	                break;
	            case '&':
	                result.append("&amp;");
	                break;
	            case '\\':
	            	result.append("\\\\");
	                break;
	            case '/':
	            	result.append("\\/");
	                break;
	            case '"':
	            	result.append("\\\"");
	                break;    
	            case '\'':
	            	result.append("\\'");
	            	break;
	            case '\t':
	            	result.append("\\t");
	                break;
	            case '\f':
	            	result.append("\\f");
	                break;
	            case '\b':
	            	result.append("\\b");
	                break;
	            case '\n':
	            	result.append("\\n");
	                break;
	            case '\r':
	            	result.append("\\r");
	                break;
	            default:
	                result.append(content[i]);
	            }
	        }
	        return (result.toString());

	    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
