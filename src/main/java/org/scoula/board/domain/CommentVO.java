package org.scoula.board.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVO {
    private Long no;
    private Long bno;

    private String writer;
    private String content;
    private Date regDate;
    private Date updateDate;

    List<ReplyVO> replyList;

}
