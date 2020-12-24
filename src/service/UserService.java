package service;

import java.util.HashMap;
import java.util.Map;

import util.PrintUtil;
import util.ScanUtil;
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
		System.out.println("          PW : \n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("ID> ");
		String userID = ScanUtil.nextLine();
		System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		PrintUtil.title();
		System.out.print("\n\n          ID : "+userID+"\n");
		System.out.println("          PW : \n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("PASSWORD> ");
		String password = ScanUtil.nextLine();

		Map<String, Object> user = userDao.userSignIn(userID,password);

		if(user.size() ==0){	//user == null
			System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
		}else{
			Controller.user = user;

			if(user.get("USER_ID").equals("admin"))	//	로그인한 계정이 admin일 경우 관리화면으로 갑니다
				return View.ADMIN_MAIN;

			return View.USER_MAIN;
		}



		return View.SIGNIN;
	}

	public int signUp(){	
		PrintUtil.title();;
		System.out.println("                                    🥄회원가입🥢");
		System.out.print("        ID : \n");
		System.out.print("        PW : \n");
		System.out.print("                 닉네임 : \n\n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("ID> ");
		String userID = ScanUtil.nextLine();
		
		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢");
		System.out.print("        ID : ");System.out.print(userID+"\n");
		System.out.print("        PW : \n");
		System.out.print("                 닉네임 : \n\n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("PASSWORD> ");
		String password = ScanUtil.nextLine();
		
		PrintUtil.title();
		System.out.println("                                    🥄회원가입🥢 ");
		System.out.print("        ID : ");System.out.print(userID+"\n");
		System.out.print("        PW : ");
		for(int i=0; i<password.length(); i++)
			System.out.print("*");
		System.out.print("\n                 닉네임 : \n\n");
		System.out.print("\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("닉네임 >");
		String nickname = ScanUtil.nextLine();

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

	public int userMain(){
		String nickname = Controller.user.get("NICKNAME").toString();
		System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
		for(int i=nickname.length(); i<32; i++)
			System.out.print(" ");
		System.out.printf("[%s](으)로 접속중\n",nickname);
		PrintUtil.title();
		System.out.println("□거리별                                    🥘 오늘의 추천식당🍝                                              ");
		System.out.println("□가격별                       1. 가게명 / 평점          5. 가게명 / 평점      ");                   
		System.out.println("□평점별                       2. 가게명 / 평점          6. 가게명 / 평점     ");
		System.out.println("□음식종류별                 3. 가게명 / 평점          7. 가게명 / 평점");
		System.out.println("□리뷰많은순                 4. 가게명 / 평점          8. 가게명 / 평점");
		System.out.println("");            
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");

		ScanUtil.nextLine();

		return View.MAIN;

	}

	public int guestMode(){	

		Map<String, Object> user = userDao.userSignIn("guest","guest");	//guest 로 로그인

		Controller.user = user;
		System.out.println("비회원으로 이용합니다.");

		return View.USER_MAIN;


	}

}
