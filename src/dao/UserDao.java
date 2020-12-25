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
	
	public List<Map<String,Object>> resByDistance(){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt"
				       +" from restaurants a, "
						   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
							  +" from review"
							 +" group by res_id) b"
					  +" where a.res_id = b.res_id(+)"
				     +" order by distance";
		List<Map<String,Object>> list = jdbc.selectList(sql);
		
		return list;
	}
	
	public List<Map<String,Object>> resByScore(){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt"
				       +" from restaurants a, "
						   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
							  +" from review"
							 +" group by res_id) b"
					  +" where a.res_id = b.res_id(+)"
				     +" order by score desc";
		List<Map<String,Object>> list = jdbc.selectList(sql);
		
		return list;
	}
	
	public List<Map<String,Object>> resByRvcnt(){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt"
				       +" from restaurants a, "
						   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
							  +" from review"
							 +" group by res_id) b"
					  +" where a.res_id = b.res_id(+)"
				     +" order by rv_cnt desc";
		List<Map<String,Object>> list = jdbc.selectList(sql);
		
		return list;
	}
	
	public boolean isIdExist(String id){		// 입력한 아이디가 이미 존재하는지 확인하는 쿼리입니다.
		String sql = "select count(user_id) cnt from users where user_id=?";
		List<Object> p = new ArrayList<>();
		p.add(id);
		if(jdbc.SelectOne(sql, p).get("CNT").toString().equals("1"))
			return true;
		else return false;
		
	}
	
	public boolean isNicknameExist(String nickname){		// 입력한 닉네임이 이미 존재하는지 확인하는 쿼리입니다.
		String sql = "select count(nickname) cnt from users where nickname=?";
		List<Object> p = new ArrayList<>();
		p.add(nickname);
		if(jdbc.SelectOne(sql, p).get("CNT").toString().equals("1"))
			return true;
		else return false;
		
	}

}
