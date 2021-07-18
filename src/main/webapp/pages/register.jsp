<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register page</title>
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
        <h2>Register form</h2>
        <p>Please, enter the credentials of the new user</p>
        <form method="post" class="needs-validation" novalidate>
            <div class="form-group">
                <input type="text" class="form-control w-50" id="name"
                       placeholder="Enter name" name="name" value="${name}" required>
                <div class="invalid-feedback">Please fill out name field</div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control w-50" id="surname"
                       placeholder="Enter surname" name="surname" value="${surname}" required>
                <div class="invalid-feedback">Please fill out surname field</div>
            </div>
            <div class="form-group">
                <input type="date" class="form-control w-50" id="date_of_birth"
                       placeholder="Enter date of birth" name="date_of_birth" value="${date_of_birth}" required>
                <div class="invalid-feedback">Please fill out data field</div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control w-50" id="email"
                       placeholder="Enter email/login" name="email" value="${email}" required>
                <div class="invalid-feedback">Please fill out email field</div>
            </div>
            <div class="form-group">
                <input type="password" class="form-control w-50" id="password"
                       placeholder="Enter password" name="password" required>
                <div class="invalid-feedback">Please fill out password field</div>
            </div>
            <div class="form-group">
                <input type="password" class="form-control w-50" id="second_password"
                       placeholder="Enter your password again" name="second_password" required>
                <div class="invalid-feedback">Please fill out password field</div>
            </div>
            <button type="submit" class="btn btn-primary w-50" formaction="/sonyshop/signin">Register</button>
            <p style="color: red">${info}</p>
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
