<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx }/layui/css/layui.css">
	<link rel="stylesheet" href="${ctx }/css/style.css">
	<script>  
        var ctx = "${ctx}";  
    </script>
	<title>登录</title>
</head>
<body background="./image/back_03.jpg">
	<div class="login-main">
		<header class="layui-elip">登 录</header>
		<form class="layui-form" lay-filter="login">
			<div class="layui-input-inline">
				<input id="username" type="text" placeholder="用户名" class="layui-input">
			</div>
			<div class="layui-input-inline">
				<input id="password" type="password" placeholder="密码" class="layui-input">
			</div>
			<div class="layui-input-inline" style="display: flex;">
				<input type="text" id="code" placeholder="验证码" class="layui-input">
				<canvas id="canvas" width="100%" height="35"></canvas>
			</div>
			<div class="layui-input-inline">
				<button id="loginBt" class="layui-btn">登录</button>
			</div>
		</form>
		
	</div>
	<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx }/js/login.js"></script>
</body>
</html>