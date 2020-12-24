<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
  <%@ page import="java.util.*,com.marve.entity.Question" %>
<!DOCTYPE html>
<html>
<head>
<title>Created Test</title>
<style>
.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default radio button */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
}

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
  <a href="/JavaTutorials">Home</a>
  <a href="test.htm">createtest</a>
  <a href="showcreatedtests"><span >Show Created Tests</span></a>
  <a href="/logout">Logout</a>
  <a href="/addmorequestions">AddMoreQuestions</a>
</div>
<div>
<%int i=1; %>
<c:forEach var="l" items="${sessionScope.test}"> 
<h1><%=i%>. <c:out value="${l.question}"/></h1>
<label class="container">1. <c:out value="${l.opt1}"/>
 
  <span class="checkmark"></span>
</label>
<label class="container">2. <c:out value="${l.opt2}"/>
  <span class="checkmark"></span>
</label>
<label class="container">3. <c:out value="${l.opt3}"/>
  <span class="checkmark"></span>
</label>
<label class="container">4. <c:out value="${l.opt4}"/>
  <span class="checkmark"></span>
</label>
<table>
<tr>
<td>
<a href="/questionupdater?testid=${sessionScope.testid}&qnumber=<%=i%>">Update</a></td>
<td><a href="/deletequestion?testid=${sessionScope.testid}&qnumber=<%=i%>">Delete</a></td>
</tr>
</table>   
<br>
<%i++;%>
   </c:forEach>
   <%if(i==1) %><h1> NO Question Found in This Test. Add More Questions Or You can delete This Test.</h1>
</div>
</body>
</html>