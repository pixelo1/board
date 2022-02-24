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
		<span>[ <a href="/board/listPageSearch?num=${page.startPageNum - 1}">이전</a> ]</span>
	</c:if>
	
	<!-- 반복문 중간에 조건을 넣어서, select의 값이 num과 다를 경우 링크를 그대로 출력하고
		select의 값이 num과 같을 경우 링크가 아닌 굵은 글자로 출력 -->		
	<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
 	<span>
 
  	<c:if test="${select != num}">
   		<a href="/board/listPageSearch?num=${num}">${num}</a>
  	</c:if>    
  
  	<c:if test="${select == num}">
   		<b>${num}</b>
  	</c:if>
    
 		</span>
	</c:forEach>

<c:if test="${page.next}">
 <span>[ <a href="/board/listPageSearch?num=${page.endPageNum + 1}">다음</a> ]</span>
</c:if>
	

<%--무식한 페이지 번호 출력코드  
<c:forEach begin="1" end="${pageNum}" var="num">
    <span>
     <a href="/board/listPage?num=${num}">${num}</a>
  </span>
 </c:forEach>--%>
 
 <!-- 검색 기능 추가 -->
 	<div>
 		<select name="searchType">
 			<option value="title">제목</option>
 			<option value="content">내용</option>
 			<option value="title_content">제목+내용</option>
 			<option value="writer">작성자</option>
 		</select>
 		
 		<input type="text" name="keyword" />
 		
 		<button type="button" id="searchBtn">검색</button>
 		
 	</div>
 	
 </div>


<script>

	document.getElementById("searchBtn").onclick = function() {
		
		let searchType = document.getElementsByName("searchType")[0].value;
		let keyword = document.getElementsByName("keyword")[0].value;
		
		location.href = "/board/listPageSearch?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
	};
</script>

 <!-- button 태그에 id="searchBtn 추가후 아래 스크립트 추가 
 id가 searchBtn인 html 엘리먼트에 클릭 이벤트가 발생하면, function() {} 내부의 코드가 실행
 name이 searchType인 html 엘리먼트중 첫번째([0])의 값을 변수(let) searchType에 저장하고,
  name이 keyword html 엘리먼트중 첫번째([0])의 값을 변수(let) keyword에 저장합니다.

여기서 왜 첫번째([0])가 나오냐면, html 엘리먼트에 사용되는 name 속성은 2개 이상 복수로 사용할 수 있기 때문에 
document.getElementsByName() 로 데이터를 가져오려고하면 배열로 가져오기 때문에 가장 첫번째인 0번째 데이터를 가져오는겁니다.
 
 location.href = [ URL ] 는 해당 URL로 이동하는 기능입니다. 
searchType은 선택한 검색 타입, keyword는 검색어가 들어가므로, '작성자'를 선택하고 '123'을 입력했다면
 이동될 실제 URL은 /board/listPageSearch?num=1&searchType=writer&keyword=123 이 됩니다. -->



</body>
</html>