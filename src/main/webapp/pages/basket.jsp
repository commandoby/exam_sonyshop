<%@ page import="com.commandoby.sonyShop.dao.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.commandoby.sonyShop.dao.domain.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Basket</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2 align="center">Basket of products</h2>

<form method="post">
    <div class="container" align="right">
        <div class="btn-group">
            <button type="submit" class="btn btn-success" name="command" value="pay">&nbsp&nbsp Pay &nbsp&nbsp
            </button>
            <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
            <button type="submit" class="btn btn-primary" name="command" value="user">
                ${sessionScope.user.getEmail()}</button>
            <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
        </div>
    </div>
</form>

<%
    int id_product = 0;
    Order order = (Order) session.getAttribute("order");
    if (order == null) order = new Order();
%>
<div class="container">
    <br>
    <h3>There are ${basket_size} products in the basket for the amount of:
        <b style="color: orangered">${basket_price}</b></h3>
    <h3>User balance: <b style="color: orangered">${sessionScope.user.getBalance()}</b></h3>
    <c:if test="${not empty order}">
        <c:forEach items="<%= order.getProductList() %>" var="product">
            <form method="post">
                <div class="media border">
                    <input type="hidden" name="remove_id" value="<%= id_product %>"/>
                    <input type="hidden" name="product_name" value="${product.getName()}"/>
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${contextPath}/images/${product.getCategory().getTag()}/${product.getImageName()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <button type="submit" class="btn btn-primary" name="command" value="product">
                            List of product
                        </button>
                        <button type="submit" class="btn btn-primary" name="command" value="basket">
                            Remove from basket
                        </button>
                    </div>
                </div>
            </form>
            <br>
            <% id_product++; %>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
