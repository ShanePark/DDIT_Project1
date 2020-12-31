package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.PrintUtil;
import util.ScanUtil;
import util.View;
import controller.Controller;
import dao.AdminDao;
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
	private AdminDao adminDao = AdminDao.getInstance();
	private int currentBoardNo;
	
	private int page = 1;
	
	private int select = 2;
	private int boardNum;
	public int boardList()
	{
		
		boardDao.BoardArray();
		List<Map<String,Object>> boardList = boardDao.selectBoardList();
		int perpage = 3;
		int start = (page-1)*perpage;
		int end =perpage*page-1;
		int maxpage = (boardList.size()-1)/perpage+1;
		boar:while(true)
		{
			
		PrintUtil.title2();
		System.out.println("번호     말머리 \t제목\t작성자\t작성일");
		System.out.println("----------------------------------------");
		if(end > boardList.size()){end = boardList.size()-1;}
		
		for(int i=start; i <= end; i++)
		{
			if(boardList.size() <= i)
			{
				System.out.println();
				continue;
			}
			
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
		case 2: double size = boardList.size()/perpage;
				if(page != maxpage){page++;}
				select = 2;
				break;
		case 3:System.out.print("게시글 번호 입력 >");
				return View.BOARD_MANAGE2;
		case 4:if(Controller.user.get("USER_ID").toString().equals("guest"))
					{  PrintUtil.boardbase1();
					System.out.println("로그인이 필요한 서비스입니다");
				    PrintUtil.boardbase2();
					String temp = ScanUtil.nextLine();
					return View.BOARD_MAIN;}
				return View.BOARD_ADD;
		case 5:
			if(Controller.user.get("USER_ID").toString().equals("guest"))
					{  
						PrintUtil.boardbase1();
						System.out.println("로그인이 필요한 서비스입니다");
					    PrintUtil.boardbase2();
						String temp = ScanUtil.nextLine();
						return View.BOARD_MAIN;
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
						return View.BOARD_MAIN;
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
						return View.BOARD_MAIN;
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
	
	//식당문의 확인(관리자)
	public int boardRes_admin()
	{
		boardDao.ResBoardArray();
		select = 1;
		page = 1;
		int perpage = 3;
		int start =(page-1)*perpage;
		int end =perpage*page;
		List<Map<String,Object>> boardaList = boardDao.selectBoardRes();
		int maxpage = (boardaList.size()-1)/perpage+1;
		boar:while(true)
		{
		PrintUtil.title2();
		System.out.println("번호\t승인여부\t식당이름\t음식종류\t작성자");
		System.out.println("----------------------------------------");
		
		
		
		for(int i=start; i <= end; i++)
		{
			if(boardaList.size() <= i)
			{
				System.out.println();
				continue;
			}
			Map<String, Object> boarda = boardaList.get(i);
			String resNAME = (String)boarda.get("RES_NAME");
			if(resNAME.length()>3)
			{
				resNAME = resNAME.substring(0, 3) + "..";
			}
			System.out.println( boarda.get("RES_BOARD_NO")+ "\t"
					+boarda.get("AVAIL") + "\t"
					+resNAME + "\t"
					+boarda.get("COUSINE") + "\t"
					+boarda.get("USER_ID"));
			
		
		}
		
		
		

		
		if( select == 1|| select == 5)
		{
			System.out.print( "◀ "+page);
			System.out.print( "page▷\t");			
			System.out.print( "□게시글 조회  ");
			System.out.print( "□뒤로가기");
		}
		if( select == 2)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▶\t");		
			System.out.print( "□게시글 조회  ");	
			System.out.print( "□뒤로가기");
		}
		if(select == 3)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▷\t");	
			System.out.print( "■게시글 조회  ");		
			System.out.print( "□뒤로가기");
			
		}
		
		
		if(select == 4||select == 0)
		{
			System.out.print( "◁ "+page);
			System.out.print( "page▷\t");	
			System.out.print( "□게시글 조회  ");
			System.out.print( "■뒤로가기");
			
		}
		if(select ==5){select = 1;}
		if(select ==0){select = 4;}
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
		case 2: long size = boardaList.size()/perpage;
				if(page != maxpage){page++;}
				select = 2;
				break;
		case 3:System.out.print("게시글 번호 입력 >");
				return View.BOARD_ADMIN_MANAGE;
		case 4: return View.BOARD_MAIN;
				
			default : return View.BOARD_ADMIN;
		}
		 return View.BOARD_ADMIN;
		
		}
		
	
	
	
	
	//유저 식당문의
	public int boardRes_user()
	{
		
		int select = 1;
		int[] complete = {0,0,0}; 
		String resName="",cousine="",add1="",openTime="",closeTime="",userID = Controller.user.get("USER_ID").toString(), avail = "미승인";
		int distance=0;

		addRes:while(true){
			input:while(true){
				int max = 4;
				if(complete[0]==1 && complete[1]==1 && complete[2]==1)
					max = 5;
				PrintUtil.title();
				System.out.println("  📄건의사항✏️\n");
				if(select ==1)		System.out.print("           ■");
				else				System.out.print("           □");
				if(complete[0]==0)
					System.out.print(" 가게명 음식스타일  입력하기 \n");
				else
					System.out.printf(" 가게명: %s [%s] \n",resName,cousine);
				if(select ==2)		System.out.print("           ■");
				else				System.out.print("           □");
				if(complete[1]==0)
					System.out.print(" 주소,거리  입력하기 \n");
				else
					System.out.printf(" 주소: %s [거리 %dm] \n",add1,distance);
				if(select ==3)		System.out.print("           ■");
				else				System.out.print("           □");
				if(complete[2]==0)
					System.out.print(" 영업시간  입력하기\n");
				else
					System.out.printf(" 영업시간  [%s - %s]\n",openTime,closeTime);
				if(select ==4)		System.out.print("           ■");
				else				System.out.print("           □");
					System.out.print(" 뒤로가기 ");
				if(complete[0]==1 && complete[1]==1 && complete[2]==1){
					if(select ==5)		System.out.print("\n           ■");
					else				System.out.print("\n           □");
					System.out.print(" 입력 완료! 식당 등록하기 ");
				}else System.out.println();
				System.out.print("\t\t");
				if(!(complete[0]==1 && complete[1]==1 && complete[2]==1))
					PrintUtil.joystick2();
				else
					PrintUtil.printBar2();

				switch(ScanUtil.nextLine()){
				case "5":
					if(select==1)
						select=max;
					else select--;
					break;
				case "2":
					if(select==max)
						select=1;
					else select++;
					break;
				case "":
					break input;
				default:
					break;
				}
			}
		switch(select){
		case 1: if(complete[0]==1){break;}
		else{
			PrintUtil.title();
			System.out.println("  📄건의사항✏️\n");
			System.out.println("                      식당 이름을 입력해주세요\n\n");
			PrintUtil.printBar();
			resName = ScanUtil.nextLine();

			PrintUtil.title();
			System.out.println("  📄건의사항✏️\n");
			System.out.println("                       식당 이름 : " + resName);
			System.out.println("                      음식스타일을 입력해주세요\n");
			PrintUtil.printBar();
			cousine = ScanUtil.nextLine();

			complete[0] = 1;

		}
		break;
		case 2:if(complete[1]==1){break;}
		else{
			PrintUtil.title();
			System.out.println("  📄건의사항✏️\n");
			System.out.println("                        주소를 입력해주세요\n\n");
			PrintUtil.printBar();
			add1 = ScanUtil.nextLine();

			PrintUtil.title();
			System.out.println("  📄건의사항✏️\n");
			System.out.println("                       주소 : " + add1);
			System.out.println("               학원으로부터 거리(m)를 숫자로 입력해주세요\n");
			PrintUtil.printBar();
			distance = Integer.parseInt(ScanUtil.nextLine());

			complete[1] = 1;

		}
		break;
		case 3:if(complete[2]==1){break;}
		else{
			PrintUtil.title();
			System.out.println("  📄건의사항✏️\n");
			System.out.println("                    오픈시간을 입력해주세요 (예)12:00\n\n");
			PrintUtil.printBar();
			openTime = ScanUtil.nextLine();

			PrintUtil.title();
			System.out.println("  📄건의사항✏️\n");
			System.out.println("                       오픈시간 " + openTime);
			System.out.println("                    마감시간을 입력해주세요 (예)12:00\n");
			PrintUtil.printBar();
			closeTime = ScanUtil.nextLine();

			complete[2] = 1;
		}
		break;
		case 4:
			return View.BOARD_MAIN;
		case 5:
			break addRes;
		}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("RES_NAME", resName);
		param.put("COUSINE", cousine);
		param.put("OPEN_TIME", openTime);
		param.put("CLOSE_TIME", closeTime);
		param.put("ADD1", add1);
		param.put("DISTANCE", distance);
		param.put("USER_ID", userID);
		param.put("AVAIL", avail);
		
		int result = boardDao.BoardresAdd(param);
		
		if(0 < result){
			
			PrintUtil.boardbase1();
			System.out.println("글 등록 성공");
		    PrintUtil.boardbase2();
		    String tes =ScanUtil.nextLine();
			return View.BOARD_MAIN;	
		}else{
			PrintUtil.boardbase1();
			System.out.println("글 등록 실패");
		    PrintUtil.boardbase2();
		    String tes =ScanUtil.nextLine();
			return View.BOARD_MAIN;	
			}
		
		
		
	}
	
	
	//식당문의 상세조회
	public int boardSelectAdmin()
	{

		int boardNum = ScanUtil.nextInt();

		int select = 1;
		Map<String, Object> board = boardDao.selectBoardOneRes(boardNum);
		String resName = board.get("RES_NAME").toString();
		String avAil = board.get("AVAIL").toString();
		String disTance = board.get("DISTANCE").toString();
		String openTime = board.get("OPEN_TIME").toString();
		String Add = board.get("ADD1").toString();			
		String closeTime = board.get("CLOSE_TIME").toString();
		String couSine = board.get("COUSINE").toString();
		String userID = board.get("USER_ID").toString();
		boardone:while(true)
		{
			
			
			PrintUtil.title2();
			System.out.println("식당명 : "+resName);
			System.out.println("승인여부 : "+avAil);
			System.out.println("주소 :" +Add);
			System.out.println("거리 : "+disTance);
			System.out.println("영업시간 : "+openTime+"~"+closeTime);
			System.out.println("음식장르 : "+couSine);
			System.out.println("작성자 : "+userID);

			if(select == 1){System.out.println("■승인		□미승인");}
			if(select == 2){System.out.println("□승인		■미승인");}
			System.out.print("\n                           (1,3)← → (⏎)확인 \n");
			System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
			String tes = ScanUtil.nextLine();
			switch(tes)
			{
			case "1" :if(select == 1)select =2;else select--;break;
			case "3" :if(select == 2)select =1;else select++;break;
			case "" : break boardone;
			}
		}
				
			switch(select)
			{
			case 1 : Map<String, Object> param = new HashMap<String, Object>();
					 param.put("RES_NAME", resName);
					 param.put("COUSINE", couSine);
					 param.put("OPEN_TIME", openTime);
					 param.put("CLOSE_TIME", closeTime);
					 param.put("ADD1", Add);
					 param.put("DISTANCE", disTance);
					 
					 int result = adminDao.resAdd(param);
					 
					 if(0 < result)
					 {
						boardDao.ResBoardDelete(boardNum);
					 	System.out.println("식당 등록 성공");
					 	return View.BOARD_ADMIN;	
					 }else
					 {
						System.out.println("식당 등록 실패");
						return View.BOARD_ADMIN;	
					 }
				
			case 2 : return View.BOARD_ADMIN;
			
			}
			return View.BOARD_ADMIN;
		
	}
	
}