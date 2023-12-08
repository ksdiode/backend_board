package org.scoula.board.service;

import java.util.List;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.domain.Criteria;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
    public int getTotal(Criteria cri);

    public List<BoardVO> getList(Criteria cri);

    public BoardVO get(Long no);

    public void create(BoardVO board, List<MultipartFile> files);

    public boolean update(BoardVO board, List<MultipartFile> files);

    public boolean delete(Long no);

    public BoardAttachmentVO getAttachment(Long no);

    public boolean deleteAttachment(Long no);

}
