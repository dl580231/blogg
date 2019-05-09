$(function() {
	var postId = getQueryString("postId");
	if (isNaN(postId)) {
		 window.location.href = "/a4q/stage/headPage/headpage.html"; 
	} else {
		initPage(postId);
	}
	
	//	设置点击事件
	$("#reply").click(function(){
		replyHandle();
	});
});

	

	function initPage(postId){
		initPost(postId);
		loginState(postId);//登录状态判断
	}
	
	// 初始化帖子信息
	function initPost(postId) {
		var initPostUrl = "/a4q/post/getPostById?postId=" + postId+"&fresh=" + + Math.random();
		$.ajax({
			url : initPostUrl,
			type : "GET",
			asyn : false,
			cache : false,
			success : function(data){
					if (data.state == 0) {
							var post = data.data;
							$("#courseNameShow").text(post.course.courseName);
							$("#courseNameShow").attr("href","postList.html?courseId="+post.course.courseId);
							$("#postTitle").html('<h style="font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(100, 100, 100); ">​'+post.postTitle+'</h1>');
							$("#postContent").html(post.postContent);
							$("#userName").text(post.deployUser.userName);
							$("#userName").attr("href","personInfoShow.html?userId="+post.deployUser.userId);
							$("#createTime").text(format(post.createTime));
							var isResolved =post.bestAnswerId;
							/*alert("bestAnswer :"+isResolved);*/
							if(isResolved != null){
								$("#isResolved").text("已解决");
								$("#isResolved").css("color","blue");
								initFloor(postId,isResolved)
							}else{
								$("#isResolved").text("未解决");
								$("#isResolvedSubmit").html('<input id="floorBest" placeholder="输入最佳答案所属楼"/><input type="button" onclick="elect()" value="提交">');
								initFloor(postId,null);
							}
					} else {
						alert("查询失败");
					}
			}
		});
	}
	
	// 初始化楼信息
	function initFloor(postId,isResolved) {
		/*alert(isResolved);*/
		var initFloorUrl = null;
		if(isResolved != null){
			initFloorUrl = "/a4q/floor/getFloorListWithNum?postId="+postId+"&isResolved="+isResolved+"&fresh="+Math.random();
		}else{
			initFloorUrl = "/a4q/floor/getFloorListWithNum?postId="+postId+"&fresh="+Math.random();
		}
		
		$.getJSON(initFloorUrl, function(data) {
			if (data.state == 0) {
				if(isResolved != null){
					$("#bestAnswer").text("最佳回答为:"+data.data.bestAnswer);
				}
				$(".floorCount").text(data.data.list.length+"个评论");
				var tempHtml = "";
				$.map(data.data.list, function(value, index) {
					index = index + 1;
					tempHtml += '<div class="answer-item atl-item" floorid="1">'+
	                			'<div class="atl-head-reply moderator-delete"><a onclick="remove('+value.floorId+')">版主删除权限</a></div>'+
	                			'<div class="user">'+
	                			'<a href="personInfoShow.html?userId='+value.user.userId+'" target="_blank">'+value.user.userName+'</a>&nbsp;'+format(value.createTime)+'&nbsp;<span floorId="'+value.floorId+'" id="f'+index+'">'+index+'楼</span></div>'+
	                			'<div class="content" id="floorContent">'+value.floorContent+'</div></div>';
				});
				$("#floorShow").html(tempHtml);
				$(".moderator-delete").hide();
			} else {
				alert(data.stateInfo);
			}
		});
	}
	
	// 登录状态判断
	function loginState(postId){
		var loginStateUrl = "/a4q/personInfoAdmin/loginState?fresh=" + Math.random();
		$.ajax({
			url : loginStateUrl,
			type : "GET",
			asyn : false,
			success : function(data){
				if(data.state == 0){
					user = data.data;
					isLogin = true;
					$(".loginState").text("个人中心");
					$(".loginState").attr("href","personInfoShow.html?userId="+user.userId);
					$(".register").hide();
					moderatorJudge();
				}else{
					/*alert("未登录");*/
					isLogin = false;
				}
			}
		});
	}

	
	//回答操作
	function replyHandle(){
			var addFloorUrl = "/a4q/floor/addFloor";
			var formData = new FormData();
			var floorContent = $(".floorContent").val();
			formData.append("floorContent",floorContent);
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
						$(".floorContent").val("");
						initPage(data.data);
					}else{
						alert(data.stateInfo);
					}
				}
			});
		}

	//版主判断
	function moderatorJudge(){
		var moderatorJudgeUrl = '/a4q/course/moderatorJudge?fresh='+Math.random();
		$.getJSON(moderatorJudgeUrl,function(data){
			if(data.state == 0){
				isModerator = true;
				$(".moderator-delete").show();
			}else{
				alert("不是版主");
			}
		});
	}

	//版主删除楼信息
	function remove(floorId){
		var result = confirm("确认删除该楼信息");
		if(result){
			var removeUrl = "/a4q/floor/removeFloor?floorId="+floorId;
			$.getJSON(removeUrl,function(data){
				if(data.state == 0){
					alert("删除成功");
					initPage(data.data);
				}else{
					alert(data.stateInfo);
				}
			});
		}
	}

	//提交最佳答案
	function elect(){
		var result = confirm("确认提交?");
		if(result){
			var num = $("#floorBest").val();
			if(isNaN(num)){
				alert("请输入楼对应的数字");
			}else{
				var floorId = $("#f"+num).attr("floorId");
				if(floorId){
					var url = "/a4q/post/electBestAnswer?floorId="+floorId;
					$.getJSON(url,function(data){
						if(data.state == 0){
							alert("指定成功");
							initPage(data.data);
						}else{
							alert(data.stateInfo);
						}
					});
				}else{
					alert("请输入正确楼数")
				}
			}
		}
	}