<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>

<script src="/resources/js/board.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="/resources/js/rest.js"></script>
<script src="/resources/js/comment.js"></script>

<script>
    const COMMENT_URL = '/api/board/${board.no}/comment/';
    const bno = ${board.no};		// 글번호
    const writer = '${username}';	// 작성자(로그인 유저)

    console.log(bno, writer);

    $(document).ready(function() {
        setup_comment(bno, writer);
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

<div class="text-right">
    <c:forEach var="file" items="${board.attaches}">
        <div class="attach-file-item">
            <a href="/board/download/${file.no}" class="file-link">
                <i class="fa-solid fa-floppy-disk"></i>
                ${file.filename} (${file.formatSize})<br>
            </a>
        </div>
    </c:forEach>
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
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="no" value="${board.no}"/>
</form>


<!-- 코멘트 추가 / 작성자가 아닌 경우에만 보여주기 -->
<c:if test="${username != board.writer }">
    <div class="bg-light p-2 rounded my-5">
        <div>${username == null ? '댓글을 작성하려면 먼저 로그인하세요' :
                '<i class="fa-regular fa-message"></i> 댓글 작성' }</div>
        <div>
			<textarea class="form-control new-comment-content" rows="3"
                      ${username == null ? 'disabled' : '' }></textarea>
            <div class="text-right">
                <button class="btn btn-primary btn-sm my-2 comment-add-btn"
                        ${username == null ? 'disabled' : '' } >
                <i class="fa-regular fa-comment"></i> 확인
                </button>
            </div>
        </div>
    </div>
</c:if>

<div class="my-5">
    <i class="fa-regular fa-comments"></i> 댓글 목록
    <hr>
    <div class="comment-list"></div>
</div>




<%@include file="../layouts/footer.jsp"%>
