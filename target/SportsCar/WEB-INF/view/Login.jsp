<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link  rel="stylesheet" href="Resources/Bootstrap/bootstrap.min.css">
<script src="Resources/Bootstrap/bootstrap.js"></script>
<script src="Resources/JQuery/jquery-3.1.1.min.js"></script>
<title>Login</title>
</head>
<body>
<jsp:include page="CommonHeader.jsp"></jsp:include>
<!-- 
<div class="container">
<form class="form-horizontal" action="login">
<div class="col-xs-6">
<div class="input-group">
<span class="input-group-addon"> Name </span>
 <input type="text" class="form-control" placeholder="Enter Name" name="uname"><br>
</div>
</div>
<br>
<br>
<div class="col-xs-6">
<div class="input-group">
<span class="input-group-addon"> Password </span>
<input type="password" class="form-control" placeholder="Enter password" name="upwd"><br>
</div>
</div>
<br>
<div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <input type="submit" class="btn btn-default" value="Submit">
      </div>
    </div>
<center><p>${ msg }</p></center>
</form>
</div>
 -->
 <form action="login">
 Enter ID:<input type="text" name="uname" id="uname"><br>
 Enter password:<input type="password" id="upwd" name="upwd"><br>
 <input type="submit" value="Login">
 </form>
<jsp:include page="CommonFooter.jsp"></jsp:include>
</body>
</html>