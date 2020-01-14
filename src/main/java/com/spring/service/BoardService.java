package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {
	//�� ���
	public void register(BoardVO vo) throws Exception;
	//�� ���
	//public List<BoardVO> getList() throws Exception;
	public List<BoardVO> getList(Criteria cri) throws Exception;
	//��ü �Խù� �� 
	public int getTotalCount() throws Exception;
	//�� �� ��ȸ
	public BoardVO get(int bno) throws Exception;
	//�� ����
	public boolean modify(BoardVO vo) throws Exception;
	//�� ����
	public boolean remove(int bno) throws Exception;
}
