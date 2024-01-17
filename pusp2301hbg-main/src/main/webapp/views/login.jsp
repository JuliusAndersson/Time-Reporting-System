<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        h2 {
            font-size: 2em;
            text-align: center;
            margin-top: 50px;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            display: block;
            font-size: 1.2em;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 100%;
            height: 50px;
            margin-bottom: 30px;
            background: rgb(214, 212, 212);
            font-size: 1.2em;
            outline: none;
            border: none;
            padding: 10px;
        }  

        input[type="password"] {
            width: 100%;
            height: 50px;
            margin-bottom: 30px;
            background: rgb(214, 212, 212);
            font-size: 1.2em;
            outline: none;
            border: none;
            padding: 10px;
        }

        input[type="submit"] {
            width: 100%;
            height: 55px;
            border-bottom: 40px;
            background-color: #0f4e6e;
            border: none;
            font-size: 1.5em;
            color: #fff;
            cursor: pointer;
        }

        section {
            min-height: 100vh;
            width: 100%;
            background: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .user-input {
            position: relative;
            margin-bottom: 30px;
            width: 310px;
            border-bottom: 2px solid #0f4e6e;
        }

        .user-input ion-icon {
            position: absolute;
            right: 8px;
            font-size: 1.2em;
            top: 20px;
            color: #0f4e6e;
        }

        .alert {
            background-color: #f2dede;
            border-color: #ebccd1;
            color: #a94442;
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            text-align: center;
        }
    </style>

    <title>Login</title>
</head>
<body>
<section>

    <form action="login" style="width: 12%">
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
        <%
            }
        %>
        <h2>Login</h2>
        <div class="user-input">
            <ion-icon name="person-outline"></ion-icon>
            <input type="text" placeholder="Username" id="username" name="username" style=" text-align:left;"
                   size="10"></input>
        </div>

        <div class="user-input">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <input type="password" placeholder="Password" id="password" name="password" style=" text-align:left;"
                   size="10"></input>
        </div>
        <input type="submit" value="Login"/>
    </form>
</section>

<script
        type="module"
        src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
></script>
<script
        nomodule
        src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
></script>
</body>
</html>

