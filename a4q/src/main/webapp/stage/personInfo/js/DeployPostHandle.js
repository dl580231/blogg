var userId = getQueryString("userId");
$(function(){
	initDeployPost();
});

/*初始化个人发布帖子*/
function initDeployPost(){
	var url = "/a4q/post/getPostList?userId="+userId;
	$.getJSON(url,function(data){
		if(data.state==0){
			var temp = '';
			$.map(data.data,function(value,index){
				temp += '<tr><td width="50%"><div class="typographic">'+value.postTitle+'</div>'
			      		+'</td><td width="30%" class="center"><ul class="unstyled"><li id="readCount">阅读量：'+value.readCount+'</li><li id="creatTime">发布时间：'+format(value.createTime)+'</li></ul>'
			      		+'</td><td width="20%" class="center"><ul class="unstyled"><li><a target="_blank" href="/a4q/stage/postShow.html?postId='+value.postId+'">查看</a></li><li><a href="#">编辑</a></li><li><a onclick="rmPost('+value.postId+')">删除</a></li></ul></td></tr>';
			});
			$("#deployPostContent").html(temp);
		}
	});
}

function rmPost(postId){
	var url = "/a4q/post/logicRmPost?postId="+postId;
	$.getJSON(url,function(data){
		var result = confirm("是否删除");
		if(result){
			if(data.state==0){
				alert("删除成功");
				initDeployPost();
			}else{
				alert(data.stateInfo);
			}
		}
	});
}