<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="product" value="${product}"/>
<h2 align="center">Product page</h2>

<form method="get">
    <div class="container" align="right">
        <div class="btn-group">
            <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">Home page</button>
            <c:if test="${not empty sessionScope.user}">
                <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/basket'">
                    Basket (${sessionScope.order.getProductList().size()})</button>
                <button type="button" class="btn btn-primary"
                        onclick="document.location='/sonyshop/user?email=${sessionScope.user.getEmail()}'">
                        ${sessionScope.user.getEmail()}</button>
                <button type="button" class="btn btn-danger" onclick="document.location='/sonyshop/signin'">
                    Escape</button>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <button type="button" class="btn btn-success" onclick="document.location='/sonyshop/signin'">
                    Sign in</button>
            </c:if>
        </div>
    </div>
</form>

<form method="post">
    <div class="container">
        <input type="hidden" name="product_id" value="product.getId()"/>
        <div class="media">
            <img class="card-img p-3" style="max-width:400px;max-height: 640px"
                 src="${contextPath}/images/${product.getCategory().getTag()}/${product.getImageName()}"
                 alt="Card image">
            <div class="media-body">
                <h2>${product.getName()}</h2>
                <p class="card-text">${product.getDescription()}</p>
                <br>
                <h3><small> Price: </small><b style="color: orangered">${product.getPrice()}</b></h3>
                <c:if test="${not empty sessionScope.user}">
                    <button type="submit" class="btn btn-primary" name="id"
                            value="${product.getId()}">Add to basket</button>
                </c:if>
            </div>
        </div>
    </div>
</form>
</body>
</html>