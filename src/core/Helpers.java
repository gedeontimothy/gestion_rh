package core;

public class Helpers {

	public static String getType(Object... obj) {
		
		Boolean simple = (java.lang.Boolean) (obj.length == 2 ? obj[1] : false);
		
		return simple ? obj[0].getClass().getSimpleName() : obj[0].getClass().getName();
		
	}
	

}
