package com.guest.persistence;

import java.util.List;

import com.guest.domain.Criteria;
import com.guest.domain.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(int bno) throws Exception;// 해당 글에 대한 전체 댓글
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(int rno) throws Exception;
	
	public  List<ReplyVO> listPage(int bno,Criteria cri) throws Exception;
	public int count(int bno) throws Exception;
}
