var userId = getQueryString("userId");
$(function() {
	if (userId) {
		initPage();
	} else {
		/* window.location.href = "/a4q/stage/headPage/headpage.html"; */
	}
	
	$("#modify").click(function(){
		var personInfoUrl = "../register.html?userId="+userId;
		window.open(personInfoUrl);
	});
	$("#logout").click(function(){
		logoutHandle();
	});
});

function initPage() {
	initUserInfo();
	loginState(aSuccess,aDefault);
}

//初始化用户信息
function initUserInfo(){
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

/*判断登录状态成功之后调用的函数*/
function aSuccess(data){
	var user = data.data;
	if(user.userId == userId){
		$("#modify").show();
		$("#logout").show();
		$("#modify").attr("userId",user.userId);
	}else{
		$("#modify").hide();
		$("#logout").hide();
	}
}

/*判断登录状态失败之后调用的函数*/
function aDefault(data){
	$("#modify").hide();
	$("#logout").hide();
}

/*注销操作*/
function logoutHandle(){
	var loginStateUrl = "/a4q/personInfoAdmin/logout";
	$.getJSON(loginStateUrl,function(data){
		if(data == "unLogin"){
			alert("未登录");
		}
		else{
			if(data.state == 0){
				alert("注销成功");
				window.open("/a4q/stage/headPage/headpage.html","_parent");
			}else{
				alert(data.stateInfo);
			}
		}
	});
}