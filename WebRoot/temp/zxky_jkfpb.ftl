<#list projectlist as ls>
<#if (ls_index+1)%2==1&&ls_index!=0 >
<!--pdfpage-->
</#if>
<#if (ls_index+1)%2==1>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
<!--
body{margin:0;font-size:10.5pt;font-family:FangSong_GB2312;color:black}
table{table-layout:fixed;border-collapse:collapse;border-spacing:0;border:1px solid #000;width:100%}
.sected{position:relative;width:520pt;margin:0 auto;margin-top:32.9pt}
.sected h1{text-align:center;margin-bottom:33pt}
.sected td p{margin:0}
.std1{text-align:center;line-height:26.65pt;font-weight:800;font-family: SimHei;}
.std2{text-align:left;vertical-align:middle;padding:0 5.4pt;height:26.65pt;line-height:20.65pt}
.s_p{text-indent:21.0pt;margin:5pt 0;line-height:225%}
.s_p span{line-height:150%}
.s_p1{line-height:128%;text-align:left}
.s_p1 span{margin-right:3pt;line-height:150%}
div.sected td{border-right:1px solid #000;border-bottom:1px solid #000}
.pos{position:absolute}
.zbdw{top:32pt;left:20pt}
.fpbxh{top:32pt;left:129pt}
.zbsj{top:32pt;left:240pt}
.zbr{top:32pt;right:72pt}
.sign_zhengg{top:12pt;right:0}
-->
</style>
</head>
<body>
</#if>
<div class="sected">
	<h1 class="std1">纵向科研项目经费建卡分配表</h1>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="std1" style="width:65pt;">经费类别</td>
      <td class="std2">${ls.feeType}</td>
      <td class="std1">项目级别</td>
      <td colspan="2" class="std2">${ls.projectlevel}</td>
      <td class="std1">项目编号</td>
      <td class="std2" style="width:30%;">${ls.projectcode}</td>
    </tr>
  </table>

  <table>
    <tr>
      <td class="std1">项目名称</td>
      <td colspan="8" class="std2">${ls.projectname}</td>
    </tr>
    <tr>
      <td class="std1">计划类别</td>
      <td colspan="6" class="std2">
      		<#if ls.isother != '1'>
      			${ls.plantype}
      		<#else>
				${ls.othertype}
			</#if>
      	</td>
      <td class="std1">立项时间</td>
      <td class="std2">${ls.builddate}</td>
    </tr>
    <tr>
      <td class="std1">所在单位</td>
      <td colspan="6" class="std2">${ls.college}</td>
      <td class="std1">负责人</td>
      <td class="std2">${ls.dutyperson}</td>
    </tr>
    <tr>
      <td colspan="2" rowspan="2" class="std1">到款金额（万元）</td>
      <td colspan="5" rowspan="2" class="std2">#{ls.amount;m4}</td>
      <td class="std1"><span class="std1" style="width:10%;">管理费 -4%</span></td>
      <td class="std1">科研费-96%</td>
    </tr>
    <tr>
      <td class="std2">#{ls.managefee;m4}</td>
      <td class="std2">#{ls.keyanfee;m4}</td>
    </tr>
    <tr>
      <td class="std1">财务总号</td>
      <td colspan="2" class="std2">${ls.fintotal}</td>
      <td class="std2"><span class="std1">凭证编号</span></td>
      <td colspan="3" class="std2">${ls.pzNo}</td>
      <td class="std1">是否后续</td>
      <td class="std2">
		<#if ls.isfollow != '1'>
			否
		<#else>
			是
		</#if>
	  </td>
    </tr>
  </table>
  <div class="pos zbdw">制表单位：科技处</div>
  <div class="pos fpbxh">分配表序号：${ls.xuhao}</div>
  <div class="pos zbsj">制表时间：${ls.allotdate}</div>
  <div class="pos zbr">制表人：</div>
  <#if issign != '0'>
  <div class="pos sign_zhengg"><img src="file:/e:/DEV/tomcat6/webapps/kjdx/temp/sign_zhengg.png" width="96" height="58"  alt=""/></div>
  </#if>
</div>
<#if (ls_index+1)%2==1>
<hr style="margin-top:74pt;" />
</#if>
<#if (ls_index+1)%2==0||!ls_has_next>
</body>
</html>
</#if>
</#list>
