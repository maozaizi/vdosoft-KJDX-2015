<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台菜单模板</title>

	<link href="${pageContext.request.contextPath}/css/pure.css" rel="stylesheet" type="text/css"/>
    <!--[if lte IE 8]>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grids-responsive-old-ie-min.css"/>
    <![endif]-->
    <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grids-responsive.css"/>
    <!--<![endif]-->
	<style type="text/css">
	.pure-menu.pure-menu-open {
		visibility: visible;
		z-index: 2;
		width: 99%;
	}
	</style>
</head>

<body>
<div class="pure-menu pure-menu-open">
    <c:forEach items="${parentList}" var="parent">
	    <div class="pure-menu-heading cstm-fontsize"><i class="${parent.authicon}"></i> ${parent.authname}</div>

	    <ul>
	        <c:forEach items="${parent.childList}" var="child">
	        <li><a class="cust-padding" href="javascript:void(0);"><input type="hidden" value="${pageContext.request.contextPath}${child.url}"/>${child.authname}</a></li>
	        </c:forEach>
	    </ul>
    </c:forEach>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
$(function(){
    $(".cust-padding").click(function(){
        window.parent.document.getElementsByName("rightFrame")[0].src=$(this).find("input").get(0).value;
    });
});

</script>
</body>
</html>
