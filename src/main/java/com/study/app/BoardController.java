package com.study.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	 
    @RequestMapping(method = RequestMethod.GET, value = "/searchBoard")
    public ModelAndView searchBoard(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("GET - /searchBoard");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
        
        boolean isValidParam = boardService.isValidParam_searchBoard(data);
        
        if(isValidParam) {
        	// 게시글 목록
        	int pageNum = Integer.parseInt((String) data.get("pageNum"));
        	int limit = Integer.parseInt((String) data.get("limit"));
        	data.put("limit", limit);
    		data.put("offset", (pageNum-1)*limit);
    		mv.addObject("list", boardService.searchBoard(data));
    		
    		// 페이징
    		int totalPage = (int)Math.ceil(boardService.pageCount(data)/10.0f);
    		int start = pageNum/10 + 1;
    		int end = totalPage < (start+10) ? totalPage : start+10; 
    		mv.addObject("start", start);
    		mv.addObject("end", end);
    		
    		// 검색조건
    		String searchKeyword = (String) data.get("searchKeyword");
    		mv.addObject("limit", data.get("limit"));
    		mv.addObject("pageNum", data.get("pageNum"));
    		mv.addObject("searchKeyword", searchKeyword==null ? "" : searchKeyword);
    		
    		// 페이지 이동
            mv.setViewName("boardList");
        }
        
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/seeBoard")
    public ModelAndView seeBoard(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("GET - /seeBoard");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
        
        boolean isValidParam = boardService.isValidParam_seeBoard(data);
        if(isValidParam) {
        	Board board = boardService.seeBoard(data);
        	if(board == null) {
        		throw new BoardNotFoundException((String)data.get("id"));
        	}else {
        		mv.addObject("detail", board);
        		mv.setViewName("boardDetail");
        	}
        }
        
        return mv;
    }
 
    @RequestMapping(method = RequestMethod.POST, value = "/addBoard")
    public ModelAndView addBoard(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("POST - /addBoard");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
        
        boolean isValidParam = boardService.isValidParam_addBoard(data);
        if(isValidParam) {
        	mv.addObject("addBoard", boardService.addBoard(data));
        	mv.setViewName("boardList");
        }
        
        return mv;
    }
 
    @RequestMapping(method = RequestMethod.POST, value = "/editBoard")
    public ModelAndView editBoard(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("POST - /editBoard");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
        
        boolean isValidParam = boardService.isValidParam_editBoard(data);
        if(isValidParam) {
        	Board board = boardService.seeBoard(data);
        	if(board == null) {
        		throw new BoardNotFoundException((String)data.get("id"));
        	}else {
        		mv.addObject("editBoard", boardService.editBoard(data));
            	mv.setViewName("redirect:/seeBoard?id="+data.get("id")); // 302
        	}
        }
        
        return mv;
    }
 
    @RequestMapping(method = RequestMethod.GET, value = "/deleteBoard")
    public ModelAndView deleteBoard(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("GET - /deleteBoard");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
        
        boolean isValidParam = boardService.isValidParam_deleteBoard(data);
        if(isValidParam) {
        	mv.addObject("isDeleted", boardService.deleteBoard(data));
        	mv.setViewName("redirect:/searchBoard?limit=10&pageNum=1");
        }
        
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/boardAddForm")
    public ModelAndView boardAddForm(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("GET - /boardAddForm");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
    	mv.setViewName("boardForm");
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/boardEditForm")
    public ModelAndView boardEditForm(@RequestParam Map<String, Object> data) throws Exception {
        System.out.println("GET - /boardEditForm");
        System.out.println("param : " + data.toString());
        
        ModelAndView mv = new ModelAndView();
        
        boolean isValidParam = boardService.isValidParam_seeBoard(data);
        if(isValidParam) {
        	Board board = boardService.seeBoard(data);
        	if(board == null) {
        		throw new BoardNotFoundException((String)data.get("id"));
        	}else {
        		mv.addObject("detail", board);
        		mv.setViewName("boardForm");
        	}
        }
        
        return mv;
    }

}
