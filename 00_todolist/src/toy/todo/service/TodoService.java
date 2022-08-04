package toy.todo.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import toy.todo.dao.TodoDao;
import toy.todo.vo.Complete;
import toy.todo.vo.Todo;

public class TodoService {

	private TodoDao dao;

	public TodoService() {
		super();
		dao = new TodoDao();
	}

	public ArrayList<Todo> selectAllTodo() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Todo> list = dao.selectAllTodo(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public Todo selectOneTodo(int num) {
		Connection conn = JDBCTemplate.getConnection();
		Todo t = dao.selectOneTodo(conn, num);
		JDBCTemplate.close(conn);
		return t;
	}
	
	public int insertTodo(Todo t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertTodo(conn, t);
		if(result > 0) {
			JDBCTemplate.commit(conn);;
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateTodo(Todo t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateTodo(conn, t);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteTodo(Todo t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertComplete(conn, t);
		if (result > 0) {
			int result2 = dao.deleteTodo(conn, t.getNo());
			if(result2 > 0) {
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
	
	public ArrayList<Complete> selectAllComTodo() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Complete> list = dao.selectAllComTodo(conn);
		JDBCTemplate.close(conn);
		return list;
	}
}
