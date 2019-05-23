var userId = getQueryString("userId");
$(function(){
	if(userId != '')
		initBlogMessage();
});

/*初始化个人发布帖子*/
function initBlogMessage(){
	var url = "/a4q/blog/getBlogList?userId="+userId;
	$.getJSON(url,function(data){
		if(data.state==0){
			var temp = '';
			$.map(data.data,function(value,index){
				temp += '<tr><td width="50%"><div class="typographic">'+value.blogTitle+'</div>'
			      		+'</td><td width="30%" class="center"><ul class="unstyled"><li id="readCount">阅读量：'+value.readCount+'</li><li id="creatTime">发布时间：'+format(value.createTime)+'</li></ul>'
			      		+'</td><td width="20%" class="center"><ul class="unstyled"><li><a target="_blank" href="/a4q/stage/blogShow.html?blogId='+value.blogId+'">查看</a></li><li><a href="#">编辑</a></li><li><a onclick="rmPost('+value.blogId+')">删除</a></li></ul></td></tr>';
			});
			$("#blogContent").html(temp);
		}else{
			alert(data.stateInfo);
		}
	});
}

function rmPost(blogId){
	var url = "/a4q/blog/logicRm?blogId="+blogId;
	var result = confirm("是否删除");
	if(result){
		$.getJSON(url,function(data){
				if(data.state==0){
					alert("删除成功");
					initBlogMessage();
				}else{
					alert(data.stateInfo);
				}
		});
	}
}