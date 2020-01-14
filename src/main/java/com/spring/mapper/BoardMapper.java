package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardMapper {
	public int insertSelectKey(BoardVO vo);
	//public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	public int totalCnt();
	public BoardVO read(int bno);
	public int update(BoardVO vo);
	public int delete(int bno);
}
