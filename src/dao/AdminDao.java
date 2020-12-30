package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDao {
	
	private static AdminDao instance;
	private AdminDao(){}
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public static AdminDao getInstance(){
		if(instance == null){
			instance = new AdminDao();
		}
		return instance;
	}
	
	public int resAdd(Map<String, Object> param){	//완료 BUT 테스트 필요
		String sql = "insert into RESTAURANTS(RES_ID,RES_NAME,COUSINE,OPEN_TIME,CLOSE_TIME,ADD1,DISTANCE)"
                +"values((select nvl(max(RES_ID),0)+1 from RESTAURANTS)"
				+",?,?,?,?,?,?)";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("RES_NAME"));
		p.add(param.get("COUSINE"));
		p.add(param.get("OPEN_TIME"));
		p.add(param.get("CLOSE_TIME"));
		p.add(param.get("ADD1"));
		p.add(param.get("DISTANCE"));
		
		return jdbc.update(sql,p);
	}
	
	public String resIdToName(String resId){
		String sql = "select res_name from restaurants where res_id = ?";
		List<Object> p = new ArrayList<>();
		p.add(resId);
		Map<String, Object> map = jdbc.selectOne(sql,p);
		return map.get("RES_NAME").toString();
	}
	
	public int menuAdd(String resId, String food, int price){
		String sql = "insert into menu(res_id, food, price) values(?,?,?)";
		List<Object> p = new ArrayList<>();
		p.add(resId);
		p.add(food);
		p.add(price);
		return jdbc.update(sql,p);
	}

	
}
