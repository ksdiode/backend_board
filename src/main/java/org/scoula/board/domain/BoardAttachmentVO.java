package org.scoula.board.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.domain.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class BoardAttachmentVO extends UploadedFile {

    public static final String UPLOAD_PATH = "c:/upload/board";

    private Long bno;

    public BoardAttachmentVO(Long bno, MultipartFile part) {
        super(UPLOAD_PATH, part);
        this.bno = bno;
    }


}
