<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>

<div style="width: 500px" class="mx-auto">
	<h1 class="my-5"><i class="fa-solid fa-user-lock"></i> 비밀번호 변경</h1>

	<div>
		<form:form modelAttribute="member" action="/auth/change_password?_csrf=${_csrf.token}">
			<div class="form-group">
				<form:label path="username"><i class="fa-solid fa-user"></i> 사용자 ID:</form:label>
				<form:input path="username" cssClass="form-control-plaintext" disabled="true"/>
			</div>

			<div class="form-group">
				<form:label path="oldPassword"><i class="fa-solid fa-lock"></i> 기존 비밀번호:</form:label>
				<form:password path="oldPassword" class="form-control" />
				<form:errors path="oldPassword" cssClass="error"/>
			</div>
			<div class="form-group">
				<form:label path="password"><i class="fa-solid fa-lock"></i> 새 비밀번호:</form:label>
				<form:password path="password" class="form-control" />
				<form:errors path="password" cssClass="error"/>
			</div>
			<div class="form-group">
				<form:label path="password2"><i class="fa-solid fa-lock"></i> 새 비밀번호 확인:</form:label>
				<form:password path="password2" class="form-control" />
				<form:errors path="password2" cssClass="error"/>
			</div>

			<div class="text-center mt-3">
				<button type="submit" class="btn btn-primary">
					<i class="fa-solid fa-user-lock"></i> 비밀번호 변경
				</button>
			</div>
		</form:form>
	</div>
</div>	
<%@ include file="../layouts/footer.jsp" %>
