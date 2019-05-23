
$(function() {
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor', {autoHeightEnabled: false});
	var isLogin = false;
	var user = null;
	loginState();
	initCourseInfo();
	$("#deploy").click(function() {
		submitForm();
	});

	// 初始化课程信息
	function initCourseInfo() {
		var initCourseInfoUrl = "/a4q/course/getCourseListName";
		$.getJSON(initCourseInfoUrl, function(data) {
			if (data.state == 0) {
				var tempHtml = '<option value="">选择分类</option>';
				$.map(data.data, function(value, index) {
					tempHtml += '<option value="' + value.courseId + '">'
							+ value.courseName + '</option>';
				});
				$("#courseInfo").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		});
	}

	// 提交表单,发布帖子
	function submitForm() {
		if (user == null) {
			alert("发表问题之前请登录,登录成功后刷新页面");
		} else {
			var deployUrl = "/a4q/post/deployPost";
			/*var data = $("#submitForm").serialize();*/
			postContent = ue.getContent()
//			var data = "courseId="+$("#courseInfo").val()+"&postContent='"+postContent+"'&postTitle='"+$("#title").val()+"'";
			var data = {
				courseId:$("#courseInfo").val(),
				postContent:postContent,
				postTitle:$("#title").val()
			};
			$.ajax({
				url : deployUrl,
				type : "POST",
				cache : false,/*
				contentType: "application/json",*/
				data : data,
				success : function(data) {
					if (data.state == 0) {
						alert("发表成功"+data.data);
						window.location.href=("../postShow.html?postId="+data.data);
					} else {
						alert(data.stateInfo);
					}
				}
			});
		}
	}

	// 登录状态判断
	function loginState() {
		var loginStateUrl = "/a4q/personInfoAdmin/loginState";
		$.ajax({
			url : loginStateUrl,
			type : "GET",
			asyn : false,
			success : function(data) {
				if (data.state == 0) {
					user = data.data;
					isLogin = true;
					$("#login").text("个人中心");
					$("#login").attr("href",
							"/a4q/stage/personInfo/personInfoHead.html?userId=" + user.userId);
					$("#register").hide();
				} else {
					alert("提问之前需要登陆");
					isLogin = false;
				}
			}
		});
	}
});