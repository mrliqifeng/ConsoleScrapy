<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>通用爬虫 - 首页</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="scripts/function.js"></script>
	<script type="text/javascript">
		function detail(id){
			document.location = 'detail?id=' + id;
		}
		function deleteScrapy(id){
			document.location = 'deleteScrapy?id=' + id;
		}
	</script>
	<sx:head/>
</head>
<body>
<div class="main wrap">
	<table class="house-list">
	<s:if test="list!=null">
	<s:iterator value="list" id="info" status="status">
		<c:if test="${status.count%2 != 0}">
		<s:hidden name="statusString" key="statusString" value="%{#request.statusMap[#info.id]}"/>
		<tr>
			<td>
				<dl>
					<dt>${info.taskname}</dt>
					<dd>
						网址：${info.url}<br/>
						状态：<s:property value="%{#request.statusMap[#info.id]}"/>
					</dd>
				</dl>
			</td>
			<td class="house-type"><label class="ui-green"><input type="button" onclick="deleteScrapy(${info.id})" name="deleteButton" value="删   除" /></label></td>
			<td class="house-type"><label class="ui-green"><input type="button" onclick='detail(${info.id})' name="modify" value="任务详情" /></label></td>
		</tr>
		</c:if>
		<c:if test="${status.count%2 == 0}">
		<s:hidden name="statusString" key="statusString" value="%{#request.statusMap[#info.id]}"/>
		<tr class="odd">
			<td>
				<dl>
					<dt>${info.taskname}</dt>
					<dd>
						网址：${info.url}<br/>
						状态：<s:property value="%{#request.statusMap[#info.id]}"/>
					</dd>
				</dl>
			</td>
			<td class="house-type"><label class="ui-green"><input type="button" onclick="deleteScrapy(${info.id})" name="deleteButton" value="删   除" /></label></td>
			<td class="house-type"><label class="ui-green"><input type="button" onclick='detail(${info.id})' name="modify" value="任务详情" /></label></td>
		</tr> 
		</c:if>
		
    </s:iterator>
    </s:if>
	</table>
	<div class="pager">
		<ul>
			<li class="current"><a href="pagging.action?page=1">首页</a></li>
			<li><a href='pagging.action?page=<s:property value="prevPage"/>'>上一页</a></li>
			<li><a href='pagging.action?page=<s:property value="nextPage"/>'>下一页</a></li>
			<li><a href='pagging.action?page=<s:property value="totalPage"/>'>末页</a></li>
		</ul>
		<a href='page/add.jsp'>新建爬虫任务</a>
		<a href='page/login.jsp'  style="margin-left:20px">登出</a>
	</div>
</div>
</body>
</html>