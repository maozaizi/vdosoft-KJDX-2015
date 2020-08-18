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
<title>纵向科研项目经费建卡分配详情</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap-theme.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="js/jquery.js"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script src="js/bootstrap.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.js"></script>
<![endif]-->

<style type="text/css" >
.mt48px {
	margin-top:48px
}
.color_element {
	font-weight:700;
	background-color:#d9edf7;
}
.dingwei {
	position:relative;
}
.pos {
	position:absolute;
	top:97px
}
.zbdw {
	left:2%
}
.fpbxh {
	left:20%
}
.zbsj {
	left:42%
}
.zbr {
	right:2%
}
</style>
<script type="text/javascript">
	function back(){
		window.close();
	}
	
</script>
</head>

<body>
<div class="container">
	<form class="form-horizontal" id="myform" name="myform" method="post">
	<h2 class="page-header text-center">纵向科研项目经费建卡历史详情</h2>
	<div style="margin:20px 0">
     	<a href="#" class="btn btn-success btn-sm" onclick="back();" role="button">关闭</a>
    </div>
	<c:forEach items="${projectList}" var="project">
  <div class="row dingwei">
    
  	<table class="table table-bordered table-striped">
      <tr>
        <td class="color_element" style="width:12%">项目类别</td>
        <td>${project.feeType }</td>
        <td class="color_element" style="width:12%">项目级别</td>
        <td>${project.projectlevel }</td>
      </tr>
      <tr>
        <td class="color_element">立项时间</td>
        <td>${project.builddate }</td>
        <td class="color_element">项目编号</td>
        <td>${project.projectcode }</td>
      </tr>
      <tr>
        <td class="color_element">项目名称</td>
        <td colspan="3">${project.projectname }</td>
      </tr>
      <tr>
        <td  class="color_element">计划类别</td>
        <td <c:if test="${project.isother!='1' }">colspan="3"</c:if>>${project.plantype }</td>
        <c:if test="${project.isother=='1' }">
	        <td class="color_element">其他计划类别</td>
	        <td>${project.othertype }</td>
        </c:if>
      </tr>
      <tr>
        <td class="color_element">所在单位</td>
        <td>${project.college }</td>
        <td class="color_element">负责人</td>
        <td>${project.dutyperson }</td>
      </tr>
      <tr>
        <td rowspan="3" class="color_element">到款金额（万元）</td>
        <td rowspan="3">${project.amount }</td>
        <td class="color_element">管理费（4%）</td>
        <td>${project.managefee }</td>
      </tr>
      <tr>
        <td class="color_element">科研费（96%）</td>
        <td>${project.keyanfee }</td>
      </tr>
      <tr>
        <td class="color_element"></td>
        <td>${project.fuzhufee }</td>
      </tr>
      <tr>
        <td class="color_element">财务总号</td>
        <td>${project.fintotal }</td>
        <td class="color_element">凭证编号</td>
        <td>${project.pzNo }</td>
      </tr>
    </table>
    <!--
    <div class="pos zbdw">制表单位：科技处</div>
    <div class="pos fpbxh">分配表序号：001</div>
    <div class="pos zbsj">制表时间：2014-11-9 20:41</div>
    <div class="pos zbr">制表人：郑罡</div>
    -->
	</div>
  
  <div class="row">
  	<table class="table">
      <tr>
        <th>制表单位</th>
        <th>分配表序号</th>
        <th>制表时间</th>
        <th>制表人</th>
      </tr>
      <tr>
        <td>科技处</td>
        <td>${project.xuhao }</td>
        <td>${project.allotdate }</td>
        <td>郑罡</td>
      </tr>
    </table>

  </div>
  </c:forEach>
  </form>
</div>

</body>
</html>