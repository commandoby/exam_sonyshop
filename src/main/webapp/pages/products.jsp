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


<div class="container">
    <form method="get">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-primary" formaction="/sonyshop/search">Search</button>
            </div>
            <input type="text" class="form-control" placeholder="Enter text"
                   name="search_value" value="${search_value}">
            <div class="input-group-append">
                <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">
                    Home page
                </button>
                <c:if test="${not empty sessionScope.user.getEmail()}">
                    <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/basket'">
                        Basket (${sessionScope.order.getProductList().size()})
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="document.location='/sonyshop/user?email=${sessionScope.user.getEmail()}'">
                            ${sessionScope.user.getEmail()}</button>
                    <button type="button" class="btn btn-danger" onclick="document.location='/sonyshop/signin'">
                        Escape
                    </button>
                </c:if>
                <c:if test="${empty sessionScope.user.getEmail()}">
                    <button type="button" class="btn btn-success" onclick="document.location='/sonyshop/signin'">
                        Sign in
                    </button>
                </c:if>
            </div>
        </div>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="page_items" value="${page_items}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>
</div>

<div class="container" align="right">
    <form method="get" style="display: inline">
        <div class="btn-group" data-toggle="tooltip" title="The number of products per page.">
            <button type="submit" class="btn btn-primary" name="page_items" value="5"
                    <c:if test="${page_items == '5'}">disabled</c:if>>5
            </button>
            <button type="submit" class="btn btn-primary" name="page_items" value="10"
                    <c:if test="${page_items == '10'}">disabled</c:if>>10
            </button>
            <button type="submit" class="btn btn-primary" name="page_items" value="20"
                    <c:if test="${page_items == '20'}">disabled</c:if>>20
            </button>
            <button type="submit" class="btn btn-primary" name="page_items" value="50"
                    <c:if test="${page_items == '50'}">disabled</c:if>>50
            </button>
        </div>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>

		<c:if test="${not empty product_list}">
			<form method="get" style="display: inline">
				<div class="btn-group" data-toggle="tooltip" title="Product pages.">
					<button type="submit" class="btn btn-primary" name="page_number"
						value="${page_number - 1}"
						<c:if test="${page_number == '1'}">disabled</c:if>>Previous
					</button>
					<button type="button" class="btn btn-primary" disabled>${page_items * (page_number - 1) + 1} - 
						<c:if test="${page_items * page_number < found_items}">
                            ${page_items * page_number}
                        </c:if>
						<c:if test="${page_items * page_number >= found_items}">
                            ${found_items}
                        </c:if>
					</button>
					<button type="submit" class="btn btn-primary" name="page_number"
						value="${page_number + 1}"
						<c:if test="${page_items * page_number >= found_items}">disabled</c:if>>Next
					</button>
				</div>
				<input type="hidden" name="category_tag" value="${category_tag}" />
				<input type="hidden" name="page_items" value="${page_items}" />
			</form>
		</c:if>
	</div>

<form>
    <div class="container">
        <br>
        <p>Found ${found_items} products.</p>
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
                        <p class="card-text">Quantity in stock: ${product.getQuantity()}</p>
                        <button type="button" class="btn btn-primary" formmethod="get"
                                onclick="document.location='/sonyshop/product?product_id=${product.getId()}'">
                            List of product
                        </button>
                        <c:if test="${not empty sessionScope.user.getEmail()}">
                        <c:if test="${product.getQuantity() > 0}">
                            <button type="submit" class="btn btn-primary" formmethod="post"
                                    name="product_id" value="${product.getId()}">
                                Add to basket
                            </button>
                        </c:if>
                        </c:if>
                    </div>
                </div>
                <br>
            </c:forEach>
        </c:if>
    </div>
</form>

	<c:if test="${not empty product_list}">
		<div class="container" align="right">
			<form method="get">
				<div class="btn-group" data-toggle="tooltip" title="Product pages.">
					<button type="submit" class="btn btn-primary" name="page_number"
						value="${page_number - 1}"
						<c:if test="${page_number == '1'}">disabled</c:if>>Previous
					</button>
					<button type="button" class="btn btn-primary" disabled>${page_items * (page_number - 1) + 1} - 
						<c:if test="${page_items * page_number < found_items}">
                            ${page_items * page_number}
                        </c:if>
						<c:if test="${page_items * page_number >= found_items}">
                            ${found_items}
                        </c:if>
					</button>
					<button type="submit" class="btn btn-primary" name="page_number"
						value="${page_number + 1}"
						<c:if test="${page_items * page_number >= found_items}">disabled</c:if>>Next
					</button>
				</div>
				<input type="hidden" name="category_tag" value="${category_tag}" />
				<input type="hidden" name="page_items" value="${page_items}" />
			</form>
			<br>
		</div>
	</c:if>

	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
</body>
</html>
