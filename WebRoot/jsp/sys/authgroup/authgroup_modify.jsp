<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改权限组</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.1.7.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		function goBack() {
			window.location.href = "${param.url}";
		}
	</script>
</head>
<body>
<div class="container-fluid">
	<div class="span10">
	<div class="row-fluid">
	        <ul class="breadcrumb">
	          <li class="active">系统设置 / </li>
	          <li class="active">权限组管理 / </li>
	          <li class="active">修改权限组</li>
	        </ul>
	   </div>

			<form id="modifyForm" name="modifyForm" method="post" action="${pageContext.request.contextPath }/api/authgroup/modify" >
			<input type="hidden" name="url" id="url" value="${param.url}"/>
			<input type="hidden" name="authGroupId" id="authGroupId" value="${authGroup.authGroupId}"/>
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="personName"><span style="color: Red">*</span>权限组名称</label>
					<div class="controls">
						<input type="text" class="span2" name="authGroupName" value="${authGroup.authGroupName }" id="authGroupName"  size="30" check="1" placeholder="请填写权限组名称" maxlength="50" required/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remark">描述</label>
					<div class="controls">
						<textarea name="authGroupRemark" id="authGroupRemark" rows="3"  maxlength="150" required>${authGroup.authGroupRemark }</textarea>
					</div>
				</div>
				<div class="form-actions">
						<button type="submit" class="btn btn-primary">保存</button>
						<button type="button" class="btn" onclick="goBack()">取消</button>
				</div>
				</div>
			  </form>
		</div>
	</div>
<!--鼠标划过tr背景色改变-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<script type="text/javascript">
$(function($){
	$("#modifyForm").validate({
		rules: {
			authGroupName: {
				required: true
			},
			authGroupRemark:{
			    required: true
			}
		}
});
});
</script>
</body>
</html>

