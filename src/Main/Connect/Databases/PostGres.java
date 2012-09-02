package Main.Connect.Databases;
import Main.Connect.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.EmptyStackException;

public class PostGres implements XLibDB_Connection{
	private PostGres(){
	
	}
	public static void CreateConnect(Access access){
		try{
			Connection connect = access.getConnection();
			if(checkConnection(connect)){
				SelectDriverDB();
				Access.stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
			}
		}
		catch(Exception e){
			throw new EmptyStackException();
		}	
	}
	private static boolean checkConnection(Connection connect){
		return (connect != Access.EMPTY_CONNECT) ? true : false;
	}
	
	@Override 
	public void Connect()
	{
	
	}
	public static void Closed(){	
		try{
			Access.connect.close();
			Access.stmt.close();
		}catch(Exception e){
			throw new EmptyStackException();	
		}
	}
	
	private static void SelectDriverDB(){
		try{
			Class.forName( (LocationDriver.POSTGRES).getPath() );
			System.out.println( "Succeed..." );
		}catch(Exception e){
			System.out.println( e.getMessage() );
		}
	}

}



