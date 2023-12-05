<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>

<link rel="stylesheet" href="/resources/vendors/summernote/summernote-lite.min.css">
<script src="/resources/vendors/summernote/summernote-lite.min.js"></script>
<script src="/resources/vendors/summernote/lang/summernote-ko-KR.min.js"></script>

<script>
    $(document).ready(function() {
		$('#summernote').summernote({
            height: 300,				// 에디터 높이
            focus: true,				// 에디터 로딩 후 포커스 부여 여부
            lang: "ko-KR",			// 한글 설정
         });

    });
</script>

<h1 class="page-header my-4"><i class="far fa-edit"></i> 글 수정</h1>

<div>
    <form role="form" method="post" >
        <input type="hidden" name="no" value="${board.no}">
        <div class="form-group">
            <label>제목</label>
            <input name="title" class="form-control" value="${board.title}">
        </div>

        <div class="form-group">
            <label>작성자</label>
            <input name="writer" class="form-control" value="${board.writer}">
        </div>

        <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" id="summernote" name="content" rows="10">${board.content}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">
            <i class="fas fa-check"></i> 확인</button>
        <button type="reset" class="btn btn-primary">
            <i class="fas fa-undo"></i> 취소</button>
        <a href="get?no=${board.no}" class="btn btn-primary">
            <i class="fas fa-file-alt"></i> 돌아가기</a>
    </form>
</div>

<%@include file="../layouts/footer.jsp"%>
