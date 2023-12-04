package org.scoula.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
