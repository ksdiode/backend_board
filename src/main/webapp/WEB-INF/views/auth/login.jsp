<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>

<div style="width: 500px" class="mx-auto">
    <h1 class="my-5"> <i class="fa-solid fa-right-to-bracket"></i> 로그인 </h1>

    <form action="/auth/login" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="form-group">
            <label for="username"><i class="fa-solid fa-user"></i> 사용자 ID:</label>
            <input type="text" name="username" id="username" class="form-control" />
        </div>

        <div class="form-group">
            <label for="password"><i class="fa-solid fa-lock"></i> 비밀번호:</label>
            <input type="password" name="password" id="password" class="form-control" />
        </div>

        <button type="submit" class="btn btn-primary btn-block">
            <i class="fa-solid fa-right-to-bracket"></i> 로그인
        </button>

    </form>
</div>

<%@include file="../layouts/footer.jsp"%>
