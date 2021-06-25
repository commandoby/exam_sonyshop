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
<c:set var="search" value="${search_value}"/>
<c:set var="pageItems" value="${page_items}"/>
<c:set var="min" value="${min_price}"/>
<c:set var="max" value="${max_price}"/>
<h2 align="center">Search page</h2>

<form method="post">
    <input type="hidden" name="page_items" value="${pageItems}"/>
    <div class="container">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-primary" name="command" value="search">Search</button>
            </div>
            <c:if test="${not empty search}">
                <input type="text" class="form-control" id="search_value" name="search_value" value="${search}">
            </c:if>
            <c:if test="${empty search}">
                <input type="text" class="form-control" id="search_value" placeholder="Enter text" name="search_value">
            </c:if>
            <div class="input-group-append">
                <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
                <c:if test="${not empty sessionScope.email}">
                    <button type="submit" class="btn btn-primary" name="command" value="basket">Basket (${basket_size})
                    </button>
                    <button type="submit" class="btn btn-primary" name="command" value="user">
                            ${sessionScope.email}</button>
                    <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
                </c:if>
                <c:if test="${empty sessionScope.email}">
                    <button type="submit" class="btn btn-success" name="command" value="sign-in">Sign in</button>
                </c:if>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="btn-group">
            <div class="dropdown" align="left">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    All categories
                </button>
                <div class="dropdown-menu">
                    <c:forEach items="${categories}" var="category">
                        <a class="dropdown-item" href="#">${category.getName()}</a>
                    </c:forEach>
                    <a class="dropdown-item" href="#">All categories</a>
                </div>
            </div>
        </div>
        <div class="btn-group col-sm-3">
            <c:if test="${empty min}">
                <input type="text" class="form-control" id="min_price" placeholder="Min price" name="min_price">
            </c:if>
            <c:if test="${not empty min}">
                <input type="text" class="form-control" id="min_price" name="min_price" value="${min}">
            </c:if>
            <c:if test="${empty max}">
                <input type="text" class="form-control" id="max_price" placeholder="Max price" name="max_price">
            </c:if>
            <c:if test="${not empty max}">
                <input type="text" class="form-control" id="max_price" name="max_price" value="${max}">
            </c:if>
        </div>
        <div class="btn-group">
            <c:choose>
                <c:when test="${pageItems == 'all'}">
                    <button type="button" class="btn btn-primary">10</button>
                    <button type="button" class="btn btn-primary">20</button>
                    <button type="button" class="btn btn-primary">50</button>
                    <button type="button" class="btn btn-primary" disabled>All</button>
                </c:when>
            </c:choose>
        </div>
    </div>
</form>

<div class="container">
    <br>
    <p>Found ${product_size} products.</p>
    <c:if test="${not empty product_list}">
        <c:forEach items="${product_list}" var="product">
            <form method="post">
                <input type="hidden" name="search_value" value="${search}"/>
                <input type="hidden" name="min_price" value="${min}"/>
                <input type="hidden" name="max_price" value="${max}"/>
                <input type="hidden" name="product_name" value="${product.getName()}"/>
                <div class="media border">
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${contextPath}/images/${product.getCategories().getTag()}/${product.getImageName()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <button type="submit" class="btn btn-primary"
                                name="command" value="product">
                            List of product
                        </button>
                        <c:if test="${not empty sessionScope.email}">
                            <button type="submit" class="btn btn-primary"
                                    name="command" value="search">
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