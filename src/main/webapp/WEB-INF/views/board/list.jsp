<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>

<h1 class="page-header my-4"><i class="fas fa-list"></i> 글 목록</h1>

<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 130px">등록일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.no}</td>
            <td>
                <a href="get?no=${board.no}">${board.title}</a>
            </td>
            <td>${board.writer}</td>
            <td>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>


<ul class="pagination justify-content-center">
    <c:if test="${pageMaker.cri.pageNum > 1}">
        <li class="page-item">
            <a class="page-link" href="?pageNum=1">
                <i class="fa-solid fa-backward-step"></i>
            </a>
        </li>
    </c:if>

    <c:if test="${pageMaker.prev}">
        <li class="page-item">
            <a class="page-link" href="?pageNum=${pageMaker.startPage-1}">
                <i class="fa-solid fa-angle-left"></i>
            </a>
        </li>
    </c:if>

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
        <li class="page-item ${ pageMaker.cri.pageNum == num ? 'active' : '' }">
            <a class="page-link" href="?pageNum=${num}">${num}</a>
        </li>
    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li class="page-item">
            <a class="page-link" href="?pageNum=${pageMaker.endPage+1}">
                <i class="fa-solid fa-angle-right"></i>
            </a>
        </li>
    </c:if>

    <c:if test="${pageMaker.cri.pageNum < pageMaker.totalPage}">
        <li class="page-item">
            <a class="page-link" href="?pageNum=${pageMaker.totalPage}">
                <i class="fa-solid fa-forward-step"></i>
            </a>
        </li>
    </c:if>
</ul>



    <div class="text-right">
    <a href="create" class="btn btn-primary">
        <i class="far fa-edit"></i>
        글쓰기
    </a>
</div>

<%@include file="../layouts/footer.jsp"%>
