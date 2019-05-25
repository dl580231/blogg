$(function(){
	var key = getQueryString("key");
	$("#iframeShow").attr("src","postList.html?key="+key);
});