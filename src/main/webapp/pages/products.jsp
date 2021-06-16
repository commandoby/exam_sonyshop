<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2 align="center">List products category: ${category_name}</h2>

<form method="post">
    <input type="hidden" name="category_tag" value="${category_tag}"/>
    <div class="container">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-primary" name="command" value="product_list">Search</button>
            </div>
            <input type="text" class="form-control w-25" id="search_value" placeholder="Enter name" name="search_value">
            <div class="input-group-append">
                <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
                <button type="submit" class="btn btn-primary" name="command" value="basket">Basket (${basket_size})
                </button>
                <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
            </div>
        </div>
    </div>
</form>

<div class="container">
    <br>
    <p>Found ${product_size} products.</p>
    <c:if test="${not empty product_list}">
        <c:forEach items="${product_list}" var="product">
            <form method="post">
                <input type="hidden" name="category_tag" value="${category_tag}"/>
                <input type="hidden" name="product_name" value="${product.getName()}"/>
                <div class="media border">
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${contextPath}/images/${category_tag}/${product.getImageName()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <button type="submit" class="btn btn-primary"
                                name="command" value="product">
                            List of product
                        </button>
                        <button type="submit" class="btn btn-primary"
                                name="command" value="product_list">
                            Add to basket
                        </button>
                    </div>
                </div>
            </form>
            <br>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
