<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Search</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="search_comparing" value="${search_comparing}"/>
<h2 align="center">Search page</h2>

<form method="get">
    <div class="container">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-primary" formaction="search">Search</button>
            </div>
            <input type="text" class="form-control" placeholder="Enter text"
                   name="search_value" value="${search_value}">
            <div class="input-group-append">
                <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">
                    Home page</button>
                <c:if test="${not empty sessionScope.user}">
                    <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/basket'">
                        Basket (${sessionScope.order.getProductList().size()})</button>
                    <button type="button" class="btn btn-primary" name="command" value="user">
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
    <div class="container">
        <div class="btn-group">
            <div class="dropdown" align="left">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    <c:if test="${empty search_category}">All categories</c:if>
                    <c:if test="${not empty search_category}">${search_category}</c:if>
                </button>
                <div class="dropdown-menu">
                    <c:forEach items="${categories}" var="category">
                        <button class="dropdown-item" type="submit" name="search_category"
                                value="${category.getTag()}">${category.getName()}</button>
                    </c:forEach>
                    <button class="dropdown-item" type="submit" name="search_category"
                            value="">All categories</button>
                </div>
<%--                <input type="hidden" name="search_category" value="${search_category}"/>--%>
            </div>
        </div>
        <div class="btn-group">
            <div class="dropdown" align="left">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    ${search_comparing}
                </button>
                <div class="dropdown-menu">
                    <button class="dropdown-item" type="submit" name="search_comparing"
                            value="Price+">Price +</button>
                    <button class="dropdown-item" type="submit" name="search_comparing"
                            value="Price-">Price -</button>
                    <button class="dropdown-item" type="submit" name="search_comparing"
                            value="Name+">Name +</button>
                    <button class="dropdown-item" type="submit" name="search_comparing"
                            value="Name-">Name -</button>
                </div>
<%--                <input type="hidden" name="search_comparing" value="${search_comparing}"/>--%>
            </div>
        </div>
        <div class="btn-group col-sm-3">
            <input type="text" class="form-control" id="min_price" placeholder="Min price"
                   name="min_price" value="${min_price}">
            <input type="text" class="form-control" id="max_price" placeholder="Max price"
                   name="max_price" value="${max_price}">
        </div>
        <div class="btn-group">
            <button type="submit" class="btn btn-primary" name="page_items" value="10"
                    <c:if test="${page_items == '10'}">disabled</c:if>>10</button>
            <button type="submit" class="btn btn-primary" name="page_items" value="20"
                    <c:if test="${page_items == '20'}">disabled</c:if>>20</button>
            <button type="submit" class="btn btn-primary" name="page_items" value="50"
                    <c:if test="${page_items == '50'}">disabled</c:if>>50</button>
            <button type="submit" class="btn btn-primary" name="page_items" value="0"
                    <c:if test="${page_items == '0'}">disabled</c:if>>All</button>
        </div>
        <c:if test="${page_items != '0'}">
            <div class="btn-group">
                <button type="submit" class="btn btn-primary" name="page_number" value="${page_number - 1}"
                        <c:if test="${page_number == '1'}">disabled</c:if>>Previous</button>
                <button type="button" class="btn btn-primary">Page ${page_number}</button>
                <button type="submit" class="btn btn-primary" name="page_number" value="${page_number + 1}">
                    Next</button>
            </div>
        </c:if>
    </div>
    <input type="hidden" name="page_items" value="${page_items}"/>
    <input type="hidden" name="page_number" value="${page_number}"/>
</form>

<form>
    <div class="container">
        <br>
        <p>Found ${product_size} products.</p>
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
                            List of product</button>
                        <c:if test="${not empty sessionScope.user}">
                            <button type="submit" class="btn btn-primary" formmethod="post"
                                    name="id" value="${product.getId()}">
                                Add to basket</button>
                        </c:if>
                    </div>
                </div>
                <br>
            </c:forEach>
        </c:if>
    </div>
</form>
<form method="get">
    <c:if test="${page_items != '0'}">
        <div class="btn-group">
            <button type="submit" class="btn btn-primary" name="page_number" value="${page_number - 1}"
                    <c:if test="${page_number == '1'}">disabled</c:if>>Previous</button>
            <button type="button" class="btn btn-primary">Page ${page_number}</button>
            <button type="submit" class="btn btn-primary" name="page_number" value="${page_number + 1}">
                Next</button>
        </div>
    </c:if>
</form>
</body>