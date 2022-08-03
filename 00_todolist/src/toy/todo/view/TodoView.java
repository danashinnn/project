package toy.todo.view;

import java.util.ArrayList;
import java.util.Scanner;

import toy.todo.vo.Todo;

public class TodoView {

	private Scanner sc;

	public TodoView() {
		super();
		sc = new Scanner(System.in);
	}

	////////////////////////////////////////////// 로그인
	
	public String login() {
		System.out.println("\n===== 다혜의 To Do List! =====\n");
		System.out.print("비밀번호를 입력하세요. >>> ");
		return sc.next();
	}

	public void noLogin() {
		System.out.println("비밀번호가 틀렸습니다. ");
	}
	
	////////////////////////////////////////////// 메인 메뉴
	
	public int showMenu() {
		System.out.println("\n===== MENU =====\n");
		System.out.println("1. 전체 목록 보기");
		System.out.println("2. 상세 내용 보기");
		System.out.println("3. 할 일 추가하기");
		System.out.println("4. 할 일 수정하기");
		System.out.println("5. 할 일 삭제하기");
		System.out.println("6. 완료한 목록 보기");
		System.out.println("0. 프로그램 종료하기");
		System.out.print("선택 >>> ");
		return sc.nextInt();
	}
	
	////////////////////////////////////////////// 상세 메뉴

	public void selectAllTodo(ArrayList<Todo> list) {
		System.out.println("\n===== 전체 목록 보기 =====\n");
		System.out.println("No.\t할 일");
		System.out.println("=======================");
		for(Todo t : list) {
			System.out.print(t.getNo() + "\t");
			System.out.println(t.getTitle());
		}
	}

	public void selectOneTodo(Todo t) {
		System.out.println("\n===== 상세 내용 보기 =====\n");
		System.out.println("No." + t.getNo());
		System.out.println("할 일 : " + t.getTitle());
		System.out.println("내 용 : " + t.getContent());
		System.out.println("중요도 : " + t.getStatus());
		System.out.println("등록일 : " + t.getWriteDate());
		System.out.println("\n=======================\n");
	}
	
	public Todo insertTodo() {
		Todo t = new Todo();
		System.out.println("\n===== 할 일 추가하기 =====\n");
		System.out.print("할 일 : ");
		sc.nextLine();
		t.setTitle(sc.nextLine());
		System.out.print("상세 내용 : ");
		t.setContent(sc.nextLine());
		System.out.print("중요도 [중요 / 보통 / 여유] : ");
		t.setStatus(sc.next());
		return t;
	}

	public Todo updateTodo(Todo t) {
		System.out.println("\n===== 할 일 수정하기 =====\n");
		System.out.print("할 일 : ");
		sc.nextLine();
		t.setTitle(sc.nextLine());
		System.out.print("상세 내용 : ");
		t.setContent(sc.nextLine());
		System.out.print("중요도 [중요 / 보통 / 여유] : ");
		t.setStatus(sc.next());
		return t;
	}
	////////////////////////////////////////////// 기타 메세지
	
	public void exit() {
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void incorrectInput() {
		System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
	}

	public void success(String str) {
		System.out.println(str + " 성공!");
	}

	public void fail(String str) {
		System.out.println(str + " 실패. 다시 시도해주세요.");
	}

	public void noTodo() {
		System.out.println("등록된 할 일이 없습니다.");
	}

	public void wrongTodo() {
		System.out.println("일치하는 할 일 번호가 없습니다. ");
	}
	
	public int getNo(String str1, String str2) {
		System.out.println("\n===== "+ str1 +" =====\n");
		System.out.print(str2 + "할 할 일 번호를 입력하세요. >>> ");
		return sc.nextInt();
	}







	
	
}
