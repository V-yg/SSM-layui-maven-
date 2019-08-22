<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<script>
	var ctx = "${ctx}";
</script>
</head>
<body>
	<div class="layui-card-header">修改密码</div>
	<div class="layui-card-body" pad15="">
		<form class="layui-form" id="cpwd">
			<input type="hidden" name="id" value="${admin1.id }" />
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="username"
						value="${admin.username }" disabled="disabled"></input>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">旧密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="oldPassword" required
						lay-verify="oldPassword" placeholder="请输入旧密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="newPassword1" required id="newPassword1"
						lay-verify="newPassword1" placeholder="请输入新密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">重复密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="newPassword2" required
						lay-verify="newPassword2" placeholder="请确认新密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-card-header"></div>
			<div class="layui-card-body" pad12="6">
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="changePassword">确认修改</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx }/js/admin/changePassword.js"></script>
	
</body>
</html>