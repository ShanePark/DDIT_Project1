package service;

import java.util.List;
import java.util.Map;

import util.ScanUtil;
import util.View;
import controller.Controller;
import dao.BoardDao;

public class BoardService {
	
	private static BoardService instance;
	private BoardService(){}
	public static BoardService getInstance(){
		if(instance == null){
			instance = new BoardService();
		}
		return instance;
	}
	
	private BoardDao boardDao = BoardDao.getInstance();
	
	private int currentBoardNo;
	
	public int boardList(){
		List<Map<String,Object>> boardList = boardDao.selectBoardList();
		System.out.println("=================================");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("---------------------------------");
		for(int i=0; i< boardList.size(); i++){
			Map<String, Object> board = boardList.get(i);
			System.out.println(board.get("BOARD_NO") + "\t"
					+board.get("TITLE") + "\t"
					+board.get("USER_ID") + "\t"
					+board.get("REG_DATE"));
		}
		System.out.println("=================================");
		System.out.println("1.조회\t2.등록\t0.로그아웃");
		System.out.println("입력>");
		
		int input = ScanUtil.nextInt();
		
		switch(input){
		case 1:
			System.out.print("게시글 번호 입력 >");
			currentBoardNo=ScanUtil.nextInt();
			return View.BOARD_VIEW;

		case 2:
			return View.BOARD_INSERT_FORM;
		case 0:
			Controller.loginUser = null;
			return View.HOME;
		}
		return View.BOARD_LIST;

	}

}
