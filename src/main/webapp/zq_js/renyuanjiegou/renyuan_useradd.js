$(function($) {
var id= $("#id").val();
	if (id != '') {
		$.getJSON(ctx + "/portal/org/role/gridAllRoleJson.zq", function(data) {
			$("#role_select").append("<option value='0'>请选择...</option>");
			$.each(data, function(i, item) {
				$("#role_select").append(
						"<option value='" + item.id + "'>" + item.roleGroup
								+ "-" + item.roleName + "</option>");
			});
		});
	}

	/**
	 * 保存按钮点击事件
	 */
	$("#save").bind(
			'click',
			function() {
				if ($("#user_account").val().trim().length == 0) {
					alert("账户不允许为空！");
					return;
				} else if ($("#user_name").val().trim().length == 0) {
					alert("姓名不允许为空！");
					return;
				} else {
					$.post(ctx + "/portal/org/user/addUser.zq", {
						userAccount : $("#user_account").val(),
						userName : $("#user_name").val(),
						deptManager : $("input[name='dept_manager']").eq(0)
								.is(":checked") ? "是" : "否",
						userTel : $("#user_tel").val(),
						userFax : $("#user_fax").val(),
						userMobile : $("#user_mobile").val(),
						userMail : $("#user_mail").val(),
						userCode : $("#user_code").val(),
						roleId : $("#role_select").val(),
						userSex : $("input[name='dept_manager']").eq(0).is(
								":checked") ? "男" : "女",
						type : type,
						id : id
					}, function(data) {
						alert(data);
						parent.regreshNodeById(id);
					});

				}

			});

})