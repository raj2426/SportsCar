<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link  rel="stylesheet" href="Resources/Bootstrap/bootstrap.min.css">
<script src="Resources/Bootstrap/bootstrap.js"></script>
<script src="Resources/JQuery/jquery-3.1.1.min.js"></script>
<title>Cart Creation</title>
</head>
<body>
<jsp:include page="CommonHeader.jsp"></jsp:include>
<center>Create a cart and then add Products to your cart</center>
<form method="post" action="createCartOfUser">
<div class="form-group">
<label for="title">User ID:</label>
<input type="text" value="${userID}" disabled="disabled">
<input type="hidden" id="usID" name="usID" value="${userID}"><br>
</div>
<div class="form-group">
<label for="title">Deliver To (Receiver Name):</label>
<input type="text" id="usName" name="usName" value="${ UserName }"><br>
</div><br>
<div class="form-group">
<label for="title">Address Line1:</label>
<input type="text" id="addr1" name="addr1" placeholder="Enter your street"><br>
</div><br>
<div class="form-group">
<label for="title">City:</label>
<input type="text" id="addr2" name="addr2"><br>
</div><br>
<div class="form-group">
<label for="title">State:</label>
<input type="text" id="addr3" name="addr3"><br>
</div><br>
<div class="form-group">
<label for="title">Pincode</label>
<input type="text" id="addr4" name="addr4"><br>
</div><br>
<input type="submit" value="Create Cart">
</form>
</body>
</html>