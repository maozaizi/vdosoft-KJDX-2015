<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
	<title>修改模块</title>

	<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.1.7.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		function goBack() {
			window.location.href = "${param.actionUrl}?expandId=${auth.authId}";
		}

		function init() {
			var selected = "${auth.authType}";
			var authType = document.getElementById("authType");
			for (var i = 0; i < authType.length; i++) {
				if (authType.options[i].value == selected) {
					authType.options[i].selected = true;
				}
			}
		}
	</script>

	</head>
<body onload="init()">
    <div class="row-fluid">
	    <ul class="breadcrumb">
			<li class="active">您当前的位置：</li>
			<li class="active">${navigation}</li>
        </ul>
    </div>
	<div class="row">
	  <div class="span0"></div>
	  <div class="span12">
	     <form id="addForm" name="addForm" method="post" action="" >
			<input type="hidden" name="actionUrl" id="actionUrl" value="${param.actionUrl}"/>
			<input type="hidden" name="authId" id="authId" value="${auth.authId}"/>
			<input type="hidden" name="authGrade" id="authGrade" value="${auth.authGrade}"/>
			<c:if test="${empty auth.authIcon}">
			   <input type="hidden" name="authIcon" id="authIcon_hidden" value="fa fa-cube"/>
			</c:if>
			<c:if test="${not empty auth.authIcon}">
			   <input type="hidden" name="authIcon" id="authIcon_hidden" value="${auth.authIcon}"/>
			</c:if>
		 <div class="form-horizontal">
		     <c:if test="${'3'== auth.authGrade}">
		         <div class="control-group">
					<label class="control-label" for="authName">权限名称</label>
					<div class="controls">
				        <input type="text" class="span3" id="authName" name="authName" value="${auth.authName}" placeholder="请输入权限名称" maxlength="50" required/>
					</div>
				</div>
		     </c:if>
		    <c:if test="${'3'!= auth.authGrade}">
			<div class="control-group">
				<label class="control-label" for="authName">权限名称</label>
				<div class="controls">
				      <div class="btn-group input-prepend">
				        	<span class="add-on"><i id="authIcon" class="${auth.authIcon}"></i></span>
						    <input type="text" class="span3" id="authName" name="authName" value="${auth.authName}" placeholder="请输入权限名称" maxlength="50" required/>
					  </div>
				</div>
			</div>

			<div class="control-group">
					<label class="control-label" for="">设置图片</label>
					<div class="controls">
			                <div class="btn-group">
			                  <a class="btn btn-success active" href="####"><i class="fa fa-cube"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-cubes"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-database"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-book"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-briefcase"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-credit-card"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-cogs"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-cog"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-flag"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-shopping-cart"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-gavel"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-rss-square"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-trophy"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-tasks"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-sitemap"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-bar-chart-o"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-microphone"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-volume-up"></i></a>
			                  <a class="btn btn-default" href="####"><i class="fa fa-truck"></i></a>
			                </div>
		             </div>
				</div>
			</c:if>
			<div class="control-group">
				<label class="control-label" for="authRemark">权限描述</label>
				<div class="controls">
					<textarea rows="3" name="authRemark" id="authRemark" maxlength="150">${auth.authRemark }</textarea>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">分类</label>
				<div class="controls">
					<select name="authType" id="authType" class="span2" disabled="disabled">
						<option value="0">模块</option>
				  		<option value="1">页面</option>
				  		<option value="2">控件</option>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="url">URL</label>
				<div class="controls">
					<input type="text" class="span4" name="url" value="${auth.url }" id="url" placeholder="请输入URL"  maxlength="100"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="authCode">权限code</label>
				<div class="controls">
					<input type="text" class="span2" name="authCode" value="${auth.authCode }" id="authCode" placeholder="请输入权限code" maxlength="50"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="authOrder">排序</label>
				<div class="controls">
					<input name="authOrder" id="authOrder" value="${auth.authOrder }" type="text" class="span2" placeholder="请输入排序" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					<span class="help-inline">请输入排序</span>
				</div>
			</div>
			 <div class="form-actions">
				<button type="submit" class="btn btn-primary">保存</button>
				<button type="button" class="btn" onclick="goBack();">取消</button>
			</div>
		</div>
		</form>

	  </div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<script type="text/javascript">
$(function($){
//设置权限名称前的图片
$(".btn-group a").click(function(){
    $("#authIcon").attr("class",$(this).find("i").first().attr("class"));
    $("#authIcon_hidden").val($(this).find("i").first().attr("class"));
    $(".btn-group a").each(function(){
        if("btn btn-success active" ==  $(this).attr("class")) {
             $(this).attr("class","btn btn-default");
        }
    });
    $(this).attr("class","btn btn-success active");
    $this = $(this);

});




	$("#addForm").validate({
		rules: {
			authName: {
				required: true
			},
			authRemark:{
			    required: true
			},
			authOrder:{
			    number:true
			}
		},
		submitHandler:function() {
		    //提交
			if (confirm("请确定保存权限信息吗？")) {
				document.forms[0].action = "${pageContext.request.contextPath }/api/auth/modify";
				document.forms[0].submit();
			}
    }
});
});
</script>
</body>
</html>

