package controller;

import java.util.Map;

import service.AdminService;
import service.BoardService;
import service.BoxService;
import service.UserService;
import util.LoadingUtil;
import util.PrintUtil;
import util.ScanUtil;
import util.View;
import dao.UserDao;

public class Controller {

	public static void main(String[] args) {
//		LoadingUtil.loadingTest();
//		LoadingUtil.loading();
		new Controller().start();
		
//		new Controller().Testing();		// 테스트용
		
	}
	
	private void Testing(){
		UserDao userDao = UserDao.getInstance();
		Map<String, Object> user = userDao.userSignIn("user","1234");
//		Map<String, Object> user = userDao.userSignIn("admin","password");
		Controller.user = user;
		////////////////////////// 테스팅할 코드 ↓
		
		userService.signUp();
		
		////////////////////////// 테스팅할 코드 ↑
	}
	
	
	public static Map<String, Object> user;
	private UserService userService = UserService.getInstance();
	private AdminService adminService = AdminService.getInstance();
	private BoxService boxService = BoxService.getInstance();
	private BoardService boardService = BoardService.getInstance();
	
	
	private void start(){	
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.ADMIN_MAIN : 			view = adminService.adminMain(); 			break;
			case View.BOARD_ADD : 			view = boardService.boardAdd();				break;
			case View.BOARD_ADMIN : 		view = boardService.boardRes_admin(); 		break;
			case View.BOARD_ADMIN_MANAGE : 	view = boardService.boardSelectAdmin(); 	break;
			case View.BOARD_MAIN :			view = boardService.boardList(); 			break;
			case View.BOARD_MANAGE2 : 		view = boardService.boardSelect();			break;
			case View.BOARD_USER : 			view = boardService.boardRes_user(); 		break;
			case View.BOARD_ALTER :			view = boardService.boardAlter(); 			break;
			case View.BOARD_DELETE : 		view = boardService.boardDelete(); 			break;
			case View.BOX_DAEJEON : 		view = boxService.daejeonMain();			break;
			case View.BOX_ORDER_LIST :		view = adminService.boxOrderList();			break;
			case View.BOX_MANAGE :			view = adminService.boxManage();			break;
			case View.CHANGE_NICKNAME : 	view = userService.changeNickname();		break;
			case View.CHANGE_PASSWORD :		view = userService.changePassword();		break;
			case View.CHANGE_PHONE : 		view = userService.changePhone();			break;
			case View.DELETE_ACCOUNT :		view = userService.deleteAccount();			break;
			case View.ERROR : 				view = error();								break;
			case View.LOAD_CREDIT : 		view = adminService.loadCredit();			break;
			case View.LUNCHBOX_ORDER : 		view = userService.lunchboxOrder();			break;
			case View.MAIN :				view = menu(); 								break;
			case View.MANAGE_ACCOUNT : 		view = userService.manageAccount();			break;
			case View.MANAGE_PROFILE : 		view = userService.manageProfile();			break;
			case View.MYREVIEW : 			view = userService.myReview();				break;
			case View.MYPAGE :				view = userService.myPage();				break;
			case View.MY_ORDER_LIST : 		view = userService.myOrder();				break;
			case View.PICK_LIST : 			view = userService.pickList();				break;
			case View.RESTAURANT_ADD : 		view = adminService.resAdd(); 				break;
			case View.RESTAURANT_MOD :		view = adminService.resMod(); 				break;
			case View.RESTAURANT_MANAGE :	view = adminService.manageRestaurant(); 	break;
			case View.SEARCH_COUSINE : 		view = userService.searchByCousine();		break;
			case View.SEARCH_MENU :		 	view = userService.searchByMenu();			break;
			case View.SEARCH_NAME : 		view = userService.searchByName();			break;
			case View.SEARCH_RES : 			view = userService.searchRes();				break;
			case View.SIGNIN :	 			view = userService.signIn(); 				break;
			case View.SIGNUP : 	 			view = userService.signUp();	 			break;
			case View.USER_MAIN : 			view = userService.userMain(); 				break;
			default : 						view = error();								break;
			}
		}
		
	}
	

	private int menu(){	
		
		int select = 2;
		main:while(true){
			PrintUtil.title();
			System.out.println();
			String[] menu = {" 회원가입\n"," 로그인\n"," 비회원 이용\n"," 종료\n\n"};

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
