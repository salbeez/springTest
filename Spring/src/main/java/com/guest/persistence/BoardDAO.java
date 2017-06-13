package com.guest.persistence;

import java.util.List;

import com.guest.domain.GuestVO;

public interface BoardDAO {
	public void create(GuestVO vo) throws Exception;

	public GuestVO read(int bno) throws Exception;

	public void update(GuestVO vo) throws Exception;

	public void delete(int bno) throws Exception;

	public List<GuestVO> listAll() throws Exception;
}
