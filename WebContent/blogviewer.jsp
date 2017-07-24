<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博文详情-${blog.blogTitle }</title>

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
		<!-- 第三行 内容 -->
		<div class="row">
			<div class="col-md-10 col-md-offset-1" style="overflow: hidden;">
				<p>
				<h1>
					<span class="label label-warning">${blog.classify.item }</span>
					${blog.blogTitle }
				</h1>
				<small> <fmt:formatDate value="${blog.blogDate}" type="both"
						timeStyle="short" dateStyle="medium" /> <span
					class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>（${blog.views }）
				</small>
				</p>
				<div id="blogBody">${blog.blogBody }</div>
			</div>
		</div>

		<!-- 第4行 评论列表 -->
		<div class="row" id="comments">
			<h3>评论</h3>
			<table  class="table">
				<tbody>
					<c:forEach items="${blog.comments }"  var="com" varStatus="vs">
						<tr>
							<td style="width: 15%">
								<a href="mailto:${com.posterEMail }"><strong>${com.posterName }</strong></a><br>
								<small><fmt:formatDate value="${com.postDate}"
											type="both" timeStyle="short" dateStyle="medium" /></small>
							</td>
							<td style="width: 85%">
								<p class="text-right">
									<small>#${vs.count }</small>
								</p>
								<p>${com.comment}</p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- 第5行 新增评论 -->
		<h3>发布评论</h3>
		<div class="row" id="commentform">
			<div class="container-fluid">
				<form
					action="<%=request.getContextPath()%>/savecomment?blogid=${blog.id }"
					method="post">
					<div class="row">
						<div class="col-xs-6">
							<!-- 称呼 -->
							<div class="input-group">
								<span class="input-group-addon">称呼*</span><input name="name"
									type="text" placeholder="您的称呼" class="form-control" required maxlength="20">
							</div>
						</div>
						<div class="col-xs-6">
							<!-- email -->
							<div class="input-group">
								<span class="input-group-addon">邮箱*</span><input name="email"
									type="text" placeholder="您的Email地址" class="form-control"  maxlength="200" required pattern="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">
							</div>
						</div>
					</div>
						<br>
					<div class="row">
						<div class="col-xs-12">
							<!-- 评论内容与提交按钮 -->
							<textarea name="comment" class="form-control" required pattern=".{1-10}"></textarea>
							<small>*最多2000字</small>
							<br>
							<input type="submit" value="点击发布评论" class="btn btn-primary btn-lg btn-block">
						</div>
					</div>
				</form>
			</div>
		</div>
		<br>
<div class="row">
			<%@ include file="footrow.jsp"%>
		</div>
	</div>
</body>
</html>