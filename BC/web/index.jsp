<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Стартовая страница</title>
    <link href="https://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/4.0/examples/blog/blog.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="text-muted" href="logIn">Войти</a>
            </div>
            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" href="#">Быки и коровы</a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <a class="btn btn-sm btn-outline-secondary" href="signUp">Регистрация</a>
            </div>
        </div>
    </header>

    <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
        <div class="col-md-7 px-0">
            <h1 class="display-4 font-italic">Быки и коровы </h1>
            <p class="lead my-3"> — логическая игра, в ходе которой за несколько попыток игроков должен определить, что
                задумал компьютер.</p>
            <p class="lead mb-0"><a href="signUp" class="text-white font-weight-bold">Присоединиться...</a></p>
        </div>
    </div>
</div>
</body>
</html>
