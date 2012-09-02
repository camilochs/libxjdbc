package test;
//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Main.Connect.*;
import Main.Connect.Databases.PostGres;
import Query.Query;
import Query.Utils;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class PostGresTest {

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
		long start = System.currentTimeMillis();
	
		
		try{
		//	PostGres.CreateConnect(new Access("jdbc:postgresql://localhost/postgres", "postgres", "poulette"));
			HashMap<Object, Object> data = new HashMap<Object,Object>();
			data.put("id", 1999);
			//Query.insert("game", data );
			//Query.getNumRow(Query.execute("SELECT * FROM game"));
			//Query.delete("game","where id = %s and id = %d and id = %s",Arrays.asList("0_s", 1.456,"1"));
		
			Query.update("game", data, "where id = %s and id = %d and id = %s", Arrays.asList("hola", 1.23, "chao"));
			
			/*	while(rs.next()){
				//System.out.println(rs.getString("id"));
			}
			*/
			PostGres.Closed();
	
		}catch(Exception e){
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Tiempo total:" + (end-start));
	
		
		
		/*
		ArrayList<String> str = new ArrayList<String>();
		str.add("camilo");
		str.add("chacon");
		str.add("jose");
		str.add("sartori");
		System.out.println(Utils.explode(',', ",Hola,,,Mundo,,"));
		*/
		
		
		
 	}
	 
		
		
}
