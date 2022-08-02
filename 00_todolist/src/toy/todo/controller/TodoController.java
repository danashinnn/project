package toy.todo.controller;

import java.util.ArrayList;

import toy.todo.service.TodoService;
import toy.todo.view.TodoView;
import toy.todo.vo.Todo;

public class TodoController {
	
	private TodoView view;
	private TodoService service;

	public TodoController() {
		super();
		view = new TodoView();
		service = new TodoService();
	}
	
	public void main() {
		while(true) {
			String pw = view.login();
			if(pw.equals("0423")) {
				while(true) {
					int sel = view.showMenu();
					switch(sel) {
					case 1:
						// 전체 목록 보기
						selectAllTodo();
						break;
					case 2:
						// 상세 내용 보기
						selectOneTodo();
						break;
					case 3:
						// 할 일 추가하기
						insertTodo();
						break;
					case 4:
						// 할 일 수정하기
						updateTodo();
						break;
					case 5:
						// 할 일 삭제하기
						deleteTodo();
						break;
					case 6:
						// 완료한 목록 보기
						selectAllComTodo();
						break;
					case 0:
						// 프로그램 종료
						view.exit();
						return;
					default:
						view.incorrectInput();
						break;
					}
				} 
			} else {
				view.noLogin();
			}
		}
	} // 메인 메소드 끝

	public void selectAllTodo() {
		ArrayList<Todo> list = service.selectAllTodo();
		if(list.isEmpty()) {
			view.noTodo();
		} else {
			view.selectAllTodo(list);
		}
	} // 전체 목록 보기 끝

	public void selectOneTodo() {
		int num = view.getNo("상세 내용 보기", "검색");
		Todo t = service.selectOneTodo(num);
		if(t == null) {
			view.wrongTodo();
		} else {
			view.selectOneTodo(t);
		}
	} // 상세 내용 보기 끝

	public void insertTodo() {
		Todo t = view.insertTodo();
		int result = service.insertTodo(t);
		if (result > 0) {
			view.success("할 일 추가");
		} else {
			view.fail("할 일 추가");
		}
	} // 할 일 추가하기 끝

	public void updateTodo() {
		int num = view.getNo("할 일 수정하기", "수정");
	} // 할 일 수정하기 끝

	public void deleteTodo() {
		
	} // 할 일 삭제하기 끝

	public void selectAllComTodo() {
		
	} // 완료한 목록 보기 끝
}
