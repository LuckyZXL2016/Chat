package com.zxl.domain;

/**
 * @author ZXL
 * 聊天保存的基本信息
 */
public class ChatItem {
	private String msg;
	private String time;
	private String uname;
	private String pos;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	@Override
	public String toString() {
		String str = "{msg:'"+msg+"',time:'"+time+"',uname:'"+uname+"',pos:'"+pos+"'}";
		return str;
	}
}
