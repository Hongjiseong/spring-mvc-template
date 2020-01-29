package com.study.app;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardValidationService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardValidationService.class);
	
	public boolean isValidParam_searchBoard(Map<String, Object> data) throws Exception {
		// 받은 파라미터
		String param_pageNum = (String) data.get("pageNum");
		String param_limit = (String) data.get("limit");

		// PageNum - 검증하기
		int pageNum = 0;
		boolean isPageNum = param_pageNum != null;

		// PageNum - 데이터 존재여부 검증
		if (!isPageNum) {
			logger.error("pageNum 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}

		// PageNum - 숫자 여부 / 1 보다 작은지 여부 검증
		try {
			pageNum = Integer.parseInt(param_pageNum);
			if (pageNum < 1) {
				logger.error("pageNum 이 1 보다 작습니다: {}", data.toString());
				throw new BoardInvalidParameterException("잘못된 요청입니다");
			}
		} catch (NumberFormatException e) {
			logger.error("pageNum 이 숫자가 아닙니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}

		
		// Limit - 검증하기
		int limit = 0;
		boolean isLimit = param_limit != null;

		// Limit - 데이터 존재여부 검증
		if (!isLimit) {
			logger.error("limit 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}

		// Limit - 숫자 여부 / 1 보다 작은지 여부 검증
		try {
			limit = Integer.parseInt(param_limit);
			if (limit < 1) {
				logger.error("limit 이 1 보다 작습니다: {}", data.toString());
				throw new BoardInvalidParameterException("잘못된 요청입니다");
			}
		} catch (NumberFormatException e) {
			logger.error("limit 이 숫자가 아닙니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		return true;
	}
	
	public boolean isValidParam_seeBoard(Map<String, Object> data) throws Exception {
		// 받은 파라미터
		String param_id = (String) data.get("id");

		// Id - 검증하기
		boolean isId = param_id != null;

		// Id - 데이터 존재여부 검증
		if (!isId) {
			logger.error("id 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		return true;
	}
	
	public boolean isValidParam_addBoard(Map<String, Object> data) throws Exception {
		// 받은 파라미터
		String param_title = (String) data.get("title");
		String param_content = (String) data.get("content");
		String param_writer = (String) data.get("writer");

		// Id - 검증하기
		boolean isTitle = param_title != null;
		boolean isContent = param_content != null;
		boolean isWriter = param_writer != null;

		// Title - 데이터 존재여부 검증
		if (!isTitle) {
			logger.error("title 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		// Content - 데이터 존재여부 검증
		if (!isContent) {
			logger.error("content 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		// Writer - 데이터 존재여부 검증
		if (!isWriter) {
			logger.error("writer 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		return true;
	}
	
	public boolean isValidParam_editBoard(Map<String, Object> data) throws Exception {
		// 받은 파라미터
		String param_id = (String) data.get("id");
		String param_title = (String) data.get("title");
		String param_content = (String) data.get("content");
		String param_writer = (String) data.get("writer");

		// 검증하기
		boolean isId = param_id != null;
		boolean isTitle = param_title != null;
		boolean isContent = param_content != null;
		boolean isWriter = param_writer != null;
		
		// Id - 데이터 존재여부 검증
		if (!isId) {
			logger.error("id 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}

		// Title - 데이터 존재여부 검증
		if (!isTitle) {
			logger.error("title 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		// Content - 데이터 존재여부 검증
		if (!isContent) {
			logger.error("content 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		// Writer - 데이터 존재여부 검증
		if (!isWriter) {
			logger.error("writer 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		return true;
	}
	
	public boolean isValidParam_deleteBoard(Map<String, Object> data) throws Exception {
		// 받은 파라미터
		String param_id = (String) data.get("id");

		// Id - 검증하기
		boolean isId = param_id != null;

		// Id - 데이터 존재여부 검증
		if (!isId) {
			logger.error("id 값이 없습니다: {}", data.toString());
			throw new BoardInvalidParameterException("잘못된 요청입니다");
		}
		
		return true;
	}
}
