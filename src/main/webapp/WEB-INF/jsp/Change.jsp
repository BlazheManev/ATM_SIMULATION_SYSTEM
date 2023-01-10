<%@ page import="com.example.ATM_SIMULATION_SYSTEM.model.Cards" %>
<%@ page import="java.util.List" %>
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
            <li class="nav-item ">
                <a class="nav-link" href="Users">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-5">

    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Card Number</th>
            <th> Money </th>
            <th>Change Pin</th>
        </tr>
        </thead>

        <%
            List<Cards> cardList = (List<Cards>) request.getAttribute("cardsList");
            for (Cards c : cardList) {
        %>
        <tr>
            <td><%= c.getCardNumber()%></td>
            <td><%= c.getMoney()%></td>
            <td>
                <form action="/changed" method="POST">
                    <div class="form-group">
                        <label for="oldPin">Card Number</label>
                        <input type="number" class="form-control" id="oldPin" name="oldPin" placeholder="Enter old Pin"
                               minlength="4"  maxlength="4" required>
                    </div>
                    <div class="form-group">
                        <label for="newPin">Pin</label>
                        <input type="number" class="form-control" id="newPin" name="newPin" placeholder="Enter new pin"  minlength="4"  maxlength="4" required>
                    </div>
                    <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
                    <input type="hidden" id="CardNumber" name="CardNumber" value="<%= c.getCardNumber()%>">

                    <button type="submit" class="btn btn-primary">Change PIN</button>
                </form>
                <a href="DeleteCard?userId=<%=c.getId()%>" class="btn btn-danger">Delete Card</a>

            </td>
        </tr>

        <%
            }
        %>
    </table>

</div>>


</body>
</html>
