var userId = getQueryString("userId");

$(function(){
	$("#iframeShow").attr("src","personInfoShow.html?userId="+userId);
});

//切换导航栏方法
function setTab(sort){
	var tagItem = $(".top").children();
	tagItem.each(function(i){
		if(i == sort){
			currentTab = i;
			tagItem.eq(i).addClass("active").siblings().removeClass("active");
		}
	});
	if(sort==0){
		$("#userContent").css("display","block");
		$("#postContent").css("display","none");
	}else if(sort==1){
		$("#postContent").css("display","block");
		$("#userContent").css("display","none");
		$("#postContent").load("deployPost.html?userId="+userId);
	}else if(sort==2){
		$("#postContent").css("display","block");
		$("#userContent").css("display","none");
		$("#postContent").load("replyPost.html?userId="+userId);
	}
}