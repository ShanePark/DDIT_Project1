package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class BoardDao {
	
	private static BoardDao instance;
	private BoardDao(){}
	public static BoardDao getInstance(){
		if(instance == null){
			instance = new BoardDao();
		}
		return instance;
	}
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	public List<Map<String, Object>> selectBoardList(){
		
		String sql = "SELECT A.BOARD_NO, A.TITLE, A.CONTENT, B.NICKNAME, SUBSTR(A.B_DATE,1,10), A.CATEGORY"
				+ " FROM BOARD A"
				+ " LEFT OUTER JOIN USERS B"
				+ " ON A.USER_ID = B.USER_ID"
//				+ " WHERE BOARD_NO BETWEEN ? AND ?"
				+ " ORDER BY CASE WHEN B.NICKNAME LIKE '관리자' THEN 1 ELSE 2 END"
				+ " ,A.BOARD_NO DESC ";
				
		return jdbc.selectList(sql);
				
	}
	
		public int centerBoardAdd(Map<String, Object> param){
	{
		String sql = "INSERT INTO BOARD(BOARD_NO,TITLE,CONTENT,CATEGORY,B_DATE,USER_ID) VALUES"
				+ " ((select nvl(max(BOARD_NO),0)+1 from BOARD),?,?,?,(SELECT TO_DATE(SYSDATE, 'yyyy mm dd')FROM DUAL),?)";
		List<Object> p = new ArrayList<>();
		p.add(param.get("TITLE"));
		p.add(param.get("CONTENT"));
		p.add(param.get("CATEGORY"));
		p.add(param.get("USER_ID"));
		
		return jdbc.update(sql,p);
	}
	
	
}
		public Map<String,Object> selectBoardOne(int boardNum){
		{
			String sql = "SELECT A.BOARD_NO, A.TITLE, A.CONTENT, B.NICKNAME, SUBSTR(A.B_DATE,1,10), A.CATEGORY, A.USER_ID"
					+ " FROM BOARD A"
					+ " LEFT OUTER JOIN USERS B"
					+ " ON A.USER_ID = B.USER_ID"
					+ " WHERE BOARD_NO = ?";
			
			List<Object> p = new ArrayList<>();
			p.add(boardNum);
			return jdbc.selectOne(sql, p);
			
					
		}
		
		}
		public int selectBoardAlter(String title,String content,int boardNum){
			String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? "
					+ " WHERE BOARD_NO = ?";
			List<Object> p = new ArrayList<>();
			p.add(title);
			p.add(content);
			p.add(boardNum);
			return jdbc.update(sql, p);

    }
		public int BoardDelete(int boardNum)
		{
			String sql = "DELETE FROM board WHERE board_no = ?";
			List<Object> p = new ArrayList<>();
			p.add(boardNum);
			return jdbc.update(sql,p);
		}
		
		
		public int BoardArray()
		{
			String sql = "UPDATE board SET BOARD_NO = ROWNUM";
			
			
			return jdbc.update(sql);
		}
		
		
		public int BoardresAdd(Map<String, Object> param){	//완료 BUT 테스트 필요
			String sql = "insert into RESBOARD(RES_NAME,COUSINE,OPEN_TIME,CLOSE_TIME,ADD1,DISTANCE,USER_ID,AVAIL)"
	                +"values((select nvl(max(RES_BOARD_NO),0)+1 from RESBOARD)"
					+",?,?,?,?,?,?,?,?)";
			
			List<Object> p = new ArrayList<>();
			p.add(param.get("RES_NAME"));
			p.add(param.get("COUSINE"));
			p.add(param.get("OPEN_TIME"));
			p.add(param.get("CLOSE_TIME"));
			p.add(param.get("ADD1"));
			p.add(param.get("DISTANCE"));
			p.add(param.get("USER_ID"));
			p.add(param.get("AVAIL"));
			
			return jdbc.update(sql,p);
		}
		
		
		public List<Map<String, Object>> selectBoardRes(int page){
			int perpage = 3;
			int start = 1 + (page-1)*perpage;
			int end =perpage*page;
			String sql = "RES_BOARD_NO,RES_NAME,COUSINE,USER_ID,AVAIL"
					+ " FROM RESBOARD"
					+ " WHERE RES_BOARD_NO BETWEEN ? AND ?"
					+ " ORDER BY RES_BOARD_NO DESC";
					List<Object> p = new ArrayList<>();
					p.add(start);
					p.add(end);
			return jdbc.selectList(sql,p);
					
		}
}