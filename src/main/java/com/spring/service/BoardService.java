package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {
	//글 등록
	public void register(BoardVO vo) throws Exception;
	//글 목록
	//public List<BoardVO> getList() throws Exception;
	public List<BoardVO> getList(Criteria cri) throws Exception;
	//전체 게시물 수 
	public int getTotalCount() throws Exception;
	//글 상세 조회
	public BoardVO get(int bno) throws Exception;
	//글 수정
	public boolean modify(BoardVO vo) throws Exception;
	//글 삭제
	public boolean remove(int bno) throws Exception;
}
