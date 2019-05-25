$(function(){
	var key = getQueryString("key");
	initPage(key);
});

function initPage(key){
	initSerachBlog(key);
}

//初始化已解决问题列表
function initSerachBlog(key){
	var url = "/a4q/blog/getSerachBlog?key="+key;
		$.getJSON(url,function(data){
			if(data.state == 0){
				$("#blogShow").html(iterator(data));
			}else{
				alert(data.stateInfo);
			}
		})
}


//内容遍历
function iterator(data) {
	var tempHtml = '';
	$.map(data.data,function(value,index) {
		tempHtml += '<li><div title="'+value.blog.blogTitle+" "+formatD(value.blog.createTime)+'" class="item-content">'
					+'<div class="item-inner"><div class="item-title">'
					+'<span style="float:left" class="tagTalk">博客</span><a onclick="openBlog('+value.blog.blogId+')">'+value.blog.blogTitle+'</a>'
					+'<div>阅读量：'+value.blog.readCount+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
					+'发布时间：'+formatD(value.blog.createTime)+'<div></div></div></div></li>';
	});
	return tempHtml;
}

function openBlog(blogId){
	window.open("../blogShow.html?blogId="+blogId);
}