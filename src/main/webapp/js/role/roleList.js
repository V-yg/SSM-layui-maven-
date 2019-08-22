layui.use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,table = layui.table,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
		
		//数据表格
		table.render({
			id:'roleList',
		    elem: '#roleList'
		    ,url: ctx+'/admin/getRoleList' //数据接口
		    ,cellMinWidth: 80
		    ,limit:10//每页默认数
		    ,limits:[10,20,30,40]
		    ,cols: [[ //表头
		       {field:'roleId', title: 'ID', sort: true, align: 'center',width:120}
              ,{field:'roleName', title: '角色名', align: 'center'}
              ,{field:'roleRemark', title: '角色描述', align: 'center'}
              ,{title: '操作',toolbar: '#barEdit', align: 'center'}
		    ]]
				,page: true //开启分页
		  });
		
		//监听工具条
		  table.on('tool(roleList)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'del'){
		    	if(data.roleName=='超级管理员'){
		    		layer.msg("不允许操作此角色！",{icon: 5});
		    		return;
		    	}
		      layer.confirm('真的删除行么', function(index){
		    	  $.ajax({
		    		  url:ctx+'/admin/delRole/'+data.roleId,
		    		  type : "get",
		    		  success : function(d){
		    			  if(d.code==0){
		    				  //obj.del();
		    				  table.reload('roleList', {})
		    			  }else{
		    				  layer.msg("权限不足，联系超管！",{icon: 5});
		    			  }
		    		  }
		    	  })
		        layer.close(index);
		      });
		    } else if(obj.event === 'edit'){
		      layer.open({
		    	  type: 2,
		    	  title:"编辑角色",
		    	  area: ['380px', '600px'],
		    	  content:ctx+"/admin/editRole?roleId="+data.roleId,
		      })
		    }
		  });
		  
	//添加角色
	$(".roleAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加角色",
			type : 2,
			content : ctx+"/admin/addRole",
			area: ['380px', '550px']
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
	})
	
})
