<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<div class="container-fluid">
			<header class="my-2 p-5 text-center text-bg-primary">
				<h1>포스트 상세 보기</h1>
			</header>
			
			<nav class="navbar navbar-expand-lg bg-light">
				<ul class="nav justify-content-center">
					<li class="nav-item">
						<c:url var="mainPage" value="/"/>
						<a class="nav-link" href="${ mainPage }">메인 페이지</a>
					</li>
					<li class="nav-item">
						<c:url var="postListPage" value="/post/list"/>
						<a class="nav-link" href="${ postListPage }">포스트 목록</a>
					</li>
				</ul>
			</nav>
			
			<main class="my-2">
				<section class="card">
					<form class="card-body">
						<div>
							<label class="form-label" for="id">번호</label>
							<input class="form-control" id="id" value=${ post.id } readonly/>
						</div>
						<div>
							<label class="form-label" for="title">제목</label>
							<input class="form-control" id="title" value="${ post.title }" readonly/>
						</div>
						<div class="my-2">
							<label class="form-label" for="content">내용</label>
							<textarea class="form-control" id="content" readonly>${ post.content }</textarea>
						</div>
						<div class="my-2">
							<label class="form-label" for="author">작성자 아이디</label>
							<input class="form-control" id="author" value="${ post.author }" readonly/>
						</div>
						<div class="my-2">
							<label class="form-label" for="createdTime">작성 시간</label>
							<fmt:formatDate value="${ post.createdTime }" pattern="yyyy-MM-dd HH:mm" var="created"/>
							<input class="form-control" id="createdTime" value="${ created }" readonly/>
						</div>
						<div class="my-2">
							<label class="form-label" for="modifiedTime">수정 시간</label>
							<fmt:formatDate value="${ post.modifiedTime }" pattern="yyyy-MM-dd HH:mm" var="modified"/>
							<input class="form-control" id="modifiedTime" value="${ modified }" readonly/>
						</div>
					</form>
					<div class="card-footer">
						<c:url var="postModifyPage" value="/post/modify">
							<c:param name="id" value="${ post.id }"></c:param>
						</c:url>
						<a class="btn btn-outline-primary form-control" href="${ postModifyPage }">수정하기</a>
					</div>
				</section>	<!-- 포스트 상세보기 카드 -->
				
				<section class="my-2 card">
					<div class="card-header">
						<span>댓글</span><span id="replyCount">${ post.replyCount }</span>개	<!-- TODO: 실제 댓글 개수 -->
						<button id="btnToggleReply" class="btn" data-toggle="toggle-off">보이기</button>
					</div>
					<div id="replyToggleDiv" class="card-body collapse">
						<!-- 내 댓글 등록 -->
						<div class="row my-2">
							<label class="form-label" for="replyText">나의 댓글</label>
							<div class="col-10">
								<textarea class="form-control" id="replyText"></textarea>
								<input class="d-none" id="writer" value="admin"/>	<!-- TODO: 로그인 사용자 아이디 -->
							</div>
							<div class="col-2">
								<button id="btnAddReply" class="form-control btn btn-outline-success">등록</button>
							</div>
						</div>
						
						<!-- 댓글 목록 보여줄 영역 -->
						<div class="row my-2" id="replies">
							댓글 목록
							
						</div>
					</div>
				</section>	<!-- 댓글 등록, 댓글 리스트 카드 -->
			</main>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
					crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
			<script type="text/javascript" src="../static/js/reply.js"></script>			
		</div>
	</body>
</html>