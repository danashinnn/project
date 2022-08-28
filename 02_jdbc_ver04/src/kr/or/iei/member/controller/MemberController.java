package kr.or.iei.member.controller;

import java.util.ArrayList;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.view.MemberView;

public class MemberController {
	
	private MemberView view;
	private MemberService service;
	private Member login;
	
	public MemberController() {
		super();
		view = new MemberView();
		service = new MemberService();
	}

	public void main() {
		while(true) {
			if (login == null) {
				int sel = view.mainMenu();
				switch(sel) {
				case 1:
					login();
					break;
				case 2:
					joinMember();
					break;
				case 0:
					view.exit();
					return;
				default:
					view.incorrectInput();
					break;
				}
			} else { // 로그인 했을 경우
				int sel = view.loginMenu(login.getMemberName());
				switch(sel) {
				case 1:
					selectMyInfo();
					break;
				case 2:
					selectNameMember();
					break;
				case 3:
					updateMember();
					break;
				case 4:
					deleteMember();
					break;
				case 0:
					logout();
					break;
				default:
					view.incorrectInput();
					break;
				}
			}
			
		}
	}

	/////////////////////////// 로그인 안 했을 때 메뉴
	
	private void login() {
		Member m = view.getLoginInfo();
		Member m2 = service.selectOneMember(m);
		if(m2 == null) {
			view.fail("로그인");
		} else {
			login = m2;
			view.success("로그인");
		}
	}

	private void joinMember() {
		String id = "";
		while(true) {
			id = view.getId();
			boolean check = service.selectOneMember(id);
			if(check) { // 가입한 적 있는 아이디인 경우
				view.checkId();
			} else { // 사용 가능한 아이디인 경우
				break;
			}
		}
		Member m = view.insertMember();
		m.setMemberId(id);
		int result = service.insertMember(m);
		if(result > 0) {
			view.success("회원가입");
		} else {
			view.fail("회원가입");
		}
	}

	/////////////////////////// 로그인 했을 때 메뉴

	private void selectMyInfo() {
		view.selectMyInfo(login);
	}

	private void selectNameMember() {
		String name = view.getName();
		ArrayList<Member> list = service.selectNameMember(name);
		if(list.isEmpty()) {
			view.noSearch();
		} else {
			view.selectNameMember(list);
		}
	}

	private void updateMember() {
		// view에 login 객체 주소값을 바로 전달해주면
		// 이후에 dao에서 update 쿼리문을 실패하는 경우
		// login 객체 정보만 수정되고 DB상 데이터는 고쳐지지 않게 되므로,
		// 새로운 객체를 만들어서 진행해야함 
		Member m = view.updateMember();
		m.setMemberId(login.getMemberId());
		int result = service.updateMember(m);
		if(result > 0) {
			// 내 정보 조회 시 DB 값을 가져와서 조회하는 것이 아니고
			// login 객체를 통해서 바로 view로 보여주는 경우, 
			// 내 정보를 수정하면 login 객체에 수정 정보를 다시 저장해줘야 함.
			login = service.selectOneMember(m);
			view.success("내 정보 수정");
		} else {
			view.fail("내 정보 수정");
		}
	}

	private void deleteMember() {
		char check = Character.toUpperCase(view.deleteMember());
		if(check == 'N') {
			return;
		} else if (check == 'Y') {
			int result = service.deleteMember(login);
			if(result > 0) {
				login = null;
				view.success("회원 탈퇴");
				view.logout();				
			} else {
				view.fail("회원 탈퇴");
			}
		} else {
			view.incorrectInput();
		}
	}

	private void logout() {
		char check = Character.toUpperCase(view.chkLogout());
		if(check == 'N') {
			return;
		} else if (check == 'Y') {
			login = null;
			view.logout();
		} else {
			view.incorrectInput();
		}
	}
}
