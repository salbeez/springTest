package com.guest.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.guest.domain.Criteria;
import com.guest.domain.ReplyVO;
import com.guest.persistence.ReplyDAO;

@Service
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

	@Override
	public List<ReplyVO> listReplyPage(int bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listPage(bno, cri);
	}

	@Override
	public int count(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.count(bno);
	}

}
