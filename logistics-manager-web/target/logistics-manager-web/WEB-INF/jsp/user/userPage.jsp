<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>无标题文档</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="/js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/index">首页</a></li>
    <li><a href="#">用户管理</a></li>

    </ul>
    </div>
    
    <div class="rightinfo">
    
	    <div class="tools">
	    
	    	<ul class="toolbar">
	        <li class="click">
	        	<a href="/user/getAddOrUpdateInfo.action">
			        <span>
			        	<img src="/images/t01.png" />
			        </span>
		        	添加
	        	</a>
	        	</li>
	        <li class="click"><span><img src="/images/t02.png" /></span>修改</li>
	        <li><span><img src="/images/t03.png" /></span>删除</li>
	        <li><span><img src="/images/t04.png" /></span>统计</li>
	        </ul>
	        
	        
	        <ul class="toolbar1">
	        <li><span><img src="/images/t05.png" /></span>设置</li>
	        </ul>
	    
	    </div>
	    
	    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
	        <th><input name="" type="checkbox" value="" /></th>
	        <th>编号<i class="sort"><img src="/images/px.gif" /></i></th>
	        <th>用户名</th>
	        <th>真实姓名</th>
	        <th>电子邮箱</th>
	        <th>手机号</th>
	        <th colspan="2">操作</th>
	        </tr>
	        </thead>
	        <tbody>
		        <c:forEach items="${pageModel.list}" var="users">
			        <tr>
				        <td><input name="" type="checkbox" value="" /></td>
				        <td>${users.userId }</td>
				        <td>${users.userName }</td>
				        
				        <td>${users.realName }</td> 
				        <td>${users.email }</td> 
				        <td>${users.phone }</td> 
				        <td><a href="/user/getAddOrUpdateInfo.action?userId=${users.userId}" class="tablelink">修改</a>       
				        <td><a href="javascript:void(0)" onclick="deleteUserAndRole(${users.userId});" class="tablelink">删除</a></td>
			        </tr>
		        </c:forEach>  
	        </tbody>
	    </table>
			<div class="inline pull-right page" style="margin-top: 20px;">
			    <form action="/user/userPage.action" id="pager">
			        <input type="hidden" name="pageSize" id="pageSize" value="${pageModel.pageSize }">
			        <input type="hidden" name="pageNum" id="pageNum" value="${pageModel.pageNum }">
			    </form>
			    <jsp:include page="/pageBar.jsp"></jsp:include>
			</div>
	    
	    <div class="tip">
	    	<div class="tiptop"><span>提示信息</span><a></a></div>
	        
	      <div class="tipinfo">
	        <span><img src="/images/ticon.png" /></span>
	        <div class="tipright">
	        <p>是否确认对信息的修改 ？</p>
	        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
	        </div>
	        </div>
	        
	        <div class="tipbtn">
	        <input name="" type="button"  class="sure" value="确定" />&nbsp;
	        <input name="" type="button"  class="cancel" value="取消" />
	        </div>
	    
	    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	function deleteUserAndRole(userId){
		if(window.confirm("确认要删除此用户吗？"))
		location.href="/user/deleteUser.action?userId="+userId;
		
	}
	</script>

</body>
</html>
