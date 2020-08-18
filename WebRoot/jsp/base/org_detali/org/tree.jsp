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
<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
<link href="${pageContext.request.contextPath }/css/fire/newlayout.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/fire/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/fire/layout.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"></link>

<script src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/strutils.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath }/js/bootstrap/jquery-1.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>

<script type="text/javascript">
	var setting = {
			view: {
				showLine: false
			},
			data:{
				key:{
					children:'childlist',
					name:'orgname',
					url:'eurl'
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
					window.parent.frames["app_rightFrame"].location.href = "${pageContext.request.contextPath}/api/org/getInfo?id="+node.id;
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
	$("document").ready(function(){
		$.ajax({
				url:'${pageContext.request.contextPath}/ajaxapi/org/getTree',
				type:'post',
				dataType:'json',
				success:function(data, textStatus, jqXHR){
					var zNodes = data.organizationdetalilist;
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					treeObj.expandAll(true);
				},
				error:function(){
					alert("error");
				}
			});
			$("#del_top").click(function(){
				if(confirm("您确认要作废吗?")){
			        var delform = document.getElementById("delform");
					delform.submit();
				}
				
			});
			$("#del_bottom").click(function(){
				if(confirm("您确认要作废吗?")){
			        var delform = document.getElementById("delform");
					delform.submit();
				}
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
</script>
</head>
<body>
<div class="treeblock_out" >
   <table cellspacing="0" cellpadding="0" class="treeblock_in" width="100%" >
 <tr>
 	<th colspan="2" class="treeblock_th">组织结构</th>
 </tr>
 <tr>
   <td class="treeblock_td">

	<table cellspacing="0" cellpadding="0" >
	      <tr>
			<td class="rtext">
				<div class="content_wrap">
					<div class="tooBar">
				            <a href="####" onclick="toSave()" id="save_top" title="添加当前级别的组织结构节点" style="display:none"><span class="label">添加</span></a>
				            <a href="####" onclick="toUpdate()" id="update_top" title="编辑当前选择节点" style="display:none"><span class="label">修改</span></a>
				            <a href="####" id="del_top" title="作废当前选择节点" style="display:none"><span class="label">作废</span></a>
					</div>
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					<div>
						<form method="post"  action="" name="form" id="form" target="app_rightFrame">
				  	 		<input type="hidden" name="id" value="" id="id"/>
				  	 		<input type="hidden" name="baseOrganizationId" value="" id="baseOrganizationId"/>
				  	 		<input type="hidden" name="parentId" value="" id="parentId"/>
							<input type="hidden" name="tempUrl" id="tempUrl" value = "${pageContext.request.contextPath}/api/org/getTree"/>
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
				 			<input type="hidden" id="tempUrl" name="tempUrl" value="${pageContext.request.contextPath}/api/org/getTree"/>
					</form>
				</div>
				</div>
		  	 	<form method="post"  action="" name="form1" id="form1"   target="app_rightFrame" >
		  	 		<input type="hidden" name="parentId" id="parentId"/>
		  	 		<input type="hidden" name="organizationDetailId" id="organizationDetailId"/>
		  	 		<input type="hidden" name="baseOrganizationId" id="baseOrganizationId"/>
		  	 		<input type="hidden" name="type" id="type" value="${param.type }"/>
					<input type="hidden" name="tempUrl" id="tempUrl" value = "${pageContext.request.contextPath}/api/org/getTree"/>
					<input type="hidden" name="nodeType" id="nodeType"/>
		  	 	</form>
			</td>
		  </tr>
	</table>	  
   </td>
 </tr>
 
	</table> 
</div>
</body>
</html>
