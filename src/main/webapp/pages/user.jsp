<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${sessionScope.user.getEmail()}</title>
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
            <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop'">
                Home page
            </button>
            <button type="button" class="btn btn-primary" onclick="document.location='/sonyshop/basket'">
                Basket (${sessionScope.order.getProductList().size()})
            </button>
            <button type="button" class="btn btn-danger" onclick="document.location='/sonyshop/signin'">
                Escape
            </button>
        </div>
    </div>
</form>

<form>
    <div class="container">
        <input type="hidden" name="user_edit" value="${user_edit}"/>
        <div class="media">
            <img class="card-img p-3" style="width:320px;height: 320px"
                 src="${contextPath}/images/user.jpeg"
                 alt="Card image">
            <div class="media-body">
                <br>
                <c:if test="${empty user_edit}">
                    <h2><small>Email: </small>${sessionScope.user.getEmail()}</h2>
                    <h2><small>Name: </small>${sessionScope.user.getName()}</h2>
                    <h2><small>Surname: </small>${sessionScope.user.getSurname()}</h2>
                    <h2><small>Date of birth: </small>${sessionScope.user.getDateOfBirth()}</h2>
                    <h2><small>Balance: </small><b style="color: orangered">${sessionScope.user.getBalance()}</b></h2>
                    <button type="button" class="btn btn-primary" formmethod="get" style="display: inline"
                            onclick="document.location='/sonyshop/user?email=${sessionScope.user.getEmail()}&user_edit=edit'">
                        Edit data
                    </button>
                    <button type="button" class="btn btn-primary" formmethod="get" style="display: inline"
                            onclick="document.location='/sonyshop/user?email=${sessionScope.user.getEmail()}&user_edit=password'">
                        Edit password
                    </button>
                </c:if>
                <c:if test="${user_edit == 'edit'}">
                    <h2><small>Email: </small>${sessionScope.user.getEmail()}</h2>
                    <div class="form-group">
                        <input type="text" class="form-control w-50" id="new_name"
                               placeholder="Enter name" name="new_name"
                               value="${sessionScope.user.getName()}" required>
                        <div class="invalid-feedback">Please fill out name field</div>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control w-50" id="new_surname"
                               placeholder="Enter surname" name="new_surname"
                               value="${sessionScope.user.getSurname()}" required>
                        <div class="invalid-feedback">Please fill out surname field</div>
                    </div>
                    <div class="form-group">
                        <input type="date" class="form-control w-50" id="new_date_of_birth"
                               placeholder="Enter date of birth" name="new_date_of_birth"
                               value="${sessionScope.user.getDateOfBirth()}" required>
                        <div class="invalid-feedback">Please fill out data field</div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control w-50" id="password"
                               placeholder="Enter password" name="old_password" required>
                        <div class="invalid-feedback">Please fill out password field</div>
                    </div>
                    <p style="color: red">${info}</p>
                    <button type="submit" class="btn btn-primary w-50" formmethod="post"
                            formaction="/sonyshop/user?email=${sessionScope.user.getEmail()}">Save</button>
                </c:if>
                <c:if test="${user_edit == 'password'}">
                    <h2><small>Email: </small>${sessionScope.user.getEmail()}</h2>
                    <div class="form-group">
                        <input type="password" class="form-control w-50" id="old_password"
                               placeholder="Enter old password" name="old_password" required>
                        <div class="invalid-feedback">Please fill out password field</div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control w-50" id="new_password"
                               placeholder="Enter new password" name="new_password" required>
                        <div class="invalid-feedback">Please fill out password field</div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control w-50" id="second_password"
                               placeholder="Enter new password again" name="second_password" required>
                        <div class="invalid-feedback">Please fill out password field</div>
                    </div>
                    <p style="color: red">${info}</p>
                    <button type="submit" class="btn btn-primary w-50" formmethod="post"
                            formaction="/sonyshop/user?email=${sessionScope.user.getEmail()}">Save</button>
                </c:if>
            </div>
        </div>
    </div>
</form>
<% int id_order = 0; %>
<form method="post">
    <div class="container">
        <c:if test="${not empty sessionScope.user.getOrders()}">
            <h3>Purchases list</h3>
            <c:forEach items="${sessionScope.user.getOrders()}" var="order">
                <div class="container p-3 my-3 border">
                    <h4>Order number <%=++id_order%>.
                        Order price: ${order.getOrderPrice()}.
                        Order date: ${order.getDate()}</h4>
                    <c:forEach items="${order.getProductList()}" var="product">
                        <button type="button" class="btn btn-light" style="width:340px;height:200px"
                                onclick="document.location='/sonyshop/product?product_id=${product.getId()}'">
                            <div class="media" style="word-break: break-word">
                                <img class="card-img p-3" style="max-width:160px;max-height: 180px"
                                     src="data:image/jpeg;base64, ${product.getImage().getBase64Image()}"
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

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>