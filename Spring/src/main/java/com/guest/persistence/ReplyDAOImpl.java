package com.guest.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.guest.domain.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.guest.ReplyMapper";
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return session.selectList(namespace+".list",bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace+".create",vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace+".update",vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		session.delete(namespace+".delete",rno);
	}

}
