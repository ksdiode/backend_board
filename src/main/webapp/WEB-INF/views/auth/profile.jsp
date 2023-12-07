<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../layouts/header.jsp" %>
<style>
.profile th {
    padding-right: 20px;
}
</style>
<h1 class="my-5"><i class="fa-solid fa-user-gear"></i> 회원정보 보기</h1>

<div class="d-flex my-3">
    <div>
        <img src="/auth/avatar/${member.username }" width="80"/>
    </div>

    <div class="ml-4">
        <table class="profile">
            <tr>
                <th>사용자 ID</th>
                <td>${member.username }</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${member.email }</td>
            </tr>
            <tr>
                <th>가입일</th>
                <td><fmt:formatDate value="${member.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
            <tr>
                <th>수정일</th>
                <td><fmt:formatDate value="${member.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </table>
        <div class="my-4">
            <a href="/auth/update" class="btn btn-primary"><i class="fa-solid fa-user-pen"></i> 개인 정보 수정</a>
            <a href="/auth/change_password" class="btn btn-primary"><i class="fa-solid fa-user-lock"></i> 비밀번호 변경</a>
        </div>
    </div>
</div>

<%@ include file="../layouts/footer.jsp" %>
