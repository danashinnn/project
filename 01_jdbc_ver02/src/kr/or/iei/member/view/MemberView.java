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

	public int showMenu() {
		System.out.println("\n===== 회원 관리 프로그램 v2 복습 =====\n");
		System.out.println("1. 전체 회원 조회");
		System.out.println("2. 아이디로 회원 조회");
		System.out.println("3. 이름으로 회원 조회");
		System.out.println("4. 회원 가입");
		System.out.println("5. 회원 정보 수정");
		System.out.println("6. 회원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >>> ");
		return sc.nextInt();
	}

	public void incorrect() {
		System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
	}

	public void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	public void noMember() {
		System.out.println("등록된 회원이 없습니다.");
	}

	public void selectAllMember(ArrayList<Member> list, String str) {
		System.out.println("\n===== " + str + " 회원 조회 =====\n");
		System.out.println("아이디\t비밀번호\t이름\t주소\t\t전화번호\t\t나이\t성별\t가입일");
		System.out.println("===================================================================================");
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

	public String getId(String str1, String str2) {
		System.out.println("\n===== 회원 " + str1 + " =====\n");
		System.out.print(str1 + "할 " + str2 + " : ");
		return sc.next();
	}

	public void noSearch() {
		System.out.println("회원 정보를 찾을 수 없습니다.");
	}

	public void searchOneMember(Member m) {
		System.out.println("\n===== 아이디로 회원 조회 =====\n");
		System.out.println("아이디 : " + m.getMemberId());
		System.out.println("비밀번호 : " + m.getMemberPw());
		System.out.println("이름 : " + m.getMemberName());
		System.out.println("주소 : " + m.getMemberAddr());
		System.out.println("전화번호 : " + m.getMemberPhone());
		System.out.println("나이 : " + m.getMemberAge());
		System.out.println("성별 : " + m.getMemberGender());
		System.out.println("가입일 : " + m.getEnrollDate());
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
		System.out.print("성별 [남/여] : ");
		m.setMemberGender(sc.next());
		return m;
	}

	public void alreadyJoin() {
		System.out.println("이미 가입된 아이디입니다.");
	}

	public void success(String str) {
		System.out.println("회원 " + str + " 성공!");
	}

	public void fail(String str) {
		System.out.println("회원 " + str + " 실패. 다시 시도해주세요.");
	}

	public void updateMember(Member m) {
		System.out.println("\n===== 회원 정보 수정 =====\n");
		System.out.print("비밀번호 : ");
		m.setMemberPw(sc.next());
		System.out.print("주소 : ");
		sc.nextLine();
		m.setMemberAddr(sc.nextLine());
		System.out.print("전화번호 [010-0000-0000] : ");
		m.setMemberPhone(sc.next());
	}

}
