package com.guest.service;

import java.util.List;

import com.guest.domain.Criteria;
import com.guest.domain.GuestVO;

public interface BoardService {
	public void regist(GuestVO vo) throws Exception;

	public GuestVO read(int bno) throws Exception;

	public void modify(GuestVO vo) throws Exception;

	public void remove(int bno) throws Exception;

	public List<GuestVO> listAll() throws Exception;
	
	public List<GuestVO> listCriteria(Criteria cri) throws Exception;
	
	public int totCount() throws Exception;
}
