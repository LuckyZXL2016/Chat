<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>

<style type="text/css">
.div{
	border:blue 1px solid;
	width:100%;
	height:400px;
	z-index:1;
	overflow:auto;
}
</style>

</head>
<body>
<div id="cdiv" class=div>
	<table border="1" width="100%">
		<tbody id="tbd">
		</tbody>
	</table>
</div>
	<form action="#">
		<textarea rows="5px" cols="60px" name="content" id="tent"></textarea>
		<input type="button" id="but" value="提交">
	</form>
</body>
<script>
	//--全局计数器,记录当前页面显示到了哪条记录
	var c = 0;
	//--每100毫秒触发ajax，访问服务器，获取聊天信息
	window.setInterval(ref, 100);
	function ref(){
		//参数c为当前页面显示到了哪条记录，服务器只要发送这条记录之后的数据就可以了
		$.post("${pageContext.request.contextPath}/servlet/RefChat",{count:c},function(data){
			//返回的数据中以json格式保存了聊天信息格式为[{msg:'msg',time:'time',uname:'uname',pos:'pos'},{另一条记录},....]
			$(eval("("+data+")")).each(function(i,ele){
				c=ele.pos;//--循环的过程中保存显示到了哪条记录，这样循环结束后c的值就是当前显示的最后一条记录的pos
				//显示当前记录
				var tr = $("<tr><td width='20%'>"+ele.uname+"</td><td>"+ele.msg+"</td><td width='20%'>"+ele.time+"</td></tr>");
				$(tr).appendTo($("#tbd"));
			});
		});
		//滚动条置底
		document.getElementById('cdiv').scrollTop=document.getElementById('cdiv').scrollHeight ;
	}
	
	//--点击提交按钮时，获取当前输入框中的值发送ajax请求，发送聊天数据，清空输入框
	$("#but").click(function(){
		var text = $("#tent").val();
		$.post("${pageContext.request.contextPath}/servlet/ChatMsg",{msg:text});
		$("#tent").val("");
	});
	
	
</script>
</html>