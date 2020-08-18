<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/public/limit_top.jsp" %>
<%@include file="/jsp/config/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.1.9.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/highchart/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/highchart/modules/exporting.js"></script>
		<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '订单统计'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
                        }
                    }
                }
            },
            series: ${data}
        });
    });

</script>

   <style type="text/css">
    .left {
        float:left;
        display:inline;
        width:70%;
    }
    .right{
        float:left;
        display:inline;
        width:29%;
        height: 580px;
        border: 1px solid rgb(70, 115, 167);
        border-radius: 15px;
    }
    .divtext {
		overflow: hidden; /*自动隐藏文字*/
		text-overflow: ellipsis;/*文字隐藏后添加省略号*/
		white-space: nowrap;/*强制不换行*/
		width: 20em;/*不允许出现半汉字截断*/
		color:#6699ff;border:1px #ff8000 dashed;
	}
   </style>
	</head>
	<body>
  <div class="container-fluid top20">
  <div class="row-fluid" style="width:100%;height:100%">
  <div class="span12">
 		<div class="left" style="height: 580px;border:1px solid #4673a7;border-radius: 15px">
		<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		<div>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" style="font-size:14px;">
                <tr>
                  <td class="bold" height="25" > &nbsp;&nbsp;<strong>1</strong>、待审核 <span class="badge badge-success">${piebasic.dsh}</span>&nbsp;&nbsp;-&nbsp;&nbsp;
                  <!--
                  <strong>2</strong>、计划生产 <span class="badge badge-success">${piebasic.dsh}</span>&nbsp;&nbsp;-&nbsp;&nbsp;
                  -->
                  <strong>2</strong>、开始生产 <span class="badge badge-success">${piebasic.scjh}</span>&nbsp;&nbsp;-&nbsp;&nbsp;
                  <strong>3</strong>、完工入库 <span class="badge badge-success">${piebasic.kssc}</span>&nbsp;&nbsp;-&nbsp;&nbsp;
                  <!--
                  <strong>5</strong>、备货中 <span class="badge badge-success">${piebasic.bhz}</span>&nbsp;&nbsp;-&nbsp;&nbsp;
                  -->
                  <strong>4</strong>、已发货 <span class="badge badge-success">${piebasic.yfh}</span> &nbsp;&nbsp;-&nbsp;&nbsp;
                  <strong>5</strong>、已收货 <span class="badge badge-success">${piebasic.ysh}</span> &nbsp;&nbsp;-&nbsp;&nbsp;
                  <!--
                  <strong>8</strong>、待付款 <span class="badge badge-success">${piebasic.dfk}</span> &nbsp;&nbsp;-&nbsp;&nbsp;
                  -->
                  <strong>6</strong>、已付款 <span class="badge badge-success">${piebasic.yfk}</span> &nbsp;&nbsp;-&nbsp;&nbsp;
                  <strong>7</strong>、已完成 <span class="badge badge-success">${piebasic.ywc}</span>
                </tr>
        </table>
        <table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#FFFFFF"  class="inputstyle" style="font-size:14px;" >
          <tr>
            <td width="25%" bgcolor="#F0F0F0"><div align="right">总订单数：</div></td>
            <td width="25%" class="font12"><span class="badge">${piebasic.ddzs}</span> 个</td>
            <td width="25%" bgcolor="#F0F0F0"><div align="right">待审核订单：</div></td>
            <td width="25%" class="font12"><span class="badge">${piebasic.dsh}</span> 个</td>
          </tr>
          <tr>
            <td width="25%" bgcolor="#F0F0F0"><div align="right">备货中订单：</div></td>
            <td width="25%" class="font12"><span class="badge">${piebasic.bhz}</span> 个</td>
            <td width="25%" bgcolor="#F0F0F0"><div align="right">已完成订单：</div></td>
            <td width="25%" class="font12"><span class="badge">${piebasic.ywc}</span> 个</td>
          </tr>
          <tr>
            <td width="25%" >&nbsp;</td>
            <td width="25%" class="font12">&nbsp;</td>
            <td width="25%" >&nbsp;</td>
            <td width="25%" align="center"></td>
          </tr>
        </table>
        </div>
  </div>
  <div class="right">
  	<div id="mydiv">
		<table  style="width:100%;">
			<tr><td width="40%">订单号</td> <td width="40%">客户名称</td> <td width="20%">订单总价</td> </tr>
		</table>
		<div id="tablediv" style="border:1px solid #c0c0c0;text-align:left;width:100%;height:240px;overflow:hidden;">
			<table id="mytable" style="width:100%;">
				<c:forEach items="${orderList}" var="orderinfo" varStatus="status">
					<tr height="30px;">
						<td class="divtext"><a target="_blank" href="${pageContext.request.contextPath}/api/orderinfo/getOrderInfoMessage?orderId=${orderinfo.order_id}">${orderinfo.order_id}</a></td>
						<td class="divtext">${orderinfo.customer_name}</td>
						<td class="divtext">¥<span class="text-error">${web:formatAmount(orderinfo.order_total)}</span></td>
					</tr>
				</c:forEach>
		</table>
	</div>
  	<div id="mydiv1">
		<table  style="width:100%;">
			<tr>
				<td width="50%" align="right">欢迎您：</td>
				<td width="50%">${userMap.userName }</td>
			</tr>
			<tr>
				<td  align="right">上次登录时间为：</td>
				<td>${userMap.lastLoginTime }</td>
			</tr>
		</table>
	</div>
</div>
  </div>

  </div>
  </div>
  </div>
	</body>
</html>
<script type="text/javascript">
//滚动插件
(function($){
$.fn.extend({
        Scroll:function(opt,callback){
                //参数初始化
                if(!opt) var opt={};
                var _this=this.eq(0).find("table:first");
                var lineH=_this.find("tr:first").height(), //获取行高
                        line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
                        speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
                        timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
                if(line==0) line=1;
                var upHeight=0-line*lineH;
                //滚动函数
                scrollUp=function(){
                        _this.animate({
                                marginTop:upHeight
                        },speed,function(){
                                for(i=1;i<=line;i++){
                                        _this.find("tr:first").appendTo(_this);
                                }
                                _this.css({marginTop:0});
                        });
                }
                //鼠标事件绑定
                _this.hover(function(){
                        clearInterval(timerID);
                },function(){
                        timerID=setInterval("scrollUp()",timer);
                }).mouseout();
        }
})
})(jQuery);


$(document).ready(function(){
        $("#tablediv").Scroll({line:1,speed:500,timer:2000});
        $("#mytable tr:even").css({background: "#FEEDCE"}); // 2, 4 行
});
</script>
