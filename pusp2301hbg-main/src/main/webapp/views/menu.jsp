<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<link rel="stylesheet" type="text/css" href="/css/menu.css"/>
</head>
<body>
<div id="container">
    <div class="sidenav">
        <h2><a href="/" style="text-decoration: none;">MENU</a></h2>

        <ul>

            <li><a href="/"> HOME </a></li>
            <li><a href="/timereportnew"> REPORT TIME </a></li>
            <li><a href="/update-report"> UPDATE REPORT </a></li>
            <li><a href="/PGHome"> PG </a></li>
            <li><a href="/stats"> STATISTICS </a></li>
            <li><a href="admin"> ADMIN </a></li>
            <% if (session.getAttribute("name") == null) { %>
            <li><a href="/login">LOG IN</a></li>
            <% } else { %>
            <li><a href="/logout">LOG OUT</a></li>
            <% } %>
        </ul>
    </div>

