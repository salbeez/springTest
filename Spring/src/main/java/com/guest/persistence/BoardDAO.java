package com.guest.persistence;

import java.util.List;

import com.guest.domain.Criteria;
import com.guest.domain.GuestVO;
import com.guest.domain.SearchCriteria;

public interface BoardDAO {
	public void create(GuestVO vo) throws Exception;

	public GuestVO read(int bno) throws Exception;

	public void update(GuestVO vo) throws Exception;

	public void delete(int bno) throws Exception;

	public List<GuestVO> listAll() throws Exception;

	public List<GuestVO> listPage(int page, int perPageNum) throws Exception;

	public List<GuestVO> listCriteria(Criteria cri) throws Exception;

	public int totCount() throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public List<GuestVO> listSearch(SearchCriteria cri) throws Exception;
	
	public void updateReplyCnt(int bno, int amount) throws Exception;//댓글 수
	public void updateViewCnt(int bno) throws Exception;// 조회수
}
