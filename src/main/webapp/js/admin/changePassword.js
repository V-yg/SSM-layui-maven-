layui.use([ 'form', 'layer' ], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form;

	form.verify({
		newPassword1 : [ /(.+){6,16}$/, '密码必须6到16位' ],
		newPassword2 : function(value) {
			var repassvalue = $('#newPassword1').val();
			if (value != repassvalue) {
				return '两次输入的密码不一致!';
			}
		}
	});

	// 监听提交
	form.on("submit(changePassword)", function(data) {
		var index = layer.load(1, {
			shade : [ 0.5, '#000' ]
		});
		$.ajax({
			url : ctx + "/admin/changeAdminPassword",
			async:false,
			type : "post",
			data : data.field,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					layer.close(index);
					layer.msg("修改成功!");
					$('#cpwd')[0].reset();
				} else {
					layer.close(index);
					layer.msg(data.msg);
					$('#cpwd')[0].reset()
				}
			}
		});
		return false;
	});

});