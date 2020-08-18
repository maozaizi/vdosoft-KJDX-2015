<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp" %>
<%@include file="/jsp/public/limit_top.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>导入历史项目数据</title>
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/pure-base.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/pure-buttons.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/pure-forms.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/ys-ht-pure-custom.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/uploadify/uploadify.css"rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
            $("#uploadify").uploadify({
               	height         :  30,
               	buttonClass    :  'btn',
               	buttonText     :  '选择上传文件',
			   	swf            :  '${pageContext.request.contextPath}/js/uploadify/uploadify.swf',
				uploader       :  '${pageContext.request.contextPath}/ajaxapi/fileUpload/upload',
				width          :  120,
				auto           : true,//是否选取文件后自动上传
                multi          : true,//是否支持多文件上传
                fileTypeExts   :'*.xls',
                fileTypeDesc   :'请选择EXCEL',
                fileSizeLimit  :'51200',
                onUploadSuccess: function(file, data, response){
                	var jsondata =  JSON.parse(data);  //以JSON的形式处理文件信息包括：路径filepath,名称name等
                	var filelist = jsondata.filelist;
    				var fileUrl = filelist[0].filepath;
    				var params ="fileUrl="+fileUrl;
	   				$.ajax({
					   type: "POST",
					   url: "${pageContext.request.contextPath}/ajaxapi/project/saveHistoryProjects",
					   data: params,
					   dataType:"json",
					   success: function(ajaxdata){
				   	   		if(ajaxdata.message==undefined){
					   			document.getElementById("message").innerHTML="您的导入EXCEL格式错误，请检查后重新上传!";
					   		}else{
					   			document.getElementById("message").innerHTML=ajaxdata.message;
					   		}
					   }
				    });
                   },    
               'onError' : function(event, queueID, fileObj){ 
                   alert("文件:" + fileObj.name + " 上传失败");}  ,
                onUploadStart:function(){
                   		document.getElementById("message").innerHTML="正在上传,请稍后...";
                }
            });
        });
        function back(){
        	window.location.href="${pageContext.request.contextPath}/api/project/list";
        }
</script>
</head>
<body>
<div class="main_layout">
 <div class="pure-g-r">
  <div class="pure-u-1">
			<dl class="dl-horizontal">
				<dd><h4>导入历史项目记录</h4></dt>
				<dd>
					<p>
						<p><input type="file" name="uploadify" id="uploadify" /></p>
					</p>
					<p>
						导入说明:<br />
						1.导入文件的格式必须是xls格式，并且大小不超过50MB；
						<br />
						2.文件的内容格式必须完全依照模板进行编辑：填写前请仔细阅读批注；
						<br />
						3.请确定导入文件中除数量、单价、金额列其他列均为文本；
						<br />
						4.导入完成后请仔细阅读导入信息；
						<br />
						5.如果导入失败，请将excel中的数据有效性全部变为任何值。
						<br />
						
					</p>
				</dd>
			</dl>
			<dl class="dl-horizontal">
				<dd>
					<div id="message"></div>
				</dd>
			</dl>
			<dl class="dl-horizontal">
				<dd>
					<a href="javascript:void(0);" onclick="back();" class="pure-button pure-button-small">
						返回
					</a>
				</dd>
			</dl>
		</div>
	</div>
</div>
</body>
</html>
