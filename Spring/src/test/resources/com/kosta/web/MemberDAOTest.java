package com.kosta.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Emp;
import org.zerock.domain.MemberVO;
import org.zerock.domain.User;
import org.zerock.presistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;
	@Inject // 인젝트는 바로 밑의 행만
	private SqlSession sqlSession;

	private static final String namespace = "org.zerock.mapper.MemberMapper";

	@Test
	public void testTime() throws Exception {
		System.out.println(dao.getTime());
	}

	// @Test
	public void testInsertMember() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setUserid("user01");
		vo.setUserpw("user00");
		vo.setUsername("USER00");
		vo.setEmail("user00@aaa.com");
		dao.insertMember(vo);
		System.out.println("vo 끝");
	}

	// 수업용
	// @Test
	public void selEname() throws Exception {
		String ename = sqlSession.selectOne(namespace + ".selEname");
		System.out.println("검색된 이름 : " + ename);
	}

	// @Test
	public void selEMP() throws Exception {
		int deptno = 30;
		List<Emp> list = sqlSession.selectList(namespace + ".selEMP", deptno);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	//@Test
	public void selGuest() throws Exception {
//		소량의 데이터에서 필요한 구간을 가져올 경우 엄청나게 효율적
//		하지만 건너띄어야 할 데이터를 모두 가져온다는 함정이 있다.
//		이는 건너띄어야 할 데이터가 많으면 많을수록 불리하다.
//		속도 역시 너무 좋지 않았다.
		RowBounds bounds = new RowBounds(0, 5);//시작 위치, 결과행 갯수
		List<User> list = sqlSession.selectList(namespace + ".selGuest",null,bounds);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

}