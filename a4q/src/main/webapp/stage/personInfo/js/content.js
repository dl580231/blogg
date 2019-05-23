var userId = getQueryString("userId");
$(function(){
	loginState(aSuccess,aDefault);
	$("#iframeShow").attr("src","personInfoShow.html?userId="+userId);
});

function aSuccess(data){
	var user = data.data;
	if(user.userId == userId){
		if(user.notice == 1)
			$(".redpoint").show();
		else if(user.notice == 0)
			$(".redpoint").hide();
		$("#message").show();
	}else{
		$("#message").hide();
	}
}

function aDefault(data){
	
}

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
		$("#postContent").load("deployPost.html?userId="+userId+"&fresh="+Math.random());
	}else if(sort==2){
		$("#postContent").css("display","block");
		$("#userContent").css("display","none");
		$("#postContent").load("replyPost.html?userId="+userId+"&fresh="+Math.random());
	}else if(sort==4){
		$("#postContent").css("display","block");
		$("#userContent").css("display","none");
		$("#postContent").load("postMssageCenter.html?fresh="+Math.random());
	}else if(sort==3){
		$("#postContent").css("display","block");
		$("#userContent").css("display","none");
		$("#postContent").load("blogData.html?userId="+userId+"&fresh=" + + Math.random());
	}
}