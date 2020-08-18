<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>数据字典项</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
		<%--返回 --%>
		function goBack() {
		   	window.location.href = "${pageContext.request.contextPath}/api/dictionary/get";
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
	<!-- jquery验证start -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
	<!-- jquery验证end -->
	<script type="text/javascript">
	$(function($){
			$("#addForm").validate({
				rules: {
					dataItemName: {
						required: true,
						maxlength: 50
					},
					dataItemCode: {
						required: true,
					},
					orderby: {
						digits:true
					}
				}
			});
	});
	</script>
	</head>
	<body>
	<div class="container-fluid" id="divId">
		 <div class="row-fluid">
		        <ul class="breadcrumb">
		          <li class="active">您当前的位置：</li>
		          <li class="active">${navigation}>>添加数据项</li>
		        </ul>
	      </div>
		<div class="span2"></div>
		<div class="span10">


			<form method="post"  id="addForm"  action="${pageContext.request.contextPath}/api/dictionary/add" >
				<input type="hidden" name="parentId" id="parentId" value="${param.parentId}"/>
				<input type="hidden" name="url" id="url" value = "${pageContext.request.contextPath}/api/dictionary/get"/>
				<div class="form-horizontal">
					<div class="control-group">
						<h3>基本信息添加</h3>
					</div>
					<div class="control-group">
						<label class="control-label" for="personName"><span style="color: Red">*</span>名称：</label>
						<div class="controls">
							<input type="text" class="span2" name="dataItemName"   maxlength="50" placeholder="请填写名称"/>
						</div>
					</div>
					<!--
					<div class="control-group">
						<label class="control-label" for="personName"><span style="color: Red">*&nbsp;</span>值：</label>
						<div class="controls">
							<input type="text" class="span2" name="dataItemValue"  size="30"   maxlength="100" check="1"  placeholder="请填写值"><span class="help-inline">请填写值</span>
						</div>
					</div>
					 -->
					<div class="control-group">
						<label class="control-label" for="personName"><span style="color: Red">*</span>编码：</label>
						<div class="controls">
							<input type="text" class="span2" name="dataItemCode"  size="30" maxlength="40"><span ></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="personName">排序：</label>
						<div class="controls">
							<input type="text" class="span2" name="orderby"  size="30" maxlength="3"><span ></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="remark">描述：</label>
						<div class="controls">
							<p class="muted">请输入1-200字的描述&nbsp;<span id="relable" class="text-success"></span></p>
							<textarea onkeyup="textCounter('memo','relable','200');" id="memo" name="memo"  rows="3"></textarea>
						</div>
					</div>
					<div class="form-actions">
						<input class="btn btn-primary" type="submit" value="保存"/>
						<input type="button" onclick="javascript:history.go(-1);" class="btn" value="返回"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	</body>
</html>

