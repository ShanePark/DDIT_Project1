package service;

import util.ScanUtil;
import util.View;

public class AdminService {
	
	private static AdminService instance;
	private AdminService(){}
	public static AdminService getInstance(){
		if(instance == null){
			instance = new AdminService();
		}
		return instance;
	}
	
	public int adminMain(){
		System.out.println("ADMIN MAIN");
		ScanUtil.nextLine();
		
		return View.MAIN;
		
	}

}
