package com.kosta.myapp.s0610.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.myapp.s0610.service.BoardService;
import com.kosta.myapp.s0610.service.dao.BoardinfoDAO;
import com.kosta.myapp.s0610.vo.BoardinfoVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardinfoDAO boardMapper;

	@Override
	@Transactional
	public List<BoardinfoVO> s_ALLBoardList() throws Exception {
		return boardMapper.s_ALLBoardList();
	}

}
