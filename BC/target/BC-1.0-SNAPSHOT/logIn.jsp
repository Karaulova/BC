<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Войти</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link href="http://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
            integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
            integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
            crossorigin="anonymous"></script>
</head>
<body class="text-center">
<c:if test="${user != null}">
<form class="form-signin" action="loged" method="post">
    </c:if>
    <c:if test="${user == null}">
    <form class="form-signin" action="sign" method="post">
        </c:if>

        <a class="navbar-brand" href="index.jsp">Быки и коровы </a>
        <label for="inputText" class="sr-only">Логин</label>
        <input type="text" id="inputText" class="form-control" placeholder="Логин" required="" autofocus="" name="login"
               maxlength="20" a>
        <label for="inputPassword" class="sr-only">Пароль</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required="" name="password"
               maxlength="20">
        <c:if test="${user != null}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </c:if>
        <c:if test="${user == null}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button>
        </c:if>
    </form>

</body>
</html>
