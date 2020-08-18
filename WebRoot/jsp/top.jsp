<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp"%>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>top.jsp</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap-theme.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${pageContext.request.contextPath}/js/jquery.js"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
  <script src="${pageContext.request.contextPath}/js/respond.js"></script>
<![endif]-->

<script type="text/javascript">
	function toPage(top,left,right){
		document.getElementById("1").className="";
		//document.getElementById("3").className="";
		 //document.getElementById("2").className="";
		document.getElementById(top).className="nav_avtive";
		
		if(left.trim()!=""){
		    parent.frames["xfjLeftFrame"].location.href=left;
		}
		parent.frames["rightFrame"].location.href=right;
	}
	
	function todes(){
		var url = "${pageContext.request.contextPath}/welcome.jsp";
		window.parent.window.location.href="${pageContext.request.contextPath}/api/login/loginOut?url="+url;
	}
	function showMessage(count){
	$("#infoMesssage").html("");
	document.getElementById("infoMesssage").className="";
	if (count>0){
	  document.getElementById("infoMesssage").className="sys_message";
	  $("#infoMesssage").html("您有"+count+"条短消息未查看！");
	  }
	}
	//function toupdatePwd(){
	//	window.showModalDialog("${pageContext.request.contextPath}/api/login/toupdatePwd?tmp=" + Math.round(Math.random() * 10000),"","dialogWidth=400px;dialogHeight=350px;");
	//}
</script>
</head>

<body>


<div role="navigation" class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand">纵向科研项目经费管理系统 （beta 0.1.6）</a>
		</div>
		
		<div class="navbar-collapse collapse" id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="####" title="Hello ${sessionScope.userMap.name}">${sessionScope.userMap.name}</a></li>
				<!-- <li><a href="###" onclick="toupdatePwd();" title="修改密码">修改密码</a></li> -->
				<li><a href="javascript:todes();" title="退出登录">退出登录</a></li>
			</ul>
		</div>
		
	</div>
</div>

</body>
</html>