var position = 5;
$(function(){
	loginState(aSuccess,aDefault);
});

/*判断登录状态成功之后调用的函数*/
function aSuccess(data){
	user = data.data;
	if(user.notice == 1)
		$("#login").html("个人中心<span class='redpoint'>");
	else
		$("#login").html("个人中心");
	$("#login").attr("href","/a4q/stage/personInfo/personInfoHead.html?userId=" + user.userId);
	$("#login").attr("target","_blank");
	$("#register").hide();
	judgeUrl();
}

/*判断登录状态失败之后调用的函数*/
function aDefault(data){
	judgeUrl();
}

function judgeUrl(){
	currentUrl = window.location.href;
	if(currentUrl.indexOf("a4q.html") != -1){
		position = 0;
		$("#deploy").text("我要提问");
	}else if(currentUrl.indexOf("blog") != -1){
		$("#deploy").text("我要发布");
		position = 1;
	}
}

function query(){
	if(position == 0){
		var key = $("#key").val();
		var url = "/a4q/stage/headPage/listComposea4q.html?key="+key;
		window.open(url,"_self");
	}else if(position == 1){
		var key = $("#key").val();
		var url = "/a4q/stage/headPage/blogListCompose.html?key="+key;
		window.open(url,"_self");
	}
}
	
function ask(){
	if(position == 0){
		var url = "/a4q/stage/headPage/a4q.html";
		window.open(url,"_self"); 
	}else if(position == 1){
		var url = "/a4q/stage/headPage/blogDeploy.html";
		window.open(url,"_self"); 
	}
}	
