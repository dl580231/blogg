var userId = getQueryString("userId");
$(function() {
	if (userId) {
		initPage();
	} else {
		/* window.location.href = "/a4q/stage/headPage/headpage.html"; */
	}
});

function initPage() {
	initDeployPost();
	initReplyPost();
}

//发布的帖子的展示
function initDeployPost() {
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
function initReplyPost(){
	var initDeployPostUrl = "/a4q/post/getAnswerPost?userId=" + userId;
	$.ajax({
		url : initDeployPostUrl,
		type : "GET",
		success : function(data){
			if(data.state == 0){
				var tempHtml = '';
				/*$.map(data.data,function(value,index) {
					tempHtml += '<li><div title="'+""+" "+formatD(value.createTime)+'" class="item-content">'+
								'<div class="item-inner"><div class="item-title">'+
								'<a href="../postShow.html?postId='+value.postId+'">'+value.floorContent+'</a>'+
								'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
								formatD(value.createTime)+'</div></div></div></li>';
				});*/
				$("#replyPost").html(iterator(data));
			}else{
				alert(data.stateInfo);
			}		
		}
	});
}

// 内容遍历
function iterator(data) {
	var tempHtml = '';
	$.map(data.data,function(value,index) {
		tempHtml += '<li><div title="'+value.postTitle+" "+formatD(value.createTime)+'" class="item-content">'+
					'<div class="item-inner"><div class="item-title">'+
					'<a target="_blank" href="../postShow.html?postId='+value.postId+'">'+value.postTitle+'</a>'+
					'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
					formatD(value.createTime)+'</div></div></div></li>';
	});
	return tempHtml;
}