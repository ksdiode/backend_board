<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
    <a class="navbar-brand" href="#"><i class="fa-solid fa-house"></i> Backend</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <!-- 좌측 메뉴 구성 -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴2</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴3</a>
            </li>
        </ul>

        <!-- 우측 메뉴 -->
        <ul class="navbar-nav ml-auto">
            <%-- 로그인 된 상태 --%>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.username" var="username" />
                <li class="nav-item">
                    <a class="nav-link" href="/member/profile">
                        <img src="/auth/avatar/${username}" class="avatar-sm"/>
                        ${username}
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link logout-link" href="#">
                        <i class="fa-solid fa-right-from-bracket"></i> 로그아웃
                    </a>
                </li>
            </sec:authorize>

            <%-- 로그아웃 된 상태 --%>
            <sec:authorize access="isAnonymous()">
                <li class="nav-item">
                    <a class="nav-link" href="/auth/login">
                        <i class="fa-solid fa-right-to-bracket"></i> 로그인
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/auth/signup">
                        <i class="fa-solid fa-user-plus"></i> 회원가입
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>

<form id="logoutForm" action="/auth/logout" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>
    $(document).ready(function() {
        $('.logout-link').click(function(e) {
            e.preventDefault();
            $('#logoutForm').submit();
        });
    });
</script>


