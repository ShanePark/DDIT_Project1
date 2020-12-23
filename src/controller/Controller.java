package controller;

import java.util.Map;

import service.AdminService;
import service.UserService;
import util.ScanUtil;
import util.View;
import dao.AdminDao;
import dao.UserDao;

public class Controller {

	public static void main(String[] args) {
		
//		new Controller().start();
		
//		new Controller().userTesting();
		new Controller().adminTesting();
		
	}
	
	private void userTesting(){
		UserDao userDao = UserDao.getInstance();
		Map<String, Object> user = userDao.userSignIn("user","1234");
		Controller.user = user;
		////////////////////////// 테스팅할 코드 ↓
		userService.signUp();
		
		
		////////////////////////// 테스팅할 코드 ↑
	}
	
	private void adminTesting(){
		AdminDao adminDao = AdminDao.getInstance();
		UserDao userDao = UserDao.getInstance();
		Map<String, Object> user = userDao.userSignIn("admin","password");
		Controller.user = user;
		
		////////////////////////// 테스팅할 코드 ↓
		adminService.manageRestaurant();
		
		
		////////////////////////// 테스팅할 코드 ↑
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Map<String, Object> user;
	private UserService userService = UserService.getInstance();
	private AdminService adminService = AdminService.getInstance();
	
	
	
	
	private void start(){	
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.MAIN: view=menu(); break;
			case View.SIGNIN:	  view = userService.signIn(); 		break;
			case View.SIGNUP: 	  view = userService.signUp();	 	break;
			case View.USER_MAIN:  view = userService.userMain(); 	break;
			case View.ADMIN_MAIN: view = adminService.adminMain(); 	break;
			case View.RESTAURANT_MANAGE: view = adminService.manageRestaurant(); 	break;
			case View.RESTAURANT_ADD: view = adminService.resAdd(); 	break;
			case View.RESTAURANT_MOD: view = adminService.resMod(); 	break;
			default : 
				System.out.println("아직 구현이 필요한 기능");
				ScanUtil.nextLine();
				break;
			}
		}
		
	}
	

	private int menu(){	
		
		int select = 2;
		main:while(true){
			System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
			System.out.println("\n                                    🍽️ 오늘 뭐먹지? 🍽️\n");
			if(select ==1)
				System.out.print("           ■");
			else
				System.out.print("           □");
			System.out.print(" 회원가입\n");
			if(select ==2)
				System.out.print("           ■");
			else
				System.out.print("           □");
			System.out.print(" 로그인\n");
			if(select ==3)
				System.out.print("           ■");
			else
				System.out.print("           □");
			System.out.print(" 비회원 이용\n");
			if(select ==4)
				System.out.print("           ■");
			else
				System.out.print("           □");
			System.out.print(" 종료\n");
			System.out.print("\n                           (2)↓ (5)↑ (⏎)확인 \n");
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
			
			switch(ScanUtil.nextLine()){
			case "5":
				if(select==1)
					select=4;
				else select--;
				break;
			case "2":
				if(select==4)
					select=1;
				else select++;
				break;
			case "":
				break main;
			default:
				break;

			}
		}
		
		switch(select){
		case 1: return View.SIGNUP;
		case 2: return View.SIGNIN;
		case 3: return userService.guestMode();
		case 4:
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);
			break;
		}
		return View.USER_MAIN;
	}
	

}
