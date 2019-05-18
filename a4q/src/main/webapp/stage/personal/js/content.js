/*动态设置iframe的长度
function frameHeightHandle(){
	var h = document.documentElement.clientHeight;
	$("#iframeShow").height(h);
}

window.onresize=function(){  
	frameHeightHandle();  
}*/
$(function(){
	var userId = getQueryString("userId");
	if(userId=='')
		alert("查询用户为空");
	else{
		var url = "personInfoShow.html?userId="+userId;
		$("#iframeShow").attr("src",url);
		$("#userInfo").attr("href",url);
		$("#postManage").attr("href","postPersonal.html?userId="+userId);
	}
});
