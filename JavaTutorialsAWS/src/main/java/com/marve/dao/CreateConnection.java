package com.marve.dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection implements DatabaseDetails{
	private static Connection con;
	  public static void setConnection()
     	{
		try{  
			Class.forName(DRIVER);  
			con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
			
			}
		catch(Exception e)
		{
				System.out.println(e);
			}  
			} 
	  public static Connection getConnection()
	  {
		return con;
		  
	  }
	
}
