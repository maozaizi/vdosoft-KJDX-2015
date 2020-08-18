<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
	<title>添加模块</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.1.7.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
	//表单验证
		function doChange() {
			var authType = document.getElementById("authType");
			var typeValue;
			for (var i = 0; i < authType.length; i++) {
				if (authType.options[i].selected == true) {
					typeValue = authType.options[i].value;
				}
			}
			if (typeValue == "0") {
				document.getElementById("url").readOnly = true;
			} else {
				document.getElementById("url").readOnly = false;
			}
		}

		function init() {
			var authType = document.getElementById("authType");
			var typeValue;
			for (var i = 0; i < authType.length; i++) {
				if (authType.options[i].selected == true) {
					typeValue = authType.options[i].value;
				}
			}
			if (typeValue == "0") {
				document.getElementById("url").readOnly = true;
			}
		}

		function goBack() {
			if ("${authGrade}" == 1) {
				window.location.href = "${param.actionUrl}";
			} else {
				window.location.href = "${param.actionUrl}?expandId=${parentAuthId}";
			}
		}
	</script>

	</head>
<body onload="init()">
    <div class="row-fluid">
	    <ul class="breadcrumb">
			<li class="active">您当前的位置:</li>
			<li class="active">${navigation}</li>
        </ul>
    </div>
	<div class="row">
	  <div class="span0"></div>
	  <div class="span12">
	     <form id="addForm" method="post" action="" >
	     	<input type="hidden" name="actionUrl" id="actionUrl" value="${param.actionUrl}"/>
			<input type="hidden" name="parentAuthId" id="parentAuthId" value="${parentAuthId}"/>
			<input type="hidden" name="authGrade" id="authGrade" value="${authGrade}"/>
			<input type="hidden" name="rootAuthId" id="rootAuthId" value="${rootAuthId}"/>
	        <input type="hidden" name="authIcon" id="authIcon_hidden" value="fa fa-cube"/>
		 <div class="form-horizontal">
			<c:if test="${'3'== auth.authGrade}">
		         <div class="control-group">
					<label class="control-label" for="authName">权限名称</label>
					<div class="controls">
				        <input type="text" class="span3" id="authName" name="authName" value="" placeholder="请输入权限名称" maxlength="50" required/>
					</div>
				</div>
		     </c:if>
		    <c:if test="${'3'!= auth.authGrade}">
			<div class="control-group">
				<label class="control-label" for="authName">权限名称</label>
				<div class="controls">
				      <div class="btn-group input-prepend">
				        	<span class="add-on"><i id="authIcon" class="${auth.authIcon}"></i></span>
						    <input type="text" class="span3" id="authName" name="authName" value="" placeholder="请输入权限名称" maxlength="50" required/>
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
					<textarea rows="3" name="authRemark" id="authRemark" placeholder="请输入权限描述" maxlength="150"></textarea>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">分类</label>
				<div class="controls">
					<select name="authType" id="authType" onchange="doChange()" class="span2">
						<option value="0">模块</option>
				  		<option value="1">页面</option>
				  		<option value="2">控件</option>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="url">URL</label>
				<div class="controls">
					<input type="text" class="span4" name="url" id="url" placeholder="请输入URL" maxlength="100"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="authCode">权限code</label>
				<div class="controls">
					<input type="text" class="span2" name="authCode" id="authCode" placeholder="请输入权限code" maxlength="50"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="authOrder">排序</label>
				<div class="controls">
					<input name="authOrder" id="authOrder" value="" type="text" class="span2" maxlength="11"  placeholder="请输入排序" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
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
			if (confirm("请确定保存模块信息吗？")) {
				document.forms[0].action = "${pageContext.request.contextPath }/api/auth/add";
				document.forms[0].submit();
			}
    }
});
});
</script>
</body>
</html>

