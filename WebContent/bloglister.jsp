<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博文目录</title>

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
			<!-- 左8格,内容列表 -->
			<div class="col-md-9">
				<c:if test="${!empty recommeds }">
					<div id="carousel-example-captions" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<c:forEach items="${recommeds }" var="rs" varStatus="vs">
								<li class="${vs.count==1 ? 'active' : '' }"
									data-target="#carousel-example-captions"
									data-slide-to="${vs.count-1 }"></li>
							</c:forEach>
						</ol>
						<div class="carousel-inner" role="listbox">
							<c:forEach items="${recommeds }" var="rs" varStatus="vs">
								<div class="item ${vs.count==1 ? 'active' : '' }">
									<img
										src="${empty rs.firstImgUrl ? 'img/upload/1.jpg' : rs.firstImgUrl }"
										class="carousel-img">
									<div class="carousel-caption">
										<a href="<%=request.getContextPath() %>/viewblog?id=${rs.id }"><h3
												class="carousel-text">${rs.blogTitle }</h3></a>
										<p class="carousel-text">${rs.blogAbstract }</p>
									</div>
								</div>
							</c:forEach>
						</div>
						<a class="left carousel-control" href="#carousel-example-captions"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-captions" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
				</c:if>
				<br>
				<!-- 内容布局 -->
				<div class="container-fluid">
					<c:forEach items="${result }" var="blog">

						<div class="row">
							<c:choose>
								<c:when test="${!empty  blog.firstImgUrl }">
									<div class="col-sm-3" style="vertical-align: middle;">
										<img alt="${blog.blogTitle }-图片" src="${blog.firstImgUrl }"
											class="img-rounded center-block"
											style="width: 100%; height: auto">
									</div>
									<div class="col-sm-9">
										<h3>
											<span class="label label-warning">${blog.classify.item }</span>
											<a
												href="<%=request.getContextPath() %>/viewblog?id=${blog.id }">

												${blog.blogTitle }</a>
										</h3>
										<p>${blog.blogAbstract }</p>
										<p class="text-right">
											<small> <fmt:formatDate value="${blog.blogDate}"
													type="both" timeStyle="short" dateStyle="medium" /> &nbsp;
												&nbsp; &nbsp;<span class="glyphicon glyphicon-eye-open"></span>（${blog.views }）
												&nbsp; &nbsp; &nbsp;<span
												class="glyphicon glyphicon-comment"></span>（${fn:length(blog.comments) }）
											</small>
										</p>
									</div>
								</c:when>
								<c:otherwise>
									<!-- 无图格式 -->
									<div class="col-sm-12">
										<h3>
											<span class="label label-warning">${blog.classify.item }</span>
											<a
												href="<%=request.getContextPath() %>/viewblog?id=${blog.id }">

												${blog.blogTitle }</a>
										</h3>
										<p>${blog.blogAbstract }</p>
										<p class="text-right">
											<small> <fmt:formatDate value="${blog.blogDate}"
													type="both" timeStyle="short" dateStyle="medium" /> &nbsp;
												&nbsp; &nbsp;<span class="glyphicon glyphicon-eye-open"></span>（${blog.views }）
												&nbsp; &nbsp; &nbsp;<span
												class="glyphicon glyphicon-comment"></span>（${fn:length(blog.comments) }）
											</small>
										</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<hr />
					</c:forEach>
				</div>

				<!-- 分页器 -->
				<nav>
				<ul class="pagination">
					<c:if test="${tarpage>1 }">
						<li><a
							href="<%=request.getContextPath() %>/index.html?tarpage=${tarpage-1 }&classifyid=${classifyid }">
								<span>&laquo;</span>
						</a></li>
					</c:if>
					<c:if test="${tarpage<=1 }">
						<li class="disabled"><a href="#"><span>&laquo;</span></a></li>
					</c:if>
					<c:if test="${tarpage-2>0 }">
						<li><a
							href="<%=request.getContextPath() %>/index.html?tarpage=${tarpage-2 }&classifyid=${classifyid }">${tarpage-2 }
						</a></li>
					</c:if>
					<c:if test="${tarpage-1>0 }">
						<li><a
							href="<%=request.getContextPath() %>/index.html?tarpage=${tarpage-1 }&classifyid=${classifyid }">${tarpage-1 }
						</a></li>
					</c:if>
					<li class="active"><a href="#">${tarpage }</a></li>
					<c:if test="${tarpage+1<=maxpage }">
						<li><a
							href="<%=request.getContextPath() %>/index.html?tarpage=${tarpage+1 }&classifyid=${classifyid }">${tarpage+1 }
						</a></li>
					</c:if>
					<c:if test="${tarpage+2<=maxpage }">
						<li><a
							href="<%=request.getContextPath() %>/index.html?tarpage=${tarpage+2 }&classifyid=${classifyid }">${tarpage+2 }
						</a></li>
					</c:if>
					<c:if test="${tarpage<maxpage }">
						<li><a
							href="<%=request.getContextPath() %>/index.html?tarpage=${tarpage+1 }&classifyid=${classifyid }">
								<span>&raquo;</span>
						</a></li>
					</c:if>
					<c:if test="${tarpage>=maxpage }">
						<li class="disabled"><a href="#"> <span>&raquo;</span></a></li>
					</c:if>
				</ul>
				</nav>

			</div>

			<!-- 右4 其他内容 -->
			<div class="col-md-3">
				<!-- 搜索模块 -->
				<div>
					<form action="#" id="search">
						<div class="input-group">
							<input type="text" name="searchStr" class="form-control">
							<span class="input-group-btn"> <!-- <span class="glyphicon glyphicon-search" ></span> -->
								<input type="submit" class="btn btn-default" value="搜索">
							</span>
						</div>
					</form>
				</div>
				<!-- 热门文章模块 -->
				<div>
					<h4>热门文章</h4>
					<ol>
						<c:forEach items="${top5 }" var="list">
							<li><a
								href="<%=request.getContextPath() %>/viewblog?id=${list.id }">
									${list.blogTitle }（${list.views }）</a></li>
						</c:forEach>
					</ol>
				</div>
				<br>
				<!-- 热评文章模块 -->
				<div>
					<h4>热评文章</h4>
					<ol>
						<c:forEach items="${Comm5BlogsTitle }" var="title" varStatus="vs">
							<li><a
								href="<%=request.getContextPath() %>/viewblog?id=${Comm5Blogsid[vs.count-1] }">
									${title }（${Comm5BlogsNum[vs.count-1] }）</a></li>
						</c:forEach>
					</ol>
				</div>
				<br>
				<!-- 最新评论模块 -->
				<div>
					<h4>最新评论</h4>
					<ol>
						<c:forEach items="${newcomments }" var="nc" varStatus="vs">
							<li><a
								href="<%=request.getContextPath() %>/viewblog?id=${newcommentsid[vs.count-1] }#comments">
									${nc }</a></li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</div>
		<div class="row">
			<%@ include file="footrow.jsp"%>
		</div>
	</div>
</body>
</html>