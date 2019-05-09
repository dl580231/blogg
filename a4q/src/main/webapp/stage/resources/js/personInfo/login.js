$(function() {
	$("#login").click(function() {
		login();
	});
});

function login() {
	var loginUrl = "/a4q/personInfoAdmin/loginAuth"
	var account = $("#account").val();
	var password = $("#pw").val();
	if (account == '' || password == '') {
		alert("用户名或密码不能为空")
	} else {
		var n = account.search(/@/);
		var formData = new FormData();
		if (n == -1) {
			formData.append("phone", account);
		} else {
			formData.append("email", account);
		}
		formData.append("password", password);

		$.ajax({
			url : loginUrl,
			type : "POST",
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.state == 0) {
					alert("登录成功");
					window.location.href="/a4q/stage/headPage/headpage.html";
				} else {
					alert(data.stateInfo);
				}
			}
		});
	}
}