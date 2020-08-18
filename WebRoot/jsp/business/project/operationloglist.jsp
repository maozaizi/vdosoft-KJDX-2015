<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="" name="description">
<meta content="" name="author">
<title>项目查询</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap-theme.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/paginator.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/webcalendar.js" type="text/javascript"></script>

<script type="text/javascript">
	function back(){
		document.myform.action="${pageContext.request.contextPath}/api/project/list";
		document.myform.submit();
	}
</script>
</head>
<body>
<div class="container-fluid">
  <h2 class="page-header">查看修改历史</h2>
  <form name="myform" action="" id="myform" method="post">

  <div style="margin:20px 0">
  	<button type="button" class="btn btn-success btn-sm" onclick="back();">返回</button>
  </div>
  
  <div class="table-responsive">
    <table class="table table-bordered table-striped table-responsive">
      <thead>
        <tr>
          <th>操作人</th>
          <th>操作时间</th>
          <th style="width:75%;">日志记录</th>
        </tr>
      </thead>
      <tbody>
      	<c:forEach items="${page.resultList}" var="historys">
	        <tr>
	          <td>${historys.userName }</td>
	          <td>${historys.operatorTime }</td>
	          <td>${historys.remark }</td>
	        </tr>
        </c:forEach>
        <c:if test="${empty page.resultList}">
         	<tr> 
            	<td colspan="3" style="text-align:center;"><font color="red" style="align:center;">暂无数据！</font></td>
          	</tr>
         </c:if>
      </tbody>
      <tfoot>
			<tr>
        		<td colspan="3">
					<%@include file="/jsp/public/standard.jsp" %>
        		</td>
	        </tr>
		</tfoot>
    </table>
  </div>
  </form>
</div>
</body>
</html>