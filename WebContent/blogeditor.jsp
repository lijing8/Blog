<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增文章</title>

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

		<form method="post"
			action="<%=request.getContextPath()%>/saveblog?isedit=${isedit }&blogid=${oldBlog.id }">
			<div class="row">
				<div class="col-xs-2">
					<select name="classifyId" class="form-control">
						<c:forEach items="${classify }" var="cf">
							<option value="${cf.id }"
								${!empty oldBlog && oldBlog.classify.id ==cf.id  ?  'selected="selected"' : ''}>${cf.item }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-xs-10">
					<div class="input-group">
						<span class="input-group-addon">标题* </span> <input type="text"
							class="form-control" id="title" name="title"
							value="${!empty oldBlog ? oldBlog.blogTitle : ''}"
							placeholder="文章标题" required maxlength="80">
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-xs-12">
					<textarea id="textbody" name="textbody" required>${!empty oldBlog ? oldBlog.blogBody : '' }</textarea>
					<script>
						CKEDITOR.replace('textbody',{filebrowserUploadUrl : '<%=request.getContextPath()%>/ckupload?type=file',     
			                filebrowserImageUploadUrl : '<%=request.getContextPath()%>/ckupload?type=image',     
			                filebrowserFlashUploadUrl : '<%=request.getContextPath()%>/ckupload?type=flash'
										});
					</script>
					<small>*最多10000字</small>
					<br>
					<div class="btn-group " role="group">
						<input type="submit" class="btn btn-primary" value="提交"> <a
							class="btn btn-default"
							href="<%=request.getContextPath()%>/index.html">返回</a>
					</div>
				</div>
			</div>
		</form>
<div class="row">
			<%@ include file="footrow.jsp"%>
		</div>
	</div>

</body>
</html>