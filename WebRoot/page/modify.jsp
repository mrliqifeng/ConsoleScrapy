<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爬虫信息</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript">
		function taskStart(id,status){
			if(status=="正在运行"){
				alert("此任务正在运行，请勿重复运行")
			}
			else{
				 document.controlForm.submit();
			}
		}
		function taskStop(id,status){
			if(status=="未运行"){
					alert("此任务尚未运行,请勿重复停止")
				}
			else{
				 document.controlForm.submit();
				}
		}
		function preview(taskname){
			document.location = 'preview?taskname='+taskname;
		}
		function downloadCsv(taskname){
			document.location = 'downloadCsv?taskname='+taskname;
		}
	</script>
</head>
<body>

<div id="regLogin" class="wrap">
	<div class="dialog">
		<dl class="clearfix">
			<dt><a href="pagging.action">首页   </a> 爬虫信息  </dt>
		</dl>
		<div class="box">
			<form action="control" name="controlForm" method="post" >
				<div class="infos">
					<table class="field">
						<tr>
							<td class="field">任务名：</td>
							<td>
							<s:property value="%{#request.taskInfo.taskname}"/>
							</td>
						</tr>
						<s:hidden name="uuid" key="uuid" value="%{#request.taskInfo.uuid}"/>
						<s:hidden name="taskname" key="taskname" value="%{#request.taskInfo.taskname}"/>
						<s:hidden name="statusString" key="statusString" value="%{#request.statusString}"/>
						<s:hidden name="id" key="id" value="%{#request.taskInfo.id}"/>
						<tr>
							<td class="field">状态：</td>
							<td>
							<s:property value="%{#request.statusString}"/>
							</td>
						</tr>
						<tr>
							<td class="field">网址：</td>
							<td>
							<s:textfield cssClass="text" key="url" name="url" value="%{#request.taskInfo.url}"/>
							</td>
						</tr>
						<tr>
							<td class="field">规则内容：</td>
							<td>
							<s:textarea cssClass="text" key="rulecontent" name="rulecontent" cols="40" rows="20" value="%{#request.taskInfo.rulecontent}"/>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<!--  <s:submit onclick="stop" value="启 动"></s:submit>	-->
					<input type="button" onclick="taskStart(${taskInfo.id},'${statusString}')"  name="stopInfo" value="启 动" />
					<input type="button" onclick="taskStop(${taskInfo.id},'${statusString}')" name="stopInfo" value="停 止" />
					</div>
					<div class="buttons">
					<input type="button" onclick="preview('${taskInfo.taskname}')" name="previewButton" value="预 览" />
					<input type="button" onclick="downloadCsv('${taskInfo.taskname}')" name="downloadButton" value="下 载" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>