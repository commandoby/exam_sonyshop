<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pay</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<form method="post">
    <div class="container" align="center">
        <br><br><br>
        <h1>Thank you for choosing us!</h1>
        <br>
        <h2><small>You have purchased </small>${basket_size}<small> products.</small></h2>
        <h2>You paid <b style="color: orangered">${basket_price}</b>!</h2>
        <c:choose>
            <c:when test="${basket_price > 10000}">
                <p>Not bad!</p>
            </c:when>
            <c:when test="${basket_price > 2000}">
                <p>There could have been more.</p>
            </c:when>
            <c:when test="${basket_price > 0}">
                <p>You have disappointed us. = (</p>
            </c:when>
            <c:otherwise>
                <p>You forgot to buy something from us.</p>
            </c:otherwise>
        </c:choose>
        <br>
        <button type="submit" class="btn btn-primary" name="command" value="home_page">Home page</button>
        &nbsp&nbsp
        <button type="submit" class="btn btn-danger" name="command" value="sign-in">Escape</button>
    </div>
</form>
</body>
</html>