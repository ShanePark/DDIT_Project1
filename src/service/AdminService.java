package service;

import java.util.HashMap;
import java.util.Map;

import util.PrintUtil;
import util.ScanUtil;
import util.View;
import controller.Controller;
import dao.AdminDao;

public class AdminService {

	private static AdminService instance;
	private AdminService(){}
	public static AdminService getInstance(){
		if(instance == null){
			instance = new AdminService();
		}
		return instance;
	}

	private AdminDao adminDao = AdminDao.getInstance();
	
	public int resMod(){	// 식당 검색으로 넘겨버리겠습니다
		return View.SEARCH_RES;
	}

	public int adminMain(){	//////////////////////// 관리자용 홈  View 3
		int select = 1;

		main:while(true){
			PrintUtil.title();
			System.out.println("                                      👔관리자 전용                                              ");
			System.out.println(" ");              

			String[] selects = {"식당관리\n","뒤로가기\n","로그아웃 "};
			for(int i=0; i<selects.length; i++){
				if(select ==i+1)	System.out.print("             ■ ");
				else				System.out.print("             □ ");
				System.out.print(selects[i]);
			}
			PrintUtil.joystick();

			switch(ScanUtil.nextLine()){
			case "5":	if(select==1)	select=selects.length;	else select--;	break;
			case "2":	if(select==selects.length)	select=1;	else select++;	break;
			case "":	break main;
			default:	break;			}
			
		}

		switch(select){
		case 1: return View.RESTAURANT_MANAGE;
		case 2: return View.USER_MAIN;
		case 3: return View.MAIN;
		default: return View.ADMIN_MAIN;
		}
	}

