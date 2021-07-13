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

<form method="get">
    <input type="hidden" name="category_tag" value="${category_tag}"/>
    <div class="container">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-primary" formaction="products">Search</button>
            </div>
            <input type="text" class="form-control" placeholder="Enter text"
                   name="search_value" value="${search_value}">
            <div class="input-group-append">
                <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/search'">
                    Advanced Search
                </button>
                <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">
                    Home page
                </button>
                <c:if test="${not empty sessionScope.user}">
                    <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/basket'">
                        Basket (${sessionScope.order.getProductList().size()})
                    </button>
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
    </div>
</form>

<form>
    <div class="container">
        <br>
        <p>Found ${product_list.size()} products.</p>
        <c:if test="${not empty product_list}">
            <c:forEach items="${product_list}" var="product">
                <div class="media border">
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${contextPath}/images/${product.getCategory().getTag()}/${product.getImageName()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <button type="button" class="btn btn-primary" formmethod="get"
                                onclick="document.location='/sonyshop/product?id=${product.getId()}'">
                            List of product
                        </button>
                        <c:if test="${not empty sessionScope.user}">
                            <button type="submit" class="btn btn-primary" formmethod="post"
                                    name="id" value="${product.getId()}">
                                Add to basket
                            </button>
                        </c:if>
                    </div>
                </div>
                <br>
            </c:forEach>
        </c:if>
    </div>
</form>
</body>
</html>
