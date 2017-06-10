package com.kosta.myapp.s0610.service.dao;

import java.util.List;
import com.kosta.myapp.s0610.vo.BoardinfoVO;

public interface BoardinfoDAO {
	List<BoardinfoVO> s_ALLBoardList() throws Exception;
}
