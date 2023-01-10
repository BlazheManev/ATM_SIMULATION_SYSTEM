<%@ page import="com.example.ATM_SIMULATION_SYSTEM.model.Cards" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Cards</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="homeUser">Fictional Bank</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="cards">My Cards</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="transactions">My Transactions</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/logout">logout</a>
            </li>
        </ul>
    </div>
</nav>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Check the Transactions of the Card</th>
        <th>Date Created</th>
        <th>Money</th>
        <th>Deposit or withdraw</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Cards> cardList = (List<Cards>) request.getAttribute("cardsList");
        for (Cards c : cardList) {
    %>
    <tr>
        <td>
            <a href="/transactionByCard?cardNumber=<%= c.getCardNumber()%>"
               class="btn btn-primary"><%= c.getCardNumber()%>
            </a>
        </td>
        <td><%= c.getDate_created()%>
        </td>
        <td><%= c.getMoney()%>
        </td>
        <td>
            <a href="/withdraw?cardNumber=<%= c.getCardNumber()%>" class="btn btn-primary"><%= c.getCardNumber()%>
            </a>
        </td>

    </tr>

    <%
        }
    %>
    </tbody>
</table>

</body>
</html>