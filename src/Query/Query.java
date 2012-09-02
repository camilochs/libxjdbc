package Query;
import java.sql.*;

import java.util.*;
import java.lang.StringBuilder;
import Main.Connect.Access;

public class Query{
	
	private static final ResultSet EMPTY_RESULTSET = null;

	public static ResultSet execute(String sql){
		try{
			if(checkConnection()){
				return Access.stmt.executeQuery(sql);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return EMPTY_RESULTSET;
	}
	private static boolean checkConnection(){
		return (Access.connect != null && Access.stmt != null) ? true : false;
	}
	
	
	public static <T> ResultSet execute(String sql, List<T> parameters){
		try{
			if(checkConnection()){
				sql = searchPlaceHolder(sql, parameters);
				System.out.println(sql);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return EMPTY_RESULTSET;
		
	}
	private static <T> String searchPlaceHolder(String sql, List<T> parameters){
		
	
		StringBuilder buffer = new StringBuilder(sql);
		
		int size = buffer.length();
		if( size != 0 ){
			int ct = 0;
			int numParameters = parameters.size();
			ArrayList<Integer> positionsReplace = new ArrayList<Integer>();
			ArrayList<Object> dataReplace = new ArrayList<Object>();
			for(int i = 0, t = -1; i < size; ++i){
				if(buffer.charAt(i) == '%'){
					char character = buffer.charAt(i+1);
					if(character == 's'){
						if(Query.checkCharactersTypes(parameters.get(++t))){
							dataReplace.add( "'" + (String)parameters.get(t) + "'" );
							positionsReplace.add( i );
							++ct;
						}
					}else if(character == 'd'){
						if(Query.checkNumericTypes(parameters.get(++t))){
							dataReplace.add((String)parameters.get(t).toString());
							positionsReplace.add( i );
							++ct;
						}
					}else{
						break;
					}
				}
			}
			if( numParameters == ct){
				size = positionsReplace.size();
				
				for(int i = 0, temp = 0; i < size; ++i){
					int pos = positionsReplace.get(i);
					if( i > 0 ){
						temp += ( dataReplace.get(i-1).toString().length() - 2 );
						pos += temp;
						buffer = buffer.replace(pos,pos+2,dataReplace.get(i).toString());
					}else{
						buffer = buffer.replace(pos,pos+2,dataReplace.get(i).toString());
					}
					
				}
				return buffer.toString();
			}
		}
		return (new StringBuilder()).toString();
	}
	
	
	private static boolean checkCharactersTypes(Object parameters){
		
		ArrayList<Object> type = new ArrayList<Object>();
		type.add(String.class);
		
		boolean accept = false;
		for(int i = 0, size = type.size(); i < size; ++i){
			if(parameters.getClass().equals(type.get(i))){
				accept = true;
			}
		}
		
		return (accept) ? true : false;
		
	}
	private static boolean checkNumericTypes(Object parameters){
		
		ArrayList<Object> type = new ArrayList<Object>();
		type.add(Integer.class);
		type.add(float.class);
		type.add(Double.class);
		type.add(Long.class);
		boolean accept = false;
		for(int i = 0, size = type.size(); i < size; ++i){
			if(parameters.getClass().equals(type.get(i))){
				accept = true;
			}
		}
		
		return (accept) ? true : false;
		
	}
	
	//String -> table name, HashMap -> <Field, Value>
	public static <T> void insert(String tableName, HashMap<T, T> data){
		
		if(checkConnection()){
			if(!tableName.isEmpty()){
				if(!data.isEmpty()){
					Iterator< Map.Entry<T, T> > iter = data.entrySet().iterator();
					Map.Entry<T, T> e;
					ArrayList<T> keys = new ArrayList<T>();
					ArrayList<T> values = new ArrayList<T>();
					while(iter.hasNext()){
						e = iter.next();
						keys.add(e.getKey());
						values.add(e.getValue());
					}
					execute(SQL.INSERT.toString() + tableName + "(" + Utils.implode(",", keys) +  ") VALUES " + "(" + Utils.implode(",", values) + ")");
					
				}
			}
		}
	}
	
	public static <T> void update(String tableName, HashMap<Object, Object> data,  String sql, List<T> parameters){
		
		if(!tableName.isEmpty() && !sql.isEmpty()){
			if(!data.isEmpty() && !parameters.isEmpty()){
				StringBuilder temp = new StringBuilder(SQL.UPDATE.toString());
				Iterator< Map.Entry<Object, Object> > iter = data.entrySet().iterator();
				Map.Entry<Object, Object> e;
				temp.append(" " + tableName + SQL.SET.toString());
				while(iter.hasNext()){
					e = iter.next();
					temp.append(e.getKey() + " = " + e.getValue());
				}
			
				String conditions = searchPlaceHolder(sql, parameters);
				
				if(!conditions.isEmpty()){
					System.out.println(temp.toString() + " " + conditions);
				}else{
					System.out.println("Error Syntaxis.");
				}
			}
		}
	}
	
	public static <T> void delete(String tableName, String sql, List<T> parameters){
		if(!tableName.isEmpty() && !sql.isEmpty()){
			if(!parameters.isEmpty()){
				String conditions = searchPlaceHolder(sql, parameters);
				if(!conditions.isEmpty())
					System.out.println(SQL.DELETE.toString()  + tableName + " " + conditions);
				else
					System.out.println("Error Syntaxis.");
			}
		}
	}
	
	
	public static int getNumRow(ResultSet query){
		try{
			query.last();
			return query.getRow();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
	}

	
}



