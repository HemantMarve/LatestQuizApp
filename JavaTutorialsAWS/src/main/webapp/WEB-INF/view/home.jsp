<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

 <style type="text/css">
   html {
  height: 100%;
  width: 100%;
}

body {
  display: table;
  height: 100%;
  margin: 0;
  padding: 0;
  width: 100%;
}

.row {
  display: block;
  height: 100%;
}

.row:nth-child(1) {
 
  background-image: url(/resources/images/select.png);
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
}
.row:nth-child(1) h1{
  color: white;
  text-align: center;
  margin-top: 150px;
 font-family:cursive;
  }

.row:nth-child(2) {
  background-color: white;
}
.divimg
{
  display: block;
   margin: 20px;
   margin-top: 50px;
  border:none;
  float: left;
  width: 170px;
  height: 100px;
  border-radius: 40px;
  transition-duration: 0.5s;
  opacity:0.8;
}
.row h3
{
  color:white;
  text-align:center; 
}
.divimg:hover
{
 
opacity:1;
}
.img
{
  position: relative;
  box-sizing: block;
height: 100px;
width: 170px;
border-radius: 40px;
transition-duration: 0.5s;
}
.img:hover
{

  
  border-bottom: solid 2px green;
}
.divimg:nth-child(2)
{

   margin-left: 20%;

}
.span1{
   display: inline-block;
     color: white;
     position:relative;
    left: 8%;
    bottom:75px;
    margin-left:35px;
    font-weight: bold;
    font-size: x-large;
    
    font-family: serif;
    transition: 0.5s;
outline: none;
}
.span1:hover
{
  color: red;
  text-decoration: underline;
}
#span2
{

display:block;
text-align: center;
margin-top: 10px;
font-family:sans-serif;
font-weight:bold;
}

 </style>


</head>
<body>
<div class="row"><img src="/resources/images/1.png" style="height:100px;margin-left:105px;margin-top: 30px;">
<h3><a href="/"><span class="span1">Home</span></a>  <a href="tester.htm"><span class="span1">Attempt Test</span></a><%if(request.getSession().getAttribute("user")==null)%><a href="login"><span class="span1">Login for CreateTest</span></a>
<%if(request.getSession().getAttribute("user")!=null){%> <a href="test.htm"><span class="span1">Create Test</span></a>  <a href="showcreatedtests"><span class="span1">Show Created Tests</span></a>
 <a href="logout"><span class="span1">Logout</span></a><%} %></h3>
  <h1>GRAB WHAT YOU ARE HOPING FOR......</h1>
</div>

<div class="row">
  <h2 style="text-align:center;color:black;margin-top:70px;font-size:x-large;
font-family:cursive;">SELECT YOUR BRANCH</h2>
  <div class="divimg"> <a href="#"> <img class="img" src="/resources/images/it.jpg"></a><span id="span2">Information </span><span id="span2">Technology</span> </div>
    <div class="divimg"> <a href="#"><img class="img" src="/resources/images/cse.jpg"> </a> <span id="span2">Computer Science</span></div>
      <div class="divimg"> <a href="#"> <img  class="img" src="/resources/images/me.jpg"> </a><span id="span2">Mechanical </span> </div>
        <div class="divimg"> <a href="#"> <img class="img" src="/resources/images/elec.png"></a><span id="span2">Electronics </span></div>
</div>
</body>
</html>