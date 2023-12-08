$(document).ready(function() {
    // 게시글 삭제 버튼
    $('.delete').click(function(){
        if(!confirm('정말 삭제할까요?')) return;
        $('#deleteForm').submit();
    });

    // summernote 설정
    if($.summernote) {
        $('#content').summernote({
            height: 300,			// 에디터 높이
            focus: true,			// 에디터 로딩 후 포커스 부여 여부
            lang: "ko-KR",			// 한글 설정
        });
    }

    // 선택한 첨부파일 정보 출력
    const files = $('[name="files"]');
    const attachList = $('#attach-list');

    files.change(function (e) {
        let fileList = '';
        for(let file of this.files) {
            let fileStr = `<div><i class="fa-solid fa-file"></i> ${file.name}(${file.size.formatBytes()})</div>`;
            fileList += fileStr;
        }
        console.log(fileList)
        attachList.html(fileList);
    });

    // 첨부 파일 삭제
    $('.delete-attachment').click(function(e) {
        if(!confirm('파일을 삭제할까요?')) return;
        // ajax로 파일 삭제 요청
    });

});