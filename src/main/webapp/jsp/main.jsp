<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/css/style.css">
<script>
	var ctx = "${ctx}";
</script>
</head>
<body onload="showtime()">
	<div class="b">
		<span id="clock"></span>
	</div>
	<h1>欢迎使用</h1>
	<div class="wrap">
		<img src="../image/logo.png" alt=""> <span><strong>校园帮后台管理系统</strong>
		</span>
	</div>
	<h2>当前登录：${admin.nickname }</h2>
	<div class="c">
		<h2>登录ip:<%=getIpAddr(request)%></h2>
	</div>

	<%!public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}%>
	<script language=JavaScript>
		function showtime() {
			var today;
			var hour;
			var second;
			var minute;
			var year;
			var month;
			var date;
			var strDate;
			today = new Date();
			var n_day = today.getDay();
			switch (n_day) {
			case 0: {
				strDate = "星期日"
			}
				break;
			case 1: {
				strDate = "星期一"
			}
				break;
			case 2: {
				strDate = "星期二"
			}
				break;
			case 3: {
				strDate = "星期三"
			}
				break;
			case 4: {
				strDate = "星期四"
			}
				break;
			case 5: {
				strDate = "星期五"
			}
				break;
			case 6: {
				strDate = "星期六"
			}
				break;
			case 7: {
				strDate = "星期日"
			}
				break;
			}
			year = today.getYear() - 100 + 2000;
			month = today.getMonth() + 1;
			date = today.getDate();
			hour = today.getHours();
			minute = today.getMinutes();
			second = today.getSeconds();
			if (month < 10)
				month = "0" + month;
			if (date < 10)
				date = "0" + date;
			if (hour < 10)
				hour = "0" + hour;
			if (minute < 10)
				minute = "0" + minute;
			if (second < 10)
				second = "0" + second;
			document.getElementById('clock').innerHTML = year + "年" + month
					+ "月" + date + "日 " + strDate + " " + hour + ":" + minute
					+ ":" + second;
			setTimeout("showtime();", 1000);
		}
	</script>

</body>
</html>