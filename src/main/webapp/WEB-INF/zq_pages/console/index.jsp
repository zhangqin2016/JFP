<%@page import="com.kspt.portal.login.OnlineConstants"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/chajian/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/index/index.js"></script>
<title>首页</title>
 
</head>
<body>
	<script type="text/javascript">
var ctx="<%=request.getContextPath()%>";
	</script>
	<div class="container-fluid">
		<div class="preview-box"
			style="display: none; position: absolute; top: 150px;">
			<div style=" overflow: hidden;">
				<img id="preview-img" style="position: absolute;" alt="加载中..."
					src="">
			</div>
		</div>
	<%-- 	<div class="row" id='beautifulGirl' >
			<div
				style="float: left; margin-left: 5%; height: 150px; width: 14%; padding-top: 3px;">
				<img name="avls" title="都是美女，挑一个吧" alt="都是美女，挑一个吧" style="height: 150px; width: 100%;"
					src="<%=request.getContextPath()%>/guzhuang/a (1).JPG" />
			</div>
			<div
				style="float: left; margin-left: 5%; height: 150px; width: 14%; padding-top: 3px;">
				<img name="avls" title="都是美女，挑一个吧" alt="都是美女，挑一个吧" style="height: 150px; width: 100%;"
					src="<%=request.getContextPath()%>/guzhuang/a (166).jpg" />
			</div>
			<div
				style="float: left; margin-left: 5%; height: 150px; width: 14%; padding-top: 3px;">
				<img name="avls" title="都是美女，挑一个吧" alt="都是美女，挑一个吧" style="height: 150px; width: 100%;"
				src="<%=request.getContextPath()%>/guzhuang/a (136).JPG" />
			</div>
			<div
				style="float: left; margin-left: 5%; height: 150px; width: 14%; padding-top: 3px;">
				<img name="avls" title="都是美女，挑一个吧" alt="都是美女，挑一个吧" style="height: 150px; width: 100%;"
					src="<%=request.getContextPath()%>/guzhuang/a (162).jpg" />
			</div>
			<div
				style="float: left; margin-left: 5%; height: 150px; width: 14%; padding-top: 3px;">
				<img name="avls" type="left" title="都是美女，挑一个吧" alt="都是美女，挑一个吧"
					style="height: 150px; width: 100%;"
					src="<%=request.getContextPath()%>/guzhuang/a (161).JPG" />
			</div>
					<div style="position: absolute;margin-top: 2px;float: right;">
 	<object type="application/x-shockwave-flash"  data="<%=request.getContextPath()%>/music/dewplayer-rect.swf" width="240" height="20" id="dewplayer-rect">
   	<param name="flashvars" value="mp3=<%=request.getContextPath()%>/music/1.mp3|<%=request.getContextPath()%>/music/2.mp3&autoplay=0&autoreplay=1" />
   	</object>
				</div>
		</div> --%>
		<div class="row" id='mainDivId'>
			<ul class="nav nav-tabs">
				<li name='caidan' class="active" url="/music/music.jsp"><a href="#" >首页</a></li>
				<li name='caidan' url='/moxingku/main.zq'><a href="#">业务</a></li>
				<li name='caidan' url='/portal/navication.zq'><a href="#">前台菜单</a>
				</li>
				<li name='caidan' url='/portal/org.zq'><a href="#">人员</a></li>
				<li name='caidan'><a href="#">菜单授权</a></li>
				<li class="pull-right"><a href="#" id='outSystem'><span
						class="glyphicon glyphicon-off"></span> 退出系统</a></li>
			   <li class="pull-right"><a href="#" ><span class="glyphicon glyphicon-user"></span>  ${user_name}</a></li>
			     <li class="pull-right"><a href="#" title="点击畅聊" id='liaoId' >系统在线人数: <%=OnlineConstants.getUser().size() %></a></li>
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle">皮肤<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li col="#BEBEBE" img="1.png"><a href="#">皮肤1</a></li>
						<li col="#FEFEFE" img="2.jpg"><a href="#">皮肤2</a></li>
						<li col="#DBEAF9" img="3.jpg"><a href="#">皮肤3</a></li>
						<li class="divider"></li>
						<li col="#0E0D0B" img="4.png"><a href="#">皮肤4</a></li>
					</ul></li>
			</ul>
				<iframe name="consoleContent" id="consoleContent" frameborder="0"
			frameborder="0" width="100%" src="<%=request.getContextPath()%>/music/music.jsp"> </iframe>
		</div>
	</div>
<input type="hidden" id="user_account" value="${user_account}">
</body>
</html>