	public int manageRestaurant(){	//////////////////////// 식당관리  View 30
		int select = 1;

		main:while(true){
			PrintUtil.title();
			System.out.println("                                      🥄식당 관리🥢                                              ");
			System.out.println(" ");       
			String[] menu = {"식당추가\n","식당수정\n","뒤로가기 "};
			
			for(int i=0; i<menu.length; i++){
				if(select ==i+1)	System.out.print("             ■ ");
				else				System.out.print("             □ ");
				System.out.print(menu[i]);
			}
			
			PrintUtil.joystick();

			switch(ScanUtil.nextLine()){
			case "5":	if(select==1)	select=menu.length;	else select--;	break;
			case "2":	if(select==menu.length)	select=1;	else select++;	break;
			case "":	break main;
			default:	break;
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
	
	public int resAdd(){	//////////////////////// 식당 추가  View 301

		int select = 1;
		int[] complete = {0,0,0}; 
		String resName="",cousine="",add1="",openTime="",closeTime="";
		int distance=0;

		addRes:while(true){
			input:while(true){
				int max = 4;
				if(complete[0]==1 && complete[1]==1 && complete[2]==1)
					max = 5;
				PrintUtil.title();
				System.out.println("                                    🥄식당 추가🥢");
				if(select ==1)		System.out.print("           ■");
				else				System.out.print("           □");
				if(complete[0]==0)
					System.out.print(" 가게명 음식스타일  입력하기 \n");
				else
					System.out.printf(" 가게명: %s [%s] \n",resName,cousine);
				if(select ==2)		System.out.print("           ■");
				else				System.out.print("           □");
				if(complete[1]==0)
					System.out.print(" 주소,거리  입력하기 \n");
				else
					System.out.printf(" 주소: %s [거리 %dm] \n",add1,distance);
				if(select ==3)		System.out.print("           ■");
				else				System.out.print("           □");
				if(complete[2]==0)
					System.out.print(" 영업시간  입력하기\n");
				else
					System.out.printf(" 영업시간  [%s - %s]\n",openTime,closeTime);
				if(select ==4)		System.out.print("           ■");
				else				System.out.print("           □");
					System.out.print(" 뒤로가기 ");
				if(complete[0]==1 && complete[1]==1 && complete[2]==1){
					if(select ==5)		System.out.print("\n           ■");
					else				System.out.print("\n           □");
					System.out.print(" 입력 완료! 식당 등록하기 ");
				}else System.out.println();
				System.out.print("\t\t");
				if(!(complete[0]==1 && complete[1]==1 && complete[2]==1))
					PrintUtil.joystick2();
				else
					PrintUtil.printBar2();

				switch(ScanUtil.nextLine()){
				case "5":
					if(select==1)
						select=max;
					else select--;
					break;
				case "2":
					if(select==max)
						select=1;
					else select++;
					break;
				case "":
					break input;
				default:
					break;
				}
			}
		switch(select){
		case 1: if(complete[0]==1){break;}
		else{
			PrintUtil.title();
			System.out.println("                                    🥄식당 추가🥢\n\n");
			System.out.println("                      식당 이름을 입력해주세요\n\n");
			PrintUtil.printBar();
			resName = ScanUtil.nextLine();

			PrintUtil.title();
			System.out.println("                                    🥄식당 추가🥢\n\n");
			System.out.println("                       식당 이름 : " + resName);
			System.out.println("                      음식스타일을 입력해주세요\n");
			PrintUtil.printBar();
			cousine = ScanUtil.nextLine();

			complete[0] = 1;

		}
		break;
		case 2:if(complete[1]==1){break;}
		else{
			PrintUtil.title();
			System.out.println("                                    🥄식당 추가🥢\n\n");
			System.out.println("                        주소를 입력해주세요\n\n");
			PrintUtil.printBar();
			add1 = ScanUtil.nextLine();

			PrintUtil.title();
			System.out.println("                                    🥄식당 추가🥢\n\n");
			System.out.println("                       주소 : " + add1);
			System.out.println("               학원으로부터 거리(m)를 숫자로 입력해주세요\n");
			PrintUtil.printBar();
			distance = Integer.parseInt(ScanUtil.nextLine());

			complete[1] = 1;

		}
		break;
		case 3:if(complete[2]==1){break;}
		else{
			PrintUtil.title();
			System.out.println("                                    🥄식당 추가🥢\n\n");
			System.out.println("                    오픈시간을 입력해주세요 (예)12:00\n\n");
			PrintUtil.printBar();
			openTime = ScanUtil.nextLine();

			PrintUtil.title();
			System.out.println("                                    🥄식당 추가🥢\n\n");
			System.out.println("                       오픈시간 " + openTime);
			System.out.println("                    마감시간을 입력해주세요 (예)12:00\n");
			PrintUtil.printBar();
			closeTime = ScanUtil.nextLine();

			complete[2] = 1;
		}
		break;
		case 4:
			return View.RESTAURANT_MANAGE;
		case 5:
			break addRes;
		}
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("RES_NAME", resName);
		param.put("COUSINE", cousine);
		param.put("OPEN_TIME", openTime);
		param.put("CLOSE_TIME", closeTime);
		param.put("ADD1", add1);
		param.put("DISTANCE", distance);

		int result = adminDao.resAdd(param);

		if(0 < result){
			System.out.println("식당 등록 성공");
			return View.RESTAURANT_MANAGE;	// 식당 등록에 성공하면 RESTAURANT_MANAGE로 갑니다
		}else{
			System.out.println("식당 등록 실패");
			return View.RESTAURANT_MANAGE;	// 식당 등록에 해도 똑같이 돌아갑니다
		}
	}
	
	public void resManage(String resId){
		int select = 1;
		menu:while(true){
		PrintUtil.title();
		System.out.printf("\t[관리자용 식당관리모드]\n \t관리할 식당명 : %s\n",adminDao.resIdToName(resId));
		String[] menu = {"메뉴추가\n","영업시간변경\n","뒤로가기"};

		for(int i=0; i<menu.length; i++){
			if(select ==i+1)	System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print(menu[i]);
		}
		PrintUtil.joystick();;

		switch(ScanUtil.nextLine()){
		case "5":	if(select==1)	select=menu.length;		else select--;	break;
		case "2":	if(select==menu.length)	select=1;		else select++;	break;
		case "":	break menu;
		default:	break;			}
	}
		
		switch(select){
		case 1:
			menuAdd(resId);
			break;
		case 2: 
			break;
			//영업시간변경 구현해야함
		case 3: return;
		}
	}
	
	public void menuAdd(String resId){
		String check="";
		while(!("x".equals(check.toLowerCase()))&&!check.equals("ㅌ")){
			System.out.print("추가할 메뉴명 > ");
			String food=ScanUtil.nextLine();
			System.out.print("위 메뉴의 가격 > ");
			int price=ScanUtil.nextInt();
			int result = adminDao.menuAdd(resId,food,price);
			if(result==1)
				System.out.printf("메뉴명 :%s, 가격 : %d 등록완료\n",food,price);
			else
				System.out.println("등록에 실패하였습니다.");
			System.out.print("계속 등록하려면 엔터키를, 그만등록하려면 x를 입력:");
			check = ScanUtil.nextLine();
		}
		System.out.println("메뉴 추가하기가 종료되었습니다.");
		
	}

	
	
	
	
	
	
}
