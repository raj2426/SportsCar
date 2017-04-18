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
<title>View User Cart</title>
<script type="text/javascript">
function generateCartBill()
{
 window.location="goGenerateBill";
}
</script>
</head>
<body>
<jsp:include page="CommonHeader.jsp"></jsp:include>
 <div class="container">
  <h2>Books in Cart</h2>            
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Product Id</th>
        <th>Product Name</th>
        <th>Product Description</th>
        <th>Supplier Id</th>
        <th>Category Id</th>
        <th>Product Quantity</th>
        <th>Product Cost</th>
        <th>Edit/Remove</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${prList}" var="pList">
      <tr>
        <td><c:out value="${pList.productID}" /></td>
        <td><c:out value="${pList.productName}" /></td>
        <td><c:out value="${pList.productDesc}" /></td>
        <td><c:out value="${pList.supplierID}" /></td>
        <td><c:out value="${pList.categoryID}" /></td>
        <td><c:out value="${pList.productQty}" /></td>
        <td><c:out value="${pList.productCost}" /></td>
        <td>
        <form action="removeProdFromCart"> 
        <input type="hidden" id="prID" name="prID" value="${pList.productID}"><br>
        <input type="submit" value="Remove from Cart"> 
        </form>
        </td>
      </tr>
       </c:forEach>
    </tbody>
  </table>
</div>
<button type="button" class="btn btn-primary" id="genBill" onclick="generateCartBill()">Generate Bill</button>
    <jsp:include page="CommonFooter.jsp"></jsp:include>
</body>
</html>