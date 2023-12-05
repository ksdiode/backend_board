<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../layouts/header.jsp" %>

<script>
    $(function(){
		$('#logout-btn').click( function() {
			$('#logoutForm').submit();
		});
    });
</script>

<h1>환영합니다.</h1>
<ul>
    <li><a href="/test/all">all</a></li>
    <li><a href="/test/admin">admin</a></li>
    <li><a href="/test/member">member</a></li>
</ul>

<sec:authorize access="isAnonymous()">
    <a href="/auth/login">로그인</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username"/>
    <a href="#" id="logout-btn">로그아웃</a>
    <form action="/auth/logout" name="logoutForm" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</sec:authorize>


<%@ include file="../layouts/footer.jsp" %>

