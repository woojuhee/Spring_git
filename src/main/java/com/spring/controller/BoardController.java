package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping("/register")
	public void registerGet() {
		log.info("게시글 등록 폼 요청...");
	}

	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rttr) throws Exception {
		// register.jsp에서 넘긴 값 가져와서 로그로 출력
		log.info("게시글 등록하기...." + vo);
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:list";
	}

	// /board/list => controller 작성
	// DB작업 (전체리스트 가져오기)
	// Model 에 담고 페이지 이동
	@GetMapping("/list")
	public void list(Model model, Criteria cri) throws Exception {
		log.info("게시글 목록 보여주기.....");
		List<BoardVO> list = service.getList(cri);
		model.addAttribute("list", list);
		PageDTO dto = new PageDTO(cri, service.getTotalCount());
		model.addAttribute("pageDTO", dto);
		log.info("pageDTO: " + dto);
	}

	//
	@GetMapping(value = { "/read", "/modify" })
	public void read(int bno, Model model) throws Exception {
		log.info("게시글 보기..." + bno);
		// bno에 해당하는 내용 DB작업한 후 Model에 담고 페이지 이동하기
		BoardVO vo = service.get(bno);

		model.addAttribute("vo", vo);
	}

	@PostMapping("/modify")
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) throws Exception {
		log.info("내용 수정 요청..." + cri);

		// modify.jsp에서 넘긴 값 가져오기
		// 서비스 요청 => title,content 수정
		if (service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:list";
	}

	public String remove(int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		log.info("게시글 삭제 요청...");
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:list";
	}
}
