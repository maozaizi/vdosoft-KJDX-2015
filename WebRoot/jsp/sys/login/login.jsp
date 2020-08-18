<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/config/taglib.jsp" %>
<%@include file="/jsp/public/notlimit_top.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta charset="UTF-8">
<title>郑老师！欢迎登陆</title>

<link href="${pageContext.request.contextPath }/css/pure.css" rel="stylesheet" type="text/css"/>
<!--[if lte IE 8]>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/grids-responsive-old-ie-min.css">
<![endif]-->
<!--[if gt IE 8]><!-->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/grids-responsive.css"/>
<!--<![endif]-->
<link href="${pageContext.request.contextPath }/css/ydb-page-style.css" rel="stylesheet" type="text/css"/>


<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/bootstrap-theme.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrapv330/mz_style.css" rel="stylesheet" type="text/css" media="(orientation:landscape)">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${pageContext.request.contextPath}/js/jquery.js"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
  <script src="${pageContext.request.contextPath}/js/respond.js"></script>
<![endif]-->

<script type="text/javascript">
   $(document).ready(function (){
	    // 判断是否是IE6  开始
	    var ua = window.navigator.userAgent;
	     var version = 0;
         var msie = ua.indexOf("MSIE ");
         if (msie > 0){
         	version =  parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
         	if(version==6){
         		//window.location.href = "${pageContext.request.contextPath}/jsp/fireweb/ie.jsp"
         	}
         }
         // 判断是否是IE6  结束

         // 判断浏览器不是IE也不是火狐
         if((ua.indexOf('MSIE') >= 0) && (ua.indexOf('Opera') < 0)){
         }else if (ua.indexOf('Firefox') >= 0){
         }else if(ua.indexOf('Chrome') >= 0){
         }else{
         	//window.location.href = "${pageContext.request.contextPath}/jsp/fireweb/firefox.jsp"
         }
  });

	if (top.location != self.location){
	 	top.location.href ='${pageContext.request.contextPath}/api/login/tologin';
	 }
	function changeImg(){
	$("#vdimgck").attr("src","${pageContext.request.contextPath}/jsp/sys/login/image.jsp?d="+new Date());
	}
</script>
<!-- jquery验证start -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<!-- jquery验证end -->
<script type="text/javascript">
	$(function($){
			$("#loginForm").validate({
				rules: {
					userName: {
						required: true
					},
					userPwd: {
						required: true
					}
				}
			});
	});
</script>
<style type="text/css">
*{
	margin:0;
	padding:0;
}
* {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box
}
*:before,
*:after {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box
}
body {
	background-color:#f1f1f1
}
/*page*/
.wrap{
	width:1000px;
	margin:10% auto;
}
.loginimg { height:305px;
	background-image: url('');
	background-position: 49% 50%;
	background-repeat: no-repeat;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	position: relative;
}
.loginimg p {
	width:100%;
	padding:15px;
	text-align:center;
	position:absolute;
	font-size:large;
	color:#FFF;
	background-color: rgb( 255, 88, 66 );
  	opacity: 0.851;
	bottom:0;
	z-index:2
}
</style>
</head>
<body>

<body class="boxed">
	<div class="container">
    <div class="jumbotron">
      <h1>Hello, Mr Zheng</h1>
      
      <form method="post" name="loginForm" id="loginForm" action="${pageContext.request.contextPath}/api/login/login" role="form">
      	<input type="hidden" name="url" value="${pageContext.request.contextPath}/main.jsp"/>
        <div class="form-group">
          <label for="userName">User name</label>
          <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter User name">
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Password</label>
          <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Password">
        </div>
        
        <button type="submit" class="btn btn-primary btn-lg btn-block">来！欢乐的登录吧！分钱喽 ：）</button>       
      </form>
     
    </div>
  </div>
</body>

<!--  
<div class="wrap">


        <div class="pure-g">
            <div class="pure-u-3-5">
                <div class="loginimg">
                	<p>移动营销第一品牌 <span class="font-size:12px;">生产版V3.0.5</span></p>
                </div>
            </div>
            <div class="pure-u-2-5">

                    <div class="">

                        <form class="pure-form pure-form-stacked" >
                        	
                            <fieldset>
                                <legend><h1>欢迎登录</h1></legend>

                                <label for="username">用户名</label>
                                <input class="pure-u-1" type="text" placeholder="用户名"/>

                                <label for="password">密码</label>
                                <input id="userPwd" name="userPwd" class="pure-u-1" type="password" placeholder="密码"/>

                                
                                <label for="username">验证码</label>
                                <input name="number" id="number" class="pure-u-1-3" type="text" maxlength="4" placeholder="验证码"/>
                        		<span><img id="vdimgck" src="${pageContext.request.contextPath}/jsp/sys/login/image.jsp" alt="看不清？点击更换" style="cursor:pointer" onclick="changeImg();"/></span>
                                <span onclick="changeImg();" style="cursor: pointer;">换一张</span>

                                <label for="remember" class="pure-checkbox" style="margin-top:40px;">
                                    <input id="remember" type="checkbox"/> 记住用户名
                                </label>
                                

                                <button type="submit" class="pure-button pure-button-primary pure-u-1" style="margin-top:60px;">登　录</button>

                            </fieldset>
                        </form>

                    </div>

            </div>

        </div>
    </div>
-->
<script type="text/javascript">
<c:if test="${not empty errMsg}">
alert("${errMsg}");
</c:if>
<c:if test="${not empty param.reg}">
alert("注册成功！");
</c:if>
</script>
${msg}
</body>
</html>