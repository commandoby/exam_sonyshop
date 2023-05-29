<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Sony shop</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h2 align="center">Shop products of Sony.</h2>

	<form method="get">
		<div class="container">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<button type="submit" class="btn btn-primary"
						formaction="/sonyshop/search">Search</button>
				</div>
				<input type="text" class="form-control" placeholder="Enter text"
					name="search_value" value="${search_value}">
				<div class="input-group-append">
					<c:if test="${not empty sessionScope.user.getEmail()}">
						<button type="button" class="btn btn-primary"
							onclick="document.location='/sonyshop/basket'">Basket
							(${sessionScope.order.getProductList().size()})</button>
						<button type="button" class="btn btn-primary"
							onclick="document.location='/sonyshop/user?email=${sessionScope.user.getEmail()}'">
							${sessionScope.user.getEmail()}</button>
						<button type="button" class="btn btn-danger"
							onclick="document.location='/sonyshop/signin'">Escape</button>
					</c:if>
					<c:if test="${empty sessionScope.user.getEmail()}">
						<button type="button" class="btn btn-success"
							onclick="document.location='/sonyshop/signin'">Sign in</button>
					</c:if>
				</div>
			</div>
		</div>
	</form>

	<form method="get">
		<div class="container" align="center">
			<br>
			<c:if test="${not empty categories}">
				<div class="row">
					<c:forEach items="${categories}" var="category">
						<button type="submit" class="btn btn-light"
							style="width: 360px; height: 360px" name="category_tag"
							value="${category.getTag()}" formaction="/sonyshop/products">
							<div class="card-body">
								<h4 class="card-title">${category.getName()}</h4>
								<img class="card-img" style="height:260px"
									src="data:image/jpeg;base64, ${category.getImage().getBase64Image()}"
									alt="Card image">
							</div>
							<div class="progress" style="height: 8px">
								<div class="progress-bar bg-light"
									style="width:${50 - category.getRating() / category_max_rating * 50}%"></div>
								<div class="progress-bar" data-toggle="tooltip" title="Rating"
									style="width:${category.getRating() / category_max_rating * 100}%"></div>
								<div class="progress-bar bg-light"
									style="width:${50 - category.getRating() / category_max_rating * 50}%"></div>
							</div>
							&nbsp
						</button>
                    &nbsp
                </c:forEach>
				</div>
			</c:if>
		</div>
	</form>

	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
</body>
</html>
