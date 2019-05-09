var curentPage = 0;
var pageCount = 0;
$(function() {
	search(curentPage + 1);
});

function lastPage() {
	search(pageCount);
}

function headPage() {
	search(1);
}

function beforePage() {
	search(currentPage - 1);
}

function afterPage() {
	search(currentPage + 1);
}
// 实现用户删除
function removeUser(userId) {
	var result = confirm("是否删除用户信息");
	var removeUrl = "/a4q/personInfoAdmin/removeUser?userId=" + userId;
	if (result) {
		$.getJSON(removeUrl, function(data) {
			if (data.state == 0) {
				alert("用户信息删除成功");
				search(currentPage);
			} else {
				alert("删除失败");
			}
		});
	}
}

// 模糊查询用户信息
function search(currentPageLocal) {
	var queryUserUrl = '/a4q/personInfoAdmin/getPersonInfoList?currentPage='
			+ currentPageLocal;
	var data = $("#submitForm").serialize();
	$.ajax({
				url : queryUserUrl,
				type : "post",
				data : data,
				cache : false,
				success : function(data) {
					if (data.state == 0) {
						var pageDivide = data.data;
						/* 判断页码的显示开始*/
						if (pageDivide.pageCount == 1) {
							$('.page-control').hide();
						}
						if (pageDivide.currentPage == 1) {
							$('#head-page').hide();
							$('#before-page').hide();
						} else {
							$('#head-page').show();
							$('#before-page').show();
						}
						if (pageDivide.currentPage == pageDivide.pageCount) {
							$("#last-page").hide();
							$('#after-page').hide();
						} else {
							$("#last-page").show();
							$('#after-page').show();
						}
						/* 判断页码的显示 结束 */
						/* 给元素赋值开始 */
						$("#rowCount").text(pageDivide.rowCount);
						$("#currentPage").text(
								pageDivide.currentPage + '/'
										+ pageDivide.pageCount);
						var tempHtml = '<tr>'
								+ '<th>用户ID</th><th>用户名称</th><th>性别</th><th>profile_img</th><th>电话</th><th>邮箱</th><th>user_type</th><th>label</th>'
								+ '<th>创建时间</th><th>最后修改时间</th><th>操作</th></tr>';
						$.map(
										pageDivide.entityList,
										function(value, index) {
											tempHtml += '<tr><td>'
													+ value.userId
													+ '</td><td>'
													+ value.userName
													+ '</td><td>'
													+ value.gender
													+ '</td><td>'
													+ value.profileImg
													+ '</td><td>'
													+ value.phone
													+ '</td><td>'
													+ value.email
													+ '</td><td>'
													+ value.userType
													+ '</td><td>'
													+ value.lable
													+ '</td><td>'
													+ format(value.createTime)
													+ '</td><td>'
													+ format(value.lastEditTime)
													+ '</td><td><a href="user_edit.html?userId='
													+ value.userId
													+ '&fyID=14458579642011" class="edit">编辑</a>/'
													+ '<a onClick="removeUser('
													+ value.userId
													+ ')">删除</a></td></tr>';
										});
						$('.table').html(tempHtml);
						/*给元素赋值结束*/
						pageCount = pageDivide.pageCount;
						currentPage = currentPageLocal;
					} else {
						alert(data.stateInfo);
					}
				}
			});
}
