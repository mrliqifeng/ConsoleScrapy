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
<title>网络商城 - 用户注册</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="regLogin" class="wrap">
	<div class="dialog">
		<dl class="clearfix">
			<dt>新用户注册</dt>
			<dd class="past">填写个人信息</dd>
		</dl>
		<div>
			<s:fielderror/>
		</div>
		<div class="box">
			<form action="register" method="post">
				<div class="infos">
					<table class="field">
						<tr>
							<td class="field">用 户 名：</td>
							<td><input type="text" class="text" name="username" /> </td>
						</tr>
						<tr>
							<td class="field">密　　码：</td>
							<td><input type="password" class="text" name="pwd" /></td>
						</tr>
						<tr>
							<td class="field">确认密码：</td>
							<td><input type="password" class="text" name="repwd" /> </td>
						</tr>
						<tr>
							<td class="field">电　　话：</td>
							<td><input type="text" class="text" name="tel" /> </td>
						</tr>
						<tr>
							<td class="field">用户姓名：</td>
							<td><input type="text" class="text" name="name" /> </td>				
						</tr>
					</table>
					<div class="buttons"><input type="submit" name="submit" value="立即注册" /></div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	<dl>
    	<dt>青鸟租房 &copy; 2010 北大青鸟 京ICP证1000001号</dt>
        <dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
    </dl>
</div>
</body>
</html>

