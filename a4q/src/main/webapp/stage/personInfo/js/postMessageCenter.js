$(function(){
	$("#topPost").hide();
	$("#postMessageContent").hide();
	$("#topBlog").show();
	$("#blogMessageContent").show();
	initBlogMessage();
});

/*初始化消息通知*/
function initPostMessage(){
	notReadPost = 0;
	var url = "/a4q/floor/getNoticeFloor";
	$.getJSON(url,function(data){
		if(data.state==0){
			var temp = '';
			$.map(data.data,function(value,index){
				if(value.lookOver == 1){
					temp += '<tr>'
						+'<td width="50%"><div style="margin-bottom:5px;"><span class="tagTalk">论坛</span>用户：<a target="_blank" href="personInfoHead.html?userId='+value.user.userId+'">'+value.user.userName+'</a>回复了你的帖子</div><div class=""><a target="_blank" href="../postShow.html?postId='+value.post.postId+'">'+value.post.postTitle+'</a></div></td>'
						+'<td width="30%" class="center"><ul class="unstyled"><li id="creatTime">通知时间：'+format(value.createTime)+'</li></ul></td>'
						+'<td width="20%" class="center"><ul class="unstyled"><li><a onclick="rmNotice('+value.floorId+')">删除</a></li></ul></td>'
						+'</tr>';
				}
				else if(value.lookOver == 0){
					notReadPost += 1;
					temp += '<tr>'
							+'<td width="50%"><div style="margin-bottom:5px;"><span class="redpointM"></span><span class="tagTalk">论坛</span>用户：<a target="_blank" href="personInfoHead.html?userId='+value.user.userId+'">'+value.user.userName+'</a>回复了你的帖子</div><div class=""><a onclick="postShowHandle('+value.post.postId+','+value.floorId+')">'+value.post.postTitle+'</a></div></td>'
							+'<td width="30%" class="center"><ul class="unstyled"><li id="creatTime">通知时间：'+format(value.createTime)+'</li></ul></td>'
							+'<td width="20%" class="center"><ul class="unstyled"><li><a onclick="rmNotice('+value.floorId+')">删除</a></li></ul></td>'
							+'</tr>';
				}
			});
			$("#notReadPost").text(notReadPost);
			$("#postMessageContent").html(temp);
		}
	});
}

function postShowHandle(postId,floorId){
	var url = "/a4q/floor/lookOver?floorId="+floorId;
	$.getJSON(url,function(data){
		if(data.state == 0){
			initPostMessage();
			window.open("../postShow.html?postId="+postId);	
		}else{
			alert(data.stateInfo);
		}
	});
}

/*删除post的通知*/
function rmNotice(floorId){
	var url = "/a4q/floor/lookOverDelete?floorId="+floorId;
	var result = confirm("是否删除");
	if(result){
		$.getJSON(url,function(data){
			if(data.state==0){
				alert("删除成功");
				initPostMessage();
			}else{
				alert(data.stateInfo);
			}
		});
	}
}

function clearNoticeAll(){
	var url = "/a4q/floor/lookOverDeleteAll";
	var result = confirm("是否清除所有消息");
	if(result){
		$.getJSON(url,function(data){
			if(data.state==0){
				alert("清除所有消息成功");
				initPostMessage();
			}else{
				alert(data.stateInfo);
			}
		});
	}
}

//切换导航栏方法
function setTabM(sort){
	if(sort == 0){
		$("#nav_0").css("background-color","red");
		$("#nav_1").css("background-color","");
		$("#topPost").hide();
		$("#postMessageContent").hide();
		$("#topBlog").show();
		$("#blogMessageContent").show();
		initBlogMessage();
	}else if(sort == 1){
		$("#nav_1").css("background-color","red");
		$("#nav_0").css("background-color","");
		$("#topBlog").hide();
		$("#blogMessageContent").hide();
		$("#topPost").show();
		$("#postMessageContent").show();
		initPostMessage();
	}
}

function initBlogMessage(){
	notReadBlog = 0;
	var url = "/a4q/blogEvaluate/getNoticeEvaluate";
	$.getJSON(url,function(data){
		if(data.state==0){
			var temp = '';
			$.map(data.data,function(value,index){
				if(value.lookOver == 1){
					temp += '<tr>'
						+'<td width="50%"><div style="margin-bottom:5px;"></span><span class="tagTalk">论坛</span>用户：<a target="_blank" href="personInfoHead.html?userId='+value.user.userId+'">'+value.user.userName+'</a>回复了你的帖子</div><div class=""><a target="_blank" href="../blogShow.html?blogId='+value.blog.blogId+'">'+value.blog.blogTitle+'</a></div></td>'
						+'<td width="30%" class="center"><ul class="unstyled"><li id="creatTime">通知时间：'+format(value.createTime)+'</li></ul></td>'
						+'<td width="20%" class="center"><ul class="unstyled"><li><a onclick="rmEvaluateNotice('+value.evaluateId+')">删除</a></li></ul></td>'
						+'</tr>';
				}
				else if(value.lookOver == 0){
					notReadBlog += 1;
					temp += '<tr>'
						+'<td width="50%"><div style="margin-bottom:5px;"><span class="redpointM"></span><span class="tagTalk">论坛</span>用户：<a target="_blank" href="personInfoHead.html?userId='+value.user.userId+'">'+value.user.userName+'</a>回复了你的帖子</div><div class=""><a onclick="blogShowHandle('+value.blog.blogId+','+value.evaluateId+')">'+value.blog.blogTitle+'</a></div></td>'
						+'<td width="30%" class="center"><ul class="unstyled"><li id="creatTime">通知时间：'+format(value.createTime)+'</li></ul></td>'
						+'<td width="20%" class="center"><ul class="unstyled"><li><a onclick="rmEvaluateNotice('+value.evaluateId+')">删除</a></li></ul></td>'
						+'</tr>';
				}
			});
			$("#notReadBlog").text(notReadBlog);
			$("#blogMessageContent").html(temp);
		}
	});
}

function blogShowHandle(blogId,evaluateId){
	var url = "/a4q/blogEvaluate/lookOver?evaluateId="+evaluateId;
	$.getJSON(url,function(data){
		if(data.state == 0){
			initBlogMessage();
			window.open("../blogShow.html?blogId="+blogId);	
		}else{
			alert(data.stateInfo);
		}
	});
}


function rmEvaluateNotice(evaluateId){
	var url = "/a4q/blogEvaluate/lookOverDelete?evaluateId="+evaluateId;
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

function clearNoticeAllBlog(){
	var url = "/a4q/blogEvaluate/lookOverDeleteAll";
	var result = confirm("是否清除所有消息");
	if(result){
		$.getJSON(url,function(data){
			if(data.state==0){
				alert("清除所有消息成功");
				initPostMessage();
			}else{
				alert(data.stateInfo);
			}
		});
	}
}
	