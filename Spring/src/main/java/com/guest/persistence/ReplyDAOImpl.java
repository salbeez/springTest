package com.guest.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.guest.domain.Criteria;
import com.guest.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "com.guest.ReplyMapper";

	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return session.selectList(namespace + ".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		RowBounds bounds = new RowBounds(cri.gerPageStart(), cri.getPerPageNum());//시작 위치, 결과행 갯수
		return session.selectList(namespace + ".listPage",map,bounds);
	}

	@Override
	public int count(int bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".count",bno);
	}

	@Override
	public int getBno(int rno) throws Exception {
		return session.selectOne(namespace+".getBno",rno);
	}

}
