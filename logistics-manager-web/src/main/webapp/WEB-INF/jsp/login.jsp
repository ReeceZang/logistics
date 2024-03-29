<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<script language="JavaScript" src="/js/jquery.js"></script>
<script src="/js/cloud.js" type="text/javascript"></script>
<script language="javascript">
    $(function() {
        $('.loginbox').css({
            'position' : 'absolute',
            'left' : ($(window).width() - 692) / 2
        });
        $(window).resize(function() {
            $('.loginbox').css({
                'position' : 'absolute',
                'left' : ($(window).width() - 692) / 2
            });
        })
    });
</script>
</head>

<body
    style="background-color: #1c77ac; background-image: url(/images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
    <div id="mainBody">
        <div id="cloud1" class="cloud"></div>
        <div id="cloud2" class="cloud"></div>
    </div>

    <div class="logintop">
        <span>欢迎登录后台管理界面平台</span>
        <ul>
            <li><a href="#">回首页</a></li>
            <li><a href="#">帮助</a></li>
            <li><a href="#">关于</a></li>
        </ul>
    </div>

    <div class="loginbody">
        <span class="systemlogo"></span>
        <div class="loginbox">
            <form action="/login.action" method="post">
                <ul>
                    <li><input name="username" type="text" class="loginuser" />
                    </li>
                    <li><input name="password" type="password" class="loginpwd" />
                    </li>
                    <li><input name="" type="submit" class="loginbtn" value="登录"/>
                        <label>
                            <input name="" type="checkbox" value=""  />记住密码
                        </label>
                        <label>
                        	<font color="red">${msg}</font>
                        </label>
                    </li>
                </ul>
            </form>
        </div>
    </div>

    <div style="display: none">
        <script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
            language='JavaScript' charset='gb2312'></script>
    </div>
</body>
</html>
