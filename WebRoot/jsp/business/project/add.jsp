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
<title>添加项目页面</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap-theme.css" rel="stylesheet">


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
					document.getElementById("otherType").value="";
						for(var i=select.length-1;i>0;i--){
							 select.options.remove(i);
						}
						if(planlist.length>0){
							for(var i=0;i<planlist.length;i++){
							var opt = new Option(planlist[i].dataitemname,planlist[i].dataitemid+"_"+planlist[i].p2);
							select.options[select.length]=opt;  
	           			}
					}
					
				}
		});
		
	}
	function hiddenText(){
		var code = document.getElementById("planType").value;
		var codes = code.split("_");
		
		if(codes[1] == "1"){
			
			document.getElementById("otherType").readOnly = false;
		}else{
			document.getElementById("otherType").value="";
			document.getElementById("otherType").readOnly = true;
		}
	}
	
	function add(){
		var code = document.getElementById("planType").value;
		var codes = code.split("_");
		if(codes[1] == "1"){
			document.getElementById("isother").value = "1";
			var otherType = document.getElementById("otherType").value;
			if(otherType==""||otherType==null){
				alert("请输入计划类别！");
				return;
			}
		}else{
			document.getElementById("isother").value = "0";
		}
		var total = document.getElementById("amount").value;
		var managefee = document.getElementById("managefee").value;
		var keyanfee = document.getElementById("keyanfee").value;
		var fuzhufee = 0;
		var intotal = addition(addition(Number(managefee),Number(keyanfee)),Number(fuzhufee));
		if(total>intotal){
			alert("填写金额小于到款金额！请重新填写！");
			return;
		}else if(total<intotal){
			alert("填写金额大于到款金额！请重新填写！");
			return;
		}
		document.myform.action="${pageContext.request.contextPath}/api/project/addProject";
		document.myform.submit();
	}
	function changefee(){
		var total = document.getElementById("amount").value;
		document.getElementById("managefee").value = accDiv(accMul(total,1000000)*4,100000000);
		document.getElementById("keyanfee").value = accDiv(accMul(total,1000000)*96,100000000);
	}
	//加法
	function addition(num1,num2){
	      return  Number((num1+num2).toFixed(4));
	}
	//乘法
	function accMul(arg1,arg2)   
	{   
		var m=0,s1=arg1.toString(),s2=arg2.toString();   
		try{m+=s1.split(".")[1].length}catch(e){}   
		try{m+=s2.split(".")[1].length}catch(e){}   
		return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
	} 
	//除法
	function accDiv(arg1,arg2){   
		var t1=0,t2=0,r1,r2;   
		try{t1=arg1.toString().split(".")[1].length}catch(e){}   
		try{t2=arg2.toString().split(".")[1].length}catch(e){}   
		with(Math){   
		r1=Number(arg1.toString().replace(".",""))   
		r2=Number(arg2.toString().replace(".",""))   
		return (r1/r2)*pow(10,t2-t1);   
		}   
	} 
	function back(){
		window.location.href ="${pageContext.request.contextPath}/api/project/list";

	}
</script>
</head>
<body>
<div class="container-fluid">
  <h2 class="page-header">添加数据</h2>
  <div class="row">
  <form class="form-horizontal" id="myform" name="myform" method="post">
  <input type="hidden" id="isother" name="isother"/>
  <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/api/project/list"/>
  
    <div class="form-group">
      <label for="projectname" class="col-sm-2 control-label">项目名称</label>
      <div class="col-sm-8">
        <input type="text" class="form-control col-xs-4" id="projectname" name="projectname" placeholder="项目名称">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">项目编号</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="projectcode" name="projectcode" placeholder="项目编号">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">是否临时编号</label>
      <div class="col-sm-8">
        <input type="checkbox" class="form-control" id="istemp" name="istemp" value="1" placeholder="项目编号">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">立项时间</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="builddate" name="builddate" placeholder="立项时间">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">负责人单位</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="college" name="college" placeholder="负责人单位">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">负责人</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="dutyperson" name="dutyperson" placeholder="负责人">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">经费类别</label>
      <div class="col-sm-8">
        <select class="form-control" id="feeType" name="feeType">
          <option>6</option>
          <option>5</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">项目级别</label>
      <div class="col-sm-8">
        <select class="form-control" name="projectLevel" id="projectLevel" onchange="getplantype(this[selectedIndex].value);">
        	<option value="">请选择</option>
          	<c:forEach var="projectLevel" items="${web:getDataItem('projectLevel')}" >
	        	<option value="${projectLevel.dataItemId}">${projectLevel.dataItemName}</option>
		 	</c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">计划类别</label>
      <div class="col-sm-8">
        <select class="form-control" id="planType" name="planType" onchange="hiddenText();">
          <option value="">请选择</option>
        </select>
      </div>
    </div>
    
    
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label"></label>      
      <div class="col-sm-8">
      	<input type="text" class="form-control" id="otherType" name="otherType" placeholder="请在此输入计划类别" readonly="true">
      </div>
      
    </div>
    
    
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">到款金额</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="amount" name="amount" placeholder="到款金额" onblur="changefee();">
      </div>
    </div>
    
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">管理费（4%）</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="managefee" name="managefee" placeholder="管理费">
      </div>
    </div>
    
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">科研费（96%）</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="keyanfee" name="keyanfee" placeholder="科研费">
      </div>
    </div>
    <!-- 
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">辅助费（15%）</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="fuzhufee" name="fuzhufee" placeholder="辅助费">
      </div>
    </div>
     -->
    <div class="form-group">
   		<label for="inputEmail3" class="col-sm-2 control-label">凭证编号</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="pzNo" name="pzNo" placeholder="凭证编号">
        </div>
    </div>
    
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="button" class="btn btn-primary" onclick="add();">保存</button>
        <button type="button" onclick="back();" class="btn btn-default">取消</button>
      </div>
    </div>
  </form>
  </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${pageContext.request.contextPath}/js/jquery.js"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>