package org.scoula.board.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.service.BoardService;
import org.scoula.domain.Criteria;
import org.scoula.domain.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;


    private Map<String, String> getSearchTypes() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("", "-- 검색대상선택 --");
        map.put("T", "제목");
        map.put("W", "작성자");
        map.put("C", "내용");
        map.put("TC", "제목+내용");
        map.put("TW", "제목+작성자");
        map.put("TWC", "제목+작성자+내용");

        return map;
    }

    @GetMapping("/list")
    public void list(@ModelAttribute("cri") Criteria cri, Model model) {
        log.info("list: " + cri);
        model.addAttribute("searchTypes", getSearchTypes());
        model.addAttribute("list", service.getList(cri));

        int total = service.getTotal(cri);
        log.info("total: " + total);

    //  model.addAttribute("pageMaker", new PageDTO(cri, 123);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }


    @GetMapping("/create")
    public void create(@ModelAttribute("board") BoardVO board) {
        log.info("create");
    }



    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("board") BoardVO board, Errors errors,
                         List<MultipartFile> files, RedirectAttributes ra, Principal principal) {
        String username = principal.getName();
        board.setWriter(username);

        log.info("create: " + board);

        if(errors.hasErrors()) {
            log.error(errors);
            return "board/create";
        }

        service.create(board, files);

        ra.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@ModelAttribute("cri") Criteria cri, @RequestParam("no") Long no, Model model) {

        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("cri") Criteria cri,
                         @Valid @ModelAttribute("board") BoardVO board, Errors errors,
                         List<MultipartFile> files, RedirectAttributes ra) {
        log.info("update:" + board);

        if(errors.hasErrors()) {
            return "board/update";
        }

        if (service.update(board, files)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:" + cri.getLink("/board/get", board.getNo());
    }


    @PostMapping("/delete")
    public String delete(@ModelAttribute("cri") Criteria cri, @RequestParam("no") Long no, RedirectAttributes ra) {

        log.info("delete..." + no);
        if (service.delete(no)) {
            ra.addFlashAttribute("result", "success");
        }

        return "redirect:" + cri.getLink("/board/list");
    }

    @GetMapping("/download/{no}")
    @ResponseBody	// view를 사용하지 않고, 직접 내보냄
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {

        BoardAttachmentVO attach = service.getAttachment(no);
        attach.download(response);

    }

}
