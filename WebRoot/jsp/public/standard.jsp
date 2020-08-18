<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="pagination">
	<li class="disabled">
		<a href="###">
			<span>共(</span>
			<span> ${page.numPerPage}/${page.totalRows} </span>
			<span>)条</span>
			<span>(</span>
			<span> ${page.currentPage}/${page.totalPages} </span>
			<span>)页</span>
		</a>
	</li>
	
	<c:if test="${page.currentPage==1}"><li class="disabled"><a href="###">首页</a></li></c:if>
	<c:if test="${page.currentPage!=1}"><li><a href="${pageContext.request.contextPath}/api/${pageurl}?${page.action}&currentPageNO=1" >首页</a></li></c:if>
	
	<c:if test="${page.currentPage==1}"><li class="disabled"><a href="###">上一页</a></li></c:if>
	<c:if test="${page.currentPage!=1}"><li><a href="${pageContext.request.contextPath}/api/${pageurl}?${page.action}&currentPageNO=${page.currentPage-1}">上一页</a></li></c:if>
	
	<c:if test="${page.currentPage==page.totalPages}"><li class="disabled"><a href="###">下一页</a></li></c:if>
	<c:if test="${page.currentPage!=page.totalPages}"><li><a href="${pageContext.request.contextPath}/api/${pageurl}?${page.action}&currentPageNO=${page.currentPage+1}">下一页 </a></li></c:if>
	
	<c:if test="${page.currentPage==page.totalPages}"><li class="disabled"><a href="###">末页</a></li></c:if>
	<c:if test="${page.currentPage!=page.totalPages}"><li><a href="${pageContext.request.contextPath}/api/${pageurl}?${page.action}&currentPageNO=${page.totalPages}">末页</a></li></c:if>
	
	<li class="disabled"><a href="###">前往</a></li>
	<input id="topage.page" class="form-paginator" type="text" name="topage.page" placeholder="${page.currentPage}" onkeyup="checkvalue()" />
	
	<li><a href="###" onclick="javascript:p=document.getElementById('topage.page').value;location.href='${pageContext.request.contextPath}/api/${pageurl}?${page.action}&currentPageNO='+p;">GO</a></li>
</ul>
	
<script type="text/javascript">
	function checkvalue(){
		var topagevalue = document.getElementById("topage.page").value;
		var re = /^[1-9]*[1-9][0-9]*$/;
		if(!re.test(topagevalue)){
				document.getElementById("topage.page").value = "";
		}
	}
</script>




