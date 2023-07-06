<%@ page import="com.commandoby.sonyShop.components.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.commandoby.sonyShop.components.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<h2 align="center">Cart of products</h2>

<form method="get">
    <div class="container" align="right">
        <c:if test="${sessionScope.user.getBalance() >= sessionScope.order.getOrderPrice()}">
            <button type="button" class="btn btn-success" onclick="document.location='/sonyshop/pay'">
                &nbsp&nbsp Pay &nbsp&nbsp
            </button>
        </c:if>
        <c:if test="${sessionScope.user.getBalance() < sessionScope.order.getOrderPrice()}">
            <button type="button" class="btn btn-success" onclick="document.location='/sonyshop/pay'" disabled>
                &nbsp&nbsp Not enough money &nbsp&nbsp
            </button>
        </c:if>
        <div class="btn-group">
            <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">
                Home page
            </button>
            <button type="button" class="btn btn-primary" name="command" value="user"
                    onclick="document.location='/sonyshop/user?email=${sessionScope.user.getEmail()}'">
                ${sessionScope.user.getEmail()}</button>
            <button type="button" class="btn btn-danger" onclick="document.location='/sonyshop/signin'">
                Escape
            </button>
        </div>
    </div>
</form>

<%int id_product = 0;%>
<form>
    <div class="container">
        <br>
        <h3>There are ${sessionScope.order.getProductList().size()} products in the cart for the amount of:
            <b style="color: orangered">${sessionScope.order.getOrderPrice()}</b></h3>
        <h3>User balance: <b style="color: orangered">${sessionScope.user.getBalance()}</b></h3>
        <c:if test="${not empty sessionScope.order}">
            <c:forEach items="${sessionScope.order.getProductList()}" var="product">
                <div class="media border">
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${product.getImage().getImageURL()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <p class="card-text">Quantity in stock: ${product.getQuantity()}</p>
                        <button type="button" class="btn btn-primary" formmethod="get"
                                onclick="document.location='/sonyshop/product?product_id=${product.getId()}'">
                            List of product
                        </button>
                        <button type="submit" class="btn btn-primary" formmethod="post"
                                name="id" value="<%= id_product %>">
                            Remove from cart
                        </button>
                    </div>
                </div>
                <br>
                <% id_product++; %>
            </c:forEach>
        </c:if>
    </div>
</form>
</body>
</html>
