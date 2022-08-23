package kr.or.iei.member.controller;

import java.util.ArrayList;

import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.view.MemberView;

public class MemberController {

	private MemberView view;
	private MemberDao dao;
	
	public MemberController() {
		super();
		view = new MemberView();
		dao = new MemberDao();
	}

	public void main() {
		while(true) {
			int sel = view.showMenu();
			switch(sel) {
			case 1:
				selectAllMember();
				break;
			case 2:
				searchIdMember();
				break;
			case 3:
				searchNameMember();
				break;
			case 4:
				insertMember();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				view.exit();
				return;
			default:
				view.incorrect();
				break;
			}
		}
	}

	public void selectAllMember() {
		ArrayList<Member> list = dao.selectAllMember();
		if(list.isEmpty()) {
			view.noMember();
		} else {
			view.selectAllMember(list, "전체");
		}
	}

	public void searchIdMember() {
		String id = view.getId("조회", "아이디");
		Member m = dao.searchIdMember(id);
		if(m != null) {
			view.searchOneMember(m);
		} else {
			view.noSearch();
		}
	}

	public void searchNameMember() {
		String name = view.getId("조회", "이름");
		ArrayList<Member> list = dao.searchNameMember(name);
		if(list.isEmpty()) {
			view.noSearch();
		} else {
			view.selectAllMember(list, "이름으로");
		}
	}

	public void insertMember() {
		String chkId = "";
		while(true) {
			chkId = view.getId("가입", "아이디");
			Member chkMember = dao.searchIdMember(chkId);
			if(chkMember != null) { // 이미 가입된 아이디
				view.alreadyJoin();
			} else { // 가입 가능한 아이디
				break;
			}			
		}
		Member m = view.insertMember();
		m.setMemberId(chkId);
		int result = dao.insertMember(m);
		if(result > 0) {
			view.success("가입");
		} else {
			view.fail("가입");
		}
	}

	public void updateMember() {
		String id = view.getId("수정", "아이디");
		Member m = dao.searchIdMember(id);
		if(m != null) {
			view.updateMember(m);
			int result = dao.updateMember(m);
			if(result > 0) {
				view.success("수정");
			} else {
				view.fail("수정");
			}
		} else {
			view.noSearch();
		}
	}

	public void deleteMember() {
		String id = view.getId("삭제", "아이디");
		Member m = dao.searchIdMember(id);
		if(m != null) {
			int result = dao.deleteMember(m);
			if(result > 0) {
				view.success("삭제");
			} else {
				view.fail("삭제");
			}
		} else {
			view.noSearch();
		}
	}
}
