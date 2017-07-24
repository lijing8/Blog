<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航及页头</title>
</head>
<body>
	<div class="container-fluid">
		<!-- 第1行 导航栏及题头 -->
		<div class="row header">
				<div class="col-md-1 header-logo">
					<img alt="LOGO" src="img/logo.png">
				</div>
				<div class="col-md-11 header-title">
					<h1 class="header-title">
						师门博客系统<small></small>
					</h1>
				</div>
		</div>
		<div class="row">
				<nav class="navbar navbar-inverse">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"
						href="<%=request.getContextPath()%>/index.html"><span
						class="glyphicon glyphicon-home" ></span> 首页</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<c:forEach items="${classify }" var="cf">
							<li
								<c:if test="${cf.id == classifyid }">
							class="active"
						</c:if>><a
								href="<%=request.getContextPath() %>/index.html?classifyid=${cf.id }">${cf.item }</a></li>
						</c:forEach>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a class="navbar-brand" href="<%=request.getContextPath()%>/console"><span
								class="glyphicon glyphicon-cog" ></span></a></li>
								<li><a class="navbar-brand"  href="<%=request.getContextPath() %>/rssfeed">
        <img alt="RSS订阅" src="img/rss.png" width="20" height="20">
      </a></li>
					</ul>
				</div>
				</nav>
		</div>
		<!--第2行 警告框-->
		<div class="row">
			<c:if test="${!empty pagemessage }">
				<div class="alert ${pagemessage.alertcolor } alert-dismissible"
					role="alert">
					<button type="button" class="close" data-dismiss="alert">
						<span>&times;</span>
					</button>
					${pagemessage.message }
				</div>
			</c:if>

		</div>

	</div>

</body>
</html>