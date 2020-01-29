package com.study.app;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService extends BoardValidationService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Transactional
	int pageCount(Map<String, Object> map){
		return boardMapper.pageCount(map);
	};
	
	@Transactional
	List<Board> searchBoard(Map<String, Object> map){
		return boardMapper.searchBoard(map);
	};
	
	@Transactional
	Board seeBoard(Map<String, Object> map) {
		boardMapper.addHits(map);
		return boardMapper.seeBoard(map);
	};
	
	@Transactional
	Board addBoard(Map<String, Object> map) {
		boardMapper.addBoard(map);
		return boardMapper.seeBoard(map);
	};
	
	@Transactional
	Board editBoard(Map<String, Object> map) {
		boardMapper.editBoard(map);
		return boardMapper.seeBoard(map);
	};
	
	@Transactional
	int deleteBoard(Map<String, Object> map) {
		return boardMapper.deleteBoard(map);
	};
}
