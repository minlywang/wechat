<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>欢迎光临</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<div id="box">
		<p style="text-align: center;">
			<img src="http://minlywang.nat123.net/wechat-demo/images/medical_comunity.jpg">
			<br /> 
			<img src="http://bcs.kuaiapk.com/rbpiczy/soft/2014/11/3/e24b44b86d1441c19ac0194aeee0330d/thumb_2b1668a025fb43ec8688e29d161a9e29_640x1136.jpeg"> 
			<br /> 
			<img src="http://minlywang.nat123.net/wechat-demo/images/mmqrcode1462612048322.png">
			<br /> 
			
			<span><a href="mailto:wangminglei01@fehorizon.com">联系我</a></span>
		</p>
	</div>
</body>
</html>
