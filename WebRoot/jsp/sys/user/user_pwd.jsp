<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/jsp/config/taglib.jsp" %>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>用户密码修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base target="_self"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var flag = ${flag};
				if (flag==1){
				  alert("修改成功！");
				  window.close();
				};
		});
			function sub(){
				$("#pwdForm").submit();
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
		$("#pwdForm").validate({
			rules: {
				userPwd: {
					required: true,
				},
				newPwd: {
					required: true,
					minlength: 6,
					maxlength: 18
				},
				renewPwd: {
					required: true,
					equalTo: "#newPwd"
				}
			}
		});
});
</script>
   <body>
   <div class="MainContent">
		<div class="ListBlock">
		<h3 class="cBlack">修改用户密码</h3>
		<form method="post"  id="pwdForm"  action="${pageContext.request.contextPath}/api/login/updatePwd">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" >原始密码：</label>
					<div class="controls">
						<input type="password" id="userPwd"  name="userPwd" class="span2" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" >新密码：</label>
					<div class="controls">
						<input type="password" id="newPwd"  name="newPwd" class="span2" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" >重复密码：</label>
					<div class="controls">
						<input type="password" id="renewPwd"  name="renewPwd" class="span2" />
					</div>
				</div>
				<div class="form-actions">
					<input type="submit" class="btn btn-primary" value="保存"/>
					<input type="button" class="btn" onclick="javascript:window.close();" value="关闭"/>
				</div>
			</div>
			</form>
		</div>
</div>

<script type="text/javascript">
<c:if test="${not empty errMsg}">
alert("${errMsg}");
</c:if>
</script>

  </body>
</html>
