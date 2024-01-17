<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>time report system</title>

    <link rel="stylesheet" type="text/css" href=""/>

</head>
<body>
<nav class="navbar">
    <div class="navbar-logo">
        <h1>Time<span>Report</span>System</h1>
    </div>
    <ul class="navbar-links">
        <li><a href="/">Home</a></li>
        <% if (session.getAttribute("name") == null) { %>
        <li><a href="/login">Login</a></li>
        <% } else { %>
        <li><a href="/logout">Logout</a></li>
        <% } %>
    </ul>  
</nav>
