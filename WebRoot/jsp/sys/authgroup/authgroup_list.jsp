<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>权限组列表</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script src="${pageContext.request.contextPath}/js/webcalendar.js" type="text/javascript"></script>
		<script type="text/javascript">
			function doQuery() {
				document.form1.action = "${pageContext.request.contextPath }/api/authgroup/list";
				document.form1.submit();
			}

			function goAdd() {
				document.form1.action = "${pageContext.request.contextPath }/jsp/sys/authgroup/authgroup_add.jsp";
				document.form1.submit();
			}

			function goModify() {
				var checked = false;
				var id = document.getElementsByName("authGroupID");
				var value = "";
				for (var i = 0; i < id.length; i++) {
					if (id[i].checked) {
						checked = true;
						value = id[i].value;
					}
				}

				if(!checked) {
					alert("请选择权限组！");
					return ;
				}

				document.form1.action = "${pageContext.request.contextPath }/api/authgroup/detail?authGroupId=" + value;
				document.form1.submit();
			}

			function doDel() {
				var checked = false;
				var id = document.getElementsByName("authGroupID");
				var value = "";
				for (var i = 0; i < id.length; i++) {
					if (id[i].checked) {
						checked = true;
						value = id[i].value;
					}
				}

				if(!checked) {
					alert("请选择权限组！");
					return ;
				}

				if (confirm("确定要永久性地删除此权限组吗？")) {
					document.form1.action = "${pageContext.request.contextPath }/api/authgroup/delete?authGroupId=" + value;
					document.form1.submit();
				}
			}

			function goMembList() {
				var authGroupId;
				var checked = false;
				var id = document.getElementsByName("authGroupID");
				for (var i = 0; i < id.length; i++) {
					if (id[i].checked) {
						authGroupId = id[i].value;
						checked = true;
					}
				}

				if(!checked) {
					alert("请选择权限组！");
					return ;
				}

				document.form1.action = "${pageContext.request.contextPath }/api/authgroup/memberlist?authGroupId=" + authGroupId;
				document.form1.submit();
			}
		</script>
	</head>
	<body>
	<div class="container-fluid top20">
	<div class="row-fluid">
		        <ul class="breadcrumb">
		          <li class="active">系统设置 / </li>
		          <li class="active">权限组管理</li>
		        </ul>
	</div>
  <div class="row-fluid">
	  	<div class="span12">
		<form method="post"  id="form1" name="form1"  action="" >
			<input type="hidden" name="url" id="url" value="${pageContext.request.contextPath}/api/authgroup/list?currentPageNO=${page.currentPage}"/>
				<fieldset>
				    <legend>查询条件</legend>
					<table>
						<tbody>
						<tr>
						   <th width="96">权限组名称：</th>
							<td>
								<input name="authGroupName" id="authGroupName" size="36" type="text" value="${authGroupName }">
							    <a class="btn btn-primary" name="sss" href="####" onclick="doQuery();" style="margin-bottom: 10px;">搜　索</a>
							</td>
						</tr>
						</tbody>
					</table>
				</fieldset>
			</form>
			<form method="post"  id="form2" name="form2"  action="">
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="table table-hover table-bordered">
					<thead>
						<tr>
							<th width="10%">
								选择
							</th>
							<th width="40%">
								权限组名称
							</th>
							<th width="50%">
								描述
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.resultList}" var="authGroup">
							<tr>
								<td>
									<input type="radio" id="authGroupID" name="authGroupID" value="${authGroup.authGroupId }" />
								</td>
								<td>
									<label>
										${authGroup.authGroupName }
									</label>
								</td>
								<td>
									${authGroup.authGroupRemark }
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
			        		<td colspan="3">
			        			<div class="page">
									<a class="btn btn-primary" href="####" onclick="goAdd();" ><span class="r">添加</span></a>
									<a class="btn btn-primary" href="####" onclick="goModify();"><span class="r">修改</span></a>
									<a class="btn btn-primary" href="####" onclick="doDel();" ><span class="r">删除</span></a>
									<a class="btn btn-primary" href="####" onclick="goMembList();"><span class="r">权限管理</span></a>
								</div>
			        		</td>
					    </tr>
					</tfoot>
				</table>
				<%@include file="/jsp/public/standard.jsp"%>
			</form>





	</div>
	</div>
	</div>
	</body>
</html>

