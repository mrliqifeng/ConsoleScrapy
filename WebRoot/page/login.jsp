<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>通用爬虫 - 用户登录</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="regLogin" class="wrap">
	<div class="dialog">
		<div class="box">
			<h4>通用爬虫-用户登录</h4>
			<div>
				<s:actionerror />
			</div>
			<s:form action="login" method="post">
				<div class="infos">
				
					<table class="field">
						<tr>
							<td class="field">用 户 名：</td>
							<td>
								<s:textfield cssClass="text" required="true" name="username"></s:textfield>
							</td>
						</tr>
						<tr>
							<td class="field">密　　码：</td>
							<td>
								<s:password cssClass="text" required="true" name="pwd"></s:password>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<s:submit value="立即登录"></s:submit>
						<input type='button' value='注册' onclick='document.location="page/register.jsp"'/>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div>
</body>
</html>


