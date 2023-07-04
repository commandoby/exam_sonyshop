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
<h2 align="center">Search page</h2>

<div class="container">
    <form method="get">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
            <input type="text" class="form-control" placeholder="Enter text"
                   name="search_value" value="${search_value}">
            <div class="input-group-append">
                <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">
                    Home page
                </button>
                <c:if test="${not empty sessionScope.user.getEmail()}">
                    <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/cart'">
                        Cart (${sessionScope.order.getProductList().size()})
                    </button>
                    <button type="button" class="btn btn-primary" name="command" value="user"
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
        <input type="hidden" name="search_comparing" value="${search_comparing}"/>
        <input type="hidden" name="is_quantity" value="${is_quantity}"/>
        <input type="hidden" name="min_price" value="${min_price}"/>
        <input type="hidden" name="max_price" value="${max_price}"/>
        <input type="hidden" name="page_items" value="${page_items}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>
</div>
<div class="container">
    <form method="get" style="display: inline">
        <div class="btn-group" data-toggle="tooltip" title="Category products.">
            <div class="dropdown" align="left">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    <c:if test="${empty category_tag}">All categories</c:if>
                    <c:if test="${not empty category_tag}">${category_name}</c:if>
                </button>
                <div class="dropdown-menu">
                    <c:forEach items="${categories}" var="category">
                        <button class="dropdown-item" type="submit" name="category_tag"
                                value="${category.getTag()}"
                                <c:if test="${category_tag == category.getTag()}">disabled</c:if>>
                                ${category.getName()}</button>
                    </c:forEach>
                    <button class="dropdown-item" type="submit" name="category_tag" value=""
                            <c:if test="${category_tag == ''}">disabled</c:if>>
                        All categories
                    </button>
                </div>
            </div>
            <input type="hidden" name="search_value" value="${search_value}"/>
            <input type="hidden" name="search_comparing" value="${search_comparing}"/>
            <input type="hidden" name="is_quantity" value="${is_quantity}"/>
            <input type="hidden" name="min_price" value="${min_price}"/>
            <input type="hidden" name="max_price" value="${max_price}"/>
            <input type="hidden" name="page_items" value="${page_items}"/>
            <input type="hidden" name="page_number" value="${page_number}"/>
        </div>
    </form>

    <form method="get" style="display: inline">
        <div class="btn-group" data-toggle="tooltip" title="Sorting type.">
            <div class="dropdown" align="left">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    ${search_comparing}
                </button>
                <div class="dropdown-menu">
                    <button class="dropdown-item" type="submit" name="search_comparing" value="Price+"
                            <c:if test="${search_comparing == 'Price+'}">disabled</c:if>>Price +
                    </button>
                    <button class="dropdown-item" type="submit" name="search_comparing" value="Price-"
                            <c:if test="${search_comparing == 'Price-'}">disabled</c:if>>Price -
                    </button>
                    <button class="dropdown-item" type="submit" name="search_comparing" value="Name+"
                            <c:if test="${search_comparing == 'Name+'}">disabled</c:if>>Name +
                    </button>
                    <button class="dropdown-item" type="submit" name="search_comparing" value="Name-"
                            <c:if test="${search_comparing == 'Name-'}">disabled</c:if>>Name -
                    </button>
                </div>
            </div>
        </div>
        <input type="hidden" name="search_value" value="${search_value}"/>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="is_quantity" value="${is_quantity}"/>
        <input type="hidden" name="min_price" value="${min_price}"/>
        <input type="hidden" name="max_price" value="${max_price}"/>
        <input type="hidden" name="page_items" value="${page_items}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>

    <form method="get" style="display: inline">
        <c:if test="${is_quantity != 'on'}">
            <button type="submit" class="btn btn-primary" name="is_quantity" value="on"
                    data-toggle="tooltip" title="Display missing items?">
                Are available.
            </button>
        </c:if>
        <c:if test="${is_quantity == 'on'}">
            <button type="submit" class="btn btn-light" name="is_quantity" value=""
                    data-toggle="tooltip" title="Display missing items?">
                All goods.
            </button>
        </c:if>
        <input type="hidden" name="search_value" value="${search_value}"/>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="search_comparing" value="${search_comparing}"/>
        <input type="hidden" name="min_price" value="${min_price}"/>
        <input type="hidden" name="max_price" value="${max_price}"/>
        <input type="hidden" name="page_items" value="${page_items}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>

    <form method="get" style="display: inline">
        <div class="btn-group col-sm-3">
            <input type="text" class="form-control" id="min_price" placeholder="Min price"
                   name="min_price" value="${min_price}"
                   onkeydown="if (event.keyCode === 13) { this.form.submit(); return false; }">
            <input type="text" class="form-control" id="max_price" placeholder="Max price"
                   name="max_price" value="${max_price}"
                   onkeydown="if (event.keyCode === 13) { this.form.submit(); return false; }">
        </div>
        <input type="hidden" name="search_value" value="${search_value}"/>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="search_comparing" value="${search_comparing}"/>
        <input type="hidden" name="is_quantity" value="${is_quantity}"/>
        <input type="hidden" name="page_items" value="${page_items}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>

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
        <input type="hidden" name="search_value" value="${search_value}"/>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="search_comparing" value="${search_comparing}"/>
        <input type="hidden" name="is_quantity" value="${is_quantity}"/>
        <input type="hidden" name="min_price" value="${min_price}"/>
        <input type="hidden" name="max_price" value="${max_price}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>

        <c:if test="${not empty product_list}">
            <form method="get" style="display: inline">
                <div class="btn-group" data-toggle="tooltip" title="Product pages.">
					<c:if test="${page_number > 1}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="1">1</button>
					</c:if>
					<c:if test="${page_number > 4}">
						<button type="submit" class="btn btn-primary" name="page_number"
							disabled>...</button>
					</c:if>
					<c:if test="${page_number == 4}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="2">2</button>
					</c:if>
					<c:if test="${page_number > 2}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_number - 1}">${page_number - 1}</button>
					</c:if>
					<button type="submit" class="btn btn-success" name="page_number"
							disabled>${page_number}</button>
					<c:if test="${page_number + 1 < page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_number + 1}">${page_number + 1}</button>
					</c:if>
					<c:if test="${page_number + 3 == page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_number + 2}">${page_number + 2}</button>
					</c:if>
					<c:if test="${page_number + 3 < page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							disabled>...</button>
					</c:if>
					<c:if test="${page_number < page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_max}">${page_max}</button>
					</c:if>
				</div>
                <input type="hidden" name="search_value" value="${search_value}"/>
                <input type="hidden" name="category_tag" value="${category_tag}"/>
                <input type="hidden" name="search_comparing" value="${search_comparing}"/>
                <input type="hidden" name="is_quantity" value="${is_quantity}"/>
                <input type="hidden" name="min_price" value="${min_price}"/>
                <input type="hidden" name="max_price" value="${max_price}"/>
                <input type="hidden" name="page_items" value="${page_items}"/>
            </form>
        </c:if>
