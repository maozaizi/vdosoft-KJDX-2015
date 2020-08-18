<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
页面名称：基础组织结构
页面描述：
作者：张波
日期：2012-02-15 14:52
--%>
<%@include file="/jsp/config/taglib.jsp"%>
<%@include file="/jsp/public/limit_top.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
<title>基础组织结构</title>
<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<link href="${pageContext.request.contextPath }/css/fire/newlayout.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/strutils.js" type="text/javascript"></script>
<SCRIPT type="text/javascript">
		function goAddFirstAuth() {
				document.forms[0].parentId.value = "-1";
				toSave();
		}
		function toSave(){
		         document.form.action="${pageContext.request.contextPath}/api/baseorganization/toSave";
	             document.form.submit();
		}
		function toUpdate(){
		         document.form.action="${pageContext.request.contextPath}/api/baseorganization/toModify";
	             document.form.submit();
		}
		function toDelete(){
			if(confirm("请确定作废组织信息吗？")){
		        document.form.action="${pageContext.request.contextPath}/api/baseorganization/toDelete";
		        document.form.submit();
			}
		}



		var setting = {
			view: {
				showLine: false
			},
			data:{
				key:{
					children: "children",
					name:"orgname",
					url:'xurl'
				},
				simpleData:{
					enable: true,
					idKey:'id',
					pIdKey:'parentid',
					rootPId: -1
				}
			},
			callback:{
				onClick:function(e,id,node){
					document.forms[0].id.value = node.id;
					document.forms[0].parentId.value = node.parentId;
					document.forms[1].id.value = node.id;
					document.forms[1].parentId.value = node.parentId;
					var ilevel = node.ilevel;
					if(ilevel == 1){
						document.getElementById("del_top").style.display = "none";
						document.getElementById("del_bottom").style.display = "none";
					}else{
						document.getElementById("del_top").style.display = "inline";
						document.getElementById("del_bottom").style.display = "inline";
					}
					if(ilevel == 4){
						document.getElementById("save_top").style.display = "none";
						document.getElementById("save_bottom").style.display = "none";
					}else{
						document.getElementById("save_top").style.display = "inline";
						document.getElementById("save_bottom").style.display = "inline";
					}
					document.getElementById("update_top").style.display = "inline";
					document.getElementById("update_bottom").style.display = "inline";
				}
			}
		};

		$(document).ready(function(){
			$.ajax({
				url:'${pageContext.request.contextPath}/ajaxapi/baseorganization/list',
				type:'post',
				dataType:'json',
				success:function(data, textStatus, jqXHR){
					var zNodes = data.baseorganizationlist;
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					treeObj.expandAll(true);
				},
				error:function(){
					alert("error");
				}
			});
			$("#del_top").click(function(){
				showDelReason();
			});
			$("#del_bottom").click(function(){
				showDelReason();
			});

			$("#deleteBtn").click(function(){
				var reason = document.getElementById("reason").value;
				if(reason == ""){
					alert("请输入作废原因！");
					return false;
				}
				var delform = document.getElementById("delform");
				delform.submit();
			});
			$("#delCloseBtn").click(function(){
				closeDelReason();
			});
		});

	</SCRIPT>
</head>
<body>
	<div class="container-fluid">
	<div class="span12">
       	<ul class="breadcrumb">
         	<li class="active">基础设置 / </li>
          	<li class="active">机构设置</li>
        </ul>
	</div>
	<div class="span12">
		<div class="tooBar">
	            <a href="####" onclick="toSave()" id="save_top" title="添加当前级别的组织结构节点" style="display:none"><span class="label">添加</span></a>
	            <a href="####" onclick="toUpdate()" id="update_top" title="编辑当前选择节点" style="display:none"><span class="label">修改</span></a>
	            <a href="####" id="del_top" title="作废当前选择节点" style="display:none"><span class="label">作废</span></a>
		</div>
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<div>
			<form method="post"  action="" name="form" id="form" >
	  	 		<input type="hidden" name="id" value="" id="id"/>
	  	 		<input type="hidden" name="baseOrganizationId" value="" id="baseOrganizationId"/>
	  	 		<input type="hidden" name="parentId" value="" id="parentId"/>
				<input type="hidden" name="tempUrl" id="tempUrl" value = "${pageContext.request.contextPath}/api/baseorganization/list"/>
	  	 	</form>
		</div>
		<div class="tooBar">
	            <a href="####" onclick="toSave()" id="save_bottom" title="添加当前级别的组织结构节点" style="display:none"><span class="label">添加</span></a>
	            <a href="####" onclick="toUpdate()" id="update_bottom" title="编辑当前选择节点" style="display:none"><span class="label">修改</span></a>
	            <a href="####" id="del_bottom" title="作废当前选择节点" style="display:none"><span class="label">作废</span></a>
	    </div>
	     <form method="post"  id="delform" action="${pageContext.request.contextPath }/api/baseorganization/delete" >
	 			<input type="hidden" name="id"/>
				<input type="hidden" name="baseOrganizationId" value="" id="baseOrganizationId"/>
	 			<input type="hidden" name="parentId" />
	 			<input type="hidden" id="tempUrl" name="tempUrl" value="${pageContext.request.contextPath}/api/baseorganization/list"/>
				<%@include file="/jsp/public/delete_layout.jsp" %>
		</form>
	</div>
	</div>
</body>
</html>
