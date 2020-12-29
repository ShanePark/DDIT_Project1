package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.PrintUtil;
import util.ScanUtil;
import util.View;
import controller.Controller;
import dao.BoardDao;

public class BoardService {
	
	private static BoardService instance;
	private BoardService(){}
	public static BoardService getInstance()
	{
		if(instance == null){
			instance = new BoardService();
		}
		return instance;
	}
	
	private BoardDao boardDao = BoardDao.getInstance();
	
	private int currentBoardNo;
	
	private int page = 1;
	
	private int select = 1;
	private int boardNum;
	public int boardList()
	{
		
		boardDao.BoardArray();
		List<Map<String,Object>> boardList = boardDao.selectBoardList(page);
		boar:while(true)
		{
			
		PrintUtil.title2();
		System.out.println("번호     말머리 \t제목\t작성자\t작성일");
		System.out.println("----------------------------------------");
		if(boardList.size()%3 == 2)
		{
			System.out.println();
		}
		
		for(int i=0; i< boardList.size(); i++)
		{
			Map<String, Object> board = boardList.get(i);
			String title = (String)board.get("TITLE");
			if(title.length()>3)
			{
				title = title.substring(0, 3) + "..";
			}
			System.out.println( board.get("BOARD_NO")+ "     "
					+board.get("CATEGORY") + " \t"
					+title + "\t"
					+board.get("NICKNAME") + "\t"
					+board.get("SUBSTR(A.B_DATE,1,10)"));
			
		
		}
		 if(boardList.size()%3 == 1&&boardList.size()%3 != 2)
		{
			System.out.println();
			System.out.println();
		}
		

		
		if( select == 1|| select == 7)
		{
			System.out.print( "◀ "+page);
			System.out.print( "page▷    ");			
			System.out.print( "□게시글 조회  ");
			System.out.print( "□게시글 작성  ");
			System.out.print( "□식당문의  ");			
			System.out.print( "□뒤로가기");
		}
		if( select == 2)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▶    ");		
			System.out.print( "□게시글 조회  ");
			System.out.print( "□게시글 작성  ");
			System.out.print( "□식당문의  ");			
			System.out.print( "□뒤로가기");
		}
		if(select == 3)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▷    ");	
			System.out.print( "■게시글 조회  ");
			System.out.print( "□게시글 작성  ");
			System.out.print( "□식당문의  ");			
			System.out.print( "□뒤로가기");
			
		}
		if(select == 4)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▷    ");	
			System.out.print( "□게시글 조회  ");
			System.out.print( "■게시글 작성  ");
			System.out.print( "□식당문의  ");	
			System.out.print( "□뒤로가기");
			
		}
		if(select == 5)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▷    ");	
			System.out.print( "□게시글 조회  ");
			System.out.print( "□게시글 작성  ");
			System.out.print( "■식당문의  ");	
			System.out.print( "□뒤로가기");
		
		}
		if(select == 6||select == 0)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▷    ");	
			System.out.print( "□게시글 조회  ");
			System.out.print( "□게시글 작성  ");
			System.out.print( "□식당문의  ");	
			System.out.print( "■뒤로가기");
			
		}
		if(select ==7){select = 1;}
		if(select ==0){select = 6;}
		System.out.print("\n                           (1,3)← → (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
		String input = ScanUtil.nextLine();
		
		switch(input)
		{
		case "3" : select++;break;
		case "1" : select--;break;
		case "" : break boar;
		}
		
		}
		switch(select)
		{
		case 1: if(page != 1){page--;}
				break;
		case 2: long size = boardList.size();
				if(page <= size){page++;}
				select = 2;
				break;
		case 3:System.out.print("게시글 번호 입력 >");
				return View.BOARD_MANAGE2;
		case 4:if(Controller.user.get("USER_ID").toString().equals("guest"))
					{  PrintUtil.boardbase1();
					System.out.println("로그인이 필요한 서비스입니다");
				    PrintUtil.boardbase2();
					String temp = ScanUtil.nextLine();
					return View.SIGNUP;}
				return View.BOARD_ADD;
		case 5:
			if(Controller.user.get("USER_ID").toString().equals("guest"))
					{  
						PrintUtil.boardbase1();
						System.out.println("로그인이 필요한 서비스입니다");
					    PrintUtil.boardbase2();
						String temp = ScanUtil.nextLine();
						return View.SIGNUP;
					}
			else if(Controller.user.get("USER_ID").toString().equals("admin"))
					{
						return View.BOARD_ADMIN;
					}
			else 
					{ 
						return View.BOARD_USER;
					}
		case 6:return View.USER_MAIN;
			default : return View.BOARD_MAIN;
		}
		 return View.BOARD_MAIN;
		
		}
	
		
	
	
		//게시판 글쓰기
	public int boardAdd()
	{
		String userId=Controller.user.get("USER_ID").toString();
		String nickName = Controller.user.get("NICKNAME").toString();
		
		String cateGory = null ;  String tiTle = "___" ; String conTent = "___" ;
		if(userId == "admin") {cateGory = "공지사항";}
		else {cateGory = "건의사항";}
		
		
		Add:while(true)
		{
		
		check:while(true)
		{
			
		
		PrintUtil.title();
		System.out.println("  📄건의사항✏️\n");
		System.out.print("제목\t: " + tiTle + "\n");
		System.out.print("내용\t: " + conTent + "\n\n");
		System.out.println("\t\t   뒤로 가러면 [Enter] 키를 누르세요");
		PrintUtil.printBar();
		
		
		
		if(tiTle != "___"){
			System.out.print("내용 : ");
				conTent = ScanUtil.nextLine();
				if(conTent.equals(""))
				{
					return View.BOARD_MAIN;	
				}
					break check;
			}
		if (tiTle == "___"){
			System.out.print("제목 : ");
			tiTle = ScanUtil.nextLine();
			if(tiTle.equals(""))
			{
				return View.BOARD_MAIN;	
			}
		}
		
		
		
		}
		
		
		    
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("TITLE", tiTle);
			param.put("USER_ID",userId);
			param.put("CONTENT", conTent);
			param.put("CATEGORY", cateGory);
			int result = boardDao.centerBoardAdd(param);
			
			if(0 < result){
				System.out.println("글 등록 성공");
				return View.BOARD_MAIN;	
			}else{
				System.out.println("글 등록 실패");
				return View.BOARD_MAIN;	
				}
			}
		
	
		}
	
	
	//게시글 상세하게불러오기
	public int boardSelect()
	{

		boardNum = ScanUtil.nextInt();

		int select = 1;
		Map<String, Object> board = boardDao.selectBoardOne(boardNum);
		boardone:while(true)
		{
			if(select == 0){select =3;}
			if(select == 4){select =1;}
			String category = board.get("CATEGORY").toString();
			String nickname = board.get("NICKNAME").toString();
			String title = board.get("TITLE").toString();
			String content = board.get("CONTENT").toString();
			String userID = board.get("USER_ID").toString();
			PrintUtil.title2();
			System.out.println("말머리 : "+category);
			System.out.println("닉네임 : "+nickname);
			System.out.println("제목 : "+title);
			System.out.println("내용 : "+content);

			if(select == 1){System.out.println("■게시글 수정	□게시글 삭제	□뒤로가기");}
			if(select == 2){System.out.println("□게시글 수정	■게시글 삭제	□뒤로가기");}
			if(select == 3){System.out.println("□게시글 수정	□게시글 삭제	■뒤로가기");}
			System.out.print("\n                           (1,3)← → (⏎)확인 \n");
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
			String tes = ScanUtil.nextLine();
			switch(tes)
			{
			case "1" :select--;break;
			case "3" :select++;break;
			}
			if(tes.equals("")){
			switch(select)
			{
			case 1 : if(!Controller.user.get("USER_ID").toString().equals(userID))
					{
						PrintUtil.boardbase1();
						System.out.println("본인계정이 아닙니다.");
					    PrintUtil.boardbase2();
						String temp = ScanUtil.nextLine();
						break;
					}else if(Controller.user.get("USER_ID").toString().equals("guest"))
					{
						
						PrintUtil.boardbase1();
						System.out.println("로그인이 필요한 서비스입니다");
					    PrintUtil.boardbase2();
						String temp = ScanUtil.nextLine();
						return View.SIGNUP;
					} return View.BOARD_ALTER;
			case 2 : if(!Controller.user.get("USER_ID").toString().equals(userID))
					{
						PrintUtil.boardbase1();
						System.out.println("본인계정이 아닙니다.");
					    PrintUtil.boardbase2();
						String temp = ScanUtil.nextLine();
						break;
					}else if(Controller.user.get("USER_ID").toString().equals("guest"))
					{
						PrintUtil.boardbase1();
						System.out.println("로그인이 필요한 서비스입니다");
					    PrintUtil.boardbase2();
						String temp = ScanUtil.nextLine();
						return View.SIGNUP;
					}return View.BOARD_DELETE;
			case 3 :break boardone;
			}
			}
		}
		return View.BOARD_MAIN;	
	}

	
	
	//건의사항 글 수정
	public int boardAlter()
	{
		Map<String, Object> board = boardDao.selectBoardOne(boardNum);
		int select =0;
		String category = board.get("CATEGORY").toString();
		String nickname = board.get("NICKNAME").toString();
		String title = board.get("TITLE").toString();
		String content = board.get("CONTENT").toString();
		String userID = board.get("USER_ID").toString();
		boardone:while(true)
		{
			if(select == 0){select =3;}
			if(select == 4){select =1;}
			
			PrintUtil.title2();
			System.out.println("말머리 : "+category);
			System.out.println("닉네임 : "+nickname);
			System.out.println("제목 : "+title);
			System.out.println("내용 : "+content);

			if(select == 1){System.out.println("\t■제목     □내용     □확인     □취소");}
			if(select == 2){System.out.println("\t□제목     ■내용     □확인     □취소");}
			if(select == 3){System.out.println("\t□제목     □내용     ■확인     □취소");}
			if(select == 4){System.out.println("\t□제목     □내용     □확인     ■취소");}
			System.out.print("\n                           (1,3)← → (⏎)확인 \n");
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
			String tes = ScanUtil.nextLine();
			switch(tes)
			{
			case "1" :select--;break;
			case "3" :select++;break;
			}
			if(tes.equals(""))
			switch(select)
			{
			case 1 :
					PrintUtil.boardbase1();
					System.out.println("제목을 입력하시오...");
					PrintUtil.boardbase2();
					System.out.print("제목 : ");
					title = ScanUtil.nextLine();
					break;
			case 2 :
					PrintUtil.boardbase1();
					System.out.println("제목을 입력하시오...");
					PrintUtil.boardbase2();
					System.out.print("내용 : ");
					content = ScanUtil.nextLine();
					break;
			case 3 :
					
				    boardDao.selectBoardAlter(title,content,boardNum);
				    PrintUtil.boardbase1();
				    System.out.println("수정되었습니다.");
				    PrintUtil.boardbase2();
				    tes =ScanUtil.nextLine();
					return View.BOARD_MAIN;
			case 4 :return View.BOARD_MAIN;
			}
		}
		
		
	}
	
	
	//건의사항 글 삭제
	public int boardDelete()
	{
		int select = 1;
		delete: while(true){
		PrintUtil.boardbase1();
	    System.out.println("정말 삭제하시겠습니까?");
	    System.out.println("\n");
	    if(select%2 == 1){System.out.println("                  ■예     □아니오");}
		if(select%2 == 0){System.out.println("                  □예     ■아니오");}
		System.out.print("\n                           (1,3)← → (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");
		String tes =ScanUtil.nextLine();
		switch(tes)
		{
		case "1" : select++;break;
		case "3" : select--;break;
		case "" : if(select%2 == 1)
					{
					boardDao.BoardDelete(boardNum);
					PrintUtil.boardbase1();
				    System.out.println("삭제되었습니다.");
				    PrintUtil.boardbase2();
				    tes =ScanUtil.nextLine();
				    return View.BOARD_MAIN;
					}
				  if(select%2 == 0){return View.BOARD_MAIN;}
				  
		}
		
		}
		
		
	}
	public int boardRes_admin()
	{
		return boardNum;
		
	}
	
	
	public int boardRes_user()
	{
		return boardNum;
		
	}
	
}