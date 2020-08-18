<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/jsp/config/taglib.jsp" %>
<%@include file="/jsp/public/notlimit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
 <title>用户注册</title>
<link href="${pageContext.request.contextPath}/css/right.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
</head>
<!-- jquery验证start -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<!-- jquery验证end -->
<script type="text/javascript">
$(function($){
		$("#regForm").validate({
			rules: {
				userName: {
					required: true,
					chrnum:true,
					minlength: 5,
					maxlength: 25
				},
				userPwd: {
					required: true,
					chrnum:true,
					minlength: 6,
					maxlength: 18
				},
				reuserPwd: {
					required: true,
					minlength: 6,
					equalTo: "#userPwd"
				},
				email: {
					checkEmail: true
				}
			}
		});
});
</script>
   <body>
   注册
   <div class="right_box">
	<!-- 添加栏目 -->
	<form method="post"  id="regForm"  action="${pageContext.request.contextPath}/api/login/reg" >

		<table border="0" cellspacing="0" cellpadding="0">
		<input  type="hidden"  name="url" value="${pageContext.request.contextPath}/api/login/tologin"/>
				  <tr>
					<td width="200" class="list_name">用户名</td>
					<td class="endright right_text" colspan="3">
					  	<input  type="text"  class="shuru" name="userName" maxlength="16" style="ime-mode:disabled;" value="${param.userName}"/>
					</td>
				  </tr>

				 <tr>
					<td width="200" class="list_name">密码</td>
					<td class="endright right_text" colspan="3">
					  	<input  type="password"  class="shuru" name="userPwd"  maxlength="16" style="ime-mode:disabled;"/>
					</td>
				  </tr>

				  <tr>
					<td width="200" class="list_name">重复密码</td>
					<td class="endright right_text" colspan="3">
					  	<input  type="password"  class="shuru" name="reuserPwd"  maxlength="16" style="ime-mode:disabled;"/>
					</td>
				  </tr>

				   <tr>
					<td width="200" class="list_name">Email</td>
					<td class="endright right_text" colspan="3">
					  	<input  type="text"  class="shuru" name="email"  maxlength="50" style="ime-mode:disabled;"  value="${param.email}"/>
					</td>
				  </tr>
				  <tr>
					<td width="200" class="list_name">&nbsp;</td>
					<td  class="endright right_text" colspan="4">
					    <input type="submit" value="注&nbsp;册" />&nbsp;
						<input type="button" value="返&nbsp;回"  onclick="location.href='${pageContext.request.contextPath}/index.jsp';"/>&nbsp;
					</td>
				  </tr>

				</table>
	</form>
</div>
<script type="text/javascript">
<c:if test="${not empty errMsg}">
alert("${errMsg}");
</c:if>
</script>
  </body>
</html>
