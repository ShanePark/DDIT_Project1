package service;

import util.ScanUtil;
import util.View;

public class AdminService {

	private static AdminService instance;
	private AdminService(){}
	public static AdminService getInstance(){
		if(instance == null){
			instance = new AdminService();
		}
		return instance;
	}
	
	public int resAdd(){	// 추가 필요
		return 0;
	}
	
	public int resMod(){	// 추가 필요
		return 0;
	}
	
	public int adminMain(){	// 추가 필요
		int select = 1;
		
		main:while(true){
			System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
			System.out.println("                                    🍽️ 오늘 뭐먹지? 🍽️\n");
			System.out.println("                                      👔관리자 전용                                              ");
			System.out.println(" ");                   
			if(select ==1)
				System.out.print("             ■ ");
			else
				System.out.print("             □ ");
			System.out.print("식당관리\n");
			
			if(select ==2)
				System.out.print("             ■ ");
			else
				System.out.print("             □ ");
			System.out.print("게시판 관리\n");
			
			if(select ==3)
				System.out.print("             ■ ");
			else
				System.out.print("             □ ");
			System.out.print("로그아웃\n ");         
			System.out.print("                           (2)↓ (5)↑ (⏎)확인 \n");        
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");

			switch(ScanUtil.nextLine()){
			case "5":
				if(select==1)
					select=3;
				else select--;
				break;
			case "2":
				if(select==3)
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
		case 1: return View.RESTAURANT_MANAGE;
		case 2: return View.BOARD_MANAGE;
		case 3: return View.MAIN;
		default:
			return View.ADMIN_MAIN;
		}
	}

	public int manageRestaurant(){
		int select = 1;

		main:while(true){
			System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
			System.out.println("                                    🍽️ 오늘 뭐먹지? 🍽️\n");
			System.out.println("                                      🥄식당 관리🥢                                              ");
			System.out.println(" ");                   
			if(select ==1)
				System.out.print("             ■ ");
			else
				System.out.print("             □ ");
			System.out.print("식당추가\n");
			
			if(select ==2)
				System.out.print("             ■ ");
			else
				System.out.print("             □ ");
			System.out.print("식당수정\n");
			
			if(select ==3)
				System.out.print("             ■ ");
			else
				System.out.print("             □ ");
			System.out.print("뒤로가기\n ");         
			System.out.print("                           (2)↓ (5)↑ (⏎)확인 \n");        
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");

			switch(ScanUtil.nextLine()){
			case "5":
				if(select==1)
					select=3;
				else select--;
				break;
			case "2":
				if(select==3)
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
		case 1: return View.RESTAURANT_ADD;
		case 2: return View.RESTAURANT_MOD;
		case 3: return View.ADMIN_MAIN;
		default:
			return View.ADMIN_MAIN;
		}
		

	}
}
