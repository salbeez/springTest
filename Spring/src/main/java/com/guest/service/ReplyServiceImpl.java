package com.guest.service;

import java.util.List;

import javax.inject.Inject;

import com.guest.domain.ReplyVO;
import com.guest.persistence.ReplyDAO;

public class ReplyServiceImpl implements ReplyServics {

	@Inject
	private ReplyDAO dao;
	
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(int rno) throws Exception {
		dao.delete(rno);
	}

}
