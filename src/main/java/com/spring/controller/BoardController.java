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
		log.info("�Խñ� ��� �� ��û...");
	}

	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rttr) throws Exception {
		// register.jsp���� �ѱ� �� �����ͼ� �α׷� ���
		log.info("�Խñ� ����ϱ�...." + vo);
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:list";
	}

	// /board/list => controller �ۼ�
	// DB�۾� (��ü����Ʈ ��������)
	// Model �� ��� ������ �̵�
	@GetMapping("/list")
	public void list(Model model, Criteria cri) throws Exception {
		log.info("�Խñ� ��� �����ֱ�.....");
		List<BoardVO> list = service.getList(cri);
		model.addAttribute("list", list);
		PageDTO dto = new PageDTO(cri, service.getTotalCount());
		model.addAttribute("pageDTO", dto);
		log.info("pageDTO: " + dto);
	}

	//
	@GetMapping(value = { "/read", "/modify" })
	public void read(int bno, Model model) throws Exception {
		log.info("�Խñ� ����..." + bno);
		// bno�� �ش��ϴ� ���� DB�۾��� �� Model�� ��� ������ �̵��ϱ�
		BoardVO vo = service.get(bno);

		model.addAttribute("vo", vo);
	}

	@PostMapping("/modify")
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) throws Exception {
		log.info("���� ���� ��û..." + cri);

		// modify.jsp���� �ѱ� �� ��������
		// ���� ��û => title,content ����
		if (service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:list";
	}

	public String remove(int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		log.info("�Խñ� ���� ��û...");
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:list";
	}
}
