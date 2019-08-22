layui.use([ 'form','layer','jquery','table','laydate'], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form,table=layui.table,laydate = layui.laydate;
	var nowTime = new Date().valueOf();
	var max = null;
	active = {
			search : function(){
				var username = $('#username');
				var sex = $('#sex option:selected');
				var status = $('#status option:selected');
				var roleId = $('#roleId option:selected');
				var createTimeStart = $('#createTimeStart');
				var createTimeEnd = $('#createTimeEnd');
				var birthdayStart = $('#birthdayStart');
				var birthdayEnd = $('#birthdayEnd');
				
				table.reload('userList',{
					page : {
						curr : 1
					},
					where : {
						username : username.val(),
						sex : sex.val(),
						status : status.val(),
						roleId : roleId.val(),
						createTimeStart : createTimeStart.val(),
						createTimeEnd : createTimeEnd.val(),
						birthdayStart : birthdayStart.val(),
						birthdayEnd : birthdayEnd.val()
					}
				});
			}
	};
	
	var createTimeS = laydate.render({
		elem : '#createTimeStart',
		type : 'datetime',
		max : nowTime,
		done : function(value, date) {
			endMax = createTimeE.config.max;
			createTimeE.config.min = date;
			createTimeE.config.min.month = date.month - 1;
		}
	});
	
	var createTimeE = laydate.render({
		elem : '#createTimeEnd',
		type : 'datetime',
		max : nowTime,
		done : function(value, date) {
			if ($.trim(value) == '') {
				var curDate = new Date();
				date = {
					'date' : curDate.getDate(),
					'month' : curDate.getMonth() + 1,
					'year' : curDate.getFullYear()
				};
			}
			createTimeE.config.max = date;
			createTimeE.config.max.month = date.month - 1;
		}
	});
	
	var birthdayS = laydate.render({
		elem : '#birthdayStart',
		max : nowTime,
		done : function(value, date) {
			endMax = createTimeE.config.max;
			birthdayE.config.min = date;
			birthdayE.config.min.month = date.month - 1;
		}
	});
	
	var birthdayE = laydate.render({
		elem : '#birthdayEnd',
		max : nowTime,
		done : function(value, date) {
			if ($.trim(value) == '') {
				var curDate = new Date();
				date = {
					'date' : curDate.getDate(),
					'month' : curDate.getMonth() + 1,
					'year' : curDate.getFullYear()
				};
			}
			birthdayS.config.max = date;
			birthdayS.config.max.month = date.month - 1;
		}
	});
	
	table.render({
		id:'userList'
	    ,elem: '#userList'
	    ,url: ctx+'/user/getAllUserList'// 数据接口
	    ,limit:10// 每页默认数
	    ,limits:[10,20,30,40]
	    ,cols: [[ // 表头
	      {field:'username', title: '登录名', align: 'center',width:110}
          ,{field:'sex', title: '性别' , align: 'center',templet : '#sexTpl',width:60}
          ,{field:'birthday', title: '生日' , align: 'center',templet : '<div>{{ formatTime(d.birthday,"yyyy-MM-dd")}}</div>',width:110}
          ,{field:'phone', title: '电话', align: 'center' ,width:120}
          ,{field:'email', title: 'E-mail' , align: 'center',width:180}
          ,{field:'status', title: '状态', width:80, templet : '#statusTpl',width:95, align: 'center'}
          ,{field:'roleId', title: '等级' , align: 'center', templet : '#levelTpl',width:120}
          ,{field:'createTime', title: '注册时间' , align: 'center',templet : '<div>{{ formatTime(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>' ,width:170}
          ,{field:'address', title: '地址', align: 'center' }
          ,{field:'note', title: '备注', align: 'center' }
          , {field: 'right', title: '操作', align: 'center', toolbar: "#barDemo",width:150}
	    ]]
			,page: true // 开启分页
			,loading:true
			,where: {timestamp: (new Date()).valueOf()}
	  });
	
	form.on('switch(statusSwitch)', function(data){
		var status = this.checked ? 1 : 0;
		var id = this.value;
		$.ajax({
			url : ctx + '/user/updateUserStatusById',
			type : "get",
			data:{
				status:status,
				id:id
			},
			success : function(d) {
				if (d.code == 0) {
					layer.msg("更新用户状态成功！");
				} else {
					layer.msg("权限不足！", {
						icon : 5
					});
				}
			}
		})
	});
																																																																																																								
	table.on('tool(userList)', function (obj) {
	    var data = obj.data;
	    if (obj.event === 'delete') {
	        layer.confirm('确定要删除 '+data.username+' 么？', function (index) {
	        	$.ajax({
					url : ctx + '/user/deleteUserById',
					type : "POST",
					data: {"id": data.id},
					success : function(d) {
						if (d.code == 0) {
							layer.msg("删除成功！",{icon: 1});
							obj.del();
						} else {
							layer.msg("权限不足，删除失败！", {
								icon : 5
							});
						}
					},
					error:function(){
						layer.msg("删除失败！网络错误！",{icon: 5});
					}
				})
				layer.close(index);
	        });
	    } else if (obj.event === 'edit') {
	    	var editIndex = layer.open({
				type : 2,
				title : "编辑用户",
				area : [ '450px', '600px' ],
				content : ctx + "/user/editUser/" + data.id,
				success : function(layero, index) {

				}
	    	});
	    }	
	});
	
	$(".search_btn").click(function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	})
	
	$(".userAdd_btn").click(function() {
		var addIndex = layer.open({
			title : "添加用户",
			type : 2,
			area : [ '800px', '550px' ],
			content : ctx + "/user/addUser",
			success : function(layero, index) {

			}
		})
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
