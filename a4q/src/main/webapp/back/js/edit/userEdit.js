$(function() {
	initUserInfo();
	$("#submitUpdate").click(function() {
		submitUpdate();
	});
	
	$("#cancel").click(function(){
		window.location.href = "user.html";
	});
});

function initUserInfo() {
	var userId = getQueryString("userId");
	initUrl = "/a4q/personInfoAdmin/getUserById?userId=" + userId;
	$.ajax({
		url : initUrl,
		type : "get",
		success : function(data) {
			var user = data.data;
			if (data.state == 0) {
				$("#userName").val(user.userName);
				$("#gender").val(user.gender);
				$("#gender").attr("disabled", "disabled");
				$("#phone").val(user.phone);
				$("#email").val(user.email);
				$('#userType option[value ="' + user.userType + '"]').attr(
						"selected", "selected")
				$("#lable").val(user.lable);
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

function submitUpdate() {
	var result = confirm("是否提交修改");
	var userId = getQueryString("userId");
	var updateUrl = '/a4q/personInfoAdmin/updateUser/?userId='+userId;
	var data = $("#submitForm").serialize();
	if (result) {
		$.ajax({
			url : updateUrl,
			type : "post",
			data : data,
			cache :false,
			success : function(data){
				if(data.state == 0){
					alert("修改成功");
					window.location.href = "user.html"; 
				}else{
					alert(data.stateInfo);
				}
			}
		});
	}
}