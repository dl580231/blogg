currentTab = 0;
rowSize = 20;
rowStartR = 0;
rowStartUR = 0;
load = false;
countR = 0;
countUR =0;
courseId = null;
position = -1;
isLogin = false;
$(function(){
	courseId = getQueryString("courseId");
	position = getQueryString("position");
	initPage();
	bindScoll();
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
	initPost(0);
	initPost(1);
	initRank();
	initPostRank();
	loginState(aSuccess,aDefault);
}

function initPostRank(){
	var url = "/a4q/post/getPostRankByReadCount";
	$.getJSON(url,function(data){
		tempHtml = '';
		$.map(data.data,function(value,index){
			tempHtml += '<li class="s_c_list l_1">'+
			   		   '<span style="float:none" margin="left"><a target="_blank" href="../postShow.html?postId='+value.postId+'">'+value.postTitle+'</a><span style="float:none;color:#14a7ed;">（阅读量：'+value.readCount+'）</span></span></li>';					
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
						'<a href="/a4q/stage/headPage/postHeadPage.html?courseId='+value.courseId+'&position='+index+'">'+value.courseName+'</a>'+
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
function initPost(handle){
	var initUrl = "";
	var ele = '';
	if(handle==0){
		initUrl = "/a4q/post/getResolvedPost?rowStart="+rowStartR+"&rowSize="+rowSize+"&courseId="+courseId;
		ele = "#resolved"
	}else if(handle==1){
		initUrl = "/a4q/post/getUnResolvedPost?rowStart="+rowStartUR+"&rowSize="+rowSize+"&courseId="+courseId;
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
					addMoreJudge(0);
				}
				else if(handle==1){
					countUR = data.data.count;
					rowStartUR += rowSize;
					addMoreJudge(1);
				}
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
		/*a = $(window).height();
		b = $(document).scrollTop();
		c = $(document).height();
	    if(((a+b+10)>c)&&!load){
	    	loadJudge();//滚动完之后的下拉加载，判断对某块进行刷新
	    }*/
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
	var url = "/a4q/stage/headPage/listComposea4q.html?key="+key;
	window.open(url); 
}

//提出问题
function ask(){
	if(isLogin){
		window.open("a4q.html");
	}else{
		alert("发布帖子之前请登录");
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
	initRecommedPost()
}

function aDefault(data){
	isLogin = false;
	$("#recommend").hide();
}

/*推荐帖子*/
function initRecommedPost(){
	var url = "/a4q/post/getRecommendPost";
	$.getJSON(url,function(data){
		if(data == 'unLogin'){
			alert("未登录")
		}else{
			if(data.state == 0){
				var temp = iterator(data);
				$("#recommendContent").append(temp);
			}else{
				alert(data.stateInfo);
			}
		}
	});
}

//遍历内容
function iterator(data){
	var tempHtml = '';
	$.map(data.data.list,function(value,index){
	tempHtml += '<tr><td class="qaTitle"><span style="padding:0;"><a style="text-decoration:none" href="/a4q/stage/postShow.html?postId='+value.postId+'" target="_blank" class="qaTitle_link" style="cursor: pointer; display: block;"><span style="margin-right:5px;" class="tagTalk">论坛</span><div class="contentA" title="'+value.postTitle+'">'+value.postTitle+'</div><span style="color:black;margin-top:4px;margin-left:40px">（阅读量：'+value.readCount+'）</span></a></span></td>'+
			'<td>'+formatD(value.createTime)+'</td>'+
			'<td class="qa_askname"><a href="../personInfo/personInfoHead.html?userId='+value.deployUser.userId+'" target="_blank">'+value.deployUser.userName+'</a></td></tr>';
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
			addMoreJudge();
		}
	});
}

function addMoreJudge(temp){
	if(temp == 0){
		if(rowStartR>countR||rowStartR==countR){
			$("#rAdd").css("display","none");
		}else{
			$("#rAdd").css("display","block");
		}
	}else if(temp == 1){
		if(rowStartUR>countUR||rowStartUR==countUR){
			$("#unAdd").css("display","none");
		}else{
			$("#unAdd").css("display","block");
		}
	}
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
	}else if(currentTab==2){
		judge = false;
	}
	if(judge){	
    	load = true;//控制只执行一次函数	
		$("#loading").css("display","block");
		setTimeout(function(){
			initPost(currentTab);
		},1500);
	}
}