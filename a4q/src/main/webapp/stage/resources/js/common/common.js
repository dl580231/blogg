//更改验证码
function changeVerifyCode(img) {
	img.src = "/a4q/Kaptcha?" + Math.random();
}

// 通过传入的参数名字从url上获取对应的值
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}

// 格式化毫秒格式的时间
function format(str) {
	var nowDate = new Date(str);
	var year = nowDate.getFullYear();
	var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1)
			: nowDate.getMonth() + 1;
	var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate
			.getDate();
	var hour = nowDate.getHours() < 10 ? "0" + nowDate.getHours() : nowDate
			.getHours();
	var minute = nowDate.getMinutes() < 10 ? "0" + nowDate.getMinutes()
			: nowDate.getMinutes();
	// var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() :
	// nowDate.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute;
}

//格式化毫秒格式的时间
function formatD(str) {
	var nowDate = new Date(str);
	var year = nowDate.getFullYear();
	var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1)
			: nowDate.getMonth() + 1;
	var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate
			.getDate();
	return year + "-" + month + "-" + date;
}

//登录,版主状态判断
function loginState(aSuccess,aDefault){
	var loginStateUrl = "/a4q/personInfoAdmin/loginState?fresh=" + Math.random();
	$.ajax({
		url : loginStateUrl,
		type : "GET",
		asyn : false,
		success : function(data){
			if(data.state == 0){
				aSuccess(data);
			}else{
				aDefault(data);
			}
		}
	});
}


