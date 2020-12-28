package controller;

import java.util.Map;

import service.AdminService;
import service.UserService;
import util.PrintUtil;
import util.ScanUtil;
import util.View;
import dao.AdminDao;
import dao.UserDao;

public class Controller {

	public static void main(String[] args) {
		
		new Controller().start();
		
//		new Controller().userTesting();		// 유저 기능 테스트용
//		new Controller().adminTesting();	// 관리자 기능 테스트용
//		new Controller().error();			// Controller Method 테스트용
		
	}
	
	private void userTesting(){
		UserDao userDao = UserDao.getInstance();
		Map<String, Object> user = userDao.userSignIn("user","1234");
		Controller.user = user;
		////////////////////////// USER 테스팅할 코드 ↓
		
//		userService.viewMenu("1");
		
		////////////////////////// USER 테스팅할 코드 ↑
	}
	
	private void adminTesting(){
		AdminDao adminDao = AdminDao.getInstance();
		UserDao userDao = UserDao.getInstance();
		Map<String, Object> user = userDao.userSignIn("admin","password");
		Controller.user = user;
		
		////////////////////////// ADMIN 테스팅할 코드 ↓
		
		adminService.resManage("1");
		
		////////////////////////// ADMIN 테스팅할 코드 ↑
		
	}
	
	
	public static Map<String, Object> user;
	private UserService userService = UserService.getInstance();
	private AdminService adminService = AdminService.getInstance();
	
	
	
	
	private void start(){	
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.MAIN:	 view=menu(); 	break;
			case View.ERROR: view = error(); break;
			case View.SIGNIN:	  view = userService.signIn(); 		break;
			case View.SIGNUP: 	  view = userService.signUp();	 	break;
			case View.ADMIN_MAIN: view = adminService.adminMain(); 	break;
			case View.RESTAURANT_MANAGE: view = adminService.manageRestaurant(); 	break;
			case View.RESTAURANT_ADD: view = adminService.resAdd(); 		break;
			case View.RESTAURANT_MOD: view = adminService.resMod(); 		break;
			case View.USER_MAIN:  view = userService.userMain(); 			break;
			case View.LUNCHBOX_ORDER: view = userService.lunchboxOrder();	break;
			case View.MYPAGE: view = userService.myPage();					break;
			case View.MYREVIEW: view = userService.myReview();				break;
			case View.PICK_LIST: view = userService.pickList();				break;
			case View.SEARCH_RES : view = userService.searchRes();			break;
			default : 
				System.out.println("해당 View 번호에 대한 case가 start()에 존재하지 않습니다.");
				ScanUtil.nextLine();
				break;
			}
		}
		
	}
	

	private int menu(){	
		
		int select = 2;
		main:while(true){
			PrintUtil.title();
			String[] menu = {" 회원가입\n"," 로그인\n"," 비회원 이용\n"," 종료\n"};

			for(int i=0; i<menu.length; i++){
				if(select ==i+1)		System.out.print("           ■");
				else				System.out.print("           □");
				System.out.print(menu[i]);
			}
			PrintUtil.joystick();
			
			switch(ScanUtil.nextLine()){
			case "5": if(select==1) select=menu.length;		else select--;	break;
			case "2": if(select==menu.length)	select=1;	else select++;	break;
			case "":	break main;
			default:	break;			}
		}
		
		switch(select){
		case 1: return View.SIGNUP;
		case 2: return View.SIGNIN;
		case 3: return userService.guestMode();
		case 4:	System.out.println("프로그램이 종료되었습니다."); System.exit(0);
		}
		return View.USER_MAIN;
	}
	
	private int error(){
		PrintUtil.title();
		System.out.println("\n\n\t미구현된 기능입니다.");
		System.out.println("\t엔터키를 누르면 처음으로 돌아갑니다.");
		PrintUtil.joystick();
		ScanUtil.nextLine();
		return View.MAIN;
	}
	

}
