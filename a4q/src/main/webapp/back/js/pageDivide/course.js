$(function() {
	initCourse();
});

function initCourse() {
	var initUrl = "/a4q/course/getCourseList";
	var data = $("#submitForm").serialize();
	$.ajax({
		url : initUrl,
		type : "POST",
		cache : false,
		data : data,
		success : function(data) {
			if (data.state == 0) {
				var tempHtml = '<tr></th>' + '<th>课程ID</th>' +'<th>发帖数量</th>'+
						'<th>版主名称（版主ID）</th>'+'<th>课程名称</th>' + '<th>创建时间</th>' +
						'<th>最后修改时间</th>'+ '<th>操作</th></tr>';
				$.map(data.data, function(value, index) {
					tempHtml += '<tr>'+
							'<td>' + value.courseId + '</td>'+
							'<td>' + postCount(value.courseId) + '</td>'+
							'<td>' + moderatorJudgeNull(value.moderator)+'</td>'+
							'<td>' + value.courseName + '</td>'+
							'<td>' + format(value.createTime) + '</td>'+
							'<td>' + format(value.lastEditTime) + '</td>'+
							'<td><a href="course_edit.html?courseId='+value.courseId+'">编辑</a>'+
							'/<a onClick=removeCourse(' + value.courseId+')>删除</a></td>' +
							'</tr>';
				});
				$("table").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

// 判断版主是否为空
function moderatorJudgeNull(moderator) {
	if (moderator) {
		return moderator.userName + "（" + moderator.userId + "）";
	} else {
		return null;
	}
}

// 删除课程信息
function removeCourse(courseId) {
	var result = confirm("确认删除课程信息？");
	if (result) {
		var removeUrl = "/a4q/course/removeCourse?courseId=" + courseId;
		$.getJSON(removeUrl, function(data) {
			if (data.state == 0) {
				alert("删除成功");
				initCourse();
			} else {
				alert(data.stateInfo);
			}
		});
	}
}

//获得每个课程的发帖数量
function postCount(courseId){
	var PostCountUrl = "/a4q/course/getPostCountByCourseId?courseId="+courseId;
	var result = 0;
	$.ajax({
		url : PostCountUrl,
		type : "get",
		cache : false,
		async : false,
		success : function(data){
			if (data.state == 0) {
				result = data.data;
			} else {
				result = "请刷新页面";
			}
		}
	});
	return result;
}