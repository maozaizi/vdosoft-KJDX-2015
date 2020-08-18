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
<title>项目查询</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap-theme.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/paginator.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/webcalendar.js" type="text/javascript"></script>

<script type="text/javascript">
	function getplantype(dataItemId){
		var url = "${pageContext.request.contextPath}/ajaxapi/project/getPlanTypeList?dataItemId="+dataItemId;
		$.ajax({
				url:url,
				type:'post',
				dataType:'json',
				//ajax异步请求，默认为true
				async:false,
				success:function(data, textStatus, jqXHR){
					var planlist = data.plantypelist;
					var select = document.getElementById("planType");
						for(var i=select.length-1;i>0;i--){
							 select.options.remove(i);
						}
						if(planlist.length>0){
							for(var i=0;i<planlist.length;i++){
							var opt = new Option(planlist[i].dataitemname,planlist[i].dataitemid);
							select.options[select.length]=opt;  
	           			}
					}
					
				}
		});
		
	}
	function toAdd(){
		document.myform.action="${pageContext.request.contextPath}/api/project/toAdd";
		document.myform.submit();
	}
	
	function toSearch(){
		document.myform.action="${pageContext.request.contextPath}/api/project/list";
		document.myform.submit();
	}
	function toDel(id){
		if(confirm("是否确定删除")){
			document.myform.action="${pageContext.request.contextPath}/api/project/deleteProject?pid="+id;
			document.myform.submit();
		}
		
	}
	function toModify(id){
		document.myform.action="${pageContext.request.contextPath}/api/project/toupdateProject?pid="+id;
		document.myform.submit();
	}
	function toimportProject(){
		document.myform.action="${pageContext.request.contextPath}/api/project/toimportProject";
		document.myform.submit();
	}
	
	function toimportHisProject(){
		document.myform.action="${pageContext.request.contextPath}/api/project/toimportHisProject";
		document.myform.submit();
	}
	function viewProject(id){
		document.getElementById("id").value= id;
		document.myform.action="${pageContext.request.contextPath}/api/project/viewProject";
		document.myform.submit();
	}
	function exportProject(){
		document.myform.action="${pageContext.request.contextPath}/api/project/exportProject";
		document.myform.submit();
	}
	
	function exportExcelProject(){
		document.myform.action="${pageContext.request.contextPath}/api/project/exportExcelProject";
		document.myform.submit();
	}
	function toViewHistory(){
		document.myform.action="${pageContext.request.contextPath}/api/project/toViewHistory";
		document.myform.submit();
	}
	function selectAll(){
		var projectall = document.getElementById("projectall").checked;
		if(projectall){
			var projects = document.getElementsByName("projectid");
			for(var i=0;i<projects.length;i++){
				projects[i].checked = true;
			}
		}else{
			var projects = document.getElementsByName("projectid");
			for(var i=0;i<projects.length;i++){
				projects[i].checked = false;
			}
		}
	}
