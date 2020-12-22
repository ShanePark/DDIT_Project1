package controller;

import java.util.Map;

import service.UserService;
import util.ScanUtil;
import util.View;

public class Controller {

	public static void main(String[] args) {// main method는 자유롭게 테스팅용으로 사용하세요
		new Controller().start();
	}
	
	public static Map<String, Object> user;
	private UserService userService = UserService.getInstance();
	
	
	
	
	private void start(){	//작성 필요
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.MAIN: view=menu(); break;
			case View.SIGNIN: view= userService.signIn();break;
			case View.SIGNUP: view=userService.signUp(); break;
			case View.USER_MAIN:
				System.out.println("메인 화면 출력");
				ScanUtil.nextLine();
				break;
			default : break;
			}
		}
		
	}
	

	private int menu(){	//작성 필요
		
		System.out.println("====================오늘 뭐먹지?====================");
		System.out.println("1.회원가입\t2.로그인\t3.비회원 이용\t4.종료");
		System.out.print("번호 입력 >");
		
		int input = ScanUtil.nextInt();
		
		switch(input){
		case 1: return View.SIGNUP;
		case 2: return View.SIGNIN;
		case 3: return View.USER_MAIN;
		case 0:
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);
			break;
		}
		return View.USER_MAIN;
	}

}
