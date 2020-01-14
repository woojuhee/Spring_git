package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO vo) throws Exception {		
		mapper.insertSelectKey(vo);
	}

//	@Override
//	public List<BoardVO> getList() throws Exception {		
//		return mapper.getList();
//	}

	@Override
	public BoardVO get(int bno) throws Exception {		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO vo) throws Exception {		
		return mapper.update(vo)==1?true:false;
	}

	@Override
	public boolean remove(int bno) throws Exception {		
		return mapper.delete(bno)==1?true:false;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) throws Exception {
		return mapper.getList(cri);
	}

	@Override
	public int getTotalCount() throws Exception {
		return mapper.totalCnt();
	}
}









