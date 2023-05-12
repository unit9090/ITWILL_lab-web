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
		<h1>포스트 목록 페이지</h1>
		
		<nav>
			<ul>
				<%--
					상대경로에서 현재 폴더(./)의 의미: context root까지의 주소
					http://localhost:8081/post/
				--%>
				<c:url var="mainPage" value="/"></c:url>
				<li><a href="${ mainPage }">메인 페이지</a></li>
				<li>
					<c:url var="postCreate" value="/post/create"></c:url>
					<a href="${ postCreate }">새 포스트 작성</a>
				</li>
			</ul>
		</nav>
		
		<main>
			<table>
				<thead>				
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>수정 시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ posts }" var="post">
					<c:if test="${ posts == '' }">
						<tr>
							<td colspan="4">등록된 post가 없습니다.</td>
						</tr>
					</c:if>
						<tr>
							<td>${ post.id }</td>
							<td>
								<c:url value="/post/detail" var="postDetail">
									<c:param name="id" value="${ post.id }"></c:param>
								</c:url>
								<a href="${ postDetail }">${ post.title }</a>
							</td>
							<td>${ post.author }</td>
							<td>${ post.modifiedTime }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:url value="/post/search" var="searchPage"></c:url>
			<form action="${ searchPage }">
				<select name="category">
					<c:if test="${ category == 't' }">
						<option value="t" selected>제목</option>
					</c:if>
					<c:if test="${ category == 'c' }">
						<option value="c" selected>내용</option>
					</c:if>
					<c:if test="${ category == 'tc' }">
						<option value="tc" selected>제목 + 내용</option>
					</c:if>
					<c:if test="${ category == 'a' }">
						<option value="a" selected>작성자</option>
					</c:if>					
				</select>
				<input type="text" name="keyword" placeholder="검색어" value="${ keyword }" required autofocus/>
				<input type="submit" value="검색"/>
			</form>			
		</main>
	</body>
</html>