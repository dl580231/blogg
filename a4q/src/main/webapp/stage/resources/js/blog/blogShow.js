var moderatorTag = 0;
isLogin = false;
var blogId = getQueryString("blogId");
$(function() {
	if (isNaN(blogId)) {
		 window.location.href = "/a4q/stage/headPage/headpage.html"; 
	} else {//登录状态和版主判断
		loginState(aSuccess,aDefault);
	}
	
	//	设置点击事件
	$("#reply").click(function(){
		replyHandle();
	});
});

	// 初始化帖子信息
	function initBlog() {
		var url = "/a4q/blog/getBlogById?blogId="+blogId+"&fresh=" + + Math.random();
		$.getJSON(url,function(data){
			if (data.state == 0) {
				var blog = data.data;
				$("#courseNameShow").text(blog.course.courseName);
				$("#courseNameShow").attr("href","postList.html?courseId="+blog.course.courseId);
				$("#blogTitle").html('<h style="font-size: 22px; font-weight: bold; border-bottom: 2px solid rgb(100, 100, 100); ">​'+blog.blog.blogTitle+'</h1>');
				$("#blogContent").html(blog.blog.blogContent);
				$("#userName").text(blog.user.userName);
				$("#userName").attr("href","personInfoShow.html?userId="+blog.user.userId);
				$("#createTime").text(format(blog.blog.createTime));
				$("#readCount").text(blog.blog.readCount);
				initBlogEvaluate();
				}
			else
				alert("查询失败");
			});
	}
	
	// 初始化楼信息
	function initBlogEvaluate() {
		var url = "/a4q/blogEvaluate/getEvaluateList?fresh="+Math.random();
		$.getJSON(url, function(data) {
			if (data.state == 0) {
				$(".floorCount").text(data.data.length+"个评论");
				var tempHtml = "";
				$.map(data.data, function(value, index) {
					index = index + 1;
					tempHtml += '<div class="answer-item atl-item" floorid="1">'+
	                			'<div class="atl-head-reply moderator-delete"><a onclick="remove('+value.evaluateId+')">版主删除权限</a></div>'+
	                			'<div class="user" style="font-size:15px;">'+
	                			'<a href="personInfo/personInfoHead.html?userId='+value.user.userId+'" target="_blank">'+value.user.userName+'</a>&nbsp;'+format(value.createTime)+'&nbsp;<span evaluateId="'+value.evaluateId+'" id="f'+index+'">'+index+'楼</span></div><hr align="left" style="width:200px">'+
	                			'<div class="content" id="floorContent">'+value.evaluateContent+'</div></div>';
				});
				$("#evaluateShow").html(tempHtml);
				SyntaxHighlighter.highlight();/*代码高亮*/
				if(moderatorTag == 0)
					$(".moderator-delete").hide();
				else if(moderatorTag == 1)
					$(".moderator-delete").show();
			} else {
				alert(data.stateInfo);
			}
		});
	}
	
	/*判断登录状态成功之后调用的函数*/
	function aSuccess(data){
		user = data.data;
		isLogin = true;
		$(".loginState").text("个人中心");
		$(".loginState").attr("href","personInfo/personInfoHead.html?userId="+user.userId);
		$(".register").hide();
		$("#editor").css("display","block");
		$("#reply").css("display","inline");
		moderatorJudge();/*判断是不是版主*/
	}
	
	/*判断登录状态失败之后调用的函数*/
	function aDefault(data){
		/*alert("未登录");*/
		isLogin = false;
		initBlog();
	}
	
	//回答操作
	function replyHandle(){
			var addFloorUrl = "/a4q/blogEvaluate/addEvaluate";
			var formData = new FormData();
			var evaluateContent = ue.getContent();
			formData.append("evaluateContent",evaluateContent);
			$.ajax({
				url : addFloorUrl,
				contentType : false,
				processData : false,
				cache : false,
				asyn : false,
				type : "POST",
				data : formData,
				success : function(data){
					if(data.state == 0){
						alert("回答成功");
						ue.setContent('');
						loginState(aSuccess,aDefault);
					}else{
						alert(data.stateInfo);
					}
				}
			});
		}

	//版主判断
	function moderatorJudge(){
		var url = '/a4q/course/moderatorJudgeBlog?fresh='+Math.random();
		$.getJSON(url,function(data){
			if(data.state == 0){
				moderatorTag = 1;
			}else{
				moderatorTag = 0;
//				alert("不是版主");
			}
			initBlog();
		});
	}

	//版主删除评论信息
	function remove(floorId){
		var result = confirm("确认删除该楼信息");
		if(result){
			var removeUrl = "/a4q/floor/removeFloor?floorId="+floorId;
			$.getJSON(removeUrl,function(data){
				if(data.state == 0){
					alert("删除成功");
					loginState(aSuccess,aDefault);
				}else{
					alert(data.stateInfo);
				}
			});
		}
	}