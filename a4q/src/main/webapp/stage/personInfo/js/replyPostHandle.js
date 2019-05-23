var userId = getQueryString("userId");
$(function(){
	if(userId != '')
		initreplyPost();
});

/*初始化个人回复帖子*/
function initreplyPost(){
	var url = "/a4q/post/getAnswerPost?userId="+userId;
	$.getJSON(url,function(data){
		if(data.state==0){
			var temp = '';
			$.map(data.data,function(value,index){
				temp += '<tr><td width="50%"><div class="typographic">'+value.postTitle+'</div>'
			      		+'</td><td width="30%" class="center"><ul class="unstyled"><li id="readCount">阅读量：'+value.readCount+'</li><li id="creatTime">评论时间：'+format(value.createTime)+'</li></ul>'
			      		+'</td><td width="20%" class="center"><ul class="unstyled"><li><a target="_blank" href="/a4q/stage/postShow.html?postId='+value.postId+'">查看</a></li></ul></td></tr>';
			});
			$("#replyPostContent").html(temp);
		}
	});
}