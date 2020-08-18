<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" />
		<link href="${pageContext.request.contextPath}/css/bootstrap/datetimepicker.css" rel="stylesheet" media="screen"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.1.7.2.min.js"></script>
	  <script type="text/javascript">
		function goBack() {
			  window.location.href = "${pageContext.request.contextPath}/api/org/getInfo?id=${param.orgId}";
		}

/*
js自身没有trim()函数取消字符串中的空白字符
自定义函数：用正则替换掉空白字符
*/
function setBirthdayByIdNo(){
$("#birthData").val(getBirthdatByIdNo($("#idcardNumber").val()));
}
function trim(s) { return s.replace(/^\s+|\s+$/g, ""); };

//验证身份证号并获取出生日期
function getBirthdatByIdNo(iIdNo) {
var tmpStr = "";
var idDate = "";
var tmpInt = 0;
var strReturn = "";

iIdNo = trim(iIdNo);

if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
strReturn = "输入的身份证号位数错误";
return strReturn;
}

if (iIdNo.length == 15) {
tmpStr = iIdNo.substring(6, 12);
tmpStr = "19" + tmpStr;
tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

return tmpStr;
}
else {
tmpStr = iIdNo.substring(6, 14);
tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

return tmpStr;
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
				name: {
					required: true,
					maxlength: 25
				},
				nation: {
					required: true,
				},
				idcardNumber: {
					required: true,
					number: true
				},
				email: {
					checkEmail: true,
				}
			}
		});
});
</script>
<body>
	<div class="span11" id="divId">
		<div class="ListBlock">
				   <ul class="breadcrumb">
			          	 <li class="active">添加人员信息</li>
			        </ul>
			 </div>
			<form method="post"  id="addForm"  action="${pageContext.request.contextPath }/api/org/addUserInfo">
			<!-- form 提交表单隐藏域开始-->
			<input type="hidden" name="orgId" id="orgId" value="${param.orgId}"/>
			<input type="hidden" name="tempUrl" id="tempUrl" value="${pageContext.request.contextPath}/api/org/getInfo"/>
	        <!-- form 提交表单隐藏域结束-->
	        <div class="form-horizontal">
	        	<div class="control-group">
					<label class="control-label" for="personName">姓名</label>
					<div class="controls">
						<input type="text" name="name" class="span2" id="name" maxlength="25" placeholder="请输入姓名"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="personSex">性别</label>
					<div class="controls">
						<label class="radio inline">
						<input type="radio" name="gender" id="gender" value="男" checked />男
						</label>
						<label class="radio inline">
						<input type="radio" name="gender" id="gender" value="女" />女
						</label>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">民族</label>
					<div class="controls">
						<input type="text" name="nation" class="span2" id="nation" maxlength="20" placeholder="请输入民族"/>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">身份证号码</label>
					<div class="controls">
						<input type="text" name="idcardNumber" class="span2" id="idcardNumber" placeholder="请输入身份证号码" onblur="setBirthdayByIdNo()"/>
					</div>
				</div>
				<div class="control-group">
               		<label class="control-label" for="personName">出生日期</label>
					<div class="controls">
					    <input size="10" type="text" name="birthData" class="span2" id="birthData" readonly/>
               		</div>
           		</div>
				<div class="control-group">
					<label class="control-label" for="personEducational">学历</label>
					<div class="controls" >
						<select class="span2" name="degree" id="degree">
							<option value="">----请选择----</option>
							<!--<c:forEach var="item" items="${XL}">
								<option value="${item.dataItemValue}">${item.dataItemName}</option>
							</c:forEach>-->
							<c:forEach var="seriousness" items="${web:getDataItem('DEGREETYPE')}">
						        <option value="${seriousness.dataItemValue}">${seriousness.dataItemName}</option>
							 </c:forEach>
						</select>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">专业</label>
					<div class="controls">
						<input type="text" name="professional" class="span2" id="professional" maxlength="20" placeholder="请输入专业"/>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">职务</label>
					<div class="controls">
						<input type="text" name="position" class="span2" id="position" maxlength="40" placeholder="请输入职务"/>
					</div>
				</div>
	        	<div class="control-group">
					<label class="control-label" for="personName">电子邮件</label>
					<div class="controls">
						<input type="text" name="email" class="span2" id="email" placeholder="请输入电子邮件"/>
					</div>
				</div>
				<div class="form-actions">
				  	<input class="btn btn-primary" type="submit" value="保存"/>
				  	<input type="button" class="btn" onclick="goBack()" value="返回"/>
				</div>
	        </div>
			  </form>
 </div>
<!--鼠标划过tr背景色改变-->
	<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js"  charset="UTF-8"></script>

	<script type="text/javascript">
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });


	</script>
</body>
</html>

