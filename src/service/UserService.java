package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.PrintUtil;
import util.ScanUtil;
import util.Util;
import util.View;
import controller.Controller;
import dao.BoxDao;
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
		PrintUtil.printBar();
		System.out.print("ID> ");
		String userID = ScanUtil.nextLine();
		if("".equals(userID))	return View.MAIN;	//  그냥 엔터키를 누르면 뒤로 이동
		PrintUtil.printBar();
		PrintUtil.title();
		System.out.print("\n\n          ID : "+userID+"\n");
		System.out.println("          PW : \n\n");
		PrintUtil.printBar();
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
		System.out.println("        ■ 다시 로그인 하려면 엔터키를 입력 하세요\n");
		PrintUtil.printBar();
		ScanUtil.nextLine();
	}

	public int signUp(){	
		String userID="", password="",nickname="";
		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢");
		System.out.print("        ID : \n");
		System.out.print("        PW : \n");
		System.out.print("                 닉네임 : \n\n\t\t       뒤로 가러면 [Enter] 키를 누르세요\n");
		PrintUtil.printBar();
		System.out.print("ID> ");
		userID = ScanUtil.nextLine();
		if("".equals(userID))	return View.MAIN;	//  그냥 엔터키를 누르면 뒤로 이동
		if(userDao.isIdExist(userID))	// id 중복검사
			userID = idExist();

		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢");
		System.out.print("        ID : ");System.out.print(userID+"\n");
		System.out.print("        PW : \n");
		System.out.print("                 닉네임 : \n\n\n");
		PrintUtil.printBar();
		System.out.print("PASSWORD> ");
		password = ScanUtil.nextLine();


		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢 ");
		System.out.print("        ID : ");System.out.print(userID+"\n");
		System.out.print("        PW : ");
		for(int i=0; i<password.length(); i++)
			System.out.print("*");
		System.out.print("\n                 닉네임 : \n\n\n");
		PrintUtil.printBar();
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
			System.out.println("\n\n                  아이디를 다시 입력해주세요.\n");
			PrintUtil.printBar();
			String id=ScanUtil.nextLine();
			if(!userDao.isIdExist(id))
				return id;
		}
	}

	public String nicknameExist(){
		while(true){
			PrintUtil.title();
			System.out.println("\n                     이미 존재하는 닉네임입니다.");
			System.out.println("\n\n                  닉네임을 다시 입력해주세요.\n");
			PrintUtil.printBar();
			String nickname=ScanUtil.nextLine();
			if(!userDao.isNicknameExist(nickname))
				return nickname;
		}
	}




	public int userMain(){
		String nickname = Controller.user.get("NICKNAME").toString();
		String userId = Controller.user.get("USER_ID").toString();
		List<Map<String, Object>> list = null;
		int select = 1;
		String orderby="", resName="", distance="", rvCnt="";
		float score=0;
		String[] res = new String[5];	//페이지당 식당 수를 배열 크기로 지정하세요
		int resNameLength = 8;	// 식당 이름을 몇 글자까지 표시해줄지 정하는 변수

		userMain:while(true){
			int nicknameLength=6;	// 해당 길이보다 긴 닉네임은 ..으로 표시합니다
			if(select==2){
				PrintUtil.printBlank(20);		//
				PrintUtil.loading(0);			// 로딩쪽 손봐야합니다 !!
				list = resByRvcnt();	orderby = "리뷰수";
			}else if(select==3){
				PrintUtil.printBlank(20);
				PrintUtil.loading(0);
				list = resByDistance();	orderby = "거리순";
			}else{
				PrintUtil.printBlank(20);
				PrintUtil.loading(0);
				list = resByScore();	orderby = "평점순";
			}
			PrintUtil.title3();
			System.out.print("     "+Util.cutString2(nickname,nicknameLength));
			System.out.print("(으)로 접속중\n");
			for(int i=0; i<res.length; i++){
				resName = list.get(i).get("RES_NAME").toString();
				score = Float.parseFloat(list.get(i).get("SCORE").toString());
				distance = list.get(i).get("DISTANCE").toString();
				rvCnt = list.get(i).get("RV_CNT").toString();

				res[i]=Util.cutString(resName,resNameLength)+" [평점 "+score+"] ";
				if(select==2)
					res[i] += "(리뷰"+rvCnt+"개)";
				if(select==3)
					res[i] += distance+"m";
			}
			if(select ==1)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 평점기준                   ");
			System.out.printf("🥘 [%s] BEST 5 🍝\n",orderby);

			if(select ==2)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 리뷰수기준             1. ");
			System.out.println(res[0]);

			if(select ==3)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 거리기준                2. ");
			System.out.println(res[1]);

			if(select ==4)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 검색                      3. ");
			System.out.println(res[2]);

			if(select ==5)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 도시락주문             4. ");
			System.out.println(res[3]);

			if(select ==6)		System.out.print(" ■");
			else				System.out.print(" □");
			if(nickname.equals("관리자"))
				System.out.print(" 관리자전용             5. ");
			else if(nickname.equals("비회원"))
				System.out.print(" 로그인                   5. ");	// 비회원일때 마이페이지 대신 어떤 기능을 넣을지 정해야합니다
			else System.out.print(" 마이페이지             5. ");

			System.out.println(res[4]);

			if(select ==7)		System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(" 고객센터                        ");
			System.out.print("                     (2)↓ (5)↑ (⏎)확인\n");
			PrintUtil.printBar();

			switch(ScanUtil.nextLine()){	// 방향키 입력받는 switch 문
			case "5": if(select==1)	select=7;	else select--;		break;
			case "2": if(select==7)	select=1;	else select++;		break;
			case "":	break userMain;
			default:	break;		   }

		}
		switch(select){
		case 1: resList(resByScore()); break;
		case 2: resList(resByRvcnt()); break;
		case 3: resList(resByDistance()); break;
		case 4: return View.SEARCH_RES;
		case 5:	
			if(userId.equals("guest"))
				return View.SIGNIN;
			else if(!userDao.isDetailedAccount(userId)){
				notDetailed(); 
				return View.USER_MAIN;
			}
			else return View.LUNCHBOX_ORDER;
		case 6:
			if(nickname.equals("관리자"))	return View.ADMIN_MAIN;
			if(nickname.equals("비회원")) return View.SIGNIN;
			else return View.MYPAGE;
		}
		return View.USER_MAIN;

	}
	
	public void notDetailed(){
		int select = 1;
		menu:while(true){
			PrintUtil.title();

			String[] menu = {"뒤로가기","개인정보입력"};

			System.out.println("\t    『  ☎ 개인정보 등록  』 ");
			System.out.println("\t 이후에 사용이 가능한 기능입니다.\n");

			for(int i=0; i<menu.length; i++){
				if(select ==i+1)		System.out.print("        ■");
				else				System.out.print("        □");
				System.out.print(menu[i]);
			}
			System.out.println();
			PrintUtil.joystick4();
			switch(ScanUtil.nextLine()){
			case "1": if(select==1) select=menu.length;		else select--;	break;
			case "3": if(select==menu.length)	select=1;	else select++;	break;
			case "":	break menu;
			default:	break;			}
		}
		
		switch(select){
		case 1: return;
		case 2: putDetail(); return;
		default: return;
		}
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
		case 1: return View.BOX_DAEJEON;	// 대전도시락 주문하기로 갑니다.
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
		case 1: return View.PICK_LIST;	
		case 2: return View.ERROR;		// 주문내역 view 만들어야 합니다///////////
		case 3: return View.MYREVIEW;	// 내 리뷰 보기
		case 4: return View.MANAGE_ACCOUNT;	// 계정관리 view 만들어야 합니다////////
		case 5: return View.USER_MAIN;	// 뒤로가기///////////////////////
		}
		return View.MYPAGE;
	}

	public int myReview(){
		String userId = Controller.user.get("USER_ID").toString();
		List<Map<String, Object>> review = userDao.myReview(userId);
		int select = 1;
		int page = 1;
		int perPage = 4;
		int maxPage = (review.size()-1)/perPage+1;
		int resNameLength = 8;
		while(true){
			menu:while(true){
				PrintUtil.title();
				System.out.println("\t               📋내 리뷰📋");

				for(int i=0; i<perPage; i++){
					if((page-1)*perPage+i >= review.size()){
						System.out.println();
						continue;
					}
					Map<String, Object> reviewMap = review.get((page-1)*perPage+i);
					String resName = reviewMap.get("RES_NAME").toString();
					resName = Util.cutString(resName, resNameLength);
					String grade = Util.scoreToStars(reviewMap.get("GRADE").toString());
					String content = reviewMap.get("R_CONTENT").toString();
					System.out.printf(" %d) %s  %s    %s\n",
							review.size()-(page-1)*perPage-i,resName,grade,content);
				}

				String[] menu = {"뒤로가기","이전페이지","다음페이지 "};
				for(int i=0; i<menu.length; i++){
					if(select ==i+1)	System.out.print(" ■ ");
					else				System.out.print(" □ ");
					System.out.print(menu[i]);
				}
				System.out.printf("   [페이지 %d/%d]",page,maxPage);
				PrintUtil.printBar2();

				switch(ScanUtil.nextLine()){
				case "1":	if(select==1)	select=menu.length;	else select--;	break;
				case "3":	if(select==menu.length)	select=1;	else select++;	break;
				case "":	break menu;
				default:	break;			}
			}

		switch(select)					{
		case 1: return View.MYPAGE;
		case 2: if(page!= 1) page--;		break;
		case 3: if(page!= maxPage) page++;	break;
		default:	return View.MYPAGE;	}
		}

	}

	public int manageAccount(){
		boolean detail = userDao.isDetailedAccount(Controller.user.get("USER_ID").toString());
		int select = 1;

		menu:while(true){
			PrintUtil.title();
			System.out.println("\t                🧑 계정관리 👩");

			String[] menu = {"개인정보입력\n","적립금 충전\n","회원정보 수정\n","로그아웃\n","뒤로가기 "};
			if(detail)
				menu[0] = "개인정보입력(완료)\n";

			for(int i=0; i<menu.length; i++){
				if(select ==i+1)	System.out.print("             ■ ");
				else				System.out.print("             □ ");
				System.out.print(menu[i]);
			}
			PrintUtil.joystick2();

			switch(ScanUtil.nextLine()){
			case "5":	if(select==1)	select=menu.length;	else select--;	break;
			case "2":	if(select==menu.length)	select=1;	else select++;	break;
			case "":	break menu;
			default:	break;
			}
		}

		switch(select){
		case 1:
			if(detail) return View.MANAGE_ACCOUNT;// 개인정보 입력
			else {putDetail(); return View.MANAGE_ACCOUNT;}
		case 2:
			if(!detail){notDetailed(); return View.MANAGE_ACCOUNT;}
			else{
			buyCredit(); return View.MANAGE_ACCOUNT;// 적립금 충전
			}
//		case 3: return;		// 회원정보 수정
		case 4: return View.MAIN;		// 로그아웃
		case 5: return View.MYPAGE;		// 뒤로가기
		default:
			return View.MYPAGE;
		}
	}
	
	public void buyCredit(){
		
		PrintUtil.title();
		System.out.println("\t           💳 적립금 충전 💳\n");
		System.out.println("\t적립금 잔액 : "+Controller.user.get("MONEY")+" ₩");
		System.out.println("        적립금 충전은 현재 관리자를 통해서만 가능합니다.");
		System.out.println("        관리자에게 직접 문의해주세요. 확인후 엔터키를 눌러주세요.\n");
		PrintUtil.printBar();
		ScanUtil.nextLine();
		
	}
	
	public void putDetail(){
		String userId = Controller.user.get("USER_ID").toString();
		String nickname = Controller.user.get("NICKNAME").toString();
		String name="",phone="";

		PrintUtil.title();
		System.out.printf("\t[%s]님의 실명을 입력해주세요\n\n",nickname);
		System.out.printf("\t한글 입력시에는 화살표 오른쪽을\n\n");
		System.out.printf("\t클릭하고 입력하기를 권장합니다\n");
		PrintUtil.printBar2();
		name = ScanUtil.nextLine();
		boolean wrongPhone = false;
		while(true){
			PrintUtil.title();
			if(!wrongPhone){
			System.out.printf("\t[%s]님의 전화번호를 입력해주세요\n\n",name);
			System.out.printf("\t휴대폰 번호 입력시에는\n\n");
			System.out.printf("\t본인 확인이 진행됩니다\n");
			}else{
				System.out.printf("\t[%s]님의 전화번호를 입력해주세요\n\n",name);
				System.out.printf("\t⚠에러! 방금 입력한 번호는⚠\n\n");
				System.out.printf("\t이미 존재하는 번호입니다.\n");
			}
			PrintUtil.printBar2();
			phone = ScanUtil.nextLine();
			if(!userDao.isPhoneExist(phone))	//번호가 존재하지 않으면 입력완료
				break;
			else wrongPhone= true;
		}
		PrintUtil.title();
		System.out.printf("\t[%s]님의 모든 입력이 완료되었습니다.\n\n",nickname);
		System.out.printf("\t이름 : %s, 전화번호 : %s\n\n",name,phone);
		System.out.printf("\t확인 후 엔터키를 눌러주세요.\n");
		PrintUtil.printBar2();
		ScanUtil.nextLine();
		
		userDao.putDetail(userId,name,phone);

	}

	

	public int pickList(){
		List<Map<String,Object>> list = getPickList();	// ↓ 메뉴 및 페이징 처리를 위한 변수들입니다
		int select = 1, perPage = 4, page = 1,totalPage = (list.size()-1)/perPage+1;
		int nameLength = 7;

		page:while(true){	// 이중 반복문이 쓰인 이유는 1.페이징처리 2.메뉴이용 두 가지 기능을 모두 담기 위해서입니다.
			String[] resNumber = new String[perPage];	// 식당 번호를 저장해둘 배열입니다 (resDetail 호출을 위해 필요)
			pickList: while(true){
				PrintUtil.title();
				System.out.println("                                     ❤️ 찜리스트 ❤️");

				for(int i=0; i<perPage; i++){
					int resNum = (page-1) * perPage + i;
					String resName="", star="";
					double score=0;
					if(resNum<list.size()){
						resName = Util.cutString(list.get(resNum).get("RES_NAME").toString(),nameLength);
						score = Float.parseFloat(list.get(resNum).get("SCORE").toString());
						resNumber[i] = list.get(resNum).get("RES_ID").toString();
						star = Util.scoreToStars(score);
					}

					if(select ==i+1)	System.out.print("         ■ ");
					else				System.out.print("         □ ");
					if(!resName.equals("")) System.out.printf("%s　　%s (평점 %.2f)\n",resName,star,score);
					else System.out.println();
				}

				String[] menu = {"뒤로가기 ","이전페이지 ","다음페이지 "};
				for(int i=0; i<menu.length; i++){
					if(select ==perPage+i+1)	System.out.print(" ■ ");
					else						System.out.print(" □ ");
					System.out.print(menu[i]);
				}
				System.out.printf(" (페이지 %d/%d)총 %d개",page,totalPage,list.size());
				PrintUtil.printBar2();

				switch(ScanUtil.nextLine()){
				case "5":	
					if(select>perPage)	select = perPage;
					else if(select==1)	select=perPage+1;		
					else select--;			
					break;
				case "2":	
					if(select>perPage)	select = 1;
					else if(select==perPage+1)	select=1;		
					else select++;			
					break;
				case "1":	if(select<perPage) select=perPage+menu.length; 
				else if(select==perPage+1)	select=perPage+menu.length;		else select--;	break;
				case "3":	if(select<perPage) select=perPage+1;
				else if(select==perPage+menu.length) select=perPage+1; 		else select++;	break;
				case "":	break pickList;
				default:	break;			}
			}

		switch(select){
		case 1: if(resNumber[0]==null) break; else {resDetail(resNumber[0]); break;}
		case 2: if(resNumber[1]==null) break; else {resDetail(resNumber[1]); break;}
		case 3: if(resNumber[2]==null) break; else {resDetail(resNumber[2]); break;}
		case 4: if(resNumber[3]==null) break; else {resDetail(resNumber[3]); break;}
		case 5: return View.MYPAGE;
		case 6: if(page!=1) page--; break;
		case 7: if(page!=totalPage) page++; break;
		default: break page;	}
		
		}

		return View.PICK_LIST;
	}
	
	private List<Map<String, Object>> getPickList(){		
		return userDao.pickList(Controller.user.get("USER_ID").toString());
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
	
	public void viewMenu(String resId){
		List<Map<String, Object>> menu = userDao.viewMenu(resId);
		int select = 1;
		int perPage = 4;
		int page = 0;
		int menuLength = 8;
		int maxPage = page+menu.size()-perPage;
		if(maxPage<0) maxPage = 0;
		while(true){
			loop:while(true){
				PrintUtil.title();
				System.out.println("\t        🥄 메뉴 목록 🥢");
				printMenu:for(int i=0; i<perPage; i++){
					if(i+page >= menu.size()){
						System.out.println();
						continue printMenu;
					}
					System.out.print("\t🍴 "+(page+i+1)+" ");
					String menuName = menu.get(i+page).get("FOOD").toString();
					System.out.print(Util.cutString(menuName, menuLength));
					System.out.println(menu.get(i+page).get("PRICE")+" ₩");
				}
				String[] list = {"뒤로가기    ","↑","↓"};

				for(int i=0; i<list.length; i++){
					if(select ==i+1)	System.out.print(" ■ ");
					else				System.out.print(" □ ");
					System.out.print(list[i]);
				}
				System.out.print("           ");
				PrintUtil.joystick3();

				switch(ScanUtil.nextLine()){
				case "1":	if(select==1)	select=list.length;	else select--;	break;
				case "3":	if(select==list.length)	select=1;	else select++;	break;
				case "":	break loop;
				default:	break;
				}
			}
		switch(select){
		case 1: return;
		case 2: if(page !=0) page--; break;
		case 3: if(page !=maxPage) page++; break;
		}
		}

	}
	
	public void resDetail(String resId){	// '뒤로가기' 기능의 정상적 사용을 위해 반환타입을 void 로 변경하였습니다.
		int select = 1;
		String userId = Controller.user.get("USER_ID").toString();
		resDetail:while(true){
		Map<String,Object> res = userDao.resDetail(resId);
		String pickCnt= res.get("PICK_CNT").toString();
		String distance= res.get("DISTANCE").toString();
		String resName= res.get("RES_NAME").toString();
		float score= Float.parseFloat(res.get("SCORE").toString());
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
		System.out.printf("            ✔️ 평점 : %.2f (리뷰 %s개)\n",score, rv_cnt);
		System.out.printf("            ✔️ 영업시간 : %s\n",time);
		System.out.printf("            ✔️ 주소 : %s (거리 %sm)\n\n",add, distance);
		
		
		
		String[] selects = {" 뒤로가기"," 메뉴보기"," 리뷰보기"," 찜하기"};
		if(userDao.isPick(resId, userId))//이미 찜하기 했으면
			selects[3] = " 찜취소";
		if(Controller.user.get("USER_ID").toString().equals("admin"))
			selects[3] = "식당관리";
		for(int i=0; i<selects.length; i++){
			if(select ==i+1)	System.out.print(" ■");
			else				System.out.print(" □");
			System.out.print(selects[i]);
		}
		
		System.out.print(" ");
		PrintUtil.joystick3();
		
		switch(ScanUtil.nextLine()){
		case "1":	if(select==1)	select=selects.length;		else select--;			break;
		case "3":	if(select==selects.length)	select=1;		else select++;			break;
		case "":	break resDetail;
		default:	break;			}
		
		}
		
		switch(select){
		case 1: break;
		case 2: viewMenu(resId); break;
		case 3: resReview(resId); break;	
		case 4: 
			if(Controller.user.get("USER_ID").toString().equals("admin")){//관리자면 식당관리
				AdminService.getInstance().resManage(resId);
			}
			if(userDao.isPick(resId, userId))	userDao.resUnPick(resId, userId);
			else userDao.resPick(resId,userId);	// 찜했으면 찜취소, 찜 안했으면 찜하기
			resDetail(resId);	// 찜(or취소) 이후 해당 식당 다시 재귀호출
		default:
			break;
		}
		
	}
	
	public void resReview(String resId){
		Map<String,Object> res = userDao.resDetail(resId);
		String resName = res.get("RES_NAME").toString();
		String rvCnt = res.get("RV_CNT").toString();
		float score = Float.parseFloat(res.get("SCORE").toString());
		int select = 1;
		int page = 1;
		int perPage = 4;
		int nicknameLength = 5;

		resReview:while(true){
			List<Map<String,Object>> review = userDao.reviewList(resId);
			
			int maxPage = (review.size()-1)/perPage+1;
			select:while(true){
				PrintUtil.title2();
				System.out.printf(" [%s] %s %.2f점(리뷰 %s개)\n",
						resName,Util.scoreToStars(score),score,rvCnt);
				System.out.println("리뷰일       평점         작성자        내용");
				
				int start = perPage * (page-1);
				print:for(int i=0; i<perPage; i++){
					if(review.size()<= start+i){
						System.out.println();
						continue print;
						}
					String date=review.get(start+i).get("YYMM").toString();
					float gradescore=Float.parseFloat(review.get(start+i).get("GRADE").toString());
					String grade = Util.scoreToStars(gradescore);
					String nickname=review.get(start+i).get("NICKNAME").toString();
					nickname = Util.cutString(nickname, nicknameLength);
					String content=review.get(start+i).get("R_CONTENT").toString();
					System.out.printf("%s  %s   %s %s\n",date,grade,nickname,content);
				}

				String[] selects = {" 뒤로가기  "," 리뷰작성  "," 이전페이지  "," 다음페이지  "};
				
				if(userDao.isReviewExist(Controller.user.get("USER_ID").toString(),resId))
					selects[1] = "내리뷰관리";	// 사용자가 해당 식당에 작성한 리뷰가 있을 경우

				for(int i=0; i<selects.length; i++){
					if(select ==i+1)	System.out.print("■");
					else				System.out.print("□");
					System.out.print(selects[i]);
				}
				System.out.printf("[페이지%d/%d]",page,maxPage);
				PrintUtil.printBar2();

				switch(ScanUtil.nextLine()){
				case "1":	if(select==1)	select=selects.length;		else select--;			break;
				case "3":	if(select==selects.length)	select=1;		else select++;			break;
				case "":	break select;
				default:	break;			}
			}

		switch(select){
		case 1: break resReview;
		case 2: 
			if(userDao.isReviewExist(Controller.user.get("USER_ID").toString(),resId))
				modReview(resId);
			else newReview(resId);
			break;
		case 3: if(page!=1) page--;			break;//이전페이지
		case 4: if(page!=maxPage) page++;	break;//다음페이지
		default:
			break;		}
		}
	}
	
	public void modReview(String resId){
		int select = 1;
		modReview:while(true){
			PrintUtil.title();
			Map<String, Object> review = userDao.getReview(resId,Controller.user.get("USER_ID").toString());
			String score = Util.scoreToStars(Integer.parseInt(review.get("GRADE").toString()));
			String date = review.get("RE_DATE").toString();
			String content = review.get("R_CONTENT").toString();
			System.out.printf("\t\t[%s]\n",userDao.resIdToName(resId));
			System.out.println("\t내 별점 : "+score);
			System.out.println("\t작성일 : "+date);
			System.out.println("\t내용 : "+content);
			System.out.println();
			String[] menu = {"뒤로가기","리뷰다시작성","리뷰삭제"};
			for(int i=0; i<menu.length; i++){
				if(select ==i+1)	System.out.print(" ■ ");
				else				System.out.print(" □ ");
				System.out.print(menu[i]);
			}
			
			PrintUtil.printBar2();
			
			switch(ScanUtil.nextLine()){
			case "1":	if(select==1)	select=menu.length;	else select--;	break;
			case "3":	if(select==menu.length)	select=1;	else select++;	break;
			case "":	break modReview;
			default:	break;			}
		}
		
		switch(select){
		case 1: break;
		case 2: // 리뷰 삭제하며 새로 리뷰작성하는 곳으로 이동 
			userDao.delReview(resId,Controller.user.get("USER_ID").toString());
			newReview(resId);
			break; 
		case 3: //리뷰삭제
			userDao.delReview(resId,Controller.user.get("USER_ID").toString());
			break;
		default: break;}

	}

	public void newReview(String resId){
		String userId=Controller.user.get("USER_ID").toString();
		String grade="",content="";
		String resName=userDao.resIdToName(resId);
		int score=0;
		int select = 1;
		menu:while(true){
			PrintUtil.title();
			System.out.printf("\t\t[%s]\n",resName);
			System.out.println("\n\t주고싶은 별점을 선택해주세요\n\n");
			String[] selects = {"뒤로가기 ","★☆☆☆☆","★★☆☆☆","★★★☆☆","★★★★☆","★★★★★"};
			for(int i=0; i<selects.length; i++){
				if(select ==i+1)	System.out.print("■ ");
				else				System.out.print("□ ");
				System.out.print(selects[i]);
			}
			PrintUtil.printBar2();

			switch(ScanUtil.nextLine()){
			case "1":	if(select==1)	select=selects.length;	else select--;	break;
			case "3":	if(select==selects.length)	select=1;	else select++;	break;
			case "":	break menu;
			default:	break;			}
		}
		switch(select){
		case 1 : return;					// 리뷰 작성하기 종료
		default : score = (select-1);	// grade에 별점 부여
		break;
		}
		
		grade = Util.scoreToStars(score);
		PrintUtil.title();
		System.out.printf("\t\t[%s]\n",resName);
		System.out.printf("\n\t내 별점 : %s\n\n",grade);
		System.out.printf("\t%s에 대한 의견을 자유롭게 작성해주세요.\n",resName);
		PrintUtil.printBar2();
		content = ScanUtil.nextLine();

		Map<String, Object> review = new HashMap<String, Object>();
		review.put("resId", resId);
		review.put("userId", userId);
		review.put("content", content);
		review.put("grade", score);

		if(userDao.newReview(review)==1){
			PrintUtil.title();
			System.out.printf("\t\t[%s]\n",resName);
			System.out.printf("\t내 별점 : %s\n",grade);
			System.out.printf("\t식당명 : %s \n\t리뷰 : %s\n",resName,content);
			System.out.println("\n\t계속 하려면 [엔터]키를 눌러주세요.");
			PrintUtil.printBar();
			content = ScanUtil.nextLine();
		}else
			System.out.println("리뷰작성 실패 버그 신고해주세요");
		return;
	}

	public int resList(List<Map<String, Object>> list){
		int select = 1;
		int page = 1;
		int resPerPage = 4;
		int maxPage = (list.size()-1)/resPerPage+1;
		if(list.size()==0) maxPage = 1;
		int nameLength = 8;	// 출력하고 싶은 가게 이름의 최대 길이
		int foodLength = 6; // 음식 종류 최대 길이
		int distanceLength = 5; // 거리 표현 최대 길이 

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

					names[i] = Util.cutString(list.get(startResNum+i).get("RES_NAME").toString(),nameLength);
					resId[i] = list.get(startResNum+i).get("RES_ID").toString();
					food[i] = Util.cutString(list.get(startResNum+i).get("COUSINE").toString(), foodLength);
					score[i] = Float.parseFloat(list.get(startResNum+i).get("SCORE").toString());
					distance[i] = Util.cutString2(list.get(startResNum+i).get("DISTANCE").toString(),distanceLength);
					likes[i] = Integer.parseInt(list.get(startResNum+i).get("PICK_CNT").toString());
				}

				PrintUtil.title2();
				System.out.println("        이름                    음식             평점             거리           추천수");

				for(int i=0; i<resPerPage; i++){
					if(select ==i+1)		System.out.print(" ■ ");
					else				System.out.print(" □ ");
					if(names[i]!=null){
						System.out.printf("%s  %s %s%sm      %d개\n",names[i],food[i],Util.scoreToStars(score[i]),distance[i],likes[i]);
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
			case 1: if(resId[0]!=null) resDetail(resId[0]); break;// resID가 null일 경우 아무것도 하지 않습니다.
			case 2: if(resId[1]!=null) resDetail(resId[1]); break;// resID가 null일 경우 아무것도 하지 않습니다.
			case 3: if(resId[2]!=null) resDetail(resId[2]); break;// resID가 null일 경우 아무것도 하지 않습니다.
			case 4: if(resId[3]!=null) resDetail(resId[3]); break;// resID가 null일 경우 아무것도 하지 않습니다.
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
