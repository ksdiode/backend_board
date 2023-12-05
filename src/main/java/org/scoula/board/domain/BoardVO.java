package org.scoula.board.domain;

import java.util.Date;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardVO {

    private Long no;

    @NotBlank(message="제목은 필수 항목입니다.")
    private String title;

    @NotBlank(message="내용은 필수 항목입니다.")
    private String content;

    @NotBlank(message="작성자는 필수 항목입니다.")
    private String writer;

    private Date regDate;
    private Date updateDate;
}
