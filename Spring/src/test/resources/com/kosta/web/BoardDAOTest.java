package com.kosta.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.guest.domain.Criteria;
import com.guest.domain.GuestVO;
import com.guest.domain.SearchCriteria;
import com.guest.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	// @Test
	public void testCreate() throws Exception {
		GuestVO vo = new GuestVO();
		vo.setTitle("111새로운 글을 넣습니다");
		vo.setContent("111새로운 글 내용을 넣습니다");
		vo.setWriter("user10");
		dao.create(vo);
	}

	// @Test
	public void testRead() throws Exception {
		logger.info(dao.read(1).toString());
	}

	// @Test
	public void testUpdate() throws Exception {
		GuestVO vo = new GuestVO();
		vo.setBno(1);
		vo.setTitle("수정된 글을 넣습니다");
		vo.setContent("수정된 글 내용을 넣습니다");
		dao.update(vo);
	}

	// @Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}

	// @Test
	public void testlistALL() throws Exception {
		List<GuestVO> list = dao.listAll();
		logger.info("가져온 사이즈 : " + list.size());
	}

	// @Test
	public void testListPage() throws Exception {
		List<GuestVO> list = dao.listPage(1, 5);
		logger.info("가져온 사이즈 : " + list.size());
	}

	// @Test
	public void testListPage2() throws Exception {
		Criteria cri = new Criteria();
		List<GuestVO> list = dao.listCriteria(cri);
		logger.info("가져온 사이즈 : " + list.size());
	}

	// @Test
	public void totCount() throws Exception {
		int totCount = dao.totCount();
		logger.info("총 데이터의 수 : " + totCount);
	}

	// @Test
	public void testURI() throws Exception {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/board/read").queryParam("bno", 12)
				.queryParam("perPageNum", 20).build();
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}

	// @Test
	public void testURI2() throws Exception {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/{model}/{page}").queryParam("bno", 12)
				.queryParam("perPageNum", 20).build().expand("board", "read").encode();
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}

	//@Test
	public void testDynamic1() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("수정");
		cri.setSearchType("t");
		
		logger.info("=================");
		List<GuestVO> list = dao.listSearch(cri);
		
		for(GuestVO vo : list){
			logger.info(vo.getBno() +" : " +vo.getTitle());
		}
		logger.info("=================");
		logger.info("COUNT : "+dao.listSearchCount(cri));
	}
}
