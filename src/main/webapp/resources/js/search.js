$(document).ready(function() {
    let searchForm = $('#searchForm');
    $('#searchForm button').on('click', function(e) {
        if(!searchForm.find('option:selected').val()) {
            alert('검색종류를 선택하세요');
            return false;
        }

        if(!searchForm.find('input[name="keyword"]').val()) {
            alert('키워드를 입력하세요');
            return false;
        }

        searchForm.find('input[name="pageNum"]').val('1');
        e.preventDefault();

        searchForm.submit();
    });
});