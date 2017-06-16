package com.guest.service;

import java.util.List;

import com.guest.domain.ReplyVO;

public interface ReplyServics {
	public void addReply(ReplyVO vo) throws Exception;

	public List<ReplyVO> listReply(int bno) throws Exception;

	public void modifyReply(ReplyVO vo) throws Exception;

	public void removeReply(int rno) throws Exception;
}
