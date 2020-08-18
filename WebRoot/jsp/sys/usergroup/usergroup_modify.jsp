<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>用户组修改</title>
		<link href="${pageContext.request.contextPath}/css/fire/global.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/fire/layout.css" rel="stylesheet" type="text/css" />
		<script	src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
		<script type="text/javascript">
			function goBack() {
				window.location.href ="${param.url}";
		 	}
		 	function textCounter(field, countfield, maxlimit) {
				var s=$("#"+field).val().length;
				if(s>maxlimit){
					$("#"+field).val($("#"+field).val().substr(0,maxlimit));
				}else{
					var ss = maxlimit-parseInt(s);
					$("#"+countfield).html("还可输入" + ss + "个字符");
				}
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
	$("#addform").validate({
		rules: {
			usergroupname: {
				required: true,
				maxlength: 25
			}
		}
	});
});
</script>
<body>
	<div class="MainContent">
		<div class="ListBlock">
		<h3 class="cBlack">用户组修改</h3>
			<form method="post"   action="">
			    <input type="hidden" name="url" id="url" value="${param.url}"/>
				<div class="EditBox">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="2">
								用户组修改
							</th>
						</tr>
						<tr>
							<td class="EditBox_td1">
								<span style="color: Red">*&nbsp;</span>用户组名称：
							</td>
							<td>
								<input name="usergroupname" id="usergroupname" value="${userGroup.userGroupName}" type="text" class="ipt_text" size="30" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td class="EditBox_td1">
								备注：
							</td>
							<td colspan="3">
								<p class="muted">请输入1-200字的备注&nbsp;<span id="relable" class="text-success"></span></p>
								<input onkeyup="textCounter('userGroupRemark','relable','200');" name="usergroupremark" id="usergroupremark" value="${userGroup.userGroupRemark}" type="text" class="Input_200" />
						        <input name="usergroupid" id="usergroupid" value="${userGroup.userGroupId}" type="hidden"/>
						        <input name="createuser" id="createuser" value="${userGroup.createUser}" type="hidden"/>
						        <input name="createdate" id="createdate" value="${userGroup.createDate}" type="hidden"/>
						   </td>
						</tr>
					</table>
				</div>
				<div class="Temp-2">
					<input type="submit" class="Bbtn" value="保存"/>
					<input type="button" class="Bbtn" onclick="goBack();" value="返回"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

