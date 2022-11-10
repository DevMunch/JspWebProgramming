<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<fmt:bundle basename="messageSource">
<center>
<form action="login.do" method="post">
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="orange">
		<fmt:message key="user.login.id"/></td>
		<td><input type="text" name="id"/></td>
	</tr>
	<tr>
		<td bgcolor="orange">
		<fmt:message key="user.login.password"/></td>
		<td><input type="password" name="password"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" 
			value="<fmt:message key="user.login.loginBtn"/>"/>
		</td>
	</tr>
</table>
</form>
</center>
</fmt:bundle>

<%@ include file="../layout/footer.jsp" %>