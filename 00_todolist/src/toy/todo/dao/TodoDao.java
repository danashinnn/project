package toy.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import toy.todo.vo.Todo;

public class TodoDao {

	public ArrayList<Todo> selectAllTodo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		String query = "select * from todo";
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


}
