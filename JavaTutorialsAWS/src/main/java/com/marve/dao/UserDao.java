package com.marve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.marve.entity.User;

@Component
public class UserDao {
	
	public boolean createUserAccount(User user) throws SQLException
	{
		
		if(user==null)
			return false;
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
			try
			{
				PreparedStatement stmt=con.prepareStatement("insert into user_info values(?,?,?)");		
				stmt.setString(1,user.getUserid());  
				stmt.setString(2,user.getPassword());
				stmt.setString(3,user.getPhoneNumber());
				stmt.executeUpdate();
				
			}
			catch(Exception e)
			{
				return false;
			}
		}
		return true;
	}
	//it for authentication of user
	public boolean validateUser(String uname, String psw) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
		try
		{
			PreparedStatement stmt=con.prepareStatement("select * from user_info where userid=? and password=?");		
			stmt.setString(1,uname);  
			stmt.setString(2,psw);
	        ResultSet rs=stmt.executeQuery();
	        if(rs.next())
	        {
	        	return true;
	        }
	    	
	     }	
		catch(Exception e)
		{ 
			con.close();
		}
         return false;
	}

}
