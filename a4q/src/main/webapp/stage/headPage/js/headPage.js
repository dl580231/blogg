currentTab = 0;
rowSize = 5;
rowStartR = 0;
rowStartUR = 0;
load = false;
countR = 0;
countUR =0
$(function(){
	initPage();
	bindScoll();
});

function initPage(){
	initCourse();
	initResolved(0);
	initResolved(1);
	initRank();
	loginState();
}

//初始化课程信息展示
function initCourse(){
	var initUrl = "/a4q/course/getCourseList";
	$.ajax({
		url : initUrl,
		type : "GET",
		cache : false,
		success : function(data) {
			if (data.state == 0) {
				var tempHtml = "";
				$.map(data.data,function(value,index){
					tempHtml +='<dl><dt>'+
								'<a href="/a4q/stage/postList.html?courseId='+value.courseId+'" target="_blank"">'+value.courseName+'</a>'+
								'</dt></dl>'
				});
				$("#courseInfo").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

//初始化已解决问题列表
function initResolved(handle){
	var initUrl = "";
	var ele = '';
	if(handle==0){
		initUrl = "/a4q/post/getResolvedPost?rowStart="+rowStartR+"&rowSize="+rowSize;
		ele = "#resolved"
	}else if(handle==1){
		initUrl = "/a4q/post/getUnResolvedPost?rowStart="+rowStartUR+"&rowSize="+rowSize;
		ele = "#unResolved"
	}
	$.ajax({
		url : initUrl,
		type : "GET",
		cache :false,
		success : function(data){
			if(data.state == 0){
				temp = iterator(data);
				$(ele).append(temp);
				if(handle==0){
					countR = data.data.count;
					rowStartR += rowSize;
				}
				else if(handle==1){
					countUR = data.data.count;
					rowStartUR += rowSize;
				}
				$("#loading").css("visibility","hidden");
				load = false;
			}else{
				alert(data.stateInfo);
			}
		}
	});
}

//为窗口绑定滑动事件
function bindScoll(){
	$(window).bind("scroll",function () {
		a = $(window).height();
		b = $(window).scrollTop();
		c = $(document).height();
	    if(((a+b+10)>c)&&!load){
	    	load = true;//控制只执行一次函数
	    	loadJudge();//滚动完之后的下拉加载，判断对某块进行刷新
	    }
	});
}

//判断导航栏进行加载
function loadJudge(){
	temp = 0;
	judge = true;
	if(currentTab==0){
		if(rowStartR>countR||rowStartR==countR){
			judge = false;
		}
	}else if(currentTab==1){
		if(rowStartUR>countUR||rowStartUR==countUR){
			judge = false;
		}
	}
	if(judge){		
		$("#loading").css("visibility","visible");
		setTimeout(function(){
			initResolved(currentTab);
		},1500);
	}
}

//初始化排行榜
function initRank(){
	var initRankUrl = "/a4q/post/getUserRank";
	$.ajax({
		url : initRankUrl,
		typr : "GET",
		cache : false,
		success : function(data){
			if(data.state == 0){
				var tempHtml = '';
				$.map(data.data,function(value,index){
					tempHtml += '<li class="s_c_list l_1">'+
					   		   '<span><a href="../../stage/personInfoShow.html?userId='+value.userId+'">'+value.userName+'</a></span>'+
					   		   '<em>'+value.num+'</em></li>';					
				});
				$("#rank").html(tempHtml);
			}else{
				alert(data.stateInfo)
			}
		}
	});
}

//搜索功能实现
function search(){
	var key = $("#key").val();
	var url = "/a4q/stage/postList.html?key="+key;
	window.open(url); 
}

//提出问题
function ask(){
	window.open("/a4q/stage/a4q.html");
}

//登录状态判断
function loginState() {
	var loginStateUrl = "/a4q/personInfoAdmin/loginState";
	$.ajax({
		url : loginStateUrl,
		type : "GET",
		asyn : false,
		success : function(data) {
			if (data.state == 0) {
				user = data.data;
				isLogin = true;
				$("#login").text("个人中心");
				$("#login").attr("href",
						"/a4q/stage/personInfoShow.html?userId=" + user.userId);
				$("#register").hide();
			} else {
				alert("未登录");
				isLogin = false;
			}
		}
	});
}

//遍历内容
function iterator(data){
	var tempHtml = '';
	$.map(data.data.list,function(value,index){
	tempHtml += '<tr><td class="qaTitle"><span><a href="/a4q/stage/postShow.html?postId='+value.postId+'" target="_blank" class="qaTitle_link" style="cursor: pointer; display: block;">'+value.postTitle+'</a></span></td>'+
			'<td>'+formatD(value.createTime)+'</td>'+
			'<td class="qa_askname"><a href="../../stage/personInfoShow.html?userId='+value.deployUser.userId+'" target="_blank">'+value.deployUser.userName+'</a></td></tr>';
	});
	return tempHtml;
}

//切换导航栏方法
function setTab(name, sort){
	var tagItem = $(".tabTag_" + name).children();
	var conItem = $(".tabCon_" + name).children();
	tagItem.each(function(i){
		if(i == sort){
			currentTab = i;
			tagItem.eq(i).addClass("curTag").siblings().removeClass("curTag");
			conItem.eq(i).show().siblings().hide();
		}
	});
}

