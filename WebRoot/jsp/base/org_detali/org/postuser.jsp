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
	$(document).ready(function (){
		$("a[id=deletePost]").click(function(){
			if(confirm("请确认要移除岗位人员信息吗？")){
				var organizationDetailId = $(this).attr("orgId");
				var postUserId = $(this).attr("postUserId");
				var url = "${pageContext.request.contextPath}/api/org/deletePost";
				$("#orgId").val(organizationDetailId);
				$("#postUserId").val(postUserId);
				$("#userPostInfoList").attr("action",url).submit();
			}
		});
		$("a[id=joinPostUser]").click(function(){
				var orgId = $(this).attr("orgId");
				var userId = $(this).attr("userId");
				var postId = $(this).attr("postId");
				var postUserId = $(this).attr("postUserId");
				$("#orgId").val(orgId);
				$("#userId").val(userId);
				$("#postId").val(postId);
				$("#postUserId").val(postUserId);
				var url = "${pageContext.request.contextPath}/api/org/joinPostUser";
				$("#userPostInfoList").attr("action",url).submit();
		});
	});
</script>
</head>
<body>
	<div class="MainContent">
		<div class="ListBlock">
		    	    <ul class="breadcrumb">
			          	<li class="active">管理人员列表</li>
			        </ul>
		</div>
		<form method="post"  action="" id="userPostInfoList">
			<input type="hidden"  id="orgId" name="orgId" value="" />
			<input type="hidden"  id="postUserId" name="postUserId" value="" />
			<input type="hidden"  id="postId" name="postId" value="" />
			<input type="hidden" id="tempUrl" name="tempUrl" value="${pageContext.request.contextPath}/api/org/toManagerPost?postId=${postId }&orgId=${orgId }"/>

	  	<div class="List">
	  	<table class="table table-hover table-bordered">
	  	    <caption>人员列表</caption>
	  	  	<thead>
			<tr>
				<th width="30%" >名称</th>
	        	<th width="30%" >性别</th>
	        	<th width="30%" >民族</th>
	        	<th width="10%" >操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${userInfoList}" var="item">
				<tr>
			      	<td>
	    				<input type="hidden" name="userId" id="userId" value="${item.userId }" title="${item.userId }"/>
			      		${item.name }
			      	</td>
			      	<td>${item.gender }</td>
			      	<td>${item.nation }</td>
			      	<td>
				      	<c:if test="${item.postId != '' }"><a id="deletePost" class="btn btn-primary" orgId="${item.orgId }"  postUserId= "${item.postUserId}" href="####">移除</a></c:if>
				      	<c:if test="${item.postId == ''}"><a id="joinPostUser" class="btn btn-primary" orgId="${item.orgId }" userId="${item.userId }" postId="${postId }" postUserId="${item.postUserId }" href="####">加入</a></c:if>
			      	</td>
			    </tr>
			</c:forEach>
			</tbody>
		</table>
		<a class="btn" href="${pageContext.request.contextPath}/api/org/getInfo?id=${orgId }">返回</a>
	</div>
	</form>
</div>
</body>
</html>

