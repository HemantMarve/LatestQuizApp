<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TestCreated</title>
</head>
<body bgcolor="Green" style="color:white;">
<div >
  <h1 >Thank You!</h1>
  <p ><strong>Test Created and Your TestId:<%=request.getAttribute("testid") %></strong> 
  <hr>
  <p>
   <a href="" style="color:white;">Contact us</a>
  </p>
    <a  href="/" style="color:white;" ><h2>Continue to homepage</h2></a>
    <a  href="logout" style="color:white;" ><h2>Logout</h2></a>
</div>
</body>
</html>