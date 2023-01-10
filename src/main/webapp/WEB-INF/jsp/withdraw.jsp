<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
</body>
</html>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Fictional Bank</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Fictional Bank</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="cards">My Cards</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="cards">My Transactions</a>
        </li>
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-5">
    <h1>Our bank allows you to go negative on your account up to minus 400</h1>
    <form action="/withdraw" method="POST">
        <div class="form-group">
            <label for="amount">Enter the amount:</label><br>
            <input type="number" class="form-control" id="amount" name="amount" step="0.01" min="1" required>
        </div>
        <div class="form-group">
            <label for="transactionType">Select the transaction type:</label><br>
            <select class="form-control" id="transactionType" name="transactionType" required>
                <option value="withdraw">Withdraw</option>
                <option value="deposit">Deposit</option>
            </select>
        </div>
        <%
            String cardNumber = request.getParameter("cardNumber");
        %>
        <input type="hidden" name="cardNumber" value="<%= cardNumber %>">
        <input type="submit" class="btn btn-primary" value="Submit">
    </form>

    </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrC" />
</body>
</html>
