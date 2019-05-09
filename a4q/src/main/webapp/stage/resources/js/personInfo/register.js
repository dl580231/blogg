$(function() {
	var userId = getQueryString("userId");
	if (userId) {
		$("#vc").hide();
		$(".title").text("用户信息修改");
		initUserInfo(userId);
	}
	$("#submit").click(function() {
		if (userId) {
			update(userId);
		} else {
			register();
		}
	});
});

// 注册
function register() {
	var registerUrl = "/a4q/personInfoAdmin/userRegister";
	var data = $("#submitForm").serialize();
	$.ajax({
		url : registerUrl,
		type : "post",
		data : data,
		cache : false,
		success : function(data) {
			if (data.state == 0) {
				alert("注册成功");
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

// initUserInfo
function initUserInfo(userId) {
	initUrl = "/a4q/personInfoAdmin/getUserById?userId=" + userId;
	$.ajax({
		url : initUrl,
		type : "get",
		success : function(data) {
			var user = data.data;
			if (data.state == 0) {
				$("#userName").val(user.userName);
				$('#gender option[value="' + user.gender + '"]').attr(
						"selected", "selected");
				$("#gender").attr("disabled", "disabled");
				$("#phone").val(user.phone);
				$("#email").val(user.email);
				$("#pw").val(user.password);
				$('#userType option[value ="' + user.userType + '"]').attr(
						"selected", "selected")
				$("#lable").val(user.lable);
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

// 更新用户信息
function update(userId) {
	var result = confirm("是否提交修改");
	if (result) {
		var updateUrl = '/a4q/personInfoAdmin/updateUser/?userId=' + userId;
		var data = $("#submitForm").serialize();
		$.ajax({
			url : updateUrl,
			type : "post",
			data : data,
			cache : false,
			success : function(data) {
				if (data.state == 0) {
					alert("修改成功");
					initUserInfo(userId);
				} else {
					alert(data.stateInfo);
				}
			}
		});
	}
}