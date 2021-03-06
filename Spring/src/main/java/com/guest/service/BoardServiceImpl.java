package com.guest.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.guest.domain.Criteria;
import com.guest.domain.GuestVO;
import com.guest.domain.SearchCriteria;
import com.guest.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	
	@Transactional
	@Override
	public void regist(GuestVO vo) throws Exception {
		dao.create(vo);
		String files[] = vo.getFiles();
		if(files ==null){//첨부 파일이 없는 경우
			return;
		}
		for(String fileName : files){//여러개의 파일을 첨부 했을시
			dao.addAttach(fileName);
		}
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public GuestVO read(int bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(GuestVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<GuestVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<GuestVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int totCount() throws Exception {
		return dao.totCount();
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

	@Override
	public List<GuestVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}
}
