layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','table','laytpl'],function(){
	var form = layui.form,table = layui.table;
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
		//数据表格
		table.render({
			id:'adminList',
		    elem: '#adminList'
		    ,url: ctx+'/admin/getAdminList' //数据接口
		    ,cellMinWidth: 80
		    ,toolbar : true
		    ,limit:10//每页默认数
		    ,limits:[10,20,30,40]
		    ,cols: [[ //表头
		       {field:'id', title: 'ID', sort: true,width:80,align: 'center'}
              ,{field:'username', title: '登陆名',align: 'center'}
              ,{field:'nickname', title: '昵称',align: 'center'}
              ,{field:'email', title: '邮箱',align: 'center'}
              ,{field:'sex', title: '性别',templet: '#sexTpl',align: 'center'}
              ,{field:'phone', title: '联系方式',align: 'center'}
              ,{field:'roleName', title: '角色',align: 'center'}
              ,{title: '操作',toolbar: '#barEdit',align: 'center'}
		    ]]
				,page: true //开启分页
		  });
		//监听工具条
		  table.on('tool(test)', function(obj){
		    var data = obj.data,adminId=$("#adminId").val();
		    if(obj.event === 'del'){
		    	if(data.id==adminId){
		    		layer.msg("不允许删除自己！",{icon: 5});
		    		return;
		    	}
		      layer.confirm('真的删除行么', function(index){
		    	  $.ajax({
		    		  url:ctx+'/admin/delAdminById/'+data.id,
		    		  type : "get",
		    		  success : function(d){
		    			  if(d.code==0){
		    				  table.reload('adminList', {})
		    			  }else{
		    				  layer.msg("权限不足，联系超管！",{icon: 5});
		    			  }
		    		  }
		    	  })
		        layer.close(index);
		      });
		    } else if(obj.event === 'edit'){
		    	if(data.id=='1'){
		    		layer.msg("不允许编辑此用户！",{icon: 5});
		    		return;
		    	}
		    	if(data.id==adminId){
		    		layer.msg("不允许编辑自己！",{icon: 5});
		    		return;
		    	}
		      layer.open({
		    	  type: 2,
		    	  title:"编辑角色",
		    	  area: ['380px', '560px'],
		    	  content:ctx+"/admin/editAdmin/"+data.id
		      })
		    }
		  });
		  

	//添加角色
	$(".adminAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加管理员",
			type : 2,
			content : ctx+"/admin/addAdmin",
			area: ['450px', '570px']
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
	})
	
})
