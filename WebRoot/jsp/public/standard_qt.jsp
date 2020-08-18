<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="pure-g">
	<div class="pure-u-1">
        <div class="pure-u-1-5">
        	<a href="####" class="pure-button"><span>共(</span> <span> ${page.numPerPage}/${page.totalRows} </span> <span>)条</span><span>(</span><span> ${page.currentPage}/${page.totalPages} </span><span>)页</span></a>
        </div>
        
        <div class="pure-u-1-3">
        
            <c:if test="${page.currentPage==1}"><a href="####" class="pure-button">首页</a></c:if>
            <c:if test="${page.currentPage!=1}"><a href="${pageContext.request.contextPath}/server/${pageurl}?${page.action}&currentPageNO=1" class="pure-button">首页</a></c:if>
            
            
            <c:if test="${page.currentPage==1}"><a href="####" class="pure-button">上一页</a></c:if>
            <c:if test="${page.currentPage!=1}"><a href="${pageContext.request.contextPath}/server/${pageurl}?${page.action}&currentPageNO=${page.currentPage-1}" class="pure-button">上一页</a></c:if>
            
            <c:if test="${page.currentPage==page.totalPages}"><a href="####" class="pure-button">下一页 </a></c:if>
            <c:if test="${page.currentPage!=page.totalPages}"><a href="${pageContext.request.contextPath}/server/${pageurl}?${page.action}&currentPageNO=${page.currentPage+1}" class="pure-button">下一页</a></c:if>
            
            <c:if test="${page.currentPage==page.totalPages}"><a href="####" class="pure-button">末页</a></c:if>
            <c:if test="${page.currentPage!=page.totalPages}"><a href="${pageContext.request.contextPath}/server/${pageurl}?${page.action}&currentPageNO=${page.totalPages}" class="pure-button">末页</a></c:if>
        
        </div>
        
        <div class="pure-u-1-5">
        	<span class="">到</span>
        	<input id="topage.page" style=" border: 1px solid #ccc;border-radius: 4px;box-shadow: 0 1px 3px #ddd inset;box-sizing: border-box;display: inline-block;padding: 0.5em 0.6em; width:50px;height: 30px;" type="text" name="topage.page" placeholder="${page.currentPage}" />
        	<span class="">页</span>
        	<a href="###" onclick="javascript:p=document.getElementById('topage.page').value;location.href='${pageContext.request.contextPath}/server/${pageurl}?${page.action}&currentPageNO='+p;" class="pure-button">GO</a>
        </div>
	</div>
</div>




