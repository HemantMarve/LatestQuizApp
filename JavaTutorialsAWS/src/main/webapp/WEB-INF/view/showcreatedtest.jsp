<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page isELIgnored="false" %>
  <%@ page import="java.util.*,com.marve.entity.TestDetails" %>  
<!DOCTYPE html>
<html>
<head>
<title>Created Test</title>
<style>
.topnav {
  overflow: hidden;
  background-color: #333;
}
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
<div class="topnav">
  <a class="active" href="/">Home</a>
  <a href="test.htm">createtest</a>
  <a href="showcreatedtests"><span >Show Created Tests</span></a>
  <a href="/logout">Logout</a>
</div>
<div style="padding-left:16px;padding-top:20px">
<table id="customers">
  <tr>
    <th>TestId</th>
    <th>TestName</th>
    <th>Update Test</th>
    <th>Delete Test</th>
    <th>Show Test Results</th>
    <th>Active/Deactive</th>
  </tr>
  
  <c:forEach var="testsdetails" items="${testsdetails}"> 
  <tr> 
  <td>${testsdetails.testid}</td>
  <td>${testsdetails.testname}</td>
  <td><a href="/testupdater?testid=${testsdetails.testid}">update</a></td>
  <td><a href="/delete?testid=${testsdetails.testid}">delete</a></td>
  <td><a href="#">Results</a></td>
  <td><a href="/testactivate?testid=${testsdetails.testid}&active=${testsdetails.active}"><c:if test="${testsdetails.active=='true'}">Already Activated</c:if><c:if test="${testsdetails.active=='false'}">Already Deactivated</c:if></a></td>
  
  </tr>
   </c:forEach>
</table>
<% String s=(String)request.getAttribute("error");
    if(s!=null)out.println("<h3>"+s+"</h3>");
    %>
      
</div>
</body>
</html>
