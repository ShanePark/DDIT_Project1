package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.PrintUtil;
import util.ScanUtil;
import util.Util;
import util.View;
import controller.Controller;
import dao.UserDao;

public class UserService {

	private static UserService instance;
	private UserService(){}
	public static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}

	private UserDao userDao = UserDao.getInstance();

	public int signIn(){	
		PrintUtil.title();
		System.out.println("\n\n          ID : ");
		System.out.println("          PW : \n\n\t\t       뒤로 가러면 [Enter] 키를 누르세요");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("ID> ");
		String userID = ScanUtil.nextLine();
		if("".equals(userID))	return View.MAIN;	//  그냥 엔터키를 누르면 뒤로 이동
		System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		PrintUtil.title();
		System.out.print("\n\n          ID : "+userID+"\n");
		System.out.println("          PW : \n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("PASSWORD> ");
		String password = ScanUtil.nextLine();

		Map<String, Object> user = userDao.userSignIn(userID,password);

		if(user.size() ==0){	//user == null
			signInError();
		}else{
			Controller.user = user;

			return View.USER_MAIN;
		}

		return View.SIGNIN;
	}

	void signInError(){
		Util.wait(300);
		PrintUtil.title();
		System.out.println("\n\n\t⛔  ID와 비밀번호가 일치하지 않습니다  ⛔\n");
		System.out.println("        ■ 다시 로그인 하려면 엔터키를 입력 하세요");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");


		ScanUtil.nextLine();
	}

	public int signUp(){	
		String userID="", password="",nickname="";
		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢");
		System.out.print("        ID : \n");
		System.out.print("        PW : \n");
		System.out.print("                 닉네임 : \n\n\t\t       뒤로 가러면 [Enter] 키를 누르세요");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("ID> ");
		userID = ScanUtil.nextLine();
		if("".equals(userID))	return View.MAIN;	//  그냥 엔터키를 누르면 뒤로 이동
		if(userDao.isIdExist(userID))	// id 중복검사
			userID = idExist();


		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢");
		System.out.print("        ID : ");System.out.print(userID+"\n");
		System.out.print("        PW : \n");
		System.out.print("                 닉네임 : \n\n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("PASSWORD> ");
		password = ScanUtil.nextLine();


		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢 ");
		System.out.print("        ID : ");System.out.print(userID+"\n");
		System.out.print("        PW : ");
		for(int i=0; i<password.length(); i++)
			System.out.print("*");
		System.out.print("\n                 닉네임 : \n\n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("닉네임 > ");
		nickname = ScanUtil.nextLine();
		if(userDao.isNicknameExist(nickname))	// 닉네임 중복검사
			nickname = nicknameExist();


		Map<String, Object> param = new HashMap<String, Object>();
		param.put("USER_ID", userID);
		param.put("PASSWORD", password);
		param.put("NICKNAME", nickname);

		int result = userDao.userSignUp(param);

		if(0 < result){
			Map<String, Object> user = new HashMap<>();
			user.put("USER_ID", userID);
			user.put("NICKNAME", nickname);
			Controller.user = user;
			System.out.println("회원가입 성공");
			return View.USER_MAIN;	// 가입에 성공하면 USER_MAIN 화면으로 갑니다.
		}else{
			System.out.println("회원가입 실패");
			return View.MAIN;		// 가입에 실패하면 MAIN 화면으로 돌아갑니다.
		}
	}

	public String idExist(){
		while(true){
			PrintUtil.title();
			System.out.println("\n                     이미 존재하는 아이디입니다.");
			System.out.println("\n\n                  아이디를 다시 입력해주세요.");
			System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");
			String id=ScanUtil.nextLine();
			if(!userDao.isIdExist(id))
				return id;
		}
	}

	public String nicknameExist(){
		while(true){
			PrintUtil.title();
			System.out.println("\n                     이미 존재하는 닉네임입니다.");
			System.out.println("\n\n                  닉네임을 다시 입력해주세요.");
			System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");
			String nickname=ScanUtil.nextLine();
			if(!userDao.isNicknameExist(nickname))
				return nickname;
		}
	}




	public int userMain(){
		String nickname = Controller.user.get("NICKNAME").toString();
		List<Map<String, Object>> list = null;
		int select = 1;
		String orderby="", resName="",score ="", distance="", rvCnt="";
		String[] res = new String[5];

		userMain:while(true){
			System.out.println("\n\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
			System.out.print("                                    🍽️ 오늘 뭐먹지? 🍽️");
			for(int i=nickname.length(); i<6; i++)
				System.out.print("   ");
			int nicknameLength=6;	// 해당 길이보다 긴 닉네임은 ..으로 표시합니다
			int saveLength = nicknameLength-1;
			if(nickname.length()<nicknameLength) nicknameLength=nickname.length();
			System.out.print("   ["+nickname.substring(0,nicknameLength));
			if(nickname.length()>saveLength)	System.out.print("..");
			System.out.println("](으)로 접속중");

			if(select==2){
				list = resByRvcnt();	orderby = "리뷰수";
			}else if(select==3){
				list = resByDistance();	orderby = "거리순";
			}else{
				list = resByScore();	orderby = "평점순";
			}
			for(int i=0; i<res.length; i++){
				int resNameLength = 6;	// 식당 이름을 몇 글짜까지 표시해줄지 정하는 변수
				resName = list.get(i).get("RES_NAME").toString();
				score = list.get(i).get("SCORE").toString();
				distance = list.get(i).get("DISTANCE").toString();
				rvCnt = list.get(i).get("RV_CNT").toString();

				if(resName.length() < resNameLength)
					resNameLength = resName.length();
				res[i]=resName.substring(0,resNameLength)+" [평점 "
						+score+"] ";
				if(select==2)
					res[i] += "(리뷰"+rvCnt+"개)";
				if(select==3)
					res[i] += distance+"m";
			}
			if(select ==1)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 평점기준                            ");
			System.out.printf("🥘 [%s] BEST 5 🍝\n",orderby);

			if(select ==2)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 리뷰수기준                    1. ");
			System.out.println(res[0]);

			if(select ==3)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 거리기준                       2. ");
			System.out.println(res[1]);

			if(select ==4)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 검색                             3. ");
			System.out.println(res[2]);

			if(select ==5)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 도시락주문                    4. ");
			System.out.println(res[3]);

			if(select ==6)		System.out.print(" ■");
			else				System.out.print(" □");
			if(nickname.equals("관리자"))
				System.out.print(" 관리자전용                    5. ");
			else if(nickname.equals("비회원"))
				System.out.print(" 로그인                          5. ");	// 비회원일때 마이페이지 대신 어떤 기능을 넣을지 정해야합니다
			else System.out.print(" 마이페이지                    5. ");

			System.out.println(res[4]);

			if(select ==7)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 고객센터                        ");
			System.out.print("                     (2)↓ (5)↑ (⏎)확인\n"+ 
					"□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");

			switch(ScanUtil.nextLine()){	// 방향키 입력받는 switch 문
			case "5": if(select==1)	select=7;	else select--;		break;
			case "2": if(select==7)	select=1;	else select++;		break;
			case "":	break userMain;
			default:	break;		   }

		}
		switch(select){
		case 1: return resList(resByScore());
		case 2: return resList(resByRvcnt());
		case 3: return resList(resByDistance());
		case 4: return View.SEARCH_RES;
		case 5:	return View.LUNCHBOX_ORDER;
		case 6:
			if(nickname.equals("관리자"))	return View.ADMIN_MAIN;
			if(nickname.equals("비회원")) return View.SIGNIN;
			else return View.MYPAGE;	///////////// 사용자용 '마이페이지' 구현이 필요합니다
		default:return View.ERROR;	}

	}

	public int lunchboxOrder(){
		int select = 1;
		lunchboxOrder:while(true){
			PrintUtil.title();
			System.out.println("\t        🍱도시락 주문하기🥡\n");
			String[] menu = {"대전 도시락\n","토마토 도시락\n","뒤로가기"};

			for(int i=0; i<menu.length; i++){
				if(select ==i+1)	System.out.print("             ■ ");
				else				System.out.print("             □ ");
				System.out.print(menu[i]);
			}
			
			PrintUtil.joystick();;
			switch(ScanUtil.nextLine()){
			case "5":	if(select==1) select=menu.length;		else select--;	break;
			case "2":	if(select==menu.length) select=1;		else select++;	break;
			case "":	break lunchboxOrder;
			default:	break;			}
		}

		switch(select){
		case 1: return View.ERROR;	// 대전도시락 주문하기 view 만들어야 합니다
		case 2: return View.ERROR;	// 토마토도시락 주문하기 view 만들어야 합니다
		default:return View.USER_MAIN;
		}

	}


	public int guestMode(){	

		Map<String, Object> user = userDao.userSignIn("guest","guest");	//guest 로 로그인

		Controller.user = user;
		System.out.println("비회원으로 이용합니다.");

		return View.USER_MAIN;

	}

	public int myPage(){
		int select = 1;
		lunchboxOrder:while(true){
			PrintUtil.title();
			System.out.println("\t               🧑마이페이지👩");
			String[] menu = {"찜리스트\n","주문내역\n","내 리뷰 확인\n","계정관리\n","뒤로가기            "};

			for(int i=0; i<menu.length; i++){
				if(select ==i+1)	System.out.print("             ■ ");
				else				System.out.print("             □ ");
				System.out.print(menu[i]);
			}

			PrintUtil.joystick2();;

			switch(ScanUtil.nextLine()){
			case "5":	if(select==1)	select=menu.length;		else select--;	break;
			case "2":	if(select==menu.length)	select=1;		else select++;	break;
			case "":	break lunchboxOrder;
			default:	break;			}

		}

		switch(select){
		case 1: return View.ERROR;	// 찜리스트 view 만들어야 합니다
		case 2: return View.ERROR;	// 주문내역 view 만들어야 합니다
		case 3: return View.ERROR;	// 내 리뷰 확인 view 만들어야 합니다
		case 4: return View.ERROR;	// 계정관리 view 만들어야 합니다
		case 5: return View.USER_MAIN;	// 뒤로가기
		default:return View.USER_MAIN;
		}

	}

	private List<Map<String, Object>> resByDistance(){		
		return userDao.resByDistance();
	}
	private List<Map<String, Object>> resByScore(){
		return userDao.resByScore();
	}
	private List<Map<String, Object>> resByRvcnt(){
		return userDao.resByRvcnt();
	}
	//	private List<Map<String, Object>> resByPick(){
	//		return userDao.resByPick();
	//	}
	private List<Map<String, Object>> resByName(String name){
		return userDao.resByName(name);
	}


	public int searchRes(){
		PrintUtil.title();
		System.out.println("\n\n\t    검색할 가게명을 입력해주세요\n\n");
		System.out.println("\t\t       뒤로 가러면 [Enter] 키를 누르세요");
		PrintUtil.printBar();
		String resName = ScanUtil.nextLine();
		if(resName.equals("")) return View.USER_MAIN;	

		return resList(resByName(resName));
	}
	
	public int resDetail(String resId){
		int select = 1;
		String userId = Controller.user.get("USER_ID").toString();
		resDetail:while(true){
		Map<String,Object> res = userDao.resDetail(resId);
		String pickCnt= res.get("PICK_CNT").toString();
		String distance= res.get("DISTANCE").toString();
		String resName= res.get("RES_NAME").toString();
		String score= res.get("SCORE").toString();
		String cousine= res.get("COUSINE").toString();
		String rv_cnt= res.get("RV_CNT").toString();
		String add= res.get("ADD1").toString();
		String time;
		if(res.get("OPEN_TIME")==null || res.get("CLOSE_TIME")==null)
			time = "정보 없음";
		else
			time= res.get("OPEN_TIME").toString()+" - "+res.get("CLOSE_TIME").toString();
		PrintUtil.title2();
		System.out.printf("\t\t\t\t좋아하는 사람 %s명\n",pickCnt);
		System.out.printf("\t          %s (%s)\n",resName, cousine);
		System.out.printf("            ✔️ 평점 : %s (리뷰 %s개)\n",score, rv_cnt);
		System.out.printf("            ✔️ 영업시간 : %s\n",time);
		System.out.printf("            ✔️ 주소 : %s (거리 %sm)\n\n",add, distance);
		
		
		
		String[] selects = {" 뒤로가기"," 메뉴보기"," 리뷰보기"," 찜하기"};
		if(userDao.isPick(resId, userId))//이미 찜하기 했으면
			selects[3] = " 찜취소";
		for(int i=0; i<selects.length; i++){
			if(select ==i+1)	System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(selects[i]);
		}
		
		System.out.print(" ");
		PrintUtil.joystick3();
		
		switch(ScanUtil.nextLine()){
		case "1":	if(select==1)	select=4;		else select--;			break;
		case "3":	if(select==4)	select=1;		else select++;			break;
		case "":	break resDetail;
		default:	break;			}
		
		}
		
		switch(select){
		case 1: return View.USER_MAIN;
		case 2: return View.ERROR;	// 메뉴보기 구현 필요
		case 3: return View.ERROR;	// 리뷰보기 메뉴 필요
		case 4: 
			if(userDao.isPick(resId, userId))	userDao.resUnPick(resId, userId);
			else userDao.resPick(resId,userId);	// 찜했으면 찜취소, 찜 안했으면 찜하기
			return resDetail(resId);	// 찜(or취소) 이후 해당 식당 다시 재귀호출
		default:
			return View.USER_MAIN;
		}
		
	}

	public int resList(List<Map<String, Object>> list){
		int select = 1;
		int page = 1;
		int resPerPage = 4;
		int maxPage = (list.size()-1)/resPerPage+1;
		if(list.size()==0) maxPage = 1;
		int nameLength = 6;	// 출력하고 싶은 가게 이름의 최대 길이
		int foodLength = 5; // 음식 종류 최대 길이

		page:while(true){
			String[] resId = new String[resPerPage];

			resList:while(true){
				int startResNum = (page-1)*resPerPage;
				String[] names = new String[resPerPage];
				String[] food = new String[resPerPage];
				float[] score = new float[resPerPage];
				String[] distance = new String[resPerPage];
				int[] likes = new int[resPerPage];

				for(int i=0; i<resPerPage; i++){
					if(startResNum+i>=list.size()) break;
					String name = list.get(startResNum+i).get("RES_NAME").toString();
					String foodTemp = list.get(startResNum+i).get("COUSINE").toString();

					for(int j=name.length(); j<nameLength; j++)	// 식당 이름 길이를 맞춰줍니다.
						name += "　";
					for(int j=foodTemp.length(); j<foodLength; j++)
						foodTemp += "　";

					names[i] = name.substring(0, nameLength);
					resId[i] = list.get(startResNum+i).get("RES_ID").toString();
					food[i] = foodTemp;
					score[i] = Float.parseFloat(list.get(startResNum+i).get("SCORE").toString());
					distance[i] = list.get(startResNum+i).get("DISTANCE").toString();
					likes[i] = Integer.parseInt(list.get(startResNum+i).get("PICK_CNT").toString());
				}

				PrintUtil.title2();
				System.out.println("       이름              음식             평점            거리           추천수");

				for(int i=0; i<resPerPage; i++){
					if(select ==i+1)		System.out.print(" ■ ");
					else				System.out.print(" □ ");
					if(names[i]!=null){
						System.out.printf("%s  %s   %s          %sm      %d개\n",names[i],food[i],Util.scoreToStars(score[i]),distance[i],likes[i]);
					}else System.out.println();
				}

				System.out.printf(" [현재 페이지 %d/%d]\t\t     (1)← (3)→ \n",page,maxPage);
				if(select ==5)		System.out.print(" ■ ");	else System.out.print(" □ ");	
				System.out.print("뒤로가기");
				if(select ==6)		System.out.print(" ■ ");	else System.out.print(" □ ");	
				System.out.print("이전페이지");
				if(select ==7)		System.out.print(" ■ ");	else System.out.print(" □ ");	
				System.out.print("다음페이지");
				PrintUtil.joystick2();	

				switch(ScanUtil.nextLine()){
				case "5":	if(select==1)	select=5;	else if(select>=5) select=4;	else select--;	break;
				case "2":	if(select>=5)	select=1;	else select++;	break;
				case "1":
					if(select == 5) select = 7;
					else if(select>5) select--;
					break;
				case "3":
					if(select==7) select=5;
					else if(select>4) select++;
					break;
				case "":	break resList;
				default:	break;
				}
			}

			switch(select){
			case 1: if(resId[0]!=null) return resDetail(resId[0]);
			case 2: if(resId[1]!=null) return resDetail(resId[1]);
			case 3: if(resId[2]!=null) return resDetail(resId[2]);
			case 4: if(resId[3]!=null) return resDetail(resId[3]);
			break;	// resID가 null일 경우 아무것도 하지 않습니다.
			case 5: return View.USER_MAIN;
			case 6: if(page!=1) page--;			break;
			case 7: if(page!=maxPage) page++;	break;
			default:
				break page;
			}
		}


		return View.USER_MAIN;

	}
}
