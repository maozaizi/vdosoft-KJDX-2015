<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
页面名称：数据字典树结构
页面描述：
作者：庞海超
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<link href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>
<link href="${pageContext.request.contextPath}/css/fire/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		//<!--
		var setting = {
			view: {
				showLine: false
			},
			data:{
				key:{
					name:'dataitemname'
				},
				simpleData: {
					enable: true,
					idKey:'dataitemid',
					pIdKey:'parentid'
				}
			},
			callback:{
				onClick:function(e,id,node){
					if(node.parentid==-1||node.parentid==null){
						document.getElementById("add").style.display = "inline";
						document.getElementById("updateMx").style.display = "none";
						document.getElementById("update").style.display = "inline";
						document.getElementById("delete").style.display = "none";
						document.getElementById("addMx").style.display = "none";
					}
					if(node.type==0 && node.parentid!=-1){
						document.getElementById("add").style.display = "inline";
						document.getElementById("update").style.display = "inline";
						document.getElementById("delete").style.display = "inline";
						document.getElementById("addMx").style.display = "inline";
						document.getElementById("updateMx").style.display = "none";
					}else if(node.type==1 && node.parentid!=-1){
						document.getElementById("add").style.display = "none";
						document.getElementById("addMx").style.display = "inline";
						document.getElementById("update").style.display = "none";
						document.getElementById("updateMx").style.display = "inline";
						document.getElementById("delete").style.display = "inline";
					}
					document.forms[0].parentId.value = node.dataitemid;
					document.forms[0].dataItemId.value = node.dataitemid;
					document.forms[0].dataItemCode.value = node.dataitemcode;
					window.parent.frames["dictionaryRightFrame"].location.href = "${pageContext.request.contextPath}/api/dictionary/get?isShua=0&dataItemId="+node.dataitemid;

				}
			}
		};
		$(document).ready(function(){
			$.ajax({
				url:'${pageContext.request.contextPath}/ajaxapi/dictionary/getDataItemBaseInfoList',
				type:'post',
				dataType:'json',
				success:function(data, textStatus, jqXHR){
					$.fn.zTree.init($("#treeDemo"), setting, data.dataitembaseinfolist);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					//treeObj.expandAll(true);
					var node = treeObj.getNodeByParam("dataitemid","17220130411000001");
					treeObj.expandNode(node);
				},
				error:function(){
					alert("error");
				}
			});
		});
		//-->
		function goAddFirstAuth() {
			document.forms[0].parentId.value = "-1";
			toSave();
		}
		function toSave(){
		         document.form.action="${pageContext.request.contextPath}/api/dictionary/toSave";
	             document.form.submit();
		}
		function toSaveMx(){
		         document.form.action="${pageContext.request.contextPath}/api/dictionary/toSaveMx";
	             document.form.submit();
		}
		function toUpdate(){
		         document.form.action="${pageContext.request.contextPath}/api/dictionary/goModify";
	             document.form.submit();
		}
		function toUpdateMx(){
		         document.form.action="${pageContext.request.contextPath}/api/dictionary/goModifyMx";
	             document.form.submit();
		}

		function toDelete(){
			if(confirm("确定要永久性地作废此栏目吗？")){
		        document.form.action="${pageContext.request.contextPath}/api/dictionary/delete";
		        document.form.submit();
			}
		}


	</script>
</head>
<body>
<div id="treeblock_out" >
   <div class="tree_view_button">
	 <form method="post"  action="" name="form" id="form"  target="dictionaryRightFrame">
		<input type="hidden" name="parentId" value="" id="parentId"/>
  		<input type="hidden" name="dataItemId" value="" id="dataItemId"/>
 	 	<input type="hidden" name="dataItemCode" value="" id="dataItemCode"/>
		<input type="hidden" name="url" id="url" value = "${pageContext.request.contextPath}/api/dictionary/get"/>
		<a href="####" id="add" class="tbut" onclick="toSave()" style="display: none;" ><span class="label">添加数据项</span></a>
		<a href="####" id="addMx" class="tbut" onclick="toSaveMx()" style="display: none;" ><span class="label">添加数据项明细</span></a>
		<a href="####" id="update" class="tbut" onclick="toUpdate()" style="display: none;" ><span class="label">编辑</span></a>
		<a href="####" id="updateMx" class="tbut" onclick="toUpdateMx()" style="display: none;" ><span class="label">编辑</span></a>
		<a href="####" id="delete" class="tbut" onclick="toDelete()" style="display: none;" ><span class="label">作废</span></a>
	</form>
   </div>
   <div class="treeblock_out" >
	 <table cellspacing="0" cellpadding="0" class="treeblock_in" width="100%" >
	 <tr>
	 	<th class="treeblock_th">数据字典
		 	<c:if test="${empty dataItemBaseInfoList}">
				<span>
				<a href="####" onclick="goAddFirstAuth()" title="添加当前级别的数据字典节点">添加根</a>
				</span>
			</c:if>
	 	</th>
	 </tr>
	 <tr>
	   <td class="treeblock_td">
			<table cellspacing="1" cellpadding="0" class="table_box table_add">
				<tr>
					<td class="rtext">
						<ul id="treeDemo" class="ztree"></ul>
					</td>
				</tr>
			</table>
	   </td>
	 </tr>
	 <tr>
	   <td class="treeblock_td">
	   </td>
	 </tr>
	 </table>
   </div>
</div>
</body>
</html>