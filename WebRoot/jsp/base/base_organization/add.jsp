<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<!-- jquery验证start -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
	<!-- jquery验证end -->

	<script type="text/javascript">
		function goBack() {
		      var id = document.getElementById("parentId").value;
			  window.location.href = "${pageContext.request.contextPath}/api/baseorganization/list?";
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
<script type="text/javascript">
	$(function($){
			$("#addForm").validate({
				rules: {
					orgName: {
						required: true,
						maxlength: 25
					}
				}
			});
	});
</script>
<body>
	<div class="container-fluid">
  		<div class="span12">
	        <ul class="breadcrumb">
	          <li class="active">基础设置 / </li>
	          <li class="active">机构设置 / </li>
	          <li class="active">机构添加</li>
	        </ul>
		</div>
	  <div class="span12">
	     <form method="post"  id="addForm"  action="${pageContext.request.contextPath }/api/baseorganization/save" target="rightFrame">
			<input type="hidden" name="parentId" id="parentId" value="${param.id}"/>
			<input type="hidden" name="tempUrl" id="tempUrl" value = "${pageContext.request.contextPath}/api/org/toMain"/>

		 <div class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="orgName">名称</label>
				<div class="controls">
					<input type="text" class="span2" id="orgName" name="orgName" placeholder="请输入组织名称">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="memo">组织描述</label>
				<div class="controls">
					<p class="muted">请输入1-200字的描述&nbsp;<span id="relable" class="text-success"></span></p>
					<textarea rows="3" onkeyup="textCounter('memo','relable','200');" name="memo" id="memo"></textarea>
				</div>
			</div>

			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="保存"/>
			</div>
		</div>
		</form>

	  </div>
	</div>
</body>
</html>

