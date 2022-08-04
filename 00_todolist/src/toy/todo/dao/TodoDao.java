package toy.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import common.JDBCTemplate;
import toy.todo.vo.Complete;
import toy.todo.vo.Todo;

public class TodoDao {

	public ArrayList<Todo> selectAllTodo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		String query = "select * from todo order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo t = new Todo();
				t.setNo(rset.getInt(1));
				t.setTitle(rset.getString(2));
				t.setContent(rset.getString(3));
				t.setStatus(rset.getString(4));
				t.setWriteDate(rset.getDate(5));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public Todo selectOneTodo(Connection conn, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Todo t = null;
		String query = "select * from todo where no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				t = new Todo();
				t.setNo(rset.getInt(1));
				t.setTitle(rset.getString(2));
				t.setContent(rset.getString(3));
				t.setStatus(rset.getString(4));
				t.setWriteDate(rset.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return t;
	}
	
	public int insertTodo(Connection conn, Todo t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into todo values(SEQ_TODO.nextval, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getContent());
			pstmt.setString(3, t.getStatus());
			result = pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException sqle) {
			System.out.println("중요도는 [중요 / 보통 / 여유] 중에서 입력해주세요.");			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateTodo(Connection conn, Todo t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update todo set title = ?, content = ?, status = ? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getContent());
			pstmt.setString(3, t.getStatus());
			pstmt.setInt(4, t.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException sqle) {
			System.out.println("중요도는 [중요 / 보통 / 여유] 중에서 입력해주세요.");			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteTodo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from todo where no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertComplete(Connection conn, Todo t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into complete values(SEQ_COMPLETE.nextval, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, t.getNo());
			pstmt.setString(2, t.getTitle());
			pstmt.setString(3, t.getContent());
			pstmt.setDate(4, t.getWriteDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Complete> selectAllComTodo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Complete> list = new ArrayList<Complete>();
		String query = "select * from complete order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Complete c = new Complete();
				c.setComNo(rset.getInt(1));
				c.setTodoNo(rset.getInt(2));
				c.setComTitle(rset.getString(3));
				c.setComContent(rset.getString(4));
				c.setStartDate(rset.getDate(5));
				c.setEndDate(rset.getDate(6));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}
}
