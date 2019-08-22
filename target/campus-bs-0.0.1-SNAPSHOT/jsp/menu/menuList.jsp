<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx }/layui/css/layui.css">
<style type="text/css">
/* 数据表格复选框正常显示 */
.layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
	top: 50%;
	transform: translateY(-50%);
}
</style>
<script src="${ctx }/layui/layui.js"></script>
<script>
	var ctx = "${ctx}";
</script>

</head>
<body class="layui-layout-body" style="overflow: auto">
	<br />
	<div class="layui-btn-group TableTools" style="margin-left: 10px">
			<button class="layui-btn" id="addMenu">添加菜单</button>
			<button class="layui-btn" id="editMenu">编辑菜单</button>
			<button class="layui-btn layui-btn-danger" id="delMenu">删除菜单</button>
		<button class="layui-btn layui-btn-primary">（不选中为添加顶级菜单，选中添加子菜单）</button>
	</div>
	<div>
		<table class="layui-hidden" id="treeTable" lay-filter="treeTable"></table>
	</div>

	<script type="text/html" id="barTools">
		<a class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>
        </a>
        <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">
            <i class="layui-icon">&#xe640;</i>
        </a>
	</script>
	<script type="text/html" id="iconTpl">
 		 {{#  if(d.icon === null){ }}
   			
		{{#  } else{ }}
			<i class="layui-icon">{{ d.icon }}</i>  
  		{{#  } }}
	</script>
	<script type="text/html" id="radioTpl">
  		<span style="top:50%"><input type="radio" name="menuId" value="{{d.menuId}}" title=" " lay-filter="radiodemo"></span>
	</script>
	<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx }/js/menu/menuList.js"></script>
</body>
</html>