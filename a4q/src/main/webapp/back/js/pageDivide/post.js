$(function() {
	initPost();
});

function initPost() {
	var postUrl = '/a4q/post/getPostListBack';
	var data = $("#submitForm").serialize();
	$.ajax({
		url : postUrl,
		data : data,
		type : "post",
		success : function(data) {
			if (data.state == 0) {
				var tempHtml = '<tr>' + '<th>帖子ID</th>' + '<th>课程名称</th>'
						+ '<th>用户ID</th>' + '<th>用户名称</th>' + '<th>发帖标题</th>'
						+ '<th>最佳答复楼ID</th>'
						+ '<th>发帖时间</th>' + '<th>回复时间</th>'
						+ '<th>操作</th></tr>';
				$.map(data.data, function(value, index) {
					tempHtml = tempHtml + '<tr><td>' + value.postId + '</td>'
							+ '<td>' + value.course.courseName + '</td>'
							+ '<td>' + value.deployUser.userId + '</td>'
							+ '<td>' + value.deployUser.userName + '</td>'
							+ '<td>' + value.postTitle + '</td>'
							+ '<td>' + value.bestAnswerId + '</td>' + '<td>'
							+ format(value.createTime) + '</td>' + '<td>'
							+ format(value.lastEditTime) + '</td>'
							+ '<td><a onClick="topPost('+ value.postId+')">置顶</a>' 
							+ '/<a onClick="bottomPost('+ value.postId+')">置底</a>' 
							+ '/<a onClick="removePost('+value.postId + ')">删除</a></td>' + '</tr>'
				});
				$(".table").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		}
	});

	// 初始化course列表展示
	var getCourseNameUrl = "/a4q/course/getCourseListName";
	$.getJSON(getCourseNameUrl, function(data) {
		if (data.state == 0) {
			var tempHtml = '<option value="">--请选择--</option>';
			$.map(data.data, function(value, index) {
				tempHtml += '<option value="' + value.courseId + '">'
						+ value.courseName + '</option>'
			});
			$("#courseId").html(tempHtml);
		} else {
			alert(data.stateInfo);
		}
	});
}

// 删除帖子
function removePost(postId) {
	var result = confirm("是否删除该贴信息");
	var removeUrl = "/a4q/post/removePost?postId=" + postId;
	if (result) {
		$.getJSON(removeUrl, function(data) {
			if (data.state == 0) {
				alert("帖子信息删除成功");
				initPost();
			} else {
				alert("删除失败");
			}
		})
	}
}

// 帖子置顶操作
function topPost(postId) {
	var result = confirm("确认置顶该贴?");
	var url = "/a4q/post/topPost?postId=" + postId;
	if (result) {
		$.getJSON(url, function(data) {
			if (data.state == 0) {
				alert("置顶s成功");
				initPost();
			} else {
				alert(data.stateInfo);
			}
		});
	}
}

//置底操作
function bottomPost(postId) {
	var result = confirm("确认置底该贴?");
	var topPostUrl = "/a4q/post/bottomPost?postId=" + postId;
	if (result) {
		$.getJSON(topPostUrl, function(data) {
			if (data.state == 0) {
				alert("置底成功");
				initPost();
			} else {
				alert(data.stateInfo);
			}
		});
	}
}