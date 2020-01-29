package com.study.app;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:context/mybatis-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	BoardController boardController;

    @Before
    public void setBuild() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }
    
    /** 
	 * GET - /searchBoard 
	 * > [O] searchBoard_200_OK
	 * > [O] searchBoard_400_pageNum_is_null
	 * > [O] searchBoard_400_pageNum_less_than_1
	 * > [O] searchBoard_400_pageNum_is_not_number
	 * > [O] searchBoard_400_limit_is_null
	 * > [O] searchBoard_400_limit_less_than_1
	 * > [O] searchBoard_400_limit_is_not_number
	 * */
    @Test public void searchBoard_200_OK() throws Exception{
		// pageNum >= 1
		// limit > 0
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("pageNum", "1")
			.param("limit", "10")
		)
		.andExpect(status().isOk());
	}
	@Test public void searchBoard_400_pageNum_is_null() throws Exception{
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("limit", "10")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void searchBoard_400_pageNum_less_than_1() throws Exception{
		// pageNum < 1
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("pageNum", "0")
			.param("limit", "10")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void searchBoard_400_pageNum_is_not_number() throws Exception{
		// pageNum : 숫자가 아닌 경우
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("pageNum", "aaa")
			.param("limit", "10")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void searchBoard_400_limit_is_null() throws Exception{
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("pageNum", "1")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void searchBoard_400_limit_less_than_1() throws Exception{
		// limit < 1
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("pageNum", "1")
			.param("limit", "0")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void searchBoard_400_limit_is_not_number() throws Exception{
		// limit : 숫자가 아닌 경우
		this.mockMvc
		.perform(
			get("/searchBoard")
			.param("pageNum", "1")
			.param("limit", "aaa")
		)
		.andExpect(status().isBadRequest());
	}

	/** 
	 * GET - /seeBoard 
	 * > [O] seeBoard_200_OK
	 * > [O] seeBoard_400_id_is_null
	 * > [O] seeBoard_404_is_not_found_by_id
	 * */
	@Test public void seeBoard_200_OK() throws Exception {
		this.mockMvc
		.perform(
			get("/seeBoard")
			.param("id", "10")
		)
		.andExpect(status().isOk());
	}
	@Test public void seeBoard_400_id_is_null() throws Exception {
		this.mockMvc
		.perform(
			get("/seeBoard")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void seeBoard_404_is_not_found_by_id() throws Exception{
		this.mockMvc
		.perform(
			get("/seeBoard")
			.param("id", "abc")
		)
		.andExpect(status().isNotFound());
	}
	
	/** 
	 * POST - /addBoard 
	 * > [O] addBoard_201_CREATED --> 200 으로 대체
	 * > [O] addBoard_400_title_is_null
	 * > [O] addBoard_400_content_is_null
	 * > [O] addBoard_400_writer_is_null
	 * > [-] addBoard_409_already_exist (UNIQUE 컬럼이 존재하는 경우에 쓰입니다.)
	 * */
	@Test public void addBoard_201_CREATED() throws Exception {
		this.mockMvc
		.perform(
			post("/addBoard")
			.param("title", "테스트 제목")
			.param("content", "테스트 내용")
			.param("writer", "테스터")
		)
		.andExpect(status().isOk());
	}
	@Test public void addBoard_400_title_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/addBoard")
			.param("content", "테스트 내용")
			.param("writer", "테스터")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void addBoard_400_content_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/addBoard")
			.param("title", "테스트 제목")
			.param("writer", "테스터")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void addBoard_400_writer_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/addBoard")
			.param("title", "테스트 제목")
			.param("content", "테스트 내용")
		)
		.andExpect(status().isBadRequest());
	}
	
	/** 
	 * POST - /editBoard 
	 * > [O] editBoard_302_OK
	 * > [O] editBoard_400_title_is_null
	 * > [O] editBoard_400_content_is_null
	 * > [O] editBoard_400_writer_is_null
	 * > [O] editBoard_400_id_is_null
	 * > [O] editBoard_404_is_not_found_by_id
	 * */
	@Test public void editBoard_200_OK() throws Exception {
		this.mockMvc
		.perform(
			post("/editBoard")
			.param("id", "10")
			.param("title", "수정 : 테스트 제목")
			.param("content", "수정 : 테스트 내용")
			.param("writer", "수정 : 테스터")
		)
		.andExpect(status().is3xxRedirection());
	}
	@Test public void editBoard_400_title_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/editBoard")
			.param("id", "20")
			.param("content", "수정 : 테스트 내용")
			.param("writer", "수정 : 테스터")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void editBoard_400_content_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/editBoard")
			.param("id", "20")
			.param("title", "수정 : 테스트 제목")
			.param("writer", "수정 : 테스터")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void editBoard_400_writer_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/editBoard")
			.param("id", "20")
			.param("title", "수정 : 테스트 제목")
			.param("content", "수정 : 테스트 내용")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void editBoard_400_id_is_null() throws Exception {
		this.mockMvc
		.perform(
			post("/editBoard")
			.param("title", "수정 : 테스트 제목")
			.param("content", "수정 : 테스트 내용")
			.param("writer", "수정 : 테스터")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void editBoard_404_is_not_found_by_id() throws Exception {
		this.mockMvc
		.perform(
			post("/editBoard")
			.param("id", "abc")
			.param("title", "수정 : 테스트 제목")
			.param("content", "수정 : 테스트 내용")
			.param("writer", "수정 : 테스터")
		)
		.andExpect(status().isNotFound());
	}
	
	/** 
	 * POST - /deleteBoard 
	 * > [O] deleteBoard_204_NO_CONTENT --> 302 으로 대체 (리다이렉트 URL 사용)
	 * > [O] deleteBoard_400_id_is_null
	 * */
	@Test public void deleteBoard_302_FOUND() throws Exception {
		this.mockMvc
		.perform(
			get("/deleteBoard")
			.param("id", "abc")
		)
		.andExpect(status().is3xxRedirection());
	}
	@Test public void deleteBoard_400_id_is_null() throws Exception {
		this.mockMvc
		.perform(
			get("/deleteBoard")
		)
		.andExpect(status().isBadRequest());
	}
	
	/** 
	 * GET - /boardAddForm 
	 * > [O] boardAddForm_200_OK
	 * */
	@Test public void boardAddForm_200_OK() throws Exception {
		this.mockMvc
		.perform(
			get("/boardAddForm")
		)
		.andExpect(status().isOk());
	}
	
	/** 
	 * GET - /boardEditForm 
	 * > [O] boardEditForm_200_OK
	 * > [O] boardEditForm_400_id_is_null
	 * > [O] boardEditForm_404_is_not_found_by_id
	 * */
	@Test public void boardEditForm_200_OK() throws Exception {
		this.mockMvc
		.perform(
			get("/boardEditForm")
			.param("id", "10")
		)
		.andExpect(status().isOk());
	}
	@Test public void boardEditForm_400_id_is_null() throws Exception {
		this.mockMvc
		.perform(
			get("/boardEditForm")
		)
		.andExpect(status().isBadRequest());
	}
	@Test public void boardEditForm_404_is_not_found_by_id() throws Exception {
		this.mockMvc
		.perform(
			get("/boardEditForm")
			.param("id", "abc")
		)
		.andExpect(status().isNotFound());
	}

}
