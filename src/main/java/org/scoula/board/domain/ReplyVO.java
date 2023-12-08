package org.scoula.board.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyVO {
    private Long no;
    private Long cno;		// Comment의 no

    private String writer;
    private String content;
    private Date regDate;
    private Date updateDate;

}
