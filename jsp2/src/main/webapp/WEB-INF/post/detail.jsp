<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>Post</title>
	</head>
	
	<body>
		<header>
			<h1>포스트 상세보기 페이지</h1>
		</header>
		
		
		<nav>
			<ul>
				<li>
					<c:url var="mainPage" value="/"></c:url>
					<a href="${ mainPage }">메인 페이지</a>
				</li>
				<li>
					<c:url var="postList" value="/post"></c:url>
					<a href="${ postList }">포스트 목록 페이지</a>
				</li>
				<li>
					<c:url var="postModify" value="/post/modify">
						<c:param name="id" value="${ post.id }"></c:param>
					</c:url>
					<a href="${ postModify }">포스트 수정</a>
				</li>
			</ul>
		</nav>
		
		<main>
			<!-- it's me! -->
			<%-- <c:url var="postDetail" value="/post/detail">
				<c:param name="id" value="${ post.id }"></c:param>
			</c:url>
			<form action="${ postDetail }" method="post">
				<div>
					<input type="text" name="title" placeholder="제목 입력"
						value="${ post.title }"
						required autofocus/>
				</div>
				<div>
					<textarea rows="5" cols="80" name="content" placeholder="내용 입력"
						required>${ post.content }</textarea>
				</div>
				<div>
					<input type="text" name="author" placeholder="아이디 입력"
						value="${ post.author }" required />
				</div>
				<div>
					<input type="submit" value="수정하기"/>
				</div>
			</form> --%>
			
			<form>
				<div>
					<input type="text" value="${ post.id }" readonly/>
				</div>
				<div>
					<input type="text" value="${ post.title }" readonly/>
				</div>
				<div>
					<textarea rows="5" cols="80" readonly>${ post.content }</textarea>
				</div>
				<div>
					<input type="text" value="${ post.author }" readonly/>
				</div>
				<div>
					<input type="text" value="${ post.createdTime }" readonly/>
				</div>
				<div>
					<input type="text" value="${ post.modifiedTime }" readonly/>
				</div>
			</form>
		</main>
	</body>
</html>