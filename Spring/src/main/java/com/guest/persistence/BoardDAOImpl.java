package com.guest.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.guest.domain.GuestVO;
@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession session;

	private static String namespace = "com.guest.BoardMapper";

	@Override
	public void create(GuestVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public GuestVO read(int bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(GuestVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		session.selectList(namespace + ".listAll");
	}

	@Override
	public List<GuestVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
