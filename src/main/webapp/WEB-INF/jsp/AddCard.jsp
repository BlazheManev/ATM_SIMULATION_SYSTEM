<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>New card</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="">Fictional Bank</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item ">
                <a class="nav-link" href="register">Register New User</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="Users">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-5">Card Form</h1>
    <form action="/AddCard" method="POST">
        <div class="form-group">
            <label for="cardNumber">Card Number</label>
            <input type="number" class="form-control" id="cardNumber" name="cardNumber" placeholder="Enter card number"
                   minlength="16" required>
        </div>
        <div class="form-group">
            <label for="pin">Pin</label>
            <input type="number" class="form-control" id="pin" name="pin" placeholder="Enter pin" required>
        </div>
        <input type="hidden" id="UserId" name="UserId" value="<%= request.getParameter("UserId") %>">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>
</html>
