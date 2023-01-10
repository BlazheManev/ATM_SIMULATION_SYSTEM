<%@ page import="com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
</body>
</html>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Fictional Bank</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Fictional Bank</a>
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

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RegisteredUsers> listUsers = (List<RegisteredUsers>) request.getAttribute("listUsers");
            for (RegisteredUsers c : listUsers) {
        %>
        <tr>
            <td><%= c.getName()%>
            </td>
            <td><%= c.getSurname()%>
            </td>
            <td><%= c.getEmail()%>
            </td>
            <td><%= c.getAge()%>
            </td>
            <td>
                <a href="AddCard?UserId=<%=c.getId()%>" class="btn btn-primary">Add Card</a>
                <a href="DeleteUser?userId=<%=c.getId()%>" class="btn btn-danger">Delete User</a>
                <a href="Change?userId=<%=c.getId()%>" class="btn btn-danger">Modify Cards</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrC"/>
</body>
</html>
