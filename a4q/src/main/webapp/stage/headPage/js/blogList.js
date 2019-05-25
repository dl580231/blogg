$(function(){
	var key = getQueryString("key");
	initPage(key);
});

function initPage(key){
	initResolved(key);
	initUnResolved(key);
}

//初始化已解决问题列表
function initResolved(key){
	var initResolvedUrl = "/a4q/post/getResolved?key="+key;
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
	var tempHtml = '';
	$.map(data.data,function(value,index) {
		tempHtml += '<li><div title="'+value.postContent+" "+formatD(value.createTime)+'" class="item-content">'+
					'<div class="item-inner"><div class="item-title">'+
					'<a onclick="openPost('+value.postId+')">'+value.postTitle+'</a>'+
					'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
					formatD(value.createTime)+'</div></div></div></li>';
	});
	return tempHtml;
}


//初始化为解决问题
function initUnResolved(key){
	var initResolvedUrl = "/a4q/post/getUnResolved?key="+key;
	$.getJSON(initResolvedUrl,function(data){
		if(data.state == 0){
			$("#unResolved").html(iterator(data));
		}else{
			alert(data.stateInfo);
		}
	})
}

function openPost(postId){
	window.open("../postShow.html?postId="+postId);
}