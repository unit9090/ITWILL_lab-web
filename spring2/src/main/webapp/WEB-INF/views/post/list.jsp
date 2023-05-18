<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
			  crossorigin="anonymous">
			  
		<title>Spring 2</title>
	</head>
	
	<body>
		<header class="text-center text-bg-primary p-3">
			<h1>포스트 목록 페이지</h1>
		</header>
		
		<nav class="navbar navbar-expand-lg bg-light">
			<ul class="nav justify-content-center">
				<li class="nav-item">
					<c:url var="mainPage" value="/"/>
					<a class="nav-link active" href="${ mainPage }">메인 페이지</a>
				</li>
				<li class="nav-item">
					<c:url var="postCreatePage" value="/post/create"/>
					<a class="nav-link active" href="${ postCreatePage }">새 포스트 작성</a>
				</li>
			</ul>
		</nav>
		
		<main>
			<div class="m-5">
				<table class="table table-sm table-bordered">
					<colgroup>
						<col width="10%">
						<col width="20%">
						<col width="40%">
						<col width="20%">
					</colgroup>
					<thead class="table-primary align-middle">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>내용</th>
							<th>작성시간</th>
						</tr>
					</thead>
					<tbody>
						<form>
							<c:forEach items="${ posts }" var="post">
								<tr>
									<td>${ post.id }</td>
									<td>
										<a href="">${ post.title }</a>
									</td>
									<td>${ post.content }</td>
									<td>${ post.created_time }</td>
								</tr>
							</c:forEach>
						</form>
					</tbody>
				</table>
			</div>
		</main>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
				crossorigin="anonymous"></script>
	</body>
</html>