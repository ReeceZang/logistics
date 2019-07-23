<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/index">首页</a></li>
    <li><a href="#">修改用户信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
	    <form action="/user/saveOrUpdate.action" method="post">
	    	<input type="hidden" name="user.userId" value="${userDto.user.userId}">
		    <ul class="forminfo">
		    <li><label>账号</label><input name="user.userName" type="text" class="dfinput"  value="${userDto.user.userName}"/></li>
		    <li><label>姓名</label><input name="user.realName" type="text" class="dfinput" value="${userDto.user.realName}"/></li>
		    <c:if test="${empty userDto }">
			    <li><label>密码</label><input name="user.password" type="text" class="dfinput"/></li>		    
		    </c:if>
		    <li><label>电话</label><input name="user.phone" type="text" class="dfinput" value="${userDto.user.phone}"/></li>
		    <li><label>邮箱</label><input name="user.email" type="text" class="dfinput" value="${userDto.user.email}"/></li>
		    <li><label>分配角色</label>
		    
		    	<cite>
		    		<c:forEach items="${roles}" var="r">
		    		<c:set var ="flag" value="false"/>
		    		<c:forEach items="${userDto.roles}" var="dto">
		    			<!-- 取出用户具有的每一个角色 -->
		    			<c:if test="${dto.roleId eq r.roleId}">
		    				<c:set var="flag" value="true"/>
		    			</c:if>
		    		</c:forEach>
		    			<input name="roIds" type="checkbox" value="${r.roleId }" ${flag?'checked':''}/>
		    				${r.roleName }&nbsp;&nbsp;&nbsp;&nbsp;
		    		</c:forEach>
		    	</cite>
		    </li> 
		    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
		    </ul>	    
	    </form>  
    </div>
</body>
</html>