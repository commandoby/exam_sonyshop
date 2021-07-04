<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="col-md-8 offset-md-4">
        <h2>Login form with validation</h2>
        <p>Please, enter your credentials</p>
        <form method="post" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="username">Email:</label>
                <input type="text" class="form-control w-50" id="email"
                       placeholder="Enter email" name="email" value="${email}" required>
                <div class="invalid-feedback">Please fill out email field</div>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control w-50" id="password"
                       placeholder="Enter password" name="password" required>
                <div class="invalid-feedback">Please fill out password field</div>
            </div>
            <p style="color: red">${info}</p>
            <button type="submit" class="btn btn-success w-50" name="command" value="home_page">Login</button>
        </form>
        <br>
        <form method="post">
            <button type="submit" class="btn btn-primary w-25" name="command" value="register">Register</button>
            <button type="submit" class="btn btn-info w-25" name="command" value="home_page">Shop</button>
        </form>
    </div>
</div>
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
