package com.study.app;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	int pageCount(Map<String, Object> map);
	List<Board> searchBoard(Map<String, Object> map);
	Board seeBoard(Map<String, Object> map);
	void addBoard(Map<String, Object> map);
	void editBoard(Map<String, Object> map);
	int deleteBoard(Map<String, Object> map);
	
	void addHits(Map<String, Object> map);
}
