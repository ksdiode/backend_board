<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>

<script>
    $(document).ready(async function() {
        $('.delete').click(function(){
            if(!confirm('정말 삭제할까요?')) return;
            $('#deleteForm').submit();
        });
    });
</script>

<h1 class="page-header my-4"><i class="far fa-file-alt"></i> ${board.title}</h1>

<div class="d-flex justify-content-between">
    <div><i class="fas fa-user"></i> ${board.writer}</div>
    <div>
        <i class="fas fa-clock"></i>
        <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
    </div>
</div>

<hr>

<div>
    ${board.content}
</div>

<div class="mt-4">
    <a href="${cri.getLink('list')}" class="btn btn-primary"><i class="fas fa-list"></i> 목록</a>

    <c:if test="${username == board.writer }">
        <a href="${cri.getLink('update', board.no)}" class="btn btn-primary"><i class="far fa-edit"></i> 수정</a>
        <a href="#" class="delete btn btn-primary"><i class="fas fa-trash-alt"></i> 삭제</a>
    </c:if>
</div>


<form action="${cri.getLink('delete')}" method="post" id="deleteForm">
    <input type="hidden" name="no" value="${board.no}"/>
</form>


<%@include file="../layouts/footer.jsp"%>
