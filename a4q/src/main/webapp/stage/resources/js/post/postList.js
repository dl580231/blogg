$(function(){
	var courseId = getQueryString("courseId");
	var key = getQueryString("key");
	initPage(courseId,key);
});

function initPage(courseId,key){
	initResolved(courseId,key);
	initUnResolved(courseId,key);
}

//初始化已解决问题列表
function initResolved(courseId,key){
	var initResolvedUrl = "/a4q/post/getResolved?courseId="+courseId+"&key="+key;
		$.getJSON(initResolvedUrl,function(data){
			if(data.state == 0){
				$("#resolved").html(iterator(data));
			}else{
				alert(data.stateInfo);
			}
		})
}


//内容遍历
function iterator(data) {
	var tempHtml = '<li class="list-group-title">帖子展示</li>';
	$.map(data.data,function(value,index) {
		tempHtml += '<li><div title="'+value.postContent+" "+formatD(value.createTime)+'" class="item-content">'+
					'<div class="item-inner"><div class="item-title">'+
					'<a href="postShow.html?postId='+value.postId+'">'+value.postTitle+'</a>'+
					'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
					formatD(value.createTime)+'</div></div></div></li>';
	});
	return tempHtml;
}


//初始化为解决问题
function initUnResolved(courseId,key){
	var initResolvedUrl = "/a4q/post/getUnResolved?courseId="+courseId+"&key="+key;
	$.getJSON(initResolvedUrl,function(data){
		if(data.state == 0){
			$("#unResolved").html(iterator(data));
		}else{
			alert(data.stateInfo);
		}
	})
}