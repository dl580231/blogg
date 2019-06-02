currentTab = 0;
rowSize = 20;
rowStart = 0;
load = false;
count = 0;
courseId = null;
position = -1;
isLogin = false;
$(function(){
	courseId = getQueryString("courseId");
	position = getQueryString("position");
	initPage();
//	bindScoll();
});

//改变选中科目背景
function focus(){
	if(courseId == '')
		return;
	else{
		var course = $("#courseInfo").children();
		course.each(function(index){
			if(index == position){
				$(this).find("a").css("color","#14a7ed");
			}
		});
	}
}

function initPage(){
	initCourse();
	initBlogContent();
	initRank();
	initPostRank();
	loginState(aSuccess,aDefault);
}

function initPostRank(){
	var url = "/a4q/blog/getPostRankByReadCount";
	$.getJSON(url,function(data){
		tempHtml = '';
		$.map(data.data,function(value,index){
			tempHtml += '<li class="s_c_list l_1">'+
			   		   '<span margin="left" style="float:none;"><a target="_blank" href="../blogShow.html?blogId='+value.blogId+'">'+value.blogTitle+'</a><span style="float:none;color:#14a7ed;">（阅读量：'+value.readCount+'）</span></span></li>';					
		});
		$("#postRank").html(tempHtml);
	});
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
						'<a href="/a4q/stage/headPage/blogHeadPage.html?courseId='+value.courseId+'&position='+index+'">'+value.courseName+'</a>'+
						'</dt></dl>'
				});
				$("#courseInfo").html(tempHtml);
				focus();
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

//初始化已解决问题列表
function initBlogContent(){
	var initUrl = "/a4q/blog/getBlogLoad?rowStart="+rowStart+"&rowSize="+rowSize+"&courseId="+courseId;
	$.ajax({
		url : initUrl,
		type : "GET",
		cache :false,
		success : function(data){
			if(data.state == 0){
				temp = iterator(data);
				$("#blogContent").append(temp);
				count = data.data.count;
				rowStart += rowSize;
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
		positionHandle();
		a = $(window).height();
		b = $(document).scrollTop();
		c = $(document).height();
	    if(((a+b+10)>c)&&!load){
	    	loadJudge();//滚动完之后的下拉加载，判断对某块进行刷新
	    }
	});
}

//处理三列模式两边的position
function positionHandle(){
	var x = $("#pLeft").offset().top;
	var y = $(document).scrollTop();
	if(y > 130){
		$("#pLeft").css("position","fixed");
		$("#pLeft").css("top","0");
	}else{
		$("#pLeft").attr("style","");
	}
	if(y > 400){
		$("#pRight").css("position","fixed");
		$("#pRight").css("top","-10px");
		$("#pRight").css("left","995px");
		$("#pRight01").hide();
	}else{
		$("#pRight").attr("style","");
		$("#pRight01").show();
	}
}

//判断导航栏进行加载
function loadJudge(){
	temp = 0;
	judge = true;
	if(currentTab==0){
		if(rowStart>count||rowStart==count){
			judge = false;
		}
	}else if(currentTab==1){
		judge = false;
	}
	if(judge){	
    	load = true;//控制只执行一次函数	
		$("#loading").css("visibility","visible");
		setTimeout(function(){
			initBlogContent();
		},1500);
	}
}

//初始化排行榜
function initRank(){
	var url = "/a4q/blog/getUserRank";
	$.ajax({
		url : url,
		typr : "GET",
		cache : false,
		success : function(data){
			if(data.state == 0){
				var tempHtml = '';
				$.map(data.data,function(value,index){
					tempHtml += '<li class="s_c_list l_1">'+
					   		   '<span><a target="_blank" href="../personInfo/personInfoHead.html?userId='+value.userId+'">'+value.userName+'</a></span>'+
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
function query(){
	var key = $("#key").val();
	var url = "/a4q/stage/headPage/blogListCompose.html?key="+key;
	window.open(url); 
}

//提出问题
function ask(){
	if(isLogin){
		window.open("blogDeploy.html");
	}else{
		alert("发布博客之前请登录");
	}
}

/*依据登录状态做一些操作*/
function aSuccess(data){
	isLogin = true;
	user = data.data;
	if(user.notice == 1)
		$("#login").html("个人中心<span class='redpoint'>");
	else
		$("#login").html("个人中心");
	$("#login").attr("href","/a4q/stage/personInfo/personInfoHead.html?userId=" + user.userId);
	$("#login").attr("target","_blank");
	$("#register").hide();
	$("#recommend").show();
	initRecommedBlog()
}

function aDefault(data){
	isLogin = false;
	$("#recommend").hide();
}

/*推荐帖子*/
function initRecommedBlog(){
	var url = "/a4q/blog/getRecommendBlog";
	$.getJSON(url,function(data){
		if(data.state == 0){
			var temp = iterator(data);
			$("#recommendContent").append(temp);
		}else{
			alert(data.stateInfo);
		}
	});
}

//遍历内容
function iterator(data){
	var tempHtml = '';
	$.map(data.data.list,function(value,index){
	tempHtml += '<tr><td class="qaTitle"><span style="float:left" class="tagTalk">博客</span><span><a href="/a4q/stage/blogShow.html?blogId='+value.blog.blogId+'" target="_blank" class="qaTitle_link" style="cursor: pointer; display: block;">'+value.blog.blogTitle+'</a><span style="color:black;">（阅读量：'+value.blog.readCount+'）</span></span></td>'+
			'<td>'+formatD(value.blog.createTime)+'</td>'+
			'<td class="qa_askname"><a href="../personInfo/personInfoHead.html?userId='+value.user.userId+'" target="_blank">'+value.user.userName+'</a></td></tr>';
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

