var blogId = getQueryString("blogId");

$(function() {
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor', {autoHeightEnabled: false});
    blogInit();
	$("#deploy").click(function() {
		submitForm();
	});

	//初始化编辑博客信息
	function blogInit(){
		var url = "/a4q/blog/getBlogByIdE?blogId="+blogId;
		$.getJSON(url,function(data){
			if(data.state == 0){
				ue.addListener("ready", function () {
			    ue.setContent(data.data.blog.blogContent);
				});
			$("#title").val(data.data.blog.blogTitle);
			initCourseInfo(data.data.course.courseId);
			}else{
				alert(data.stateInfo);
			}
		});
	}
	
	// 初始化课程信息
	function initCourseInfo(courseId) {
		var url = "/a4q/course/getCourseListName";
		$.getJSON(url, function(data) {
			if (data.state == 0) {
				var tempHtml = '<option value="">选择分类</option>';
				$.map(data.data, function(value, index) {
					if(value.courseId == courseId){
						tempHtml += '<option selected="selected" value="' + value.courseId + '">'
						+ value.courseName + '</option>';
					}else{
						tempHtml += '<option value="' + value.courseId + '">'
						+ value.courseName + '</option>';
					}
				});
				$("#courseInfo").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		});
	}

	// 提交表单,修改博客
	function submitForm() {
			var url = "/a4q/blog/editBlog";
			var content = ue.getContent();
			var data = {
				courseId:$("#courseInfo").val(),
				blogContent:content,
				blogTitle:$("#title").val(),
				blogId:blogId,
			};
			$.ajax({
				url : url,
				type : "POST",
				cache : false,
				data : data,
				success : function(data) {
					if (data.state == 0) {
						alert("博客修改成功");
						window.location.href=("../blogShow.html?blogId="+blogId);
					} else {
						alert(data.stateInfo);
					}
				}
			});
		}
});