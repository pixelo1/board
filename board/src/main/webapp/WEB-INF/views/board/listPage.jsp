<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>

<body>

<div id="nav">
	<%@ include file = "../include/nav.jsp" %>
</div>

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
<!-- 링크 태그의 주소(href)는 /board/view?bno=[고유번호]가 되기 때문에, 주소의 파라미터값 컨트롤러에 전달할 수 있습니다. -->
				<td>
    				<a href="/board/view?bno=${list.bno}">${list.title}</a>
				</td>
				<td>
				<fmt:formatDate value="${list.regDate}" pattern="yyyy-mm-dd" />
				</td>
				<td>${list.writer}</td>
				<td>${list.viewCnt}</td>
				</tr>
		</c:forEach>
	
	</tbody>
</table>

<div>
<!-- 이전과 다음 링크가 조건문으로 들어가 있으며
페이지 번호를 출력하는 반복문은 페이지 시작번호부터 페이지 마지막 번호까지만 출력 .. page. 을 넣어 class에서 가져옴-->
	<c:if test="${page.prev}">
		<span>[ <a href="/board/listPage?num=${page.startPageNum - 1}">이전</a> ]</span>
	</c:if>
	
	<!-- 반복문 중간에 조건을 넣어서, select의 값이 num과 다를 경우 링크를 그대로 출력하고
		select의 값이 num과 같을 경우 링크가 아닌 굵은 글자로 출력 -->		
	<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
 	<span>
 
  	<c:if test="${select != num}">
   		<a href="/board/listPage?num=${num}">${num}</a>
  	</c:if>    
  
  	<c:if test="${select == num}">
   		<b>${num}</b>
  	</c:if>
    
 		</span>
	</c:forEach>

<c:if test="${page.next}">
 <span>[ <a href="/board/listPage?num=${page.endPageNum + 1}">다음</a> ]</span>
</c:if>
	

<%--무식한 페이지 번호 출력코드  
<c:forEach begin="1" end="${pageNum}" var="num">
    <span>
     <a href="/board/listPage?num=${num}">${num}</a>
  </span>
 </c:forEach>--%>
</div>

</body>
</html>