package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.member.model.vo.Member;

public class MemberDao {

	public ArrayList<Member> selectAllMember() {
		// 0. 자원 반환할 객체들을 미리 null로 선언
		Connection conn = null;
		PreparedStatement pstmt = null; // 쿼리문을 실행하고 결과를 가져오는 객체
		ResultSet rset = null; // select 하는 경우에만 사용
		ArrayList<Member> list = new ArrayList<Member>(); // 결과 반환할 변수
		String query = "select * from member_tbl order by 8"; // 사용할 쿼리문 변수 (; 쓰면 안 됨)
		
		try {
			// 1. 드라이버 등록 (연결 시 사용할 클래스 등록, ojdbc6이 제공)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 커넥션 생성 (1에서 등록한 드라이버 이용)
			// 접속에 문제가 없으면 connection 객체 반환, 실패하면 null
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			// 3. 쿼리문을 실행하고 결과를 받아올 statement 객체 생성
			// pstmt 객체 생성 시 쿼리를 매개변수로 전달 -> 쿼리문 문법검사를 진행
			pstmt = conn.prepareStatement(query);
			// 4. 위치홀더 없으므로 패스
			// 5. 쿼리문을 실행하고 그 결과를 저장
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. 자원 반환
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Member searchIdMember(String id) {
		// 0. 자원 반환할 객체 미리 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member_tbl where member_id = ?";
		
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 커넥션 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			// 3. 쿼리를 실행하고 결과를 받아올 pstmt 객체 생성
			// pstmt 객체 생성 시 쿼리를 매개변수로 전달하여 문법검사 진행
			pstmt = conn.prepareStatement(query);
			// 위치홀더 ?를 값으로 대체
			pstmt.setString(1, id);
			// 4. 쿼리를 실행하고 결과를 rset에 저장
			rset = pstmt.executeQuery();
			if(rset.next()) { // member_id는 pk라서 결과 최대 1개
				m = new Member();
				int i = 1;
				m.setMemberId(rset.getString(i++));
				m.setMemberPw(rset.getString(i++));
				m.setMemberName(rset.getString(i++));
				m.setMemberAddr(rset.getString(i++));
				m.setMemberPhone(rset.getString(i++));
				m.setMemberAge(rset.getInt(i++));
				m.setMemberGender(rset.getString(i++));
				m.setEnrollDate(rset.getDate(i++));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	public ArrayList<Member> searchNameMember(String name) {
		// 자원 반환할 객체 미리 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>(); // 결과 반환할 변수
		String query = "select * from member_tbl where member_name like ? order by 8";
		
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 커넥션 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			// 쿼리를 실행하고 결과를 받아올 pstmt 객체 생성, 쿼리 문법검사 실행
			pstmt = conn.prepareStatement(query);
			// 쿼리 실행 전에 위치홀더 값 변경
			pstmt.setString(1, "%"+name+"%");
			// 쿼리 실행하고 결과를 저장
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				int i = 1;
				m.setMemberId(rset.getString(i++));
				m.setMemberPw(rset.getString(i++));
				m.setMemberName(rset.getString(i++));
				m.setMemberAddr(rset.getString(i++));
				m.setMemberPhone(rset.getString(i++));
				m.setMemberAge(rset.getInt(i++));
				m.setMemberGender(rset.getString(i++));
				m.setEnrollDate(rset.getDate(i++));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int insertMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member_tbl values(?, ?, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberAddr());
			pstmt.setString(5, m.getMemberPhone());
			pstmt.setInt(6, m.getMemberAge());
			pstmt.setString(7, m.getMemberGender());
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member_tbl set member_pw = ?, member_addr = ?, member_phone = ? where member_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberAddr());
			pstmt.setString(3, m.getMemberPhone());
			pstmt.setString(4, m.getMemberId());
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return result;
	}

	public int deleteMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member_tbl where member_id = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else { 
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
