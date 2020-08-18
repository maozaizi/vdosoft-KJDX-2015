<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>数据字典项</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script src="${pageContext.request.contextPath}/js/webcalendar.js" type="text/javascript"></script>
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
				dataItemName: {
					required: true,
					maxlength: 50
				},
				dataItemValue: {
					required: true,
				}
			}
		});
});
</script>
	<body>
	<div class="row" id="divId">
		<div class="span2"></div>
		<div class="span10">
			<div class="row-fluid">
			        <ul class="breadcrumb">
			          <li class="active">您当前的位置：</li>
			          <li class="active">${navigation}>>添加数据项明细</li>
			        </ul>
		      </div>

			<form method="post"  id="addForm"  action="${pageContext.request.contextPath}/api/dictionary/addMx" >
				<input type="hidden" name="parentId" id="parentId" value="${param.parentId}"/>
				<input type="hidden" name="url" id="url" value = "${pageContext.request.contextPath}/api/dictionary/get"/>
				<div class="form-horizontal">
					<div class="control-group">
						<h3>明细信息添加</h3>
					</div>
					<div class="control-group">
						<label class="control-label" for="personName"><span style="color: Red">*</span>名称：</label>
						<div class="controls">
							<input type="text" class="span2" name="dataItemName" size="30" maxlength="50"  placeholder="请填写名称"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="personName"><span style="color: Red">*&nbsp;</span>值：</label>
						<div class="controls">
							<input type="text" class="span2" name="dataItemValue"  size="30" maxlength="50"  placeholder="请填写值"/>
						</div>
						</div>
					<div class="control-group">
						<label class="control-label" for="personName">编码：</label>
						<div class="controls">
							<input type="text" class="span2" name="dataItemCode"  size="30" maxlength="40"><span ></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="personName">属性1：</label>
						<div class="controls">
							<input type="text" name="p1" size="30"  class="span2" maxlength="40"/><span ></span>
						</div>
					</div>
						<div class="control-group">
						<label class="control-label" for="personName">属性2：</label>
						<div class="controls">
							<input type="text" name="p2" size="30"  class="span2" maxlength="40"/><span ></span>
						</div>
					</div>
						<div class="control-group">
						<label class="control-label" for="personName">属性3：</label>
						<div class="controls">
							<input type="text" name="p3" size="30"  class="span2" maxlength="40"/><span ></span>
						</div>
					</div>
						<div class="control-group">
						<label class="control-label" for="personName">属性4：</label>
						<div class="controls">
							<input type="text" name="p4" size="30"  class="span2" maxlength="40"/><span ></span>
						</div>
					</div>
						<div class="control-group">
						<label class="control-label" for="personName">属性5：</label>
						<div class="controls">
							<input type="text" name="p5" size="30"  class="span2" maxlength="40"/><span ></span>
						</div>
					</div>
						<div class="control-group">
						<label class="control-label" for="personName">属性6：</label>
						<div class="controls">
							<input type="text" name="p6" size="30"  class="span2" maxlength="40"/><span ></span>
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
						<input type="submit" class="btn btn-primary" value="保存"/>
						<input type="button" onclick="javascript:history.go(-1);" class="btn" value="取消"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	</body>
</html>

