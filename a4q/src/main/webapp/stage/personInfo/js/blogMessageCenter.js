var userId = getQueryString("userId");
var notRead = 0;
$(function(){
	initMessage();
});

/*初始化消息通知*/
function initMessage(){
	notRead = 0;
	var url = "/a4q/floor/getNoticeFloor";
	$.getJSON(url,function(data){
		if(data.state==0){
			var temp = '';
			$.map(data.data,function(value,index){
				if(value.lookOver == 1){
					temp += '<tr>'
						+'<td width="50%"><div style="margin-bottom:5px;"><span class="tagTalk">博客</span>用户：<a target="_blank" href="personInfoHead.html?userId='+value.user.userId+'">'+value.user.userName+'</a>回复了你的帖子</div><div class=""><a target="_blank" href="../postShow.html?postId='+value.post.postId+'">'+value.post.postTitle+'</a></div></td>'
						+'<td width="30%" class="center"><ul class="unstyled"><li id="creatTime">通知时间：'+format(value.createTime)+'</li></ul></td>'
						+'<td width="20%" class="center"><ul class="unstyled"><li><a onclick="rmNotice('+value.floorId+')">删除</a></li></ul></td>'
						+'</tr>';
				}
				else if(value.lookOver == 0){
					notRead += 1;
					temp += '<tr>'
							+'<td width="50%"><div style="margin-bottom:5px;"><span class="redpointM"></span><span class="tagTalk">博客</span>用户：<a target="_blank" href="personInfoHead.html?userId='+value.user.userId+'">'+value.user.userName+'</a>回复了你的帖子</div><div class=""><a onclick="postShowHandle('+value.post.postId+','+value.floorId+')">'+value.post.postTitle+'</a></div></td>'
							+'<td width="30%" class="center"><ul class="unstyled"><li id="creatTime">通知时间：'+format(value.createTime)+'</li></ul></td>'
							+'<td width="20%" class="center"><ul class="unstyled"><li><a onclick="rmNotice('+value.floorId+')">删除</a></li></ul></td>'
							+'</tr>';
				}
			});
			$("#notRead").text(notRead);
			$("#messageContent").html(temp);
		}
	});
}

function postShowHandle(postId,floorId){
	var url = "/a4q/floor/lookOver?floorId="+floorId;
	$.getJSON(url,function(data){
		if(data.state == 0){
			initMessage();
			window.open("../postShow.html?postId="+postId);	
		}else{
			alert(data.stateInfo);
		}
	});
}

/*消除红点（已查看）*/
function rmNotice(floorId){
	var url = "/a4q/floor/lookOverDelete?floorId="+floorId;
	var result = confirm("是否删除");
	if(result){
		$.getJSON(url,function(data){
			if(data.state==0){
				alert("删除成功");
				initMessage();
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
				initMessage();
			}else{
				alert(data.stateInfo);
			}
		});
	}
}