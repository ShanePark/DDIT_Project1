package service;

import java.util.HashMap;
import java.util.Map;

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
		System.out.println("=============== 로그인 ==============");
		System.out.print("아이디>");
		String userId = ScanUtil.nextLine();
		System.out.print("비밀번호>");
		String password = ScanUtil.nextLine();
		
		Map<String, Object> user = userDao.userSignIn(userId,password);
		
		if(user.size() ==0){	//user == null
			System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
		}else{
			System.out.println("로그인 성공");
			Controller.user = user;
			
			if(user.get("USER_ID").equals("admin"))	//	로그인한 계정이 admin일 경우 관리화면으로 갑니다
					return View.ADMIN_MAIN;
			
			return View.USER_MAIN;
		}
		
		
		
		return View.SIGNIN;
	}
	
	public int signUp(){	// 작성 필요
		System.out.println("=============== 회원가입 ==============");
		System.out.print("아이디 >");
		String userId = ScanUtil.nextLine();
		System.out.print("비밀번호 >");
		String password = ScanUtil.nextLine();
		System.out.print("닉네임 >");
		String nickname = ScanUtil.nextLine();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("USER_ID", userId);
		param.put("PASSWORD", password);
		param.put("NICKNAME", nickname);
		
		int result = userDao.userSignUp(param);
		
		if(0 < result){
			System.out.println("회원가입 성공");
			return View.USER_MAIN;	// 가입에 성공하면 USER_MAIN 화면으로 갑니다.
		}else{
			System.out.println("회원가입 실패");
			return View.MAIN;		// 가입에 실패하면 MAIN 화면으로 돌아갑니다.
		}
	}
	
	public int userMain(){
		System.out.println("USER MAIN");
		ScanUtil.nextLine();
		
		return View.MAIN;
		
	}
	
	public int guestMode(){	
		
		Map<String, Object> user = userDao.userSignIn("guest","guest");
		
		if(user.size() ==0){	//user == null
			System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
		}else{
			Controller.user = user;
			System.out.println("비회원으로 이용합니다.");
			
			return View.USER_MAIN;
		}
		
		
		return View.SIGNIN;
	}

}
