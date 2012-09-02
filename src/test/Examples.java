package test;
//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Main.Connect.*;
import Main.Connect.Databases.PostGreSQL;
import Query.*;
import java.util.*;
import java.sql.*;

public class Examples {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		
		try{

			//Table Example:
			/*
				Name: game.
				Column: <id> [ DataType: text ]
			
			*/
			////////////////////////////////////////////////////////////////////////////////////////////////////

			//DataBase Connection. <url>, <username>, <password>
			PostGreSQL.CreateConnect(new Access("jdbc:postgresql://localhost/postgres", "username", "password"));
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Example: Select Query.
			
			ResultSet rs = Query.execute("SELECT * FROM game");
			while(rs.next()){
				System.out.println(rs.getString("id"));
			}
				
			////////////////////////////////////////////////////////////////////////////////////////////////////

			//Example: Insert Query.
			
			HashMap<Object, Object> data = new HashMap<Object,Object>();
			data.put("id", 123);
			Query.insert("game", data );
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			//Example: Update
			Query.update("game", data, "where id = %s and id = %d and id = %s", Arrays.asList("example1", 1.23, "example2"));
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			//Example: Delete
			Query.delete("game","where id = %s and id = %s",	Arrays.asList("123", "12345"));
			
			////////////////////////////////////////////////////////////////////////////////////////////////////

			//Example: return number row.
			ResultSet rs2 = Query.execute("SELECT * FROM game");
			Query.getNumRow(rs2);
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Close Connection.
			PostGreSQL.Closed();
			
		
	
		}catch(Exception e){
			
		}
		
		
	
		
		
		
 	}
	 
		
		
}
