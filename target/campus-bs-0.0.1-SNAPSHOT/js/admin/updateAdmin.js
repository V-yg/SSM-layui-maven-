layui.use([ 'form', 'layer' ], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form;

	// 监听提交
	form.on("submit(updateAdmin)", function(data) {
		var index = layer.load(1, {
			shade : [ 0.5, '#000' ]
		});
		$.ajax({
			url : ctx + "/admin/updateAdmin",
			type : "post",
			data : data.field,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					layer.close(index);
					layer.msg("修改成功!");
				} else {
					layer.close(index);
					layer.msg("修改失败，请重试！");
				}
			}
		});
		setTimeout(function() {
			//top.layer.closeAll("iframe");
			location.reload();
		}, 2000);
		return false;
	});

});