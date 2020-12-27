package util;

public class Util {
	
	public static void wait(int i){
		long end = System.currentTimeMillis();
		long start = System.currentTimeMillis();
		while((end-start)<i)
			end=System.currentTimeMillis();
	}
	
	public static String scoreToStars(double score){
		String stars="";
		for(int i=0; i<Math.round(score); i++)
			stars+="★";
		for(int i=(int)Math.round(score); i<5; i++)
			stars+="☆";
		return stars;
	}

	public static String cutString(String string,int length){
		for(int i=string.length(); i<length ;i++)
			string += "　";
		int blank=0;
		if(string.substring(0,length).contains(" ")){
			string+=" ";
			blank++;
		}
		if(string.length()>length){
			string = string.substring(0,length-1+blank)+"…";
			return string;
		}
		return string+" ";
	}

	public static String cutString2(String string,int length){
		int blank = 0;
		for(int i=string.length(); i<length ;i++){
			string = "   " + string;
			blank += 3;
		}
		if(string.contains(" "))
			string = " " + string;
		if(string.length()>length+blank)
			string = string.substring(blank,length+blank)+"…";
		return string;
	}


}
