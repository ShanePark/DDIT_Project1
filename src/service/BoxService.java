package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import controller.Controller;
import util.PrintUtil;
import util.ScanUtil;
import util.View;
import dao.BoxDao;

public class BoxService {

	private static BoxService instance;
	private BoxService(){}
	public static BoxService getInstance(){
		if(instance == null){
			instance = new BoxService();
		}
		return instance;
	}

	SimpleDateFormat format1 = new SimpleDateFormat ( "MM월 dd일");
	Calendar time = Calendar.getInstance();
	String today = format1.format(time.getTime());

	private BoxDao boxDao = BoxDao.getInstance();

	public int daejeonMain(){
		String boxName = "대전도시락";
		Map<String, Object> box = boxDao.boxMenu(boxName);
		String boxMenu = box.get("BOX_MENU").toString();
		String userId = Controller.user.get("USER_ID").toString();
		String userName = Controller.user.get("NAME").toString();
		int boxPrice = Integer.parseInt(box.get("PRICE").toString());
		int orderToday = boxDao.boxOrderToday(boxName);
		int select = 1;

		loop:while(true){
			boolean isOrdered = boxDao.isOrderedToday(boxName, userId);
			PrintUtil.title();
			System.out.printf("\t    🍱 %s %s 🍱\n",today,boxName);
			System.out.printf("\t 금일의 가격 : %s ₩ (주문자 :%d명)\n\n",boxPrice,orderToday);
			System.out.printf("     『%s』\n",boxMenu);
			if(isOrdered)
				System.out.printf("\t🍛 %s님은 오늘 도시락을 이미 주문 했습니다.\n",userName);
			else System.out.println();
			String[] menu = {"뒤로가기      ","주문하기      ",};
			
			if(isOrdered)
				menu[1] = "주문취소      ";

			for(int i=0; i<menu.length; i++){
				if(select ==i+1)	System.out.print("    ■ ");
				else				System.out.print("    □ ");
				System.out.print(menu[i]);
			}

			PrintUtil.joystick3();;

			switch(ScanUtil.nextLine()){
			case "1":	if(select==1)	select=menu.length;		else select--;	break;
			case "3":	if(select==menu.length)	select=1;		else select++;	break;
			case "":	break loop;
			default:	break;			}

		}

		switch(select){
		case 1: return View.LUNCHBOX_ORDER;	
		case 2: boxOrder(boxName,userId); return View.USER_MAIN;			
		}
		
		return View.LUNCHBOX_ORDER;
	}
	
	public void boxOrder(String boxName, String userId){
		int price = boxDao.getPrice(boxName);
		int money = boxDao.getMoney(userId);
		int select = 1;
		
		loop:while(true){
		PrintUtil.title();
		System.out.printf("\t적립금 잔액  : %5d ₩\n",money);
		System.out.printf("\t주문 금액     : %5d ₩\n",price);
		System.out.printf("\t주문 후 잔액 : %5d ₩\n",money-price);
		if(money-price>0)
			System.out.println();
		else
			System.out.println("        ⚠️  잔액이 부족합니다. 적립금 충전 후 이용해주세요");
		
		String[] menu = {"뒤로가기","주문하기",};

		for(int i=0; i<menu.length; i++){
			if(select ==i+1)	System.out.print("       ■ ");
			else				System.out.print("       □ ");
			System.out.print(menu[i]);
		}

		PrintUtil.joystick4();

		switch(ScanUtil.nextLine()){
		case "1":	if(select==1)	select=menu.length;		else select--;	break;
		case "3":	if(select==menu.length)	select=1;		else select++;	break;
		case "":	break loop;
		default:	break;			}

	}
		
		switch(select){
		case 1: return;	
		case 2: return; /////// 주문 확정 버튼 넣어야합니다
		}
		
	}
	
	
	
	
	
	
	
	
	
}
