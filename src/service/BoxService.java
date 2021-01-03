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
	private UserService userService = UserService.getInstance();

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
			System.out.printf("\t    🍱 %s %s 🍱\n\n",today,boxName);
			if(boxPrice==0)
				System.out.printf("\t 금일의 가격 : 정보없음.추후청구 (주문자 :%d명)\n\n",orderToday);
			else 
				System.out.printf("\t 금일의 가격 : %s ₩ (주문자 :%d명)\n\n",boxPrice,orderToday);
			System.out.printf("         『%s』\n",boxMenu);
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

			PrintUtil.joystick4();;

			switch(ScanUtil.nextLine()){
			case "1":	if(select==1)	select=menu.length;		else select--;	break;
			case "3":	if(select==menu.length)	select=1;		else select++;	break;
			case "":	break loop;
			default:	break;			}

		}

		switch(select){
		case 1: return View.LUNCHBOX_ORDER;	
		case 2: boxOrder(boxName,userId); return View.BOX_DAEJEON;			
		}
		
		return View.LUNCHBOX_ORDER;
	}
	
	public void boxOrder(String boxName, String userId){
		boolean isOrdered = boxDao.isOrderedToday(boxName, userId);
		int price = boxDao.getPrice(boxName);
		int money = boxDao.getMoney(userId);
		int select = 1;
		
		loop:while(true){
		PrintUtil.title();
		System.out.printf("\n\t적립금 잔액  : %5d ₩\n",money);
		if(!isOrdered){
			System.out.printf("\t주문 금액     : %5d ₩\n",price);
			System.out.printf("\t주문 후 잔액 : %5d ₩\n\n",money-price);
			if(money-price>=0)
				System.out.println();
			else
				System.out.println("        ⚠️  잔액이 부족합니다. 적립금 충전 후 이용해주세요");
		}else{
			System.out.printf("\t환불 금액     : %5d ₩\n",price);
			System.out.printf("\t환불 후 잔액 : %5d ₩\n",money+price);
			System.out.println();
		}
		String[] menu = {"뒤로가기","주문하기",};
		if(money-price <0)
			menu[1] = "적립금충전";
		if(isOrdered)
			menu[1] = "주문취소";

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
		case 2: 
			if(isOrdered){
				if(boxDao.cancelOrder(boxName, userId)){
					payment(userId, -price);
				}
				else System.out.println("버그발생 환불 실패 관리자에게 문의하세요 ");
				return;
			}
			else if(money-price < 0){ 
				userService.buyCredit();// 주문 해야하는데 잔액 부족하면 충전 화면으로 이동
			}
			else {
				payment(userId,price);
				if(boxDao.orderBox(boxName, userId)){
					System.out.println("주문완료.");
					return;
				}else
					System.out.println("도시락 주문 실패. 관리자에게 문의하세요.");
			}
			return;
		}
	}
	
	public void payment(String userId, int price){
		String payment = "";
		if(price>0)
			payment = "결제";
		else payment = "충전";
		if(boxDao.payment(userId,price)){
			PrintUtil.title();
			System.out.printf("\n\t        💌 %s 완료 💌\n\n",payment);
			System.out.printf("                   %s금액  : %d ₩\n",payment,(-price));
			System.out.printf("                적립금 잔액 : %d ₩\n",boxDao.getMoney(userId));
			System.out.printf("\n     %s 내용을 확인 후 계속하려면 엔터를 눌러주세요.\n\n",payment);
			PrintUtil.printBar();

		}else
			System.out.println("결제에 실패했습니다. 관리자에게 문의하세요.");
		ScanUtil.nextLine();
		return;
	}


	
	
	
	
	
	
}
