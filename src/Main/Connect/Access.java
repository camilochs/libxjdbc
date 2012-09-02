package Main.Connect;
import java.sql.*;

public class Access
{
	private static String url;
	private static String username;
	private static String password;
	public static Connection connect;
	public static Statement stmt;
	public final static Connection EMPTY_CONNECT  = getConnectionEmpty();
		
	public Access(String url, String username, String password)
	{
			Access.url 			= url;
			Access.username 	= username;
			Access.password 	= password;
	}

	//Return connection to Database.
	public Connection getConnection()
	{	
		if(connect == null)
		{
			try
			{
				connect = DriverManager.getConnection(url, username, password);
				System.out.println("Conectado..");
				return connect;
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return EMPTY_CONNECT;
	}
	
	private static Connection getConnectionEmpty(){ 
		Connection empty = null;
		try
		{
			empty = DriverManager.getConnection("", "", "");
			return empty;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return empty;
	}
	
	
}