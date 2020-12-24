package com.marve.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.marve.dao.CandidateDao;
import com.marve.dao.CreateConnection;
import com.marve.dao.FeedabackDao;
import com.marve.dao.TestDao;
import com.marve.dao.UserDao;
import com.marve.entity.Candidate;
import com.marve.entity.Feedback;
import com.marve.entity.Question;
import com.marve.entity.TestDetails;
import com.marve.entity.User;
@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	TestDao td;
	
	@Autowired
	FeedabackDao fd;
	
	@Autowired
	CandidateDao cd;
	
	@Autowired
	UserDao ud;
	
	@RequestMapping("")
	String home()
	{
	return "home";	
	}
	
	@RequestMapping("/tester.htm")
	String starttest()
	{
	return "starttest";	
	
	}
	
	@RequestMapping("/login")
	String login()
	{
		return "login";
	}
	@RequestMapping("/showcreatedtests")
	public String showCreatedTests(HttpServletRequest request) throws SQLException
	{
		String user=(String) request.getSession().getAttribute("user");
		//if user==null means you have to login first to create test
		if(user==null)
		{
			request.setAttribute("errormessage","First Login");
			return "login";
		}
		else
		{
         List<TestDetails> testsdetails=td.getOldTest(user);
         if(testsdetails.size()==0)
         {
     		request.setAttribute("error","No Test Created on this account");
    		
         }
         else
         {
        	 request.setAttribute("testsdetails",testsdetails);
		
         }
		}
		return "showcreatedtest";
	}
	//This Controller starts the test and retrive test from database using TestDao
	@RequestMapping("/letstart.htm")
	String letstart(HttpServletRequest request,@ModelAttribute Candidate c) throws SQLException
	{
		
		if(cd.isValidate(c))
		{
			List<Question> l=td.getTest(c.getTestid());
			if(!td.isTestActivate(c.getTestid()))
			{
				request.setAttribute("error","Wrong TestId or Test is Still not Active");
				return "starttest";
			}
			else if(l.size()==0)
			{
			
				request.setAttribute("error","Empty Test contact to Admin");
				return "starttest";
			}
			
		    request.getSession().setAttribute("candidate",c);
		    request.getSession().setAttribute("test",l);
		}
		// if invalidate returns error message to login page
		else
		{
		request.setAttribute("error","user with this roll already given this test or contact 7508540989 if not");
		return "starttest";
		}
		
		return "focus";
	}
	
	@RequestMapping("/delete")
	public String deleteTest(@RequestParam("testid") String testid,HttpServletRequest request) throws SQLException
	{
		
		if(testid!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest(testid,(String)request.getSession().getAttribute("user")))
		{
			td.deleteTest(testid);
			List<TestDetails> testsdetails=td.getOldTest((String)request.getSession().getAttribute("user"));
	        	 request.setAttribute("testsdetails",testsdetails);
			return "showcreatedtest";
	         
		}
		
		return "home";
		
	}
	
	@RequestMapping("/testupdater")
	 public String testupdater(@RequestParam("testid") String testid,HttpServletRequest request) throws SQLException
	 {
		if(testid!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest(testid,(String)request.getSession().getAttribute("user")))
		{
			List<Question> l=td.getTest(testid);
		
		    request.getSession().setAttribute("test",l);
		    request.getSession().setAttribute("testid",testid);
		    request.getSession().setAttribute("qnumber",l.size()+1);
		    return "testupdater";
			
		}
		return "home";
	 }
	
	@RequestMapping("/questionupdater")
	public String questionUpdater(@RequestParam("testid") String testid,@RequestParam("qnumber") String qnumber,HttpServletRequest request) throws SQLException
	{
		if(testid!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest(testid,(String)request.getSession().getAttribute("user")))
		{
			Question question=td.getQuestion(testid,qnumber);
		if(question==null)
			return "updater";
		else
		{
			request.setAttribute("updateQuestion",question);
			request.setAttribute("qnumber",qnumber);
			request.setAttribute("testid",testid);
			return "questionupdate";
		}
		}
		return "home";	
	}
	
	@RequestMapping("/updatequestion")
	public String updateQuestion(HttpServletRequest request,@RequestParam("qnumber") String qnumber,@RequestParam("testid") String testid,@ModelAttribute Question question) throws SQLException
	{
		if(testid!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest(testid,(String)request.getSession().getAttribute("user")))
		{
			td.updateQuestion(question,testid,qnumber);
			return "forward:testupdater";
		}
		return "home";
	}
	
	@RequestMapping("/deletequestion")
	public String deleteQuestion(@RequestParam("testid") String testid,@RequestParam("qnumber") String qnumber,HttpServletRequest request) throws SQLException
	{
		if(testid!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest(testid,(String)request.getSession().getAttribute("user")))
		{
			td.deleteQuestion(testid,qnumber);
		}
		return "forward:testupdater";
		
	}
	@RequestMapping("/logout")
	 public String logout(HttpServletRequest request)
	 {
		if(request.getSession().getAttribute("user")!=null)
		request.getSession().invalidate();
		return "home";
	 }
	@RequestMapping("/signup.htm")
	public String signup()
	{
		return "signup";
	}
	
	
	@RequestMapping("/test.htm")
	String test()
	{
	return "test";	
	}
	
	@RequestMapping("/feedback.htm")
	String feedback()
	{
	return "feedback";	
	}
	
	//add feedback in database using FeedbackDao
	@RequestMapping("/addfeedback")
	String addfeedback(@ModelAttribute Feedback feedback) throws SQLException
	{
		fd.addFeedback(feedback);
	
	return "thanks";	
	}
	
	//Test after answering all questions submit here , then after evaluation score is submitted to database and result is shown to user
	@RequestMapping(value="/submittest.htm",method = RequestMethod.POST)
	String submitTest(HttpServletRequest request,@RequestParam("size") int size) throws SQLException
	{
		int marks=0;
		HttpSession session=request.getSession();
		ArrayList<String> a=new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		ArrayList<Question> q=(ArrayList<Question>) session.getAttribute("test");
		for(int i=1;i<=q.size();i++)
		{
			Question question=q.get(i-1);
			String ans=request.getParameter(""+i);
			int correctans=question.getAnswer();
			if(Integer.parseInt(ans)==correctans)
			{
				marks++;
			}
			a.add(ans);
		}
		request.setAttribute("answers",a);
		Candidate c=(Candidate)request.getSession().getAttribute("candidate");
		
		if(cd.isValidate(c))
		{
		c.setMarks(marks);
		cd.addResult(c);
		request.setAttribute("marks",marks);
		request.setAttribute("size",q.size());
		return "result";
		}
		else
		{
		request.setAttribute("error","Test already Submitted Close Window");
		return "starttest";
		
		}
		
	}
	
	//Test is start from this Mapping
	@RequestMapping("/starttest.htm")
	String startTest(HttpServletRequest request,@RequestParam("testname") String testname) throws SQLException
	{	
		String user=(String) request.getSession().getAttribute("user");
		//if user==null means you have to login first to create test
		if(user==null)
		{
			request.setAttribute("errormessage","First Login");
			return "login";
		}
		else
		{
		Integer testid=td.generateTestid(testname,user);
			request.getSession().setAttribute("testid",testid.toString());
			request.getSession().setAttribute("qnumber",1);
		return "question";
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String loginValidate(HttpServletRequest request,@RequestParam("uname") String uname,@RequestParam("psw") String psw) throws SQLException
	{
		
		boolean status=ud.validateUser(uname,psw);
		if(status&&(request.getSession().getAttribute("user")==null))
		{
			request.getSession().setAttribute("user",uname);
			return "home";
		}
		else
		{
			request.setAttribute("loginerror","Incorrect Credentials");
			return "login";
		}
		
	}
	
	@RequestMapping("/createaccount")
	public  String createAccount(HttpServletRequest request,@ModelAttribute User user) throws SQLException
	{
		
	      if(ud.createUserAccount(user))
	      {
	    	  request.setAttribute("loginerror","Account Created. Now Login");
	      return "login";
	      }
	      else
	      {
	    	  request.setAttribute("message","Account already Created with this UseId or Phone Number");
	    	  return "signup"; 
	      }
	      
	}
	@RequestMapping("/feedbacks.htm")
	public ModelAndView getFeedbacks() throws SQLException
	{
		ModelAndView mv=new ModelAndView("showfeedbacks");
		mv.addObject("feedbacks",fd.getFeedbacks());
		return mv;
		
	}
	
	
	@RequestMapping(value="/addquestion",method = RequestMethod.POST)
	public String addQuestion(HttpServletRequest request,@RequestParam Integer qn,@ModelAttribute Question tq) throws SQLException
	{
		HttpSession h=request.getSession();
		if(!((String) h.getAttribute("testid")!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest((String) h.getAttribute("testid"),(String)request.getSession().getAttribute("user"))))
			return "home";
		if(qn>(Integer)h.getAttribute("qnumber"))
		td.addQuestion((String)h.getAttribute("testid"),tq,String.valueOf(h.getAttribute("qnumber")));
		String sc=request.getParameter("submitormore");
		 if(qn>(Integer)h.getAttribute("qnumber"))
			 h.setAttribute("qnumber",qn);
		if(sc.equals("submittest"))
		{
			try 
			{
			CreateConnection.getConnection().close();
			}
			catch(Exception e)
			{}
			request.setAttribute("testid",h.getAttribute("testid"));
			return "testcreated";
		}
		return "question";
	   }
	
	@RequestMapping("/addmorequestions")
	public String addMoreQuestion()
	{
		
		return "question";
	}
	@RequestMapping("/testactivate")
	public String testActivate(@RequestParam("testid") String testid,@RequestParam("active")String active,HttpServletRequest request) throws SQLException
	{
		if(testid!=null&&request.getSession().getAttribute("user")!=null&&td.validateUserTest(testid,(String)request.getSession().getAttribute("user")))
		{
			if(active.equals("false"))
			{
				td.activeOrDeactive(testid,true);
			}
			else if(active.equals("true"))
			{
				td.activeOrDeactive(testid,false);
			}
			
		}
		return "forward:showcreatedtests";
	}
}


