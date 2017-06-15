package com.guest.service;

import java.util.List;

import com.guest.domain.Criteria;
import com.guest.domain.GuestVO;
import com.guest.domain.SearchCriteria;

public interface BoardService {
	public void regist(GuestVO vo) throws Exception;

	public GuestVO read(int bno) throws Exception;

	public void modify(GuestVO vo) throws Exception;

	public void remove(int bno) throws Exception;

	public List<GuestVO> listAll() throws Exception;

	public List<GuestVO> listCriteria(Criteria cri) throws Exception;

	public int totCount() throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public List<GuestVO> listSearchCriteria(SearchCriteria cri) throws Exception;
}
