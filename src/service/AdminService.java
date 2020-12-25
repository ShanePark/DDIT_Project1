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
	
	public int resMod(){	//////////////// 추가 필요
		return View.ERROR;
	}

	public int adminMain(){	//////////////////////// 관리자용 홈  View 3
		int select = 1;

		main:while(true){
			PrintUtil.title();
			System.out.println("                                      👔관리자 전용                                              ");
			System.out.println(" ");                   
			if(select ==1)		System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print("식당관리\n");
			if(select ==2)		System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print("뒤로가기\n");

			if(select ==3)		System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print("로그아웃 ");         
			PrintUtil.joystick();

			switch(ScanUtil.nextLine()){
			case "5":	if(select==1)	select=3;		else select--;			break;
			case "2":	if(select==3)	select=1;		else select++;			break;
			case "":	break main;
			default:	break;			}
			
		}

		switch(select){
		case 1: return View.RESTAURANT_MANAGE;
		case 2: return View.USER_MAIN;
		case 3: return View.MAIN;
		default:
			return View.ADMIN_MAIN;
		}
	}

	public int manageRestaurant(){	//////////////////////// 식당관리  View 30
		int select = 1;

		main:while(true){
			PrintUtil.title();
			System.out.println("                                      🥄식당 관리🥢                                              ");
			System.out.println(" ");                   
			if(select ==1)		System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print("식당추가\n");

			if(select ==2)		System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print("식당수정\n");

			if(select ==3)		System.out.print("             ■ ");
			else				System.out.print("             □ ");
			System.out.print("뒤로가기 ");         
			PrintUtil.joystick();

			switch(ScanUtil.nextLine()){
			case "5":	if(select==1)	select=3;	else select--;	break;
			case "2":	if(select==3)	select=1;	else select++;	break;
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
				int max = 3;
				if(complete[0]==1 && complete[1]==1 && complete[2]==1)
					max = 4;
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
					System.out.print(" 영업시간  입력하기 ");
				else
					System.out.printf(" 영업시간  [%s - %s]\n",openTime,closeTime);
				if(complete[0]==1 && complete[1]==1 && complete[2]==1){
					if(select ==4)		System.out.print("           ■");
					else				System.out.print("           □");
					System.out.print(" 입력 완료! 식당 등록하기 ");
				}else System.out.println();
				PrintUtil.joystick();

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

	
	
	
	
	
	
}
