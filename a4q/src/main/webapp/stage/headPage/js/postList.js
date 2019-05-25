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
	var url = "/a4q/post/getResolved?key="+key;
		$.getJSON(url,function(data){
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
		tempHtml += '<li><div title="'+value.postTitle+" "+formatD(value.createTime)+'" class="item-content">'
					+'<div class="item-inner"><div class="item-title">'
					+'<span style="float:left" class="tagTalk">论坛</span><a onclick="openPost('+value.postId+')">'+value.postTitle+'</a>'
					+'<div>阅读量：'+value.readCount+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
					+'发布时间：'+formatD(value.createTime)+'<div></div></div></div></li>';
	});
	return tempHtml;
}


//初始化为解决问题
function initUnResolved(key){
	var url = "/a4q/post/getUnResolved?key="+key;
	$.getJSON(url,function(data){
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