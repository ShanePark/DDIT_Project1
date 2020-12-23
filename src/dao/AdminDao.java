package dao;

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

}
