layui.use([ 'element', 'layer'], function(exports) {
	var $ = layui.jquery;
	var element = layui.element;
	var layer = layui.layer;

	$(function() {
		var show_num=[];
		draw(show_num);

		$("#canvas").on('click', function() {
			draw(show_num);
		});

		$('#loginBt').on('click', function() {
			var code = $('#code').val();
			var username = $('#username').val();
			var password = $('#password').val();
			var num = show_num.join("");
			
			if ("" == username) {
				layer.alert("请输入用户名！");
				$("#code").val('');
				return false;
			} else if ("" == password) {
				layer.alert("请输入密码！");
				$("#code").val('');
				return false;
			}
			if ("" == code) {
				layer.alert("请输入验证码！");
				$("#password").val('');
				draw(show_num);
				return false;
			} else if (code == num) {
				var i;
				$.ajax({
					type:"post",
					url:ctx+"/admin/login",
					data:{
						username:username,
						password:password
					},
					beforeSend:function(){
						i=show_wait();
					},
					success:function(result){
						if(result.code == 0){
							parent.location.href = ctx+'/admin/index';
							close_wait(i);
						}else if(result.code != 0){
							layer.msg('用户名或密码错误', {icon:5, shade:[0.5, '#000000'], shadeClose:true});
							draw(show_num);
							close_wait(i);
						}	
					},
					error:function(){
						layer.alert("获取数据失败！");
						draw(show_num);
						close_wait(i);
					}
				});
				$("#code").val('');
				$("#username").val('');
				$("#password").val('');
				return false;
			} else {
				layer.msg('验证码错误', {icon:5, shade:[0.5, '#000000'], shadeClose:true});
				$("#code").val('');
				$("#password").val('');
				draw(show_num);
				return false;
			}
		});

	});
	
	function show_wait(){
		return layer.load(1, {shade: [0.5,'#000']});
	};
	
	function close_wait(index){
		layer.close(index);
	}
	
	function draw(show_num) {
		var canvas_width = $('#canvas').width();
		var canvas_height = $('#canvas').height();
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");
		canvas.width = canvas_width;
		canvas.height = canvas_height;
		var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
		var aCode = sCode.split(",");
		var aLength = aCode.length;
		for (var i = 0; i <= 3; i++) {
			var j = Math.floor(Math.random() * aLength);
			var deg = Math.random() * 30 * Math.PI / 180;
			var txt = aCode[j];
			show_num[i] = txt.toLowerCase();
			var x = 10 + i * 20;
			var y = 20 + Math.random() * 8;
			context.font = "bold 23px 微软雅黑";

			context.translate(x, y);
			context.rotate(deg);

			context.fillStyle = randomColor();
			context.fillText(txt, 0, 0);

			context.rotate(-deg);
			context.translate(-x, -y);
		}
		for (var i = 0; i <= 5; i++) {
			context.strokeStyle = randomColor();
			context.beginPath();
			context.moveTo(Math.random() * canvas_width, Math.random()
					* canvas_height);
			context.lineTo(Math.random() * canvas_width, Math.random()
					* canvas_height);
			context.stroke();
		}
		for (var i = 0; i <= 30; i++) { 
			context.strokeStyle = randomColor();
			context.beginPath();
			var x = Math.random() * canvas_width;
			var y = Math.random() * canvas_height;
			context.moveTo(x, y);
			context.lineTo(x + 1, y + 1);
			context.stroke();
		}
	};

	function randomColor() {
		var r = Math.floor(Math.random() * 256);
		var g = Math.floor(Math.random() * 256);
		var b = Math.floor(Math.random() * 256);
		return "rgb(" + r + "," + g + "," + b + ")";
	};
	
});
