package controller;

import service.UserService;
import util.ScanUtil;
import util.View;

public class Controller {

	public static void main(String[] args) {
		System.out.println("Hello world.");

	}
	
	private UserService userService = UserService.getInstance();
	
	private void main(){	//작성 필요
		int view = View.MAIN;
		
		while(true){
			switch(view){
				
			}
		}
		
	}
	
	private int menu(){	//작성 필요
		
		int input = ScanUtil.nextInt();
		
		switch(input){
		}
		return View.MAIN;
	}

}
