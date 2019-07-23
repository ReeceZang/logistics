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
<script type="text/javascript" src="/js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	  
 
});


function search(){
	//alert("1111")
	var v = $("#parentId").val()
	//alert(v)
	location.href="/basic/querybasicByParent.action?parentId="+v;
}
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
	    <div class="tools">
	    
	    	<ul class="toolbar">
	        <li>
	        	<span>
	        		<select name="parentId" id="parentId">
	        			<option value="-1">请选择要查询的类别</option>
	        			<c:forEach items="${parent }" var="p">
	        				<option value="${p.baseId }">${p.baseName }</option>
	        			</c:forEach>
	        		</select>
	        	</span>
	        	</li>
	        	<li><span onclick="search()"><img src="/images/t04.png" /></span>查询</li>
	        </ul>
	    </div>
	    
	    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
	        <th><input name="" type="checkbox" value="" /></th>
	        <th>编号<i class="sort"><img src="/images/px.gif" /></i></th>
	        <th>数据名称</th>
	        <th>数据描述</th>
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	       
	        
	        <c:forEach items="${child }" var="children">
	        	<c:if test="${empty children.parentId }">
			        <tr>
				        <td><input name="" type="checkbox" value="" /></td>
				        <td>${children.baseId }</td>
				        <td>${children.baseName }</td>
				        <td>${children.baseDesc }</td>
				        <td></td>
			        </tr>
			        <!-- 再循环一次获取 该大类对应的所有的小类 -->
			        <c:forEach items="${child }" var="small">
			        	<c:if test="${small.parentId eq children.baseId }">
			        		<tr>
						        <td><input name="" type="checkbox" value="" /></td>
						        <td style="padding-left: 40px;">${small.baseId }</td>
						        <td style="padding-left: 40px;">${small.baseName }</td>
						        <td>${children.baseName }</td>
						        <td>${small.baseDesc }</td>
			        		</tr>
			        	</c:if>
			        </c:forEach>
		        </c:if>
	        </c:forEach>       
	        </tbody>
	    </table>  
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>
</html>
