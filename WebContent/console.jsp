<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理</title>

<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<link href="<%=request.getContextPath()%>/favicon.ico" rel="icon"
	type="image/x-icon" />

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<!-- 自定义主题文件 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/MainCSS.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<%@ include file="header.jsp"%>
		</div>
		<div class="row">
			<!-- 使用标签页浏览 -->
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#blogs">文章管理</a></li>
				<li><a href="#recommends">推荐文章管理</a></li>
				<li><a href="#classifies">分类管理</a></li>
				<li><a href="#comments">评论管理</a></li>
				<li><a href="#password">密码管理</a></li>
			</ul>

			<!-- 文章管理页签内容 -->
			<div class="tab-content">
				<div class="tab-pane active" id="blogs">
					<h3>文章管理</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>分类</th>
								<th>标题</th>
								<th>日期</th>
								<th>编辑</th>
								<th>删除</th>
								<th>推荐</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bloglist }" var="bl" varStatus="vs">
								<tr>
									<td>${vs.count }</td>
									<td>${bl.classify.item }</td>
									<td><a
										href="<%=request.getContextPath() %>/viewblog?id=${bl.id }">${bl.blogTitle }</a></td>
									<td><fmt:formatDate value="${bl.blogDate }" type="both"
											timeStyle="short" dateStyle="medium" /></td>
									<td><a
										href="<%=request.getContextPath() %>/editblog?id=${bl.id }"><span
											class="glyphicon glyphicon-edit"></span></a></td>
									<td><a
										href="<%=request.getContextPath() %>/delblog?id=${bl.id }"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a
										href="<%=request.getContextPath() %>/addrecommend?id=${bl.id }"><span
											class="glyphicon glyphicon-arrow-up"></span></a></td>
							</c:forEach>
						</tbody>
					</table>


					<!-- 分页器 -->
					<nav>
					<ul class="pagination">
						<li><a href="<%=request.getContextPath()%>/addblog"><span
								class="glyphicon glyphicon-plus"></span>添加文章</a></li>
						<c:if test="${blogpage>1 }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage-1 }&tabid=blogs">
									<span>&laquo;</span>
							</a></li>
						</c:if>
						<c:if test="${blogpage<=1 }">
							<li class="disabled"><a href="#"><span>&laquo;</span></a></li>
						</c:if>
						<c:if test="${blogpage-2>0 }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage-2 }&tabid=blogs">${blogpage-2 }
							</a></li>
						</c:if>
						<c:if test="${blogpage-1>0 }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage-1 }&tabid=blogs">${blogpage-1 }
							</a></li>
						</c:if>
						<li class="active"><a href="#">${blogpage }</a></li>
						<c:if test="${blogpage+1<=blogmaxpage }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage+1 }&tabid=blogs">${blogpage+1 }
							</a></li>
						</c:if>
						<c:if test="${blogpage+2<=blogmaxpage }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage+2 }&tabid=blogs">${blogpage+2 }
							</a></li>
						</c:if>
						<c:if test="${blogpage<blogmaxpage }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage+1 }&tabid=blogs">
									<span>&raquo;</span>
							</a></li>
						</c:if>
						<c:if test="${blogpage>=blogmaxpage }">
							<li class="disabled"><a href="#"> <span>&raquo;</span></a></li>
						</c:if>
					</ul>
					</nav>
				</div>

				<!-- 推荐文章分类页签内容 -->
				<div class="tab-pane " id="recommends">
					<h3>推荐文章管理</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>分类</th>
								<th>标题</th>
								<th>日期</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${recommend }" var="rc" varStatus="vs">
								<tr>
									<td>${vs.count }</td>
									<td>${rc.classify.item }</td>
									<td><a
										href="<%=request.getContextPath() %>/viewblog?id=${rc.id }">${rc.blogTitle }</a></td>
									<td><fmt:formatDate value="${rc.blogDate }" type="both"
											timeStyle="short" dateStyle="medium" /></td>
									<td><a
										href="<%=request.getContextPath() %>/delrecommend?id=${rc.id }"><span
											class="glyphicon glyphicon-trash"></span></a></td>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- 文章分类页签内容 -->
				<div class="tab-pane" id="classifies">
					<h3>分类管理</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>分类</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${classifylist }" var="cl" varStatus="vs">
								<tr>
									<td>${vs.count }</td>
									<td>${cl.item }</td>
									<td><a
										href="<%=request.getContextPath() %>/delclassify?id=${cl.id }"><span
											class="glyphicon glyphicon-trash"></span></a></td>
							</c:forEach>
						</tbody>
					</table>

					<form class="form-inline" method="post"
						action="<%=request.getContextPath()%>/saveclassify">
						<div class="input-group">
							<span class="input-group-addon">新增分类*</span> <input type="text"
								class="form-control" id="item" name="item" placeholder="文章分类" required maxlength="20">
						</div>
						<input type="submit" class="btn btn-primary" value="提交">
					</form>
				</div>

				<!-- 评论管理页面 -->
				<div class="tab-pane" id="comments">
					<h3>评论管理</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>文章</th>
								<th>发布者</th>
								<th>内容</th>
								<th>评论日期</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bloglist }" var="bl" varStatus="vs1">
								<c:forEach items="${bl.comments }" var="comm" varStatus="vs2">
									<tr>
										<td>${vs1.count }-${vs2.count }</td>
										<td>${bl.blogTitle }</td>
										<td>${comm.posterName }</td>
										<td>${comm.comment }</td>
										<td><fmt:formatDate value="${comm.postDate }" type="both"
												timeStyle="short" dateStyle="medium" /></td>
										<td><a
											href="<%=request.getContextPath() %>/delcomment?id=${comm.id }&blogid=${bl.id}"><span
												class="glyphicon glyphicon-trash"></span></a></td>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>


					<!-- 分页器 -->
					<nav>
					<ul class="pagination">
						<c:if test="${blogpage>1 }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage-1 }&tabid=comments">
									<span>&laquo;</span>
							</a></li>
						</c:if>
						<c:if test="${blogpage<=1 }">
							<li class="disabled"><a href="#"><span>&laquo;</span></a></li>
						</c:if>
						<c:if test="${blogpage-2>0 }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage-2 }&tabid=comments">${blogpage-2 }
							</a></li>
						</c:if>
						<c:if test="${blogpage-1>0 }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage-1 }&tabid=comments">${blogpage-1 }
							</a></li>
						</c:if>
						<li class="active"><a href="#">${blogpage }</a></li>
						<c:if test="${blogpage+1<=blogmaxpage }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage+1 }&tabid=comments">${blogpage+1 }
							</a></li>
						</c:if>
						<c:if test="${blogpage+2<=blogmaxpage }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage+2 }&tabid=comments">${blogpage+2 }
							</a></li>
						</c:if>
						<c:if test="${blogpage<blogmaxpage }">
							<li><a
								href="<%=request.getContextPath() %>/console?blogpage=${blogpage+1 }&tabid=comments">
									<span>&raquo;</span>
							</a></li>
						</c:if>
						<c:if test="${blogpage>=blogmaxpage }">
							<li class="disabled"><a href="#"> <span>&raquo;</span></a></li>
						</c:if>
					</ul>
					</nav>

				</div>

				<!-- 密码管理页面 -->
				<div class="tab-pane" id="password">
					<div class="row">
						<div class="col-xs-offset-4 col-xs-4">
							<form method="post"
								action="<%=request.getContextPath()%>/changepassword">
								<br>
								<div class="input-group">
									<span class="input-group-addon">旧密码</span> <input
										type="password" class="form-control" id="oldpw" name="oldpw">
								</div>
								<br>
								<div class="input-group">
									<span class="input-group-addon">新密码</span> <input
										type="password" class="form-control" id="password1"
										name="password1">
								</div>
								<br>
								<div class="input-group">
									<span class="input-group-addon">新密码</span> <input
										type="password" class="form-control" id="password2"
										name="password2">
								</div>
								<br> <input type="submit" class="btn btn-primary"
									value="提交">

							</form>
						</div>
					</div>
				</div>
			</div>

			<script>
				$(function() {
					$('#myTab a[href="#${tabid }"]').tab('show');//初始化显示哪个tab

					$('#myTab a').click(function(e) {
						e.preventDefault();//阻止a链接的跳转行为
						$(this).tab('show');//显示当前选中的链接及关联的content
					});
				});
			</script>

		</div>
		<div class="row">
			<%@ include file="footrow.jsp"%>
		</div>
	</div>

</body>
</html>