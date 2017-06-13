package com.kosta.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guest.domain.GuestVO;
import com.guest.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Test
	public void testCreate() throws Exception {
		GuestVO vo = new GuestVO();
		vo.setTitle("새로운 글을 넣습니다");
		vo.setContent("새로운 글 내용을 넣습니다");
		vo.setWriter("user10");
		dao.create(vo);
	}

	@Test
	public void testRead() throws Exception {
		logger.info(dao.read(1).toString());
	}

	@Test
	public void testUpdate() throws Exception {
		GuestVO vo = new GuestVO();
		vo.setBno(1);
		vo.setTitle("수정된 글을 넣습니다");
		vo.setContent("수정된 글 내용을 넣습니다");
		dao.update(vo);
	}

	//@Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}
}
