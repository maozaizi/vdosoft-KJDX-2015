<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>数据字典项</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<script src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript">
			function shua(){
				var isShua=${param.isShua};
				if(isShua==1){
					window.parent.frames["dictionaryLeftFrame"].location.reload();
				};
			}
		</script>
	</head>

<body onload="shua()">
  <div class="MainContent top20">
	<div class="row-fluid">
        <ul class="breadcrumb">
          <li class="active">您当前的位置：</li>
          <li class="active"><c:if test="${fn:length(navigation)>=55}">${fn:substring(navigation,0,55)}...</c:if>
		  <c:if test="${fn:length(navigation)<55}">${navigation}</c:if></li>
        </ul>
    </div>
	<div class="ListBlock side20">
		<form method="post"  id="addForm"  action="" >
		<input type="hidden" name="parentId" id="parentId" value="${param.parentId}"/>
		<input type="hidden" name="url" id="url" value = "${pageContext.request.contextPath}/api/dictionary/get"/>

		<div class="EditBox">
		<table width="100%"  border="0" cellpadding="0" class="table table-hover table-bordered" cellspacing="0">
		    <input type="hidden" name="dataItemId" value="${dataItemBaseInfo.dataItemId}" />
			<tr>
				<th colspan="2">基本信息</th>
			</tr>
		   <tr>
	         <td class="EditBox_td1" width="30%">编码：</td>
	         <td width="70%">${dataItemBaseInfo.dataItemCode}</td>
	       </tr>
	       <tr>
	         <td class="EditBox_td1">名称：</td>
	         <td>${dataItemBaseInfo.dataItemName}</td>
	       </tr>
	       <tr>
	         <td class="EditBox_td1">值：</td>
	         <td>${dataItemBaseInfo.dataItemValue}</td>
	       </tr>
	         <tr>
	         <td class="EditBox_td1">属性1：</td>
	         <td> ${dataItemBaseInfo.p1}</td>
	       </tr>
	       <tr>
	         <td class="EditBox_td1">属性2：</td>
	         <td> ${dataItemBaseInfo.p2}</td>
	       </tr>
	          <tr>
	         <td class="EditBox_td1">属性3：</td>
	         <td> ${dataItemBaseInfo.p3}</td>
	       </tr>
	           <tr>
	         <td class="EditBox_td1">属性4：</td>
	         <td> ${dataItemBaseInfo.p4}</td>
	       </tr>
	           <tr>
	         <td class="EditBox_td1">属性5：</td>
	         <td> ${dataItemBaseInfo.p5}</td>
	       </tr>
	           <tr>
	         <td class="EditBox_td1">属性6：</td>
	         <td> ${dataItemBaseInfo.p6}</td>
	       </tr>
	       <tr>
	         <td class="EditBox_td1">排序：</td>
	         <td>${dataItemBaseInfo.orderby}</td>
	       </tr>
	       <tr>
	         <td class="EditBox_td1">描述：</td>
	         <td>${dataItemBaseInfo.memo}</td>
	       </tr>
		</table>
		</div>
		</form>
	</div>
</div>
	</body>
</html>

