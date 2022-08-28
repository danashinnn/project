package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.member.model.vo.Member;

public class MemberDao {

	public Member selectOneMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = "select * from member_tbl where member_id = ? and member_pw = ? order by enroll_date, member_id";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				int i = 1;
				member.setMemberId(rset.getString(i++));
				member.setMemberPw(rset.getString(i++));
				member.setMemberName(rset.getString(i++));
				member.setMemberAddr(rset.getString(i++));
				member.setMemberPhone(rset.getString(i++));
				member.setMemberAge(rset.getInt(i++));
				member.setMemberGender(rset.getString(i++));
				member.setEnrollDate(rset.getDate(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return member;
	}

	public boolean selectOneMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean check = false;
		String query = "select member_id from member_tbl where member_id = ? union select member_id from del_member where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rset = pstmt.executeQuery();
			check = rset.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return check;
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member_tbl values(?, ?, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setString(i++, m.getMemberId());
			pstmt.setString(i++, m.getMemberPw());
			pstmt.setString(i++, m.getMemberName());
			pstmt.setString(i++, m.getMemberAddr());
			pstmt.setString(i++, m.getMemberPhone());
			pstmt.setInt(i++, m.getMemberAge());
			pstmt.setString(i++, m.getMemberGender());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectNameMember(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member_tbl where member_name like ? order by enroll_date, member_id";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+name+"%");
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member_tbl set member_pw = ?, member_addr = ?, member_phone = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberAddr());
			pstmt.setString(3, m.getMemberPhone());
			pstmt.setString(4, m.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, Member login) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member_tbl where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, login.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertDelMember(Connection conn, Member login) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into del_member values(?, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, login.getMemberId());
			pstmt.setString(2, login.getMemberName());
			pstmt.setString(3, login.getMemberPhone());
			pstmt.setInt(4, login.getMemberAge());
			pstmt.setString(5, login.getMemberGender());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
