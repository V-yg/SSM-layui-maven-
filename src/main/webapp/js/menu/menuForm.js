var $;
var $form;
var form;
layui.config({
	base : "../../js/"
}).use(['form','layer','jquery','laydate','iconPicker'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,laydate = layui.laydate,iconPicker = layui.iconPicker;
		$ = layui.jquery;
		form = layui.form;
		
		iconPicker.render({
            // 选择器，推荐使用input
            elem: '#iconPicker',
            // 数据类型：fontClass/unicode，推荐使用fontClass
            type: 'unicode',
            // 是否开启搜索：true/false
            search: true,
            // 是否开启分页
            page: true,
            // 每页显示数量，默认12
            limit: 12,
            // 点击回调
            click: function (data) {
                console.log(data);
            }
        });

		

 	form.on("submit(menuForm)",function(data){
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var msg="发生错误！",flag=false;
 		$.ajax({
    		type: "post",
            url: ctx+"/admin/menuForm",
            data:data.field,
			dataType:"json",
			async:false,
			success:function(d){
				msg=d.msg;
			},
			error:function() { 
				flag=true;
				top.layer.close(index);
				$("#menuF")[0].reset();
				layer.msg("发生错误，请检查输入！"); }
        });
 		setTimeout(function(){
 			if(!flag){
 				top.layer.close(index);
 				top.layer.msg(msg);
 				layer.closeAll("iframe");
 				//刷新父页面
 				top.location.reload();
 			}
        },1000);
 		return false;
 	})
})