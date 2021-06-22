<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
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
            <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
            <button type="submit" class="btn btn-primary" name="command" value="basket">Basket (${basket_size})
            </button>
            <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
        </div>
    </div>
</form>

<form method="post">
    <div class="container">
        <input type="hidden" name="command" value="product"/>
        <input type="hidden" name="product_name_out" value="product"/>
        <div class="media">
            <img class="card-img p-3" style="max-width:400px;max-height: 640px"
                 src="${contextPath}/images/user.jpeg"
                 alt="Card image">
            <div class="media-body">
                <br>
                <h2><small>Name: </small>${user.getName()}</h2>
                <h2><small>Surname: </small>${user.getSurname()}</h2>
                <h2><small>Email: </small>${user.getEmail()}</h2>
                <h2><small>Date of Birth: </small>${user.getData()}</h2>
                <h2><small>Balance: </small><b style="color: orangered">${user.getBalance()}</b></h2>
            </div>
        </div>
    </div>
    <div class="container">
        <h3>Purchases list</h3>
    </div>
</form>
</body>
</html>