layui.use([ 'form','layer','jquery','table','laydate'], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form,table=layui.table,laydate = layui.laydate;

	laydate.render({
	    elem: '#birthday'
	  });
	
	table.render({
		id:'userList'
	    ,elem: '#userList'
	    ,url: ctx+'/user/getAllUserList'// 数据接口
	    ,limit:10// 每页默认数
	    ,limits:[10,20,30,40]
	    ,cols: [[ // 表头
	      {field:'id', title: 'ID',width:50, align: 'center'}
          ,{field:'username', title: '登录名', align: 'center',width:110}
          ,{field:'sex', title: '性别' , align: 'center',templet : '#sexTpl',width:60}
          ,{field:'birthday', title: '生日' , align: 'center',templet : '<div>{{ formatTime(d.birthday,"yyyy-MM-dd")}}</div>',width:110}
          ,{field:'phone', title: '电话', align: 'center' ,width:120}
          ,{field:'email', title: 'E-mail' , align: 'center',width:180}
          ,{field:'status', title: '状态', align: 'center',width:80, templet : '#statusTpl',width:80}
          ,{field:'createTime', title: '注册时间' , align: 'center',templet : '<div>{{ formatTime(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>' ,width:170}
          ,{field:'address', title: '地址', align: 'center' }
          ,{field:'note', title: '备注' , align: 'center'}
          , {field: 'right', title: '操作', align: 'center', toolbar: "#barDemo"}
	    ]]
			,page: true // 开启分页
			,loading:true
	  });
	
	table.on('tool(userList)', function (obj) {
	    var data = obj.data;
	    if (obj.event === 'delete') {
	        layer.confirm('确定要删除行么？', function (index) {
	        	$.ajax({
					url : ctx + '/user/deleteUserById/' + data.id,
					type : "get",
					success : function(d) {
						if (d.code == 0) {
							obj.del();
						} else {
							layer.msg("权限不足！", {
								icon : 5
							});
						}
					}
				})
				layer.close(index);
	        });
	    } else if (obj.event === 'edit') {
	    	layer.open({
				type : 2,
				title : "编辑用户",
				area : [ '400px', '500px' ],
				content : ctx + "/user/editUser/" + data.id 
			})
	    }
	});
	
	form.on("submit(updateUser)",function(data){
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.2});
		var index1 = parent.layer.getFrameIndex(window.name);
 		var msg,flag=false;
 		$.ajax({
    		type: "post",
    		async:false,
            url: ctx+"/user/updateUser",
            data:data.field,
			dataType:"json",
			success:function(d){
				if(d.code==0){  
					msg="用户信息更新成功！";
					flag=true;
				}else{
					msg="用户信息更新失败！";
				}
			},
			error:function(){
				layer.msg("用户信息更新失败！",{icon:2});
				layer.closeAll();
				return false;
			}
			
        });
 		setTimeout(function () {
 			parent.layer.close(index1);
 			top.layer.close(index);
 			if(flag){
 				top.layer.msg(msg,{icon: 1});
 			}else{
 				top.layer.msg(msg,{icon: 5});
 			}
 			parent.location.reload();
	     }, 2000);
 		return false;
 	})
});


// 格式化时间
function formatTime(datetime, fmt) {
	if (datetime == null || datetime == 0) {
		return "";
	}
	if (parseInt(datetime) == datetime) {
		if (datetime.length == 10) {
			datetime = parseInt(datetime) * 1000;
		} else if (datetime.length == 13) {
			datetime = parseInt(datetime);
		}
	}
	datetime = new Date(datetime);
	var o = {
		"M+" : datetime.getMonth() + 1, // 月份
		"d+" : datetime.getDate(), // 日
		"h+" : datetime.getHours(), // 小时
		"m+" : datetime.getMinutes(), // 分
		"s+" : datetime.getSeconds(), // 秒
		"q+" : Math.floor((datetime.getMonth() + 3) / 3), // 季度
		"S" : datetime.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1,
					(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
							.substr(("" + o[k]).length)));
	return fmt;
}
