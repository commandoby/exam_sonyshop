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
                <input type="text" class="form-control" id="search_value" placeholder="Enter text"
                       name="search_value" value="${search}">
            <div class="input-group-append">
                <button type="submit" class="btn btn-primary" name="command" value="search">
                    Advanced Search
                </button>
                <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
                <c:if test="${not empty sessionScope.user}">
                    <button type="submit" class="btn btn-primary" name="command" value="basket">
                        Basket (${basket_size})
                    </button>
                    <button type="submit" class="btn btn-primary" name="command" value="user">
                            ${sessionScope.user.getEmail()}</button>
                    <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
                </c:if>
                <c:if test="${empty sessionScope.user}">
                    <button type="submit" class="btn btn-success" name="command" value="sign-in">Sign in</button>
                </c:if>
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
                         src="${contextPath}/images/${product.getCategory().getTag()}/${product.getImageName()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <button type="submit" class="btn btn-primary"
                                name="command" value="product">
                            List of product
                        </button>
                        <c:if test="${not empty sessionScope.user}">
                            <button type="submit" class="btn btn-primary"
                                    name="command" value="product_list">
                                Add to basket
                            </button>
                        </c:if>
                    </div>
                </div>
            </form>
            <br>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
