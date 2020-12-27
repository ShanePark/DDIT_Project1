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
		return string.substring(0, length);
	}
	

}
