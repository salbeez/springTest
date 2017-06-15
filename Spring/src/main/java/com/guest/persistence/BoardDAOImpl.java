package com.guest.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.User;

import com.guest.domain.Criteria;
import com.guest.domain.GuestVO;
import com.guest.domain.SearchCriteria;

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
		session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<GuestVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<GuestVO> listPage(int page,int perPageNum) throws Exception {
		//페이지는 1부터 시작 이지만 offset은 0부터 시작으므로 -1 해줬음
		int countPage = perPageNum;// 한 페이지에 보여질 행의 수
		int offset = (page-1)*countPage;
		RowBounds bounds = new RowBounds(offset, countPage);//시작 위치, 결과행 갯수
		return session.selectList(namespace + ".listAll",null,bounds);
	}

	@Override
	public List<GuestVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		RowBounds bounds = new RowBounds(cri.gerPageStart(),cri.getPerPageNum());//시작 위치, 결과행 갯수
		return session.selectList(namespace + ".listAll",null,bounds);
	}

	@Override
	public int totCount() throws Exception {
		
		return session.selectOne(namespace + ".totCount");
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".listSearchCount",cri);
	}

	@Override
	public List<GuestVO> listSearch(SearchCriteria cri) throws Exception {
		RowBounds bounds = new RowBounds(cri.gerPageStart(),cri.getPerPageNum());//시작 위치, 결과행 갯수
		return session.selectList(namespace+".listSearch",cri,bounds);
	}

}
