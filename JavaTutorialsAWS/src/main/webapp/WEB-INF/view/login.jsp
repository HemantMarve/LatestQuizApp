<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <html>
  <head>
 
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  	<style type="text/css">

  	.body
  	{
     
     background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: center;
  background-position: center; 
    
    
  	}	
  	.icon {
  color: black;
 position: relative;
top: 30;
left:15;
z-index: 1;
}
.input-field
{
	background-color: #E8E8E8;
 border-radius: 40px;
height:40px;
width:250px;
border:none;
margin: 0.5px 0.5px;
margin-left:10px;
font-style: italic;
font-weight: 180;
position:relative;
    top:1;
    opacity: 1;

    transition-duration: 0.5s;
padding-left:10;
font-weight: bold;
padding: 10px; 
            text-align: center;
            outline: none;
}
.input-field:hover
{
	height:45px;
width:255px;
border-bottom: 2px solid black;
         opacity: 3;
  box-shadow:5px 5px 5px grey;

}


#maindiv
{
position:fixed;
top:35%;
bottom: 20%;
left: 39%;
width:260px;
opacity: 0.9;
height:345px;
padding: 20px;
border:none;
background-attachment: fixed;

}
.btn
{

  position:relative;
  left: 90;
  margin-top:10px;
  border-radius: 40px;
  height:35px;
width:90px;
background-color: #B0B0B0;
border:none;
font-style: italic;
opacity:0.7;
transition-duration: 0.5s;
font-weight: bold;
outline: none;
}
.btn:hover
{
	outline: none;
	opacity:1;
	height:40px;
width:95px;
border-bottom: 2px solid black;
box-shadow:5px 5px 5px grey;
}


#chimg
{
	height:300px;
width:400px;
bottom:50%;
  position:fixed;
  left:35%;
}
span.psw {
  float: right;
  padding-top: 16px;
  
   display: inline-block;
     float: none;
     color: #707070;
     position:relative;
    left: 45px;
    bottom:20px;
    font-weight: bold;
    font-size: large;
    font-style: italic;
    transition: 0.5s;
outline: none;
}
.psw:hover
{
	color: black;
	text-decoration: underline;
}


  	</style>


<body class="body">
	<a href="/"><span class="psw"><h1>Home</h1> </span></a>
	<img src="/resources/images/1.png" id="chimg">
	<div id="maindiv">
		
		<form action="login" method="post">
			<div><i class="fa fa-user icon"></i>
				<input class="input-field" type="text" name="uname" required="required" placeholder="example@marve.com" pattern="(\W|^)[\w.+\-]*@marve\.com(\W|$)"></div>
            <div> <i class="fa fa-key icon"></i>
            	<input class="input-field" type="password" name="psw" required="required" placeholder="password" ></div>
            <input class="btn" type="submit" value="Login">
		</form>
		<a href="#"><span class="psw">Forgot password?  </span></a>
		<a href="signup.htm"><span class="psw">Signup</span></a>
    <%if(request.getAttribute("loginerror")!=null)
      {%>
	 <br> <h2 style="color:#FF0000">${loginerror}</h2>
	  
	 <%  }%>
	</div>



</body>
  </head>
  </html>