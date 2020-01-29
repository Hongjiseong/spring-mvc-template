package com.study.app;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardValidationService.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {BoardInvalidParameterException.class})
	protected ModelAndView handleBadRequest(HttpServletRequest req, Exception e) {
		logger.error("Exception: {}", e.toString());
		printExceptionInfo(req, e);
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("code", "400");
		mv.addObject("message", "잘못된 요청입니다");
		return mv;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {BoardNotFoundException.class})
	protected ModelAndView handleNotFound(HttpServletRequest req, Exception e) {
		logger.error("Exception: {}", e.toString());
		printExceptionInfo(req, e);
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("code", "404");
		mv.addObject("message", "페이지를 찾을 수 없습니다");
		return mv;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {SQLException.class, DataAccessException.class})
	protected ModelAndView handleInternalServerError(HttpServletRequest req, Exception e) {
		logger.error("Exception: {}", e.toString());
		printExceptionInfo(req, e);
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("code", "500");
		mv.addObject("message", "서버 내부 오류");
		return mv;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		logger.error("Exception: {}", e.toString());
		printExceptionInfo(req, e);
    }
	
	@ExceptionHandler(Exception.class)
	public void exception(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		logger.error("Exception: {}", e.toString());
		printExceptionInfo(req, e);
    }
	
	private void printExceptionInfo(HttpServletRequest req, Exception e){
//		for(StackTraceElement s : e.getStackTrace()){
//			logger.error("Stack: {}", s);
//		}
	}
	
}
