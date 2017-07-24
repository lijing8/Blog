<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录系统</title>

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
			<div class="col-xs-offset-4 col-xs-4">
				<form method="post" action="<%=request.getContextPath()%>/login?jumpto=${jumpto }">

					<div class="input-group">
						<span class="input-group-addon">用户名</span> <input type="text"
							class="form-control" id="name" name="name" placeholder="用户名">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">密码</span> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<br>
					<div class="btn-group " role="group">
						<input type="submit" class="btn btn-primary" value="登录"> <a
							class="btn btn-default"
							href="<%=request.getContextPath()%>/index.html">返回</a>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>