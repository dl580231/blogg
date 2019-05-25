$(function() {
	initFloor();
});

function initFloor() {
	var initUrl = "/a4q/floor/getFloorList";
	$.ajax({
		url : initUrl,
		cache : false,
		type : "post",
		data : $("#submitForm").serialize(),
		success : function(data) {
			if(data.state == 0){
				var tempHtml = '<tr>' + '<th>楼ID</th>' + '<th>所属帖ID</th>'
						+ '<th>用户名称（用户ID）</th>' + '<th>内容</th>' + '<th>发帖时间</th>'
						+ '<th>回复时间</th>' + '<th>操作</th>' + '</tr>';
				$.map(data.data, function(value, index) {
					tempHtml += '<tr>' + '<td>' + value.floorId + '</td>' + '<td>'
							+ value.postId + '</td>' + '<td>' + value.user.userName
							+ '(' + value.user.userId + ')' + '</td>'
							+ '<td title="' + value.floorContent + '">'
							+ value.floorContent + '</td>' + '<td>'
							+ format(value.createTime) + '</td>' + '<td>'
							+ format(value.lastEditTime) + '</td>'
							+ '<td><a onclick=removeFloor(' + value.floorId
							+ ')>删除</a>' + '</td></tr>';
				});
				alert(tempHtml);
				$(".table").html(tempHtml);
			}else{
				alert(data.stateInfo);
			}
		}
	});
}

// 删除楼信息
function removeFloor(floorId) {
	var result = confirm("确认删除该楼信息");
	if (result) {
		var removeUrl = "/a4q/floor/removeFloor?floorId=" + floorId;
		$.getJSON(removeUrl, function(data) {
			if (data.state == 0) {
				alert("删除成功");
				initFloor();
			} else {
				alert(data.stateInfo);
			}
		});
	}
}