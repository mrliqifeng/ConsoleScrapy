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
<title>新建爬虫</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript">
		function taskStart(){
				 document.controlForm.submit();
		}
		function taskStop(){
					alert("此任务尚未运行,请勿重复停止")
		}
	</script>
</head>
<body>

<div id="regLogin" class="wrap">
	<div class="dialog">
		<dl class="clearfix">
			<dt>爬虫信息</dt>
		</dl>
		<div class="box">
			<form action="control" name="controlForm" method="post" >
			<s:hidden name="id" key="id" value="0"/>
				<div class="infos">
					<table class="field">
						<tr>
							<td class="field">任务名：</td>
							<td>
							<s:textfield cssClass="text" key="taskname" name="taskname"/>
							</td>
						</tr>
						<tr>
							<td class="field">状态：</td>
							<td>
							初始化
							</td>
						</tr>
						<tr>
							<td class="field">网址：</td>
							<td>
							<s:textfield cssClass="text" key="url" name="url"/>
							</td>
						</tr>
						<tr>
							<td class="field">规则内容：</td>
							<td>
							<s:textarea cssClass="text" key="rulecontent" name="rulecontent" cols="40" rows="20"/>
							</td>
						</tr>
					</table>
					<div class="buttons">
					<input type="button" onclick="taskStart()"  name="stopInfo" value="启 动" />
					<input type="button" onclick="taskStop()" name="stopInfo" value="停 止" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>