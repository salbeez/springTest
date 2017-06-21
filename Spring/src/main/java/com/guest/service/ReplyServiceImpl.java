package com.guest.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guest.domain.Criteria;
import com.guest.domain.ReplyVO;
import com.guest.persistence.BoardDAO;
import com.guest.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyServics {

	@Inject
	private ReplyDAO dao;

	@Inject
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}
	
	@Transactional
	@Override
	public void removeReply(int rno) throws Exception {
		
		int bno = dao.getBno(rno);

		dao.delete(rno);
		boardDAO.updateReplyCnt(bno, -1);
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
