<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/jsp/public/notlimit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
 <title>用户重复登录</title>
<link href="${pageContext.request.contextPath }/css/right.css" rel="stylesheet" type="text/css" />
</head>

  <body>
   您的用户已经在线: <br>
   用户名：admin<br>
   IP：192.168.51.254<br>
   登录时间：2010-12-12 23:11:24<br>
   <input value="强制下线" type="button" onclick="location.href='${pageContext.request.contextPath}/index.jsp'">&nbsp;
   <input value="退出" type="button" onclick="location.href='${pageContext.request.contextPath}/index.jsp'"><br>
  </body>
</html>
