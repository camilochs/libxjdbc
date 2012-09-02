package Query;
import java.util.ArrayList;
import java.util.Iterator;
public class Utils{
	
	
	public static <T> String implode(String glue, ArrayList<T> pieces ){
		String str = "";
		Iterator<T> iter = pieces.iterator();
		int i = 0, size = pieces.size();
		while( iter.hasNext() ){
			str += iter.next();
			if( i < size - 1 ) str += glue;
			++i;
		}
		return str;
	}
	public static String implode(String glue, String[] pieces ){
		String str = "";
		for(int i = 0, size = pieces.length; i < size; ++i){	
			str += pieces[i];
			if( i < size - 1 ) str += glue;
		}
		return str;
	}
	
	
	public static String implode(String[] pieces ){
		String str = "";
		for(int i = 0, size = pieces.length; i < size; ++i)
			str += pieces[i];
		return str;
		
	}
	public static String[] explode(char delimiter, String str){
		final int SIZE = countChar(delimiter, str);
		String[] pieces = new String[SIZE+1];
		String temp = "";
		int x = 0, size = str.length() ;
		for(int i = 0; i < size; ++i){
			
			if( str.charAt(i) != delimiter ){
				temp += str.charAt(i);
				if( i == size - 1) pieces[x] = temp;
			}else {
				if( i == 0 ){
					pieces[x] = "";
				}else{
					pieces[x] = temp;
					temp = "";
				}
				++x;
			}
		}
		if(str.charAt(size-1) == delimiter) pieces[x] = "";
	//	System.out.println(Arrays.toString(pieces));
	//	System.out.println(pieces.length);
	
		return pieces;
	}
	private static int countChar(char search, String str){
		int count = 0;
		for(int i = 0, size = str.length(); i < size; ++i){
			if(str.charAt(i) == search)
				++count;
		}
		return count;
	}
	
	
}