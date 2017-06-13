package com.guest.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.guest.domain.GuestVO;
import com.guest.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void regist(GuestVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public GuestVO read(int bno) throws Exception {
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

}
