package org.scoula.board.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public void create(@ModelAttribute("board") BoardVO board) {
        log.info("create");
    }



    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("board") BoardVO board,
                         Errors errors,
                         RedirectAttributes ra) {

        log.info("create: " + board);

        if(errors.hasErrors()) {
            return "board/create";
        }

        service.create(board);

        ra.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@RequestParam("no") Long no, Model model) {

        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("board") BoardVO board,
                         Errors errors,
                         RedirectAttributes ra) {
        log.info("update:" + board);

        if(errors.hasErrors()) {
            return "board/update";
        }

        if (service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no, RedirectAttributes ra) {

        log.info("delete..." + no);
        if (service.delete(no)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

}
