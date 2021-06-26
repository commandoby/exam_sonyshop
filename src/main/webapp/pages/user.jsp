<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="${user}"/>
<h2 align="center">User page</h2>

<form method="post">
    <div class="container" align="right">
        <div class="btn-group">
            <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
            <button type="submit" class="btn btn-primary" name="command" value="basket">Basket (${basket_size})
            </button>
            <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
        </div>
    </div>
</form>

<form method="post">
    <div class="container">
        <input type="hidden" name="command" value="product"/>
        <input type="hidden" name="product_name_out" value="product"/>
        <div class="media">
            <img class="card-img p-3" style="width:320px;height: 320px"
                 src="${contextPath}/images/user.jpeg"
                 alt="Card image">
            <div class="media-body">
                <br>
                <h2><small>Name: </small>${user.getName()}</h2>
                <h2><small>Surname: </small>${user.getSurname()}</h2>
                <h2><small>Email: </small>${user.getEmail()}</h2>
                <h2><small>Date of birth: </small>${user.getDateOfBirth()}</h2>
                <h2><small>Balance: </small><b style="color: orangered">${user.getBalance()}</b></h2>
                <button type="submit" class="btn btn-primary" disabled>Edit</button>
            </div>
        </div>
    </div>
</form>
<% int id_order = 0; %>
<form method="post">
    <input type="hidden" name="command" value="product"/>
    <div class="container">
        <c:if test="${not empty user_orders}">
            <h3>Purchases list</h3>
            <c:forEach items="${user_orders}" var="order">
                <div class="container p-3 my-3 border">
                    <h4>Order number <%=++id_order%>.
                        Order price: ${order.getOrderPrice()}.
                        Order date: ${order.getDate()}</h4>
                    <c:forEach items="${order.getProductList()}" var="product">
                        <button type="submit" class="btn btn-light" style="width:340px;height:200px"
                                name="product_name" value="${product.getName()}">
                            <div class="media" style="word-break: break-word">
                                <img class="card-img p-3" style="max-width:160px;max-height: 180px"
                                     src="${contextPath}/images/${product.getCategories().getTag()}/${product.getImageName()}"
                                     alt="Card image">
                                <div class="media-body" align="left">
                                    <p>${product.getName()}</p>
                                    <h5>Price:</h5>
                                    <h3 style="color: orangered">${product.getPrice()}</h3>
                                </div>
                            </div>
                        </button>
                    </c:forEach>
                </div>
            </c:forEach>
        </c:if>
    </div>
</form>
</body>
</html>