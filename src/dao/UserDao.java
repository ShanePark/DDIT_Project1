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
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt, nvl(c.cnt,0) pick_cnt"
				       +" from restaurants a, "
						   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
							  +" from review"
							 +" group by res_id) b,"
						   +" (select res_id, count(*) cnt"
							  +" from user_pick"
						     +" group by res_id) c"
					  +" where a.res_id = b.res_id(+)"
					   + " and a.res_id= c.res_id(+)"
				     +" order by distance";
		List<Map<String,Object>> list = jdbc.selectList(sql);
		
		return list;
	}
	
	public List<Map<String,Object>> resByScore(){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt, nvl(c.cnt,0) pick_cnt"
			          +" from restaurants a, "
					   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
						  +" from review"
						 +" group by res_id) b,"
					   +" (select res_id, count(*) cnt"
						  +" from user_pick"
					     +" group by res_id) c"
				  +" where a.res_id = b.res_id(+)"
				   + " and a.res_id= c.res_id(+)"
				  +" order by score desc";
				     
		List<Map<String,Object>> list = jdbc.selectList(sql);
		
		return list;
	}
	
	public List<Map<String,Object>> resByRvcnt(){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt, nvl(c.cnt,0) pick_cnt"
		          +" from restaurants a, "
				   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
					  +" from review"
					 +" group by res_id) b,"
				   +" (select res_id, count(*) cnt"
					  +" from user_pick"
				     +" group by res_id) c"
			  +" where a.res_id = b.res_id(+)"
			   + " and a.res_id= c.res_id(+)"
			  +" order by rv_cnt desc";
		List<Map<String,Object>> list = jdbc.selectList(sql);
		
		return list;
	}
	
	public List<Map<String,Object>> resByName(String name){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt, nvl(c.cnt,0) pick_cnt"
		          +" from restaurants a, "
				   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
					  +" from review"
					 +" group by res_id) b,"
				   +" (select res_id, count(*) cnt"
					  +" from user_pick"
				     +" group by res_id) c"
			  +" where a.res_id = b.res_id(+)"
			   + " and a.res_id= c.res_id(+)"
			    +" and res_name like ?"
			  +" order by rv_cnt desc";
		List<Object> p = new ArrayList<>();
		p.add("%"+name+"%");	// %를 쿼리에 String sql 에 쓰면 에러가 납니다. 아에 파라미터로 보내야 합니다.
		List<Map<String,Object>> list = jdbc.selectList(sql,p);
		
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
	
	public Map<String,Object> resDetail(String resId){
		String sql =  "select a.*, nvl(score,0) score, nvl(rv_cnt,0) rv_cnt, nvl(c.cnt,0) pick_cnt"
		          +" from restaurants a, "
				   +" (select res_id , round(avg(grade),1) score, count(*) rv_cnt"
					  +" from review"
					 +" group by res_id) b,"
				   +" (select res_id, count(*) cnt"
					  +" from user_pick"
				     +" group by res_id) c"
			  +" where a.res_id = b.res_id(+)"
			   + " and a.res_id= c.res_id(+)"
			    +" and a.res_id = ?";
		List<Object> p = new ArrayList<>();
		p.add(resId);
		return jdbc.SelectOne(sql, p);
		
	}
	
	public boolean isPick(String resId, String userId){	//식당 찜한지 확인해서 그 여부를 boolean으로 리턴
		String sql = "select count(*) cnt from user_pick"
				     +" where res_id = ? and user_id = ?";
		List<Object> p = new ArrayList<>();
		p.add(resId);
		p.add(userId);
		String isPick = jdbc.SelectOne(sql, p).get("CNT").toString();
		
		if(isPick.equals("1")) return true;
		else return false;
	}
	
	public int resPick(String resId, String userId){	//식당 찜하기
		String sql = "insert into user_pick(res_id,user_id) values(?,?)";
		List<Object> p = new ArrayList<>();
		p.add(resId);
		p.add(userId);
		return jdbc.update(sql, p);
	}
	
	public int resUnPick(String resId, String userId){	//식당 찜한것 취소하기
		String sql = "delete from user_pick where res_id=? and user_id=?";
		List<Object> p = new ArrayList<>();
		p.add(resId);
		p.add(userId);
		return jdbc.update(sql, p);
	}
	
	public List<Map<String,Object>> pickList(String userId){	//찜한 리스트 받아오기
		String sql = "select b.res_name, b.res_id, round(avg(c.grade),2) as score"
				    + " from user_pick a, restaurants b, review c"
			    	+ " where a.user_id = ? and a.res_id = b.res_id and b.res_id = c.res_id"
				 + " group by b.res_name, b.res_id"
				 + " order by b.res_name";
		List<Object> p = new ArrayList<>();
		p.add(userId);
		return jdbc.selectList(sql, p);
		
		
	}

}
