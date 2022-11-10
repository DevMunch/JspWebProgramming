<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<fmt:setLocale value="${param.lang }" scope="session" />
<fmt:bundle basename="messageSource">
<center>
<!-- 검색 시작 -->
<form action="/index.jsp" method="post">
	<table border="1" cellpadding="0" cellspacing="0" width="700">
	<tr>
		<td align="right">
		<select name="searchCondition">
		<option value="TITLE" 
			<c:if test="${condition == 'TITLE' }">selected</c:if>>
			<fmt:message key="board.list.condition.title"/>
		<option value="CONTENT" 
			<c:if test="${condition == 'CONTENT' }">selected</c:if>>
			<fmt:message key="board.list.condition.content"/>
		</select>
		<input name="searchKeyword" type="text" value="${keyword }"/>
		<input type="submit" value="<fmt:message key="board.list.condition.btn"/>"/>
		</td>
	</tr>
	</table>
</form>
<!-- 검색 종료 -->

<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">
		<fmt:message key="board.list.head.seq"/></th>
	<th bgcolor="orange" width="200">
		<fmt:message key="board.list.head.title"/></th>
	<th bgcolor="orange" width="150">
		<fmt:message key="board.list.head.writer"/></th>
	<th bgcolor="orange" width="150">
		<fmt:message key="board.list.head.regDate"/></th>
	<th bgcolor="orange" width="100">
		<fmt:message key="board.list.head.cnt"/></th>
</tr>

<c:forEach var="board" items="${boardList }">
<tr>
	<td>${board.seq }</td>
	<td align="left"><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
	<td>${board.writer }</td>
	<td>${board.regDate }</td>
	<td>${board.cnt }</td>
</tr>
</c:forEach>

</table>
</center>
</fmt:bundle>

<%@ include file="../layout/footer.jsp" %>
 
