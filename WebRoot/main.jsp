<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.brick.data.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.io.*"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>信息平台</title>

<link href="${pageContext.request.contextPath }/css/pure.css" rel="stylesheet" type="text/css">
<!--[if lte IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/grids-responsive-old-ie-min.css">
<![endif]-->
<!--[if gt IE 8]><!-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/grids-responsive.css">
<!--<![endif]-->

<style type="text/css">

* {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box
}
*:before,
*:after {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box
}

/* --------------------------
 * Element Styles
 * --------------------------
*/

html, body {
	height: 100%;
	overflow-y: hidden
}
body {
	padding: 0;
	margin: 0;
	position:relative;
    min-width: 320px;
    color: #777
}
.top {
	height: 50px;
}
.side-bar {
	position:absolute;
	width:200px;
	top:0;
	bottom:0;
	background-color:#efefef;
	overflow:hidden;
}
.main {
	padding-left: 200px;
}
.layout {
    position: relative;
}

</style>

<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
 
<script type="text/javascript">
	function handleResize() {
	var h = $(window).height();
			$('#autoheight').css({'height':h -46 +'px'});
	}
	$(function(){
			handleResize();
	
			$(window).resize(function(){
			handleResize();
		});
	});
</script>

</head>
<%
	IMap<String,Object> userMap=(IMap<String,Object>)request.getSession().getAttribute("userMap");
	if(userMap==null){
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("<script>alert('尚未登录或登陆超时，请重新登陆后使用！');location.href='"+request.getContextPath()+"/api/login/tologin';</script>");
	}
%>

<body>

	<div class="top">
		<iframe frameborder="0" scrolling="auto" src="${pageContext.request.contextPath}/jsp/top.jsp" id="topFrame" name="topFrame" width="100%" height="100%" title="topFrame" frameborder="no"></iframe>
	</div>

	<div class="layout">
	
		<div class="side-bar">
			<iframe frameborder="0" scrolling="auto" src="${pageContext.request.contextPath}/api/login/getAuthTreeList?userName=${userMap.userName}&userPwd=${userMap.userPwd}" id="xfjLeftFrame" name="xfjLeftFrame" width="100%" height="100%" frameborder="no"></iframe>
		</div>
		
		<div id="autoheight" class="main">
			<iframe frameborder="0" scrolling="auto" id="rightFrame" name='rightFrame' width="100%" height="100%" src="${pageContext.request.contextPath}/api/project/list"></iframe>
		</div>
		
	</div>
                        
</body>
</html>

