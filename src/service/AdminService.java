package service;

import controller.Controller;
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

	public int adminMain(){
		int select = 1;
		String nickname = Controller.user.get("NICKNAME").toString();

		main:while(true){
			System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
			for(int i=nickname.length(); i<32; i++)
				System.out.print(" ");
			System.out.printf("[%s](으)로 접속중\n",nickname);
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
			System.out.print("식당수정\n ");         
			System.out.print("\n                           (2)↓ (5)↑ (⏎)확인 \n");        
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");

			switch(ScanUtil.nextLine()){
			case "5":
				if(select==1)
					select=2;
				else select--;
				break;
			case "2":
				if(select==2)
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
		}
		return View.USER_MAIN;
		
		

	}
}
