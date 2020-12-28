package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

	private JDBCUtil(){}
	private static JDBCUtil instance;

	public static JDBCUtil getInstance(){
		if(instance == null){
			instance = new JDBCUtil();
		}
		return instance;
	}

//	private String url = "jdbc:oracle:thin:@dditbasic.csqutft2bzky.ap-northeast-2.rds.amazonaws.com:1521:ORCL";
//	private String user = "admin";
//	private String password = "ddit302java";
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "DDIT";
	private String password = "java";

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/*
	 *1* Map<String, Object> SelectOne(String sql) 물음표가 없는 메서드
	 *2* Map<String, Object> SelectOne(String sql, List<Object> param) 물음표가 있는 메서드
	 *3* List<Map<String, Object>> selectList(String sql) list : 여러줄을 조회
	 *4* List<Map<String, Object>> selectList(String sql, List<Object> param)
	 *5* int update(String sql)
	 *6 int update(String sql, List<Object> param)
	 */

	//////////////////////////////////////////////////////////////////	↓Method 1
	public Map<String, Object> selectOne(String sql){	
		Map<String, Object> row = new HashMap<>();

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();

			ResultSetMetaData md = rs.getMetaData();

			int columnCount = md.getColumnCount();

			for(int i=1; i<= columnCount; i++){
				String key =md.getColumnName(i);
				Object value = rs.getObject(key);
				row.put(key, value);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{ rs.close(); } catch(Exception e){}
			if(ps != null) try{ ps.close(); } catch(Exception e){}
			if(con != null) try{ con.close(); } catch(Exception e){}
		}

		return row;
	}
	//////////////////////////////////////////////////////////////////	↓Method 2	
	public Map<String, Object> selectOne(String sql,List<Object> param){	
		Map<String, Object> row = new HashMap<>();

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			for(int i=0; i<param.size(); i++){
				ps.setObject(i+1, param.get(i));
			}

			rs = ps.executeQuery();
			rs.next();

			ResultSetMetaData md = rs.getMetaData();

			int columnCount = md.getColumnCount();

			for(int i=1; i<= columnCount; i++){
				String key =md.getColumnName(i);
				Object value = rs.getObject(key);
				row.put(key, value);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{ rs.close(); } catch(Exception e){}
			if(ps != null) try{ ps.close(); } catch(Exception e){}
			if(con != null) try{ con.close(); } catch(Exception e){}
		}

		return row;
	}

	//////////////////////////////////////////////////////////////////	↓Method 3
	public List<Map<String, Object>> selectList(String sql){	
		List<Map<String, Object>> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData md = rs.getMetaData();

			int columnCount = md.getColumnCount();

			while(rs.next()){
				Map<String, Object> row = new HashMap<>();
				for(int i=1; i<= columnCount; i++){
					String key =md.getColumnName(i);
					Object value = rs.getObject(key);
					row.put(key, value);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{ rs.close(); } catch(Exception e){}
			if(ps != null) try{ ps.close(); } catch(Exception e){}
			if(con != null) try{ con.close(); } catch(Exception e){}
		}

		return list;

	}
	//////////////////////////////////////////////////////////////////	↓Method 4
	public List<Map<String, Object>> selectList(String sql, List<Object> param){
		// 쿼리를 조회한 결과를 list에 담아 반환
		List<Map<String, Object>> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);

			for(int i=0; i<param.size(); i++){
				ps.setObject(i+1, param.get(i));
			}

			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();

			while(rs.next()){
				Map<String, Object> row = new HashMap<>();
				for(int i=1; i<= columnCount; i++){
					String key =md.getColumnName(i);
					Object value = rs.getObject(key);
					row.put(key, value);
				}
				list.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{ rs.close(); } catch(Exception e){}
			if(ps != null) try{ ps.close(); } catch(Exception e){}
			if(con != null) try{ con.close(); } catch(Exception e){}
		}

		return list;

	}

	//////////////////////////////////////////////////////////////////	↓Method 5
	public int update(String sql){
		int result=0;

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{ rs.close(); } catch(Exception e){}
			if(ps != null) try{ ps.close(); } catch(Exception e){}
			if(con != null) try{ con.close(); } catch(Exception e){}
		}

		return result;
	}

	//////////////////////////////////////////////////////////////////	↓Method 6	
	public int update(String sql,List<Object> param ){
		int result=0;

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			for(int i=0; i<param.size(); i++){
				ps.setObject(i+1, param.get(i));
			}
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{ rs.close(); } catch(Exception e){}
			if(ps != null) try{ ps.close(); } catch(Exception e){}
			if(con != null) try{ con.close(); } catch(Exception e){}
		}

		return result;
	}

}
