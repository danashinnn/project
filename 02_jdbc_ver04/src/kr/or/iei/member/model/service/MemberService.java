package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

public class MemberService {
	
	private MemberDao dao;

	public MemberService() {
		super();
		dao = new MemberDao();
	}

	public Member selectOneMember(Member m) { // 로그인용 (내 정보 저장해놓을 객체)
		Connection conn = JDBCTemplate.getConnection();
		Member member = dao.selectOneMember(conn, m);
		JDBCTemplate.close(conn);
		return member;
	}

	public boolean selectOneMember(String id) { // 가입시 아이디 중복 체크용
		Connection conn = JDBCTemplate.getConnection();
		boolean check = dao.selectOneMember(conn, id);
		JDBCTemplate.close(conn);
		return check;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Member> selectNameMember(String name) { // 이름으로 회원 조회용
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = dao.selectNameMember(conn, name);
		JDBCTemplate.close(conn);
		return list;
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int deleteMember(Member login) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn, login);
		if(result > 0) {
			result = dao.insertDelMember(conn, login);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);				
			}
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
