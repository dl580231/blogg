$(function() {
	var courseId = getQueryString("courseId");
	if (courseId) {
		initCourse(courseId);
	}
});

// 编辑课程信息
function initCourse(courseId) {
	var initUrl = "/a4q/course/getCourseList?courseId=" + courseId;
	$.getJSON(initUrl, function(data) {
		if (data.state == 0) {
			var course = data.data[0];
			$("#moderatorId").val(moderatorJudgeNull(course.moderator));
			$("#courseName").val(course.courseName);
		} else {
			alert(data.stateInfo);
		}
	});
}

function submitHandle() {
	var courseId = getQueryString("courseId");
	if (courseId) {
		var result = confirm("确认修改？")
		if (result) {
			update(courseId);
		}
	} else {var result = confirm("确认添加？")
		if (result) {
			insert();
		}
	}
}

function update(courseId) {
	var updateUrl = "/a4q/course/updateCourse?courseId=" + courseId;
	var data = $("#submitForm").serialize();
	$.ajax({
		url : updateUrl,
		type : "post",
		data : data,
		success : function(data) {
			if (data.state == 0) {
				alert("更新成功");
				window.location.href = "course.html"; 
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

//判断版主是否为空
function moderatorJudgeNull(moderator) {
	if (moderator) {
		return moderator.userId;
	} else {
		return null;
	}
}

function insert(courseId) {
	var insertUrl = "/a4q/course/addCourse";
	$.ajax({
		url : insertUrl,
		type : "post",
		data : $("#submitForm").serialize(),
		success : function(data){
			if(data.state == 0){
				alert("添加成功");
			}else{
				alert(data.stateInfo);
			}
		}
	});
}

function cancelHandle(){
	window.location.href = "course.html"; 
}