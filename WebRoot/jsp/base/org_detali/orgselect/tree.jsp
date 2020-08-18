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
<title>组织结构</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script src="${pageContext.request.contextPath }/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>
<SCRIPT type="text/javascript">
		window.console = window.console || (function(){
			var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function(){};
			return c;
		})();

		var setting = {
			view: {
				showLine: false
			},
			data:{
				key:{
					children:'childlist',
					name:'orgname',
					url:'eurl'
				}
			},
			callback:{
				onClick:function(e,id,node){
					console.log(node);
					if(node.url != ''){
						window.parent.frames["app_rightFrame"].location.href = "${pageContext.request.contextPath}"+node.url;
					}
				}
			}
		};
		$(document).ready(function(){
			$.ajax({
				url:'${pageContext.request.contextPath}/ajaxapi/org_select/getTree?PRU=${param.PRU }&selectedType=${param.selectedType }&treeType=${param.treeType }&closeAble=${param.closeAble }&fn=${param.fn }',
				type:'post',
				dataType:'json',
				success:function(data, textStatus, jqXHR){
					$.fn.zTree.init($("#treeDemo"), setting, data.organizationdetalilist);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					treeObj.expandAll(true);
				},
				error:function(){
					alert("error");
				}
			});
		});
	</SCRIPT>
</head>
<body>
	<div id="treeDemo" class="ztree"></div>
</body>
</html>
