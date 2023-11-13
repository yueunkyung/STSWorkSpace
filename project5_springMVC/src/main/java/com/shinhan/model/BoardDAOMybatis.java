package com.shinhan.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.BoardVO;

@Repository("boardDAO")	//이름은 필수가 아님, 같은 타입이 여러개 있는 경우는 필수
public class BoardDAOMybatis {
	
	@Autowired
	SqlSession sqlSession;	//Mybatis가 SQL문을 실행하기 위한 단위	//DB에 관련된 Session
	
	final static String NAMESPACE="net.firstzone.board.";
	Logger logger = LoggerFactory.getLogger(BoardDAOMybatis.class);
	
	public List<BoardVO> selectAll() {
		List<BoardVO> blist = sqlSession.selectList(NAMESPACE + "selectAll");
		logger.info("selectAll : {}건", blist.size());
		return blist;
	}
	
	public BoardVO selectById(int bno) {
		BoardVO board = sqlSession.selectOne(NAMESPACE + "selectById", bno);
		logger.info("selectById : {}", board);
		return board;
	}

	public int updateViewCount(int bno) {
		int result = sqlSession.update(NAMESPACE + "viewcntIncrement", bno);
		logger.info("updateViewCount : {}건 수정", result);
		return result;
	}
	
	public int insert(BoardVO board) {
		int result = sqlSession.insert(NAMESPACE + "insert", board);
		logger.info("insert : {}건 입력", result);
		return result;
	}
	
	public int update(BoardVO board) {
		int result = sqlSession.update(NAMESPACE + "update", board);
		logger.info("update : {}건 수정", result);
		return result;
	}
	
	public int delete(int bno) {
		int result = sqlSession.delete(NAMESPACE + "delete", bno);
		logger.info("delete : {}건 삭제", result);
		return result;
	}
}
