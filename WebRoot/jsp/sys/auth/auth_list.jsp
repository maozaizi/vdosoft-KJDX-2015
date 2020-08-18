<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
		<title>权限管理</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<script src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
    	<link href="${pageContext.request.contextPath}/css/fire/global.css" rel="stylesheet" type="text/css" />
	    <link href="${pageContext.request.contextPath}/css/fire/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript">
			function goAddFirstAuth() {
				document.forms[0].parentAuthId.value = "-1";
				document.forms[0].authGrade.value = "0";
				document.forms[0].rootAuthId.value = "";

				goAdd();
			}

			function goAdd() {
				document.forms[0].action = "${pageContext.request.contextPath }/api/auth/gotoAdd";
				document.forms[0].submit();
			}

			function goModify() {
				document.forms[0].action = "${pageContext.request.contextPath }/api/auth/detail";
				document.forms[0].submit();
			}

			function doDelete() {
				if(confirm("您确定删除该权限吗？")) {
					document.forms[0].action = "${pageContext.request.contextPath }/api/auth/delete";
					document.forms[0].submit();
				}
			}
		</script>
		<script type="text/javascript">
		var setting = {
			view: {
				showLine: false
			},
			data:{
				key:{
					children:'childlist',
					name:'authname',
					url:'eurl'
				}
			},
			callback:{
				onClick:function(e,id,node){
					document.forms[0].authId.value = node.authid;
					document.forms[0].parentAuthId.value = node.authid;
					document.forms[0].authGrade.value = node.authgrade;
					document.forms[0].rootAuthId.value = node.rootauthid;
				    //第三级不能添加子节点
				    if(node.authtype != null && node.authtype != "2") {
						document.getElementById("add").style.display = "inline";
					} else {
						document.getElementById("add").style.display = "none";
					}
					
					document.getElementById("update").style.display = "inline";
					document.getElementById("del").style.display = "inline";
				}
			}
		};
		$(document).ready(function(){
		   $.ajax({
				url:'${pageContext.request.contextPath}/ajaxapi/auth/list',
				type:'post',
				dataType:'json',
				success:function(data, textStatus, jqXHR){
					$.fn.zTree.init($("#treeDemo"), setting, data.authlist);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					treeObj.expandAll(true);
				},
				error:function(){
					alert("error");
				}
			});
		});

	</script>
	</head>
	<body>
	<div class="MainContent">
	   <div class="ListBlock">
		<h3 class="cBlack">系统设置 / 功能管理</h3>
			<form id="form2" name="form2" method="post" action="">
				<input type="hidden" name="actionUrl" id="actionUrl" value="${pageContext.request.contextPath}/api/auth/list"/>
				<div id="treeDemo" class="ztree"></div>
				<!--
				<input type="button" id="add" name="add" value="添加子权限"  onclick="goAdd()" class="btn btn-primary" style="display:none"/>&nbsp;
				<input type="button" id="update" name="update" value="修&nbsp;改"  onclick="goModify()" class="btn btn-primary" style="display:none"/>&nbsp;
				<input type="button" id="del" name="del" value="删&nbsp;除"  onclick="doDelete()" class="btn btn-primary" style="display:none"/>&nbsp;
				<input type="button" id="import" name="port" value="导&nbsp;入"  onclick="" class="btn btn-primary" style="display:none"/>&nbsp;
				<input type="button" id="export" name="export" value="导&nbsp;出"  onclick="" class="btn btn-primary" style="display:none"/>
				//-->
				<a href="####" id="add" name="add" class="tbut" onclick="goAdd()" style="display: none;" ><span class="label">添加子权限</span></a>&nbsp;
				<a href="####" id="update" name="update" class="tbut" onclick="goModify()" style="display: none;" ><span class="label">修&nbsp;改</span></a>&nbsp;
				<a href="####" id="del" name="del" class="tbut" onclick="doDelete()" style="display: none;" ><span class="label">删&nbsp;除</span></a>&nbsp;
				<a href="####" id="import" name="port" class="tbut" onclick="" style="display: none;" ><span class="label">导&nbsp;入</span></a>&nbsp;
				<a href="####" id="export" name="export" class="tbut" onclick="" style="display: none;" ><span class="label">导&nbsp;出</span></a>&nbsp;

				<input type="hidden" id="authId" name="authId" value=""/>
				<input type="hidden" id="parentAuthId" name="parentAuthId" value=""/>
				<input type="hidden" id="authGrade" name="authGrade" value=""/>
				<input type="hidden" id="rootAuthId" name="rootAuthId" value=""/>
			</form>
		</div>

			        <div class="form-actions">
			        	<a href="####" id="addP" name="add" class="tbut" onclick="goAddFirstAuth()"><span class="label">增加一级权限</span></a>&nbsp;
					</div>
		</div>
	</body>
</html>
