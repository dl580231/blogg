$(function() {
	$("#modify").hide();
	var userId = getQueryString("userId");
	if (userId) {
		initPage(userId);
	} else {
		/* window.location.href = "/a4q/stage/headPage/headpage.html"; */
	}
	
	$("#modify").click(function(){
		var userId = $(this).attr("userId");
		var personInfoUrl = "register.html?userId="+userId;
		window.open(personInfoUrl);
	});
});

function initPage(userId) {
	initUserInfo(userId);
	initDeployPost(userId);
	initReplyPost(userId);
	loginState(userId);
}

//发布的帖子的展示
function initDeployPost(userId) {
	var initDeployPostUrl = "/a4q/post/getPostList?userId=" + userId;
	$.getJSON(initDeployPostUrl, function(data) {
		if (data.state == 0) {
			$("#deployPost").html(iterator(data));
		} else {
			alert(data.stateInfo);
		}
	});
}

//回答的帖子展示
function initReplyPost(userId){
	var initDeployPostUrl = "/a4q/floor/getFloorList?userId=" + userId;
	$.ajax({
		url : initDeployPostUrl,
		type : "GET",
		success : function(data){
			if(data.state == 0){
				var tempHtml = '<li class="list-group-title">个人回复展示</li>';
				$.map(data.data,function(value,index) {
					tempHtml += '<li><div title="'+value.floorContent+" "+formatD(value.createTime)+'" class="item-content">'+
								'<div class="item-inner"><div class="item-title">'+
								'<a href="postShow.html?postId='+value.postId+'">'+value.floorContent+'</a>'+
								'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
								formatD(value.createTime)+'</div></div></div></li>';
				});
				$("#replyPost").html(tempHtml);
			}else{
				alert(data.stateInfo);
			}		
		}
	});
}

//初始化用户信息
function initUserInfo(userId){
	var initUserInfoUrl = "/a4q/personInfoAdmin/getUserById?userId="+userId;
	$.getJSON(initUserInfoUrl,function(data){
		if(data.state == 0){
			var user = data.data;
			$("#userName").val(user.userName);
			$("#userName").attr("disabled","disabled");
			$("#phone").val(user.phone);
			$("#phone").attr("disabled","disabled");
			$("#email").val(user.email);
			$("#email").attr("disabled","disabled");
			$("#gender").val(user.gender);
			$("#gender").attr("disabled","disabled");
			$("#userType").val(user.userType);
			$("#userType").attr("disabled","disabled");
		}else{
			alert(data.stateInfo);
		}
	});
}

// 内容遍历
function iterator(data) {
	var tempHtml = '<li class="list-group-title">个人发布帖子展示</li>';
	$.map(data.data,function(value,index) {
		tempHtml += '<li><div title="'+value.postContent+" "+formatD(value.createTime)+'" class="item-content">'+
					'<div class="item-inner"><div class="item-title">'+
					'<a href="postShow.html?postId='+value.postId+'">'+value.postTitle+'</a>'+
					'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
					formatD(value.createTime)+'</div></div></div></li>';
	});
	return tempHtml;
}

//登录状态判断
function loginState(userId){
	var loginStateUrl = "/a4q/personInfoAdmin/loginState?fresh=" + Math.random();
	$.ajax({
		url : loginStateUrl,
		type : "GET",
		asyn : false,
		success : function(data){
			if(data.state == 0){
				var user = data.data;
				if(user.userId == userId){
					$("#modify").show();
					$("#modify").attr("userId",user.userId);
				}
			}else{
				
			}
		}
	});
}