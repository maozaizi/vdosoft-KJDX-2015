<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
<title></title>
	<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" />
	<script src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">
	function goBack() {
		  window.location.href = "${pageContext.request.contextPath}/api/org/getInfo?id=${userInfo.orgId}";
	}
</script>
</head>
<!-- jquery验证start -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<!-- jquery验证end -->
<script type="text/javascript">
$(function($){
		$("#addForm").validate({
			rules: {
				userName: {
					required: true,
					maxlength: 50
				},
				userPwd: {
					required: true,
					minlength: 6,
					maxlength: 20
				},
				re_user_pwd: {
					required: true,
					minlength: 6,
					equalTo: "#userPwd"
				}
			}
		});
});
</script>
<body>
	<div class="span11">
		<div class="ListBlock">
				   <ul class="breadcrumb">
			          	 <li class="active">设置人员信息</li>
			        </ul>
			</div>
			<!-- 增加组织 -->
			<form method="post"  id="addForm"  action="${pageContext.request.contextPath }/api/org/setUserLogin" >
			   <input type="hidden" name="tempUrl" id="tempUrl" value="${pageContext.request.contextPath}/api/org/getInfo?id=${userInfo.orgId}"/>
			   <input type="hidden" name="userId" id="userId" value="${userInfo.userId}" />

			   <div class="form-horizontal">
	        	<div class="control-group">
					<label class="control-label" for="personName">登录名</label>
					<div class="controls">
						<input type="text" value = "${userInfo.userName}" name="userName" class="span2" id="userName" placeholder="请输入登录名"/>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">密码</label>
					<div class="controls">
						<input type="password" value="" name="userPwd" class="span2" id="userPwd" placeholder="请输入密码"/>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">请确认密码</label>
					<div class="controls">
						<input type="password" name="re_user_pwd" class="span2" id="re_user_pwd" placeholder="请确认密码"/>
					</div>
				</div>
				<div class="form-actions">
					<input class="btn btn-primary" type="submit" value="保存"/>
				  	<input class="btn" type="button"  onclick="goBack()" value="返回"/>
				</div>
			   </div>
			</form>
	</div>
</body>
</html>

