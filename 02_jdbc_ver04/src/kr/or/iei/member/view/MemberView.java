package kr.or.iei.member.view;

import java.util.ArrayList;
import java.util.Scanner;
import kr.or.iei.member.model.vo.Member;

public class MemberView {
	
	private Scanner sc;

	public MemberView() {
		super();
		sc = new Scanner(System.in);
	}

	public int mainMenu() {
		System.out.println("\n===== 회원 관리 프로그램 v4 (복습) =====\n");
		System.out.println("1. 로그인");
		System.out.println("2. 회원 가입");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >>> ");
		return sc.nextInt();
	}

	public int loginMenu(String memberName) {
		System.out.println("\n===== [ " + memberName + " 님 안녕하세요! ] =====\n");
		System.out.println("1. 내 정보 조회");
		System.out.println("2. 이름으로 회원 조회");
		System.out.println("3. 내 정보 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("0. 로그아웃");
		System.out.print("선택 >>> ");
		return sc.nextInt();
	}
	
	///////////////////////////////////////////////// 로그인 안 했을 때 메뉴
	
	public Member getLoginInfo() {
		System.out.println("\n===== 로그인 =====\n");
		Member m = new Member();
		System.out.print("아이디 : ");
		m.setMemberId(sc.next());
		System.out.print("비밀번호 : ");
		m.setMemberPw(sc.next());
		return m;
	}

	public Member insertMember() {
		Member m = new Member();
		System.out.print("비밀번호 : ");
		m.setMemberPw(sc.next());
		System.out.print("이름 : ");
		m.setMemberName(sc.next());
		System.out.print("주소 : ");
		sc.nextLine();
		m.setMemberAddr(sc.nextLine());
		System.out.print("전화번호 [010-0000-0000] : ");
		m.setMemberPhone(sc.next());
		System.out.print("나이 : ");
		m.setMemberAge(sc.nextInt());
		System.out.print("성별 [남 / 여] : ");
		m.setMemberGender(sc.next());
		return m;
	}
	
	///////////////////////////////////////////////// 로그인 했을 때 메뉴

	public void selectMyInfo(Member login) {
		System.out.println("\n===== 내 정보 조회 =====\n");
		System.out.println("아이디 : " + login.getMemberId());
		System.out.println("비밀번호 : " + login.getMemberPw());
		System.out.println("이름 : " + login.getMemberName());
		System.out.println("주소 : " + login.getMemberAddr());
		System.out.println("전화번호 : " + login.getMemberPhone());
		System.out.println("나이 : " + login.getMemberAge());
		System.out.println("성별 : " + login.getMemberGender());
		System.out.println("가입일 : " + login.getEnrollDate());
	}

	public String getName() {
		System.out.println("\n===== 이름으로 회원 조회 =====\n");
		System.out.print("조회할 회원 이름 : ");
		return sc.next();
	}
	
	public void selectNameMember(ArrayList<Member> list) {
		System.out.println("\n===== 회원 조회 결과 =====\n");
		System.out.println("아이디\t비밀번호\t이름\t주소\t\t전화번호\t\t나이\t성별\t가입일");
		System.out.println("============================================================================");
		for(Member m : list) {
			System.out.print(m.getMemberId() + "\t");
			System.out.print(m.getMemberPw() + "\t");
			System.out.print(m.getMemberName() + "\t");
			System.out.print(m.getMemberAddr() + "\t");
			System.out.print(m.getMemberPhone() + "\t");
			System.out.print(m.getMemberAge() + "\t");
			System.out.print(m.getMemberGender() + "\t");
			System.out.println(m.getEnrollDate());
		}
	}

	public Member updateMember() {
		System.out.println("\n===== 내 정보 수정 =====\n");
		Member m = new Member();
		System.out.print("비밀번호 : ");
		m.setMemberPw(sc.next());
		System.out.print("주소 : ");
		sc.nextLine();
		m.setMemberAddr(sc.nextLine());
		System.out.print("전화번호 [010-0000-0000] : ");
		m.setMemberPhone(sc.next());
		return m;
	}

	public char deleteMember() {
		System.out.println("\n===== 회원 탈퇴 =====\n");
		System.out.print("탈퇴하시겠습니까? [ Y / N ] : ");
		return sc.next().charAt(0);
	}
	///////////////////////////////////////////////// 기타 메뉴
	public void incorrectInput() {
		System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
	}
	
	public void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	public void fail(String str) {
		System.out.println(str + " 실패. 다시 시도해주세요.");
	}

	public void success(String str) {
		System.out.println(str + " 성공!");
	}

	public String getId() {
		System.out.println("\n===== 회원가입 =====\n");
		System.out.print("가입할 아이디 : ");
		return sc.next();
	}

	public void checkId() {
		System.out.println("이미 사용중인 아이디입니다.");
	}

	public char chkLogout() {
		System.out.println("\n===== 로그아웃 =====\n");
		System.out.print("로그아웃 하시겠습니까? [ Y / N ] : ");
		return sc.next().charAt(0);
	}

	public void logout() {
		System.out.println("초기 메뉴로 돌아갑니다.");
	}

	public void noSearch() {
		System.out.println("일치하는 회원이 없습니다.");
	}




	
}