</script>
</head>
<body>
<div class="container-fluid">
  <h2 class="page-header">项目查询</h2>
  <form name="myform" action="" id="myform" method="post">
  <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/api/project/list"></input>
  <input type="hidden" id="id" name="id"></input>
  <div class="row">
    <div class="col-md-4">
  		<div class="form-horizontal" role="form">
      
        <div class="form-group">
          <label for="projectname" class="col-sm-2 control-label">项目名称</label>
          
          <div class="col-sm-10">
            <input type="text" class="form-control" id="projectname" name="projectname" value="${param.projectname}" placeholder="项目名称">
          </div>
        </div>
        
        <div class="form-group">
          <label for="projectcode" class="col-sm-2 control-label">项目编号</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="projectcode" name="projectcode" value="${param.projectcode}" placeholder="项目编号">
          </div>
        </div>
        
        <div class="form-group">
          <label for="builddate" class="col-sm-2 control-label">立项时间</label>
          <div class="col-sm-10">
             <input type="text" class="form-control" id="builddate" name="builddate" value="${param.builddate}"  placeholder="立项时间">
          </div>
        </div>
        
        <div class="form-group">
          <label for="projectcode" class="col-sm-2 control-label">制表时间</label>
          <div class="col-sm-5">
            <input type="text" class="form-control" name="starttime" onclick="SelectDate(this,'yyyy-MM-dd',0,0)" onfocus="SelectDate(this,'yyyy-MM-dd',0,0)" placeholder="开始时间" readonly="readonly" value="${param.starttime}"/>
          </div>
          <div class="col-sm-5">
            <input type="text" class="form-control" name="endtime" onclick="SelectDate(this,'yyyy-MM-dd',0,0)" onfocus="SelectDate(this,'yyyy-MM-dd',0,0)" placeholder="结束时间" readonly="readonly" value="${param.endtime}">
          </div>
        </div>
        
        <div class="form-group">
          <label for="samount" class="col-sm-2 control-label">到款金额</label>
          <div class="col-sm-5">
            <input type="text" class="form-control" id="samount" name="samount" value="${param.samount}" placeholder="最小到款金额"/>
          </div>
          <div class="col-sm-5">
            <input type="text" class="form-control" id="eamount" name="eamount" value="${param.eamount}"  placeholder="最大到款金额">
          </div>
        </div>
        
        
      </div>
    </div>
    
    <div class="col-md-4">
  		<div class="form-horizontal" role="form">
      
      	<div class="form-group">
          <label for="college" class="col-sm-2 control-label">负责人单位</label>
          
          <div class="col-sm-10">
            <input type="text" class="form-control" id="college" name="college" value="${param.college}" placeholder="负责人单位">
          </div>
        </div>
        
        <div class="form-group">
          <label for="dutyperson" class="col-sm-2 control-label">负责人</label>
          
          <div class="col-sm-10">
            <input type="text" class="form-control" id="dutyperson" name="dutyperson" value="${param.dutyperson}" placeholder="负责人">
          </div>
        </div>
        
        <div class="form-group">
          <label for="fintotal" class="col-sm-2 control-label">财务总号</label>
          
          <div class="col-sm-10">
            <input type="text" class="form-control" id="fintotal" name="fintotal" value="${param.fintotal}" placeholder="财务总号">
          </div>
        </div>
        
        <div class="form-group">
          <label for="pzNo" class="col-sm-2 control-label">凭证编号</label>
          
          <div class="col-sm-10">
            <input type="text" class="form-control" id="pzNo" name="pzNo" value="${param.pzNo}" placeholder="凭证号">
          </div>
        </div>
        
        <div class="form-group">
          <label for="" class="col-sm-2 control-label">是否后续</label>
          
          <div class="col-sm-10">
            <input type="radio" name="isFollow" id="isFollow1" value="0" <c:if test="${param.isFollow=='0' }">checked</c:if>>
            <label for="isFollow1" >否（新添加项目）</label>
            
            <input type="radio" name="isFollow" id="isFollow2" value="1" <c:if test="${param.isFollow=='1' }">checked</c:if>>
            <label for="isFollow2" >是（后续项目）</label>
          </div>
          
        </div>
        
      </div>
    </div>
    
    <div class="col-md-4">
  		<div class="form-horizontal" role="form">
      
      	<div class="form-group">
          <label for="" class="col-sm-2 control-label">经费类别</label>
          
          <div class="col-sm-4">
            <select  id="feeType" name="feeType" class="form-control">
	          <option value="">请选择</option>
	          <option value="6" <c:if test="${param.feeType=='6' }">selected</c:if>>6</option>
	          <option value="5" <c:if test="${param.feeType=='5' }">selected</c:if>>5</option>
	        </select>
          </div>
        </div>
        
        <div class="form-group">
          <label for="" class="col-sm-2 control-label">项目级别</label>
          
          <div class="col-sm-10">
            <select class="form-control" name="projectLevel" id="projectLevel" onchange="getplantype(this[selectedIndex].value);">
	          <option value="">请选择</option>
	          	<c:forEach var="projectLevel" items="${web:getDataItem('projectLevel')}" >
		        	<option value="${projectLevel.dataItemId}" <c:if test="${projectLevel.dataItemId ==param.projectLevel }">selected</c:if>>${projectLevel.dataItemName}</option>
			 	</c:forEach>
	        </select>
          </div>
        </div>
        
        <div class="form-group">
          <label for="" class="col-sm-2 control-label">计划类别</label>
          
          <div class="col-sm-10">
            <select class="form-control" id="planType" name="planType">
          		<option value="">请选择</option>
          		<c:if test="${!empty planTypeList}">
          			<c:forEach items="${planTypeList}" var="plan">
          				<option value="${plan.dataitemid}" <c:if test="${plan.dataitemid ==param.planType }">selected</c:if>>${plan.dataitemname}</option>
          			</c:forEach>
          		</c:if>
        	</select>
          </div>
        </div>
        
        <div class="form-group">
          <label for="othertype" class="col-sm-2 control-label">其它计划类别</label>
          
          <div class="col-sm-10">
            <input type="text" class="form-control" id="othertype" name="othertype" value="${param.othertype}" placeholder="其他计划类别">	
          </div>
        </div>
        
      </div>
    </div>
       
  </div>
  
  <button type="button" class="btn btn-default btn-lg btn-block" onclick="toSearch();">开始查询</button>
  
  <div style="margin:20px 0">
  	<button type="button" class="btn btn-primary" onclick="toAdd();">添加项目</button>
  	<button type="button" class="btn btn-primary" onclick="exportExcelProject();">导出项目</button>
  	<button type="button" class="btn btn-primary" onclick="toViewHistory();">查看修改历史</button>
  	<button type="button" class="btn btn-primary" onclick="toimportProject();">导入项目</button>
  	<button type="button" class="btn btn-primary" onclick="toimportHisProject();">导入历史项目(2014以前)</button>
  </div>
  <div style="margin:20px 0">
  	<button type="button" class="btn btn-primary" onclick="exportProject();">打印分配表</button>
  	是否签字
  	<input type="radio" name="issign" id="issign1" value="0" >
            否
    <input type="radio" name="issign" id="issign2" value="1" checked>
            是     
  </div>

  <div class="table-responsive">
    <table class="table table-bordered table-striped table-responsive">
      <thead>
        <tr>
          <th><input type="checkbox" name="projectall" id="projectall" onclick="selectAll(this);">选择</th>
          <th>单序号</th>
          <th>项目编号</th>
          <th style="width:10%;">项目名称</th>
          <th>负责人</th>
          <th>负责人单位</th>
          <th>项目级别</th>
          <th>计划类别</th>
          <th>是否后续</th>
          <th>分配日期</th>
          <th>到款金额（万元）</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      	<c:forEach items="${page.resultList}" var="project">
	        <tr>
	          <td><input type="checkbox" name="projectid" value="${project.id }"></td>
	          <td>${project.xuhao }</td>
	          <td>${project.projectcode }</td>
	          <td><a href="#" onclick="viewProject('${project.id }');">${project.projectname }</a></td>
	          <td>${project.dutyperson }</td>
	          <td>${project.college }</td>
	          <td>${project.projectlevel }</td>
	          <td>
	          	<c:if test="${project.isother=='0'||project.isother==null }">
        			${project.plantype }
	        	</c:if>
	        	<c:if test="${project.isother=='1' }">
	        		${project.othertype }
	        	</c:if>
	          </td>
	          <td>
	          	<c:if test="${project.isfollow==1 }">是</c:if>
	          	<c:if test="${project.isfollow==0 }">否</c:if>
	          </td>
	          <td><tag:date value="${project.allotdate }"/></td>
	          <td>${project.amount }</td>
	          <td>
	          	<a href="###" onclick="toModify('${project.id }');">修改项目</button>
  				<a href="###" onclick="toDel('${project.id }');">删除项目</button>
	          </td>
	        </tr>
        </c:forEach>
        <c:if test="${empty page.resultList}">
         	<tr> 
            	<td colspan="12" style="text-align:center;"><font color="red" style="align:center;">暂无数据！</font></td>
          	</tr>
         </c:if>
      </tbody>
      <tfoot>
			<tr>
        		<td colspan="12">
					<%@include file="/jsp/public/standard.jsp" %>
        		</td>
	        </tr>
		</tfoot>
    </table>
  </div>
  <div style="margin:0 0 20px">
  	<button type="button" class="btn btn-primary">打印分配表</button>
  </div>
  
  
  
  </form>
</div>
</body>
</html>