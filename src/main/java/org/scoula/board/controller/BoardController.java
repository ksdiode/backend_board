package org.scoula.board.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {

        log.info("list");
        model.addAttribute("list", service.getList());

    }


    @GetMapping("/create")
    public void create() {
        log.info("create");
    }

    @PostMapping("/create")
    public String create(BoardVO board, RedirectAttributes ra) {

        log.info("create: " + board);

        service.create(board);

        ra.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@RequestParam("no") Long no, Model model) {

        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

}
