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
	<form class="layui-form" style="width: 80%;">
		<!-- 管理员id -->
		<input type="hidden" name="id" value="${user.id }" id="id" />
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" id="username" class="layui-input"
					name="username" value="${user.username }"
					disabled="disabled">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" name="email" id="email" class="layui-input"
					lay-verify="email" placeholder="请输入邮箱" value="${user.email }">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-block">
					<c:if test="${user.sex==0 }">
						<input type="radio" name="sex" value="1" title="男">
						<input type="radio" name="sex" value="0" title="女" checked>
						<input type="radio" name="sex" value="2" title="保密">
					</c:if>
					<c:if test="${user.sex==1}">
						<input type="radio" name="sex" value="1" title="男" checked>
						<input type="radio" name="sex" value="0" title="女">
						<input type="radio" name="sex" value="2" title="保密">
					</c:if>
					<c:if test="${user.sex==2 }">
						<input type="radio" name="sex" value="1" title="男">
						<input type="radio" name="sex" value="0" title="女">
						<input type="radio" name="sex" value="2" title="保密" checked>
					</c:if>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出生日期</label>
				<div class="layui-input-block">
					<input type="text" id="birthday" class="layui-input"
						autocomplete="off" name="birthday" lay-verify="date"
						placeholder="yyyy-MM-dd"
						value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>">
				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户等级</label>
				<div class="layui-input-inline">
					<select name="roleId">
						<option value="21" <c:if test="${user.roleId==21}">selected</c:if>>白银会员</option>
						<option value="22" <c:if test="${user.roleId==22}">selected</c:if>>黄金会员</option>
						<option value="23" <c:if test="${user.roleId==23}">selected</c:if>>钻石会员</option>
						<option value="24" <c:if test="${user.roleId==24}">selected</c:if>>终身荣誉会员</option>
					</select>
				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">地址</label>
				<div class="layui-input-block">
					<input type="text" name="address" class="layui-input"
						lay-verify="required" placeholder="请输入地址" value="${user.address }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-block">
					<input type="text" name="phone" class="layui-input"
						lay-verify="phone" placeholder="请输入手机号" value="${user.phone }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
				<div class="layui-input-block">
					<textarea name="note" class="layui-textarea" placeholder="请输入备注">${user.note }</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="updateUser">立即保存</button>
				</div>
			</div>
	</form>
	<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx }/js/user/editUser.js"></script>
</body>
</html>