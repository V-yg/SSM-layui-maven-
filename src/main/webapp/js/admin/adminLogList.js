layui.use([ 'form','layer','jquery','table'], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form,table=layui.table;

	table.render({
		id:'logList'
	    ,elem: '#adminLogList'
	    ,url: ctx+'/admin/getAdminLogList'//数据接口
	    ,limit:10//每页默认数
	    ,limits:[10,20,30,40]
	    ,cols: [[ //表头
	      {field:'id', title: 'ID', fixed: 'left',width:80,sort:true}
          ,{field:'adminUsername', title: '登录名', align: 'center'}
          ,{field:'loginIp', title: '登录IP' ,align: 'center'}
          ,{field:'loginTime', title: '登录时间' ,align: 'center',templet : '<div>{{ formatTime(d.loginTime,"yyyy-MM-dd hh:mm:ss")}}</div>' }
          ,{field:'logoutTime', title: '登出时间' ,align: 'center'}
          ,{field:'isSafeExit', title: '是否安全退出' ,align: 'center'}
          , {field: 'right', title: '操作', align: 'center', toolbar: "#barDemo"}
	    ]]
			,page: true //开启分页
			,loading:true
	  });
});

//格式化时间
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
