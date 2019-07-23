<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>无标题文档</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  
 
});

function deleteData(baseId){
	if(window.confirm("确定要删除该条数据吗？"))
		location.href="/basic/deleteDataById.action?id="+baseId;
	
}
function search(){
	//alert("1111")
	var v = $("#parentId").val()
	//alert(v)
	location.href="/basic/querybasic.action?parentId="+v;
}
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="/index">首页</a></li>
			<li><a href="#">基础数据管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click"><a href="/basic/getAddOrUpdateData.action">
						<span> <img src="/images/t01.png" />
					</span> 添加
				</a></li>
				<li><span> <select name="parentId" id="parentId">
							<option value="-1">请选择要查询的类别</option>
							<c:forEach items="${parent }" var="p">
								<option value="${p.baseId }">${p.baseName }</option>
							</c:forEach>
					</select>
				</span></li>
				<li><span onclick="search()" class="fa fa-search fa-2x"
					style="width: 60px; height: 35px;"><a
						style="margin-left: 10px; margin-bottom: 8px">查询</a></span></li>
			</ul>
		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" /></th>
					<th>编号<i class="sort"><img src="/images/px.gif" /></i></th>
					<th>数据名称</th>
					<th>父节点名称</th>
					<th>数据描述</th>
					<th colspan="3">操作</th>
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
							<td></td>
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
									<td><a
										href="/basic/getAddOrUpdateData.action?id=${small.baseId}"
										class="tablelink">修改</a>
									<td><a href="javascript:void(0)"
										onclick="deleteData(${small.baseId});"
										class="tablelink">删除</a></td>
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
