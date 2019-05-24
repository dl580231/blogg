var currentUel = '';
$(function(){
	var currentUrl = window.location.pathname		
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
}

/*判断登录状态失败之后调用的函数*/
function aDefault(data){
}