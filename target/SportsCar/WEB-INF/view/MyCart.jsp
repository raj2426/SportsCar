<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="row">
 <c:forEach items="${prList}" var="pList">
    <div class="col-xs-6 col-md-3">
      <div class="thumbnail">
        <img src="${path}${pList.productID}.jpg" alt="${pList.productName}" height="75">
          <div class="caption">
          <center>${pList.productName}
          <p>Cost: ${pList.productCost}</p>
          <form action="removeProductFromCart">
          <input type="submit" value="Remove from Cart">
          </form>
          <!-- <button type="button">Add to Cart</button>  -->
          </center>
          </div>
          </div>
    </div>
    </c:forEach>
    </div>
</body>
</html>