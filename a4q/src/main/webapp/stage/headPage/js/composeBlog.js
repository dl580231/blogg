$(function(){
	var key = getQueryString("key");
	$("#iframeShow").attr("src","blogList.html?key="+key);
});