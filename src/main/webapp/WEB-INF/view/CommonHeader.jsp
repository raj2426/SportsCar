<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>SportsCar</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Sports Cars</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <c:if test="${not empty loginMsg }"><li><a href="Login">Login</a></li></c:if>
      <li><a href="Register">Register</a></li>
      <li><a href="About us">About us</a></li>
    <c:if test="${not empty AdminMsg }"><li><a href="Admin">Admin</a></li></c:if>
      <li><a href="Products">Products</a></li>
      <c:if test="${(not empty AdminMsg) or (not empty UserMsg) }"><li><a href="goLogout">Logout</a></li></c:if>
    </ul>
  </div>
</nav>
</body>
</html>