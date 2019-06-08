	$(document).ready(function(){
		$("#login_sub").click(function(){
			var url = "/a4q/personInfoAdmin/adminAuth"
			var account = $("#account").val();
			var password = $("#pw").val();
			if (account == '' || password == '') {
				alert("用户名或密码不能为空")
			} else {
				var formData = new FormData();
				formData.append("account", account);
				formData.append("password", password);
				$.ajax({
					url : url,
					type : "POST",
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.state == 0) {
							alert("登录成功");
							self.location = "index.html";
							} else {
								alert(data.stateInfo);
							}
						}
					});
				}
		});
	});
	
	/*回车事件*/
	function EnterPress(e){ //传入 event 
		var e = e || window.event; 
		if(e.keyCode == 13){ 
			$("#submitForm").attr("action", "index.html").submit();
		} 
	}