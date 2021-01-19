package util;

public class PrintUtil {
	static public void bar(){
		System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■");
	}
	static public void blank(int n){
		for(int i=0; i<n; i++)
		System.out.println("");
	}
	static public void title(){
		title3();
		System.out.println();
	}
	static public void title2(){
		title();
		System.out.println();
	}
	static public void title3(){
		System.out.println("\n\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.print("               🍽️ 오늘 뭐먹지? 🍽️");
	}

	static public void joystick(){
		System.out.print("\n                           (2)↓ (5)↑ (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
	}

	static public void joystick2(){
		System.out.print("     (2)↓ (5)↑ (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
	}

	static public void joystick3(){
		System.out.print(" (1,3)← → (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
	}
	
	static public void joystick4(){
		System.out.print("\n                           (1,3)← → (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
	}
	
	static public void joystick5(){
		System.out.print("                           (1,3)← → (⏎)확인 \n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n>");
	}

	static public void printBar(){
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");
	}

	static public void printBar2(){
		System.out.println();
		printBar();
	}
	
	static public void printBar3(){
		System.out.print("                       \t\t       뒤로 가러면 [Enter] 키를 누르세요\n");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");
	}

	public static void printBlank(int n){
		for(int i=0; i<n; i++)
			System.out.println();
	}


	static public void boardbase1(){
		System.out.println("\n\n□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n");
		System.out.println("                                    🍽️ 오늘 뭐먹지? 🍽️");
		System.out.println("\n\n");
	}
	static public void boardbase2(){
		System.out.println("\n\n");
		System.out.println("                                     (⏎)확인 ");
		System.out.print("□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■\n> ");
	}
	
	static public void guest() {
		System.out.println("\n\n\n              🧑 게스트로 접속중.   🧑\n\n\n\n");
	}
	
	static public void guestLogin(){
		title();
		guest();
		printBar();
		Util.wait(700);;
		title();
		guest();
		printBar();
		Util.wait(700);;
		title();
		guest();
		printBar();
	}
	
	public static void connecting() {
		System.out.println("\n\n\n              🧑  접속중.   🧑\n\n\n\n");
	}
	public static void loading(){
		title();
		connecting();
		printBar();
		Util.wait(700);;
		title();
		connecting();
		printBar();
		Util.wait(700);;
		title();
		connecting();
		printBar();
	}
	
	public static void onlyForMember(){
		title();
		System.out.println("\n\n          🧑  회원만 이용 가능한 서비스입니다. 🧑\n\n\n");
		System.out.println("           계속 하려면 엔터키를 눌러주세요.\n");
		printBar();
		ScanUtil.nextLine();
	}
}
