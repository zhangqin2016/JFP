<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<meta name="keywords" content="前端js在线聊天" />
<meta name="description" content="前端js在线聊天" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/chajian/chat/css/chat.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/chajian/kindeditor/kindeditor-all.js"></script>

<script type="text/javascript">
var ctx="<%= request.getContextPath()%>";
var edit;
KindEditor.ready(function(K) {
	edit=K.create('textarea[name="sendmsg"]', {
		id:'editor_id',
		cssPath : ctx+'/chajian/kindeditor/plugins/code/prettify.css',
		uploadJson : ctx+'/zq/common/kinderEditor/upload.zq',
		fileManagerJson : ctx+'/zq/common/kinderEditor/fileJson.zq',
		filterMode : false,
		allowFileManager : true,
		resizeType : 0,
		allowPreviewEmoticons : false,
		items : [
			 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'emoticons', 'image','multiimage', 'link']
	});
});
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/chajian/chat/js/liaotian.js"></script>
</head>
<body>

	<div class="content">
		<div class="chatBox">
			<div class="chatLeft">
				<div class="chat01">
					<div class="chat01_title">
						<ul class="talkTo">
							<li><a href="javascript:;">好好聊吧</a></li>
						</ul>
						<a class="close_btn" href="javascript:;"></a>
					</div>
					<div class="chat01_content">
				<!--	<div class="message_box mes3" style="display: block;">
						<div class="message clearfix">
						<div class="user-logo">
						<img src="img/head/2024.jpg"/>
						</div><div class="wrap-text">
						<h5 class="clearfix">张飞</h5>
						<div>asdas</div></div>
						<div class="wrap-ri">
						<div class="clearfix">
						<span>2015-1-15  16:20:54</span>
						</div></div><div style="clear: both;">
						</div></div>
						</div> -->
				
					</div>
					<div id="divid"></div>
				</div>
				<div class="chat02">
					<div class="chat02_content">
						<textarea id="sendmsg" name="sendmsg" style="height: 100%;width: 100%"></textarea>
					</div>
					<div class="chat02_bar">
						<ul>
							<li style="right: 5px; top: 5px;"><a href="javascript:;" id='sendBtn'>
									<img src="img/send_btn.jpg"/>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="chatRight">
				<div class="chat03">
					<div class="chat03_title">
						<label class="chat03_title_t">扯蛋成员</label>
					</div>
					<div class="chat03_content">
						<ul id='user'>
						</ul>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>
</body>
</html>