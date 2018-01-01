<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<c:url value="/dir1/hello.jsp" var="myurl">
	<c:param name="username" value="Tom"/>
	<c:param name="age" value="10"/>
</c:url>
<br><a href="${myurl}">hello</a>
  </body>
</html>
