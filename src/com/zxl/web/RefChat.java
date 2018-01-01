package com.zxl.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxl.domain.ChatItem;

public class RefChat extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext context = this.getServletContext();
		
		//--获取聊天信息chatmap
		Map<Integer,ChatItem> chatmap = (Map<Integer, ChatItem>) context.getAttribute("chatmap");
		if(chatmap == null){
			//--如果聊天信息为空，返回空的json
			response.getWriter().write("[]");
			return;
		}else{
			//--如果聊天信息不为空，则发送聊天信息
			//获取浏览器需要哪条记录之后的记录
			Integer count = Integer.parseInt(request.getParameter("count")==null?"0" : request.getParameter("count"));
			//组织json数据，将count记录之后的聊天记录组织为json格式
			StringBuffer buffer = new StringBuffer();
			buffer.append("[");
			synchronized (chatmap) {
				for(Map.Entry<Integer, ChatItem> entry : chatmap.entrySet()){
					if(entry.getKey()>count){
						buffer.append(entry.getValue());
						buffer.append(",");
					}
				}
			}
			String msgs = buffer.substring(0, buffer.length()-1)+"]";
			//发送聊天数据
			response.getWriter().write(msgs);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
