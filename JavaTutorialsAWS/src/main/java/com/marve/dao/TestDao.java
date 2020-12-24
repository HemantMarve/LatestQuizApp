package com.marve.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import com.marve.entity.*;

import java.sql.*;

@Component
public class TestDao {
	
	public int addQuestion(String testid,Question t, String qnumber) throws SQLException
	{
		if(CreateConnection.getConnection()==null||CreateConnection.getConnection().isClosed())
		CreateConnection.setConnection();
		Connection con=CreateConnection.getConnection();
		int i = 0;
				try
		{
			PreparedStatement stmt=con.prepareStatement("INSERT INTO test VALUES(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE testid=?,questionnumber=?");
			stmt.setInt(1,Integer.valueOf(testid));
			stmt.setInt(2,Integer.parseInt(qnumber));  
			stmt.setString(3,t.getQuestion().trim());
			stmt.setString(4,t.getOpt1().trim());
			stmt.setString(5,t.getOpt2().trim());
			stmt.setString(6,t.getOpt3().trim());
			stmt.setString(7,t.getOpt4().trim());
			stmt.setInt(8,t.getAnswer());
			stmt.setInt(9,Integer.valueOf(testid));
			stmt.setString(10,qnumber);  
			
			i=stmt.executeUpdate();
		}
		catch(Exception e)
		{
			
				con.close();
			System.out.println(e);
			
		}
				System.out.println(i);
				return i;
	}
	

//getting test for attempt
	public List<Question> getTest(String testid) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
		List<Question> l=new ArrayList<Question>();
			try
			{
				PreparedStatement stmt=con.prepareStatement("select * from test where testid=?");
				stmt.setInt(1,Integer.parseInt(testid));
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					l.add(new Question(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)));
				}
				
				con.close();
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
	
		
		return l;
	}

	public int generateTestid(String testname,String user) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
		try
		{
			int testid=0;
			PreparedStatement stmt=con.prepareStatement("insert into testes2 values(null,?,?,null)");
			stmt.setString(1,testname);
			stmt.setString(2,user);
			int i=stmt.executeUpdate();
			if(i!=0)
			{
				 stmt=con.prepareStatement("select Max(testid) from testes2 where username='"+user+"'");
				 ResultSet rs=stmt.executeQuery();
				 if(rs.next())
				 {
					 testid=rs.getInt(1);
					 
				 }
			}
			
			return testid;
		}
		catch(Exception e)
		{
				con.close();          
			System.out.println(e);
		}
		
		return 0;
	}


	public List<TestDetails> getOldTest(String user) throws SQLException {
		
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
		List<TestDetails> l=new LinkedList<>();
			try
			{
				PreparedStatement stmt=con.prepareStatement("select * from testes2 where username=?");
				stmt.setString(1,user);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					String testid=String.valueOf(rs.getInt(1));
					l.add(new TestDetails(testid,rs.getString(2),String.valueOf(isTestActivate(testid))));
				}
				
				con.close();
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
	
		return l;
	}


	public void deleteTest(String testid) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
	
			try
			{
				PreparedStatement stmt=con.prepareStatement("delete from test where testid=?");
				stmt.setString(1,testid);
				stmt.executeUpdate();
				stmt=con.prepareStatement("delete from testes2 where testid=?");
				stmt.setString(1,testid);
				stmt.executeUpdate();
				
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
		
	}


	public boolean validateUserTest(String testid, String user) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
			try
			{
				PreparedStatement stmt=con.prepareStatement("select * from testes2 where username=? and testid=?");
				stmt.setString(1,user);
				stmt.setInt(2,Integer.parseInt(testid));
				ResultSet rs = stmt.executeQuery();
				return rs.next();
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
		return false;
	}


	public void deleteQuestion(String testid, String qnumber) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
			try
			{
				PreparedStatement stmt=con.prepareStatement("delete from test where testid=? and questionnumber=?");
				stmt.setInt(1,Integer.parseInt(testid));
				stmt.setInt(2,Integer.parseInt(qnumber));
				stmt.executeUpdate();
				stmt=con.prepareStatement("update test set questionnumber=questionnumber-1 where questionnumber>? and testid=?");
				stmt.setInt(1,Integer.parseInt(qnumber));
				stmt.setInt(2,Integer.parseInt(testid));
				stmt.executeUpdate();
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
	}


	public Question getQuestion(String testid, String qnumber) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}
		Question question = null;
			try
			{
				PreparedStatement stmt=con.prepareStatement("select * from test where questionnumber=? and testid=?");
				stmt.setInt(1,Integer.parseInt(qnumber));
				stmt.setInt(2,Integer.parseInt(testid));
				ResultSet rs=stmt.executeQuery();
				
				if(rs.next())
				{
					question=new Question(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
				}
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
		return question;
	}


	public void updateQuestion(Question t, String testid, String qnumber) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}

			try
			{
				PreparedStatement stmt=con.prepareStatement("update test set question=?,option1=?,option2=?,option3=?,option4=?,answer=? where testid=? and questionnumber=?");
				 
				stmt.setString(1,t.getQuestion().trim());
				stmt.setString(2,t.getOpt1().trim());
				stmt.setString(3,t.getOpt2().trim());
				stmt.setString(4,t.getOpt3().trim());
				stmt.setString(5,t.getOpt4().trim());
				stmt.setInt(6,t.getAnswer());
				stmt.setInt(7,Integer.valueOf(testid));
				stmt.setString(8,qnumber);  
				stmt.executeUpdate();
				
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
		
	}


	public boolean isTestActivate(String testid) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}

			try 
			{
				PreparedStatement stmt=con.prepareStatement("select active from testes2 where testid=?");
			     stmt.setInt(1,Integer.parseInt(testid));
			     ResultSet rs=stmt.executeQuery();
			     if(rs.next())
			     {
			    	 return rs.getBoolean(1);
			     }
				
			}
			catch(Exception e)
			{
					con.close();
				          
				System.out.println(e);
				
			}
		return false;
	}


	public void activeOrDeactive(String testid, boolean b) throws SQLException {
		Connection con=CreateConnection.getConnection();
		if(con==null||CreateConnection.getConnection().isClosed())
		{
			CreateConnection.setConnection();
			con=CreateConnection.getConnection();
		}

			try 
			{
				 PreparedStatement stmt=con.prepareStatement("update testes2 set active=? where testid=?");
				 stmt.setBoolean(1,b);
			     stmt.setInt(2,Integer.parseInt(testid));
			     stmt.executeUpdate();
			}
			catch(Exception e)
			{
					con.close();          
				System.out.println(e);	
			}
		
	}
	





}