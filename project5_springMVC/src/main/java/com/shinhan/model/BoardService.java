package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.dto.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAOMybatis dao;
	
	public List<BoardVO> findAll() {
		return dao.selectAll();
	}

	@Transactional	//모두 성공 또는 모두 실패
	public BoardVO updateTransViewCnt(int bno) {
		BoardVO board =  dao.selectById(bno);
		dao.updateViewCount(bno);
		return board;
	}
	
	public int insert(BoardVO board) {
		return dao.insert(board);
	}
	
	public int update(BoardVO board) {
		return dao.update(board);
	}
	
	public int delete(int bno) {
		return dao.delete(bno);
	}
}
