<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../layouts/header.jsp"%>

<link rel="stylesheet" href="/resources/vendors/summernote/summernote-lite.min.css">
<script src="/resources/vendors/summernote/summernote-lite.min.js"></script>
<script src="/resources/vendors/summernote/lang/summernote-ko-KR.min.js"></script>

<script>
    $(document).ready(function() {
		$('#content').summernote({
            height: 300,				// 에디터 높이
            focus: true,				// 에디터 로딩 후 포커스 부여 여부
            lang: "ko-KR",			// 한글 설정
         });
    });
</script>

<h1 class="page-header my-4"><i class="far fa-edit"></i> 새 글쓰기</h1>

<div >
    <form:form modelAttribute="board" role="form" >

        <div class="form-group">
            <form:label path="title">제목</form:label>
            <form:input path="title" cssClass="form-control" />
            <form:errors path="title" cssClass="error"/>
        </div>

        <div class="form-group">
            <form:label path="writer">작성자</form:label>
            <form:input path="writer" cssClass="form-control" />
            <form:errors path="writer" cssClass="error"/>
        </div>

        <div class="form-group">
            <form:label path="content">내용</form:label>
            <form:textarea path="content" class="form-control"></form:textarea>
            <form:errors path="content" cssClass="error"/>
        </div>

        <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i> 확인</button>
        <button type="reset" class="btn btn-primary"><i class="fas fa-undo"></i> 취소</button>
        <a href="list" class="btn btn-primary"><i class="fas fa-list"></i> 목록</a>
    </form:form>

</div>

<%@include file="../layouts/footer.jsp"%>
