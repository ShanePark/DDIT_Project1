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
	
	public int userSignUp(Map<String, Object> param){	
		
		String sql = "INSERT INTO USERS(USER_ID,PASSWORD,NICKNAME) VALUES (?,?,?)";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("USER_ID"));
		p.add(param.get("PASSWORD"));
		p.add(param.get("NICKNAME"));
		
		return jdbc.update(sql,p);
		
	}
	
	public Map<String, Object> userSignIn(String userId, String password){	
		String sql = "SELECT USER_ID, PASSWORD, NICKNAME"
				+ " FROM USERS"
				+ " WHERE USER_ID = ?"
				+ " AND PASSWORD = ?";
		List<Object> param = new ArrayList<>();
		param.add(userId);
		param.add(password);
		
		return jdbc.SelectOne(sql, param);
	}

}
