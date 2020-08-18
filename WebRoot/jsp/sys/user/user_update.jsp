<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
	 	<title>用户信息修改</title>
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
		$("#userForm").validate({
			rules: {
				userName: {
					required: true,
					minlength: 5,
					maxlength: 25
				},
				email: {
					checkEmail: true
				}
			}
		});
});
</script>
   <body>
   <div class="MainContent">
		<div class="ListBlock">
		<h3 class="cBlack">修改用户信息</h3>
			<form method="post"  id="userForm"  action="${pageContext.request.contextPath}/api/login/updateUser" >
			<input  type="hidden"  name="url" value="${param.url}"/>
			<input  type="hidden"  name="userId" value="${usersMap.userId}"/>
			<div class="EditBox">
				<table width="100%" cellspacing="0" cellpadding="0">
				         <tr>
				            <th colspan="2">修改用户信息</th>
				         </tr>
				         <tr>
							<td class="EditBox_td1"><span style="color: Red">*&nbsp;</span>用户名：</td>
							<td>
								<input  type="text"  name="userName" value="${usersMap.userName}" disabled="true" class="ipt_text" size="30" maxlength="25"/>
							</td>
						  </tr>
						   <tr>
							<td class="EditBox_td1">Email：</td>
							<td>
							  	<input  type="text"  value="${usersMap.email}" class="ipt_text" size="30"/><span id="emailmsg" style="color: red;"></span>
							</td>
						  </tr>
						   <tr>
							<td class="EditBox_td1">用户类型：</td>
							<td>
							  	<select name="userTypeId">
							  	<c:forEach var="type" items="${usertypes}">
					      		<option value="${type.userTypeId}" ${usersMap.userTypeId==type.userTypeId?"selected='selected'":""}>${type.userTypeName}</option>
					      		</c:forEach>
							  	</select>
							</td>
						  </tr>
						  <tr>
							<td class="EditBox_td1"><span style="color: Red">*&nbsp;</span>审核状态：</td>
							<td>
							  	<input  type="radio"  name="verifyState" value="0" ${usersMap.verifyState=="0"?"checked='checked'":""}/>未审核
							  	<input  type="radio"  name="verifyState" value="1" ${usersMap.verifyState=="1"?"checked='checked'":""}/>已审核
							</td>
						  </tr>
						</table>
					</div>
					<div class="Temp-2">
						<input class="Bbtn" type="submit" value="保存"/>
						<input class="Bbtn" type="button" onclick="location.href='${pageContext.request.contextPath}/api/login/userList'" value="返回"/>
					</div>
			</form>
		</div>
	</div>
  </body>
</html>
