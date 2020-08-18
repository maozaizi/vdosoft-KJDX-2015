<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
<title></title>
<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">
function goBack() {
	  window.location.href = "${pageContext.request.contextPath}/api/org/getInfo?organizationDetailId=${param.orgId}";
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
				postName: {
					required: true,
					maxlength: 50
				},
				postCode: {
					required: true,
				}
			}
		});
});
</script>
<body>

	<div class="MainContent">
		<div class="ListBlock">
		    		<ul class="breadcrumb">
                       <li class="active">添加岗位</li>
			        </ul>
		</div>
			<!-- 增加组织 -->
			<form method="post"  id="addForm"  action="${pageContext.request.contextPath }/api/org/addPostInfo" >
			<input type="hidden" name="orgId" id="orgId" value="${param.orgId}"/>
			<input type="hidden" name="tempUrl" id="tempUrl" value="${pageContext.request.contextPath}/api/org/getInfo"/>

			<div class="form-horizontal">
	        	<div class="control-group">
					<label class="control-label" for="personName">岗位名称</label>
					<div class="controls">
						<input type="text" name="postName" class="span2" id="postName" maxlength="50" placeholder="请输入岗位名称"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="personEducational">岗位属性</label>
					<div class="controls">
						<select class="span2" name="postCode" id="postCode" >
						<c:forEach var="item" items="${postList}">
		              	<option value="${item.AUTHGROUPID}">${item.AUTHGROUPNAME}</option>
		                </c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remark">岗位职责</label>
					<div class="controls">
						<p class="muted">请输入1-500字的岗位职责&nbsp;<span id="relable" class="text-success"></span></p>
						<textarea onkeyup="textCounter('postDuties','relable','500');" rows="6" cols="39" name="postDuties" id="postDuties"></textarea>
					</div>
				</div>
				<div class="form-actions">
					<input type="submit" class="btn btn-primary" value="保存"/>
					<input type="button" onclick="goBack()" class="btn" value="取消"/>
				</div>
			</div>
			  </form>
		 </div>
</body>
</html>

