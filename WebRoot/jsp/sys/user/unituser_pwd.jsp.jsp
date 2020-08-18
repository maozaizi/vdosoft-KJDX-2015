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
		<link href="${pageContext.request.contextPath}/css/fire/global.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/fire/layout.css" rel="stylesheet" type="text/css" />
		<script	src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
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
			<input type="hidden" name="url" value="${pageContext.request.contextPath}/api/mywork/getMyWorkUserList"/>
			<div class="EditBox">
				<table width="45%"  border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="EditBox_td1">原始密码：</td>
						<td>
						  	<input  type="password"  class="Input_20" name="userPwd"/>
						</td>
					</tr>
					<tr>
							<td class="EditBox_td1">新密码：</td>
							<td>
							  	<input  type="password"  class="Input_20" name="newPwd"/>
							</td>
						  </tr>
						  <tr>
							<td class="EditBox_td1">重复密码：</td>
							<td>
							  	<input  type="password"  class="Input_20" name="renewPwd"/>
							</td>
						  </tr>
						</table>
					</div>
					<div class="Temp-2">
						<input class="Bbtn" type="submit" value="保存" />
						<input class="Bbtn" type="button" onclick="location.href='${pageContext.request.contextPath}/api/mywork/getMyWorkUserList'" value="返回"/>
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
