package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class UserDao {
	
	private static UserDao instance;
	private UserDao(){}
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public static UserDao getInstance(){
		if(instance == null){
			instance = new UserDao();
		}
		return instance;
	}
	
	public int userSignUp(Map<String, Object> param){	// 작성 필요
		
		
		return 0;
	}
	
	public Map<String, Object> userSignIn(String userId, String password){	//작성 필요
		List<Object> param = new ArrayList<>();
		param.add(userId);
		param.add(password);
		return jdbc.SelectOne("sql", param);
	}

}
