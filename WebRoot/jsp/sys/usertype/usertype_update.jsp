<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
 <title>用户分类修改</title>
<link href="${pageContext.request.contextPath }/css/right.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
</head>
<!-- jquery验证start -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<!-- jquery验证end -->
<script type="text/javascript">
$(function($){
	$("#typeForm").validate({
		rules: {
			userTypeName: {
				required: true,
				maxlength: 25
			},
			userTypeCode: {
				required: true
			}
		}
	});
});
</script>
   <body>
   <div class="div_box">
	<div class="table_title">
		用户分类修改
	</div>
	<form method="post"  id="typeForm"  action="${pageContext.request.contextPath}/api/login/updateUsertype" >
	<input  type="hidden"  name="url" value="${pageContext.request.contextPath}/api/login/usertypeList?currentPageNO=${param.currentPage}"/>
		<table cellspacing="1" cellpadding="0" class="table_box table_add">
				  <tr>
					<td class="ltext">分类名称</td>
					<td class="rtext">
					        <input  type="hidden" name="id" value="${usertype.userTypeId}" />
					  		<input  type="text"  class="shuru" name="userTypeName" value="${usertype.userTypeName}" maxlength="25" />
					</td>
				  </tr>
				   <tr>
					<td class="ltext">分类代码</td>
					<td class="rtext">
					  	<input  type="text"  class="shuru" name="userTypeCode" value="${usertype.userTypeCode}" maxlength="25" readonly="readonly"/>
					</td>
				  </tr>
				 <tr>
					<td class="ltext">备注</td>
					<td class="rtext">
					  	<input  type="text"  class="shuru" name="userTypeRemark" value="${usertype.userTypeRemark}" maxlength="25"/>
					</td>
				  </tr>
				  <tr>
					<td class="ltext">&nbsp;</td>
					<td class="rtext">
					    <input type="submit" value="保&nbsp;存"  class="btn"/>&nbsp;
						<input type="button" value="返&nbsp;回"  onclick="window.location.href='${pageContext.request.contextPath}/api/login/usertypeList?currentPageNO=${param.currentPage}';" class="btn"/>&nbsp;
					</td>
				  </tr>
				</table>
	</form>
</div>
  </body>
</html>
