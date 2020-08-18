<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
<title></title>
<link href="${pageContext.request.contextPath}/css/fire/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/fire/layout.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
<script type="text/javascript">
function goBack() {
	  window.location.href = "${pageContext.request.contextPath}/api/org/getInfo?organizationDetailId=${param.organizationDetailId}";
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
				organizationDetailName: {
					required: true,
					maxlength: 50
				}
			}
		});
});
</script>
<body>
	<div class="MainContent">
		<div class="ListBlock">
		    	    <ul class="breadcrumb">
			          	<li class="active">修改部门</li>
					</ul>
			</div>
			<form method="post"  id="addForm"  action="${pageContext.request.contextPath }/api/org/modifyDept" >
			<input type="hidden" name="organizationDetailId" id="organizationDetailId" value="${organizationDetali.organizationDetailId}"/>
			<input type="hidden" name="tempUrl" id="tempUrl" value = "${pageContext.request.contextPath}/api/org/getInfo?organizationDetailId=${param.organizationDetailId}"/>
	       	<div class="EditBox">
	        <table width="100%" cellspacing="0" cellpadding="0">
				<tr>
				  <th colspan="2">修改部门</th>
				</tr>
			  <tr>
				<td class="EditBox_td1"><span style="color: Red">*&nbsp;</span>组织名称：</td>
				<td>
				  	<input name="organizationDetailName" id="organizationDetailName" value="${organizationDetali.organizationDetailName }" type="text" maxlength="50" class="ipt_text" />
				</td>
			  </tr>
			  <tr>
			  	<td class="EditBox_td1">组织描述：</td>
				<td>
					<p class="muted">请输入1-200字的描述&nbsp;<span id="relable" class="text-success"></span></p>
				  	<textarea name="memo" onkeyup="textCounter('memo','relable','200');" id="memo" rows="6" cols="39" class="ipt_text" >${organizationDetali.memo }</textarea>
				</td>
			  </tr>
			</table>
            </div>

			  <div class="Temp-1">
			  	<input type="submit" class="btn btn-primary" value="保存"/>
				<input type="button" onclick="goBack()" class="btn" value="取消"/>
			  </div>
			  </form>
	</div>
</body>
</html>

