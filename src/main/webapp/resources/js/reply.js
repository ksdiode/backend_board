function createReplyEditTemplate(reply) {
	return `
	<div class="bg-light p-2 rounded reply-edit-block" data-no = "${reply.no}"
       data-cno="${reply.cno}" data-writer="${reply.writer}">

		<div>${reply.no ?  '' : ' 답글 작성'}</div>

		<textarea class="form-control mb-1 reply-editor">${ reply.content || '' }</textarea>

		<div class="text-end">
			<button class="btn btn-light btn-sm py-1 ${reply.no ?  'reply-update' : 'reply-add-btn'} ">
				<i class="fa-solid fa-check"></i> 확인
      </button>
			<button class="btn btn-light btn-sm py-1 ${reply.no ?  'reply-update-cancel' : 'reply-add-cancel-btn'} ">
				<i class="fa-solid fa-undo"></i> 최소
      </button>
		</div>
	</div>
	`;
}


function showReplyAdd(e) {
	const commentEl = $(this).closest('.comment');
	const cno = commentEl.data("no");
	const reply = { cno, writer	};
	const template = createReplyEditTemplate(reply);

	commentEl.find('.reply-list').append($(template));
	commentEl.find('.btn-group').hide();

	commentEl.find('.reply-editor').focus();
}

const replyUpdatable = `
	<button class="btn btn-light btn-sm reply-update-show-btn">
		<i class="fa-solid fa-pen-to-square"></i> 수정</button>
	<button class="btn btn-light btn-sm reply-delete-btn">
		<i class="fa-solid fa-times"></i> 삭제</button>
`;

function createReplyTemplate(reply, username) {
	return `
	<div class="reply my-3" data-no="${reply.no}" data-writer="${reply.writer}">
       <div class="reply-title my-2 d-flex justify-content-between">
       		<div class="reply-head">
         		<strong class="reply-writer">
         		<img src="/auth/avatar/${reply.writer}" class="avatar-sm">
         			${reply.writer}
         		</strong>
         		<span class="text-muted ml-3 reply-date">
         			${moment(reply.regDate).format('YYYY-MM-DD hh:mm')}
         		</span>
         	</div>
         	<div class="btn-group">
            	${username && (username == reply.writer) ? replyUpdatable  : ''}
         	</div>
       </div>
       <div class="reply-body">
         <div class="reply-content">${ reply.content || '' }</div>
       </div>
	</div>
	`;
}



// 답글 추가
async function addReply() {
	// cno 추출
	const commentEl = $(this).closest('.comment');
	const replyBlock = commentEl.find('.reply-edit-block');

	const cno = parseInt(commentEl.data("no"));							// 관련 comment no 추출
	const content = replyBlock.find('.reply-editor').val(); 	// 답글 내용 추출
	let reply = { cno, writer, content };										// writer는 전역변수

	// REPLY POST API 호출
	reply  = await rest_post(REPLY_URL, reply);
	console.log(reply);

	const replyEl = $(createReplyTemplate(reply, writer));
	commentEl.find('.reply-edit-block').remove();		// 답글 인터페이스 제거
	commentEl.find('.reply-list').append(replyEl);		// 답글 내용 화면에 추가
	commentEl.find('.btn-group').show();
}

// 답글 취소
function cancelReply(e) {
	const commentEl = $(this).closest('.comment');
	commentEl.find('.reply-edit-block').remove();
	commentEl.find('.btn-group').show();
}

// 답글 수정 화면 보여주기
function showUpdateReply(e) {
	const replyEl = $(this).closest('.reply');

	const no =  replyEl.data("no");
	const content = replyEl.find('.reply-content').html();

	const reply = { no, content };
	const editor = $(createReplyEditTemplate(reply));

	replyEl.find('.reply-content').hide();
	replyEl.find('.reply-body').append(editor);
}

// 답글 수정 등록
async function updateReply(e) {
	if(!confirm('답글을 수정할까요?')) return;

	const replyEl = $(this).closest('.reply');
	const replyContent = replyEl.find('.reply-content');
	const content = replyEl.find('.reply-editor').val();

	const no = replyEl.data("no");
	let reply = { no, content };

	reply = await rest_put(REPLY_URL + no, reply);

	replyContent.html(content);
	replyContent.show();
	replyEl.find('.reply-edit-block').remove();

}


// 답글 수정 화면 취소
function cancelReplyUpdate(e) {
	const replyEl = $(this).closest('.reply');
	replyEl.find('.reply-content').show();
	replyEl.find('.reply-edit-block').remove();
}

// 답글 삭제
async function deleteReply(e) {
	if(!confirm('답글을 삭제할까요?')) return;

	const replyEl = $(this).closest('.reply');
	const no = parseInt(replyEl.data("no"));

	await rest_delete(REPLY_URL + no);
	replyEl.remove();
}


function setup_reply() {
    // 답글 처리 관련 이벤트 처리 =====================================
   	// 답글 추가 인터페이스 보이기
   	$('.comment-list').on('click', '.reply-add-show-btn', showReplyAdd);

	// 답글 등록
	$('.comment-list').on('click', '.reply-add-btn', addReply);

    // 답글 작성 취소
    $('.comment-list').on('click', '.reply-add-cancel-btn', cancelReply);

	// 답글 수정 화면 보이기
	$('.comment-list').on('click', '.reply-update-show-btn', showUpdateReply);

	// 답글 수정 등록
	$('.comment-list').on('click', '.reply-update', updateReply);

    // 답글 수정 작성 취소
    $('.comment-list').on('click', '.reply-update-cancel', cancelReplyUpdate);


	// 답글 삭제
	$('.comment-list').on('click', '.reply-delete-btn', deleteReply);


}
