<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/console/login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/chajian/carhartl-jquery-cookie-92b7715/jquery.cookie.js"></script>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/register.css"/>
<script type="text/javascript">
var ctx="<%= request.getContextPath()%>";
</script>
</head>
<body>
<div class='signup_container'>
    <h1 class='signup_title'>用户登陆</h1>
    <img src='images/people.png' id='admin'/>
    <div id="signup_forms" class="signup_forms clearfix">
            <form class="signup_form_form" >
                    <div class="form_row first_row">
                        <label for="signup_email">请输入用户名</label><div  ></div>
                        <input type="text" id="user_account" placeholder="请输入用户名"   data-required="required">
                    </div>
                    <div class="form_row">
                        <label for="signup_password">请输入密码</label><div ></div>
                        <input type="password" id="user_password" placeholder="请输入密码"  data-required="required">
                    </div>
                <!--     <div class="form_row">
                            <input type="text" name="user[password]" placeholder="" id="signup_select" value='' data-required="required">
                            <img src='images/d.png' id='d'/>
                            <ul>
                                <li>管理员</li>
                                <li>用户1</li>
                                <li>用户2</li>
                            </ul>
                    </div> -->
           </form>
    </div>

    <div class="login-btn-set"><div class='rem'>记住我</div> <a href='javascript:void(0);' class='login-btn'></a></div>
    <p class='copyright'>版权所有张钦</p>
</div>
</body>
</html>