</div>

<div class="container">
    <form method="get">
        <br>
        <c:if test="${empty info}">
				<p>
					Found ${found_items} products. Showing from ${page_items * (page_number - 1) + 1} to
					<c:if test="${page_items * page_number < found_items}">
                            ${page_items * page_number}
                        </c:if>
					<c:if test="${page_items * page_number >= found_items}">
                            ${found_items}
                        </c:if>
					results.
				</p>
			</c:if>
        <c:if test="${not empty info}">
            <p style="color: red">${info}</p>
        </c:if>
        <c:if test="${not empty product_list}">
            <c:forEach items="${product_list}" var="product">
                <div class="media border">
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${product.getImage().getImageURL()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <c:if test="${not empty product.getYear()}">
                        	<p class="card-text">Year: ${product.getYear()}</p>
                        </c:if>
                        <p class="card-text">Quantity in stock: ${product.getQuantity()}</p>
                        <button type="button" class="btn btn-primary"
                                onclick="document.location='/sonyshop/product?product_id=${product.getId()}'">
                            List of product
                        </button>
                        <c:if test="${not empty sessionScope.user.getEmail()}">
                            <c:if test="${product.getQuantity() > 0}">
                                <button type="submit" class="btn btn-primary"
                                        name="product_id" value="${product.getId()}">
                                    Add to cart
                                </button>
                            </c:if>
                        </c:if>
                    </div>
                </div>
                <br>
            </c:forEach>
        </c:if>
        <input type="hidden" name="search_value" value="${search_value}"/>
        <input type="hidden" name="category_tag" value="${category_tag}"/>
        <input type="hidden" name="search_comparing" value="${search_comparing}"/>
        <input type="hidden" name="is_quantity" value="${is_quantity}"/>
        <input type="hidden" name="min_price" value="${min_price}"/>
        <input type="hidden" name="max_price" value="${max_price}"/>
        <input type="hidden" name="page_items" value="${page_items}"/>
        <input type="hidden" name="page_number" value="${page_number}"/>
    </form>
</div>

    <c:if test="${not empty product_list}">
        <div class="container" align="right">
            <form method="get">
                <div class="btn-group" data-toggle="tooltip" title="Product pages.">
                    <c:if test="${page_number > 1}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="1">1</button>
					</c:if>
					<c:if test="${page_number > 4}">
						<button type="submit" class="btn btn-primary" name="page_number"
							disabled>...</button>
					</c:if>
					<c:if test="${page_number == 4}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="2">2</button>
					</c:if>
					<c:if test="${page_number > 2}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_number - 1}">${page_number - 1}</button>
					</c:if>
					<button type="submit" class="btn btn-success" name="page_number"
							disabled>${page_number}</button>
					<c:if test="${page_number + 1 < page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_number + 1}">${page_number + 1}</button>
					</c:if>
					<c:if test="${page_number + 3 == page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_number + 2}">${page_number + 2}</button>
					</c:if>
					<c:if test="${page_number + 3 < page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							disabled>...</button>
					</c:if>
					<c:if test="${page_number < page_max}">
						<button type="submit" class="btn btn-primary" name="page_number"
							value="${page_max}">${page_max}</button>
					</c:if>
                </div>
                <input type="hidden" name="search_value" value="${search_value}"/>
                <input type="hidden" name="category_tag" value="${category_tag}"/>
                <input type="hidden" name="search_comparing" value="${search_comparing}"/>
                <input type="hidden" name="is_quantity" value="${is_quantity}"/>
                <input type="hidden" name="min_price" value="${min_price}"/>
                <input type="hidden" name="max_price" value="${max_price}"/>
                <input type="hidden" name="page_items" value="${page_items}"/>
            </form>
            <br>
        </div>
    </c:if>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</body>
</html>