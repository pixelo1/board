<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>

<body>

<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
	</thead>
	
	<tbody>
	<!-- 컨트롤러 에서 받아온 데이터를 출럭하기 위해 jstl의 반복문을 이용하여 출력  -->
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.bno}</td>
				<td>${list.title}</td>
				<td>${list.regDate}</td>
				<td>${list.writer}</td>
				<td>${list.viewCnt}</td>
				</tr>
		</c:forEach>
	
	</tbody>
</table>

</body>
</html>