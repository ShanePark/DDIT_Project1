package service;

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
	
	public int signIn(){	// 작성 필요
		return 0;
	}
	
	public int signUp(){	// 작성 필요
		return 0;
	}

}
