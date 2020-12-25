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
		System.out.println("          PW : \n\n\t\t\t뒤로 가러면  [x] 를 입력");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("ID> ");
		String userID = ScanUtil.nextLine();
		if("x".equals(userID.toLowerCase()) || "ㅌ".equals(userID.toLowerCase()))	// "x" 혹은 "ㅌ" 키 입력시 메인화면으로 이동. 대소문자 구분 안하도록 toLowerCase() 사용
			return View.MAIN;
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

			if(user.get("USER_ID").equals("admin"))	//	로그인한 계정이 admin일 경우 관리화면으로 갑니다
				return View.ADMIN_MAIN;

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
		System.out.print("                 닉네임 : \n\n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("ID> ");
		userID = ScanUtil.nextLine();
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
			for(int i=nickname.length(); i<5; i++)
				System.out.print(" ");
			int nicknameLength=6;	// 해당 길이보다 긴 닉네임은 ..으로 표시합니다
			if(nickname.length()<nicknameLength) nicknameLength=nickname.length();
			System.out.print("   ["+nickname.substring(0,nicknameLength));
			if(nickname.length()>nicknameLength-1)
				System.out.print("..");
			System.out.println("](으)로 접속중");


			if(select==2){
				list = resByRvcnt();
				orderby = "리뷰수";
			}else if(select==3){
				list = resByDistance();
				orderby = "거리순";
			}else{
				list = resByScore();
				orderby = "평점순";
			}
			for(int i=0; i<5; i++){
				int length = 6;
				resName = list.get(i).get("RES_NAME").toString();
				score = list.get(i).get("SCORE").toString();
				distance = list.get(i).get("DISTANCE").toString();
				rvCnt = list.get(i).get("RV_CNT").toString();

				if(resName.length() < length)
					length = resName.length();
				res[i]=resName.substring(0,length)+" [평점 "
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
				System.out.print(" 관리페이지                    5. ");
			else if(nickname.equals("비회원"))
				System.out.print(" 비회원전용                    5. ");	// 비회원일때 마이페이지 대신 어떤 기능을 넣을지 정해야합니다
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

		return View.MAIN;
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


	public int guestMode(){	

		Map<String, Object> user = userDao.userSignIn("guest","guest");	//guest 로 로그인

		Controller.user = user;
		System.out.println("비회원으로 이용합니다.");

		return View.USER_MAIN;


	}

}
