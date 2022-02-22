<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>

<div id = "nav">
	<%@ include file = "../include/nav.jsp" %>
</div>

<!-- form 태그로 게시물을 작성하고 db에 입력할수있게 -->
<!-- + 주의점 입력 엘리먼트인 <input> 과 <textarea>의 이름(name) 속성의 값이 BoardVO와 동일해야한다 -->
<!-- 컨트롤러에 게시물 작성용 get 메서드 사용 (서버 -> 사용자)은 게시물 작성용은 안됨 사용자-> 서버로 이동 해야함 post 메서드 필요-->
<form method="post">

<label>제목</label>
<input type = "text" name = "title" /><br />

<label>작성자</label>
<input type = "text" name = "writer" /><br />

<label>내용</label>
<textarea cols = "50" rows = "5" name = "content"></textarea><br />

<button type = "submit">작성</button>
</form>

</body>
</html>