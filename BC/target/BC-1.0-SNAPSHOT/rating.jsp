<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Рейтинг</title>
    <link href="css/style.css" rel="stylesheet">
    <link href="https://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/4.0/examples/blog/blog.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#tableRating').DataTable({
                "paging": false,
                "ordering": false,
                "info": false
            });
        });
    </script>
</head>
<body>
<div class="container">

    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="text-muted" href="newGame">Новая игра</a>
            </div>

            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" href="#">Быки и коровы</a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <a class="btn btn-sm btn-outline-secondary" href="logOut">Выйти</a>
            </div>
        </div>
    </header>
    <c:if test="${win != null}">
        <script>
            $(function () {
                var message = "Ура!!!! Мои поздравления, вы победили за " +<c:out value="${countTry}"/>;
                alert(message);
            });
        </script>
    </c:if>
    <table id="tableRating" class="display" style="width:100%">
        <thead>
        <tr>
            <th>Пользователь</th>
            <th>Среднее число шагов</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ratingValue" items="${listRatingValue}">
            <tr>
                <td><c:out value="${ratingValue.user.login}"/></td>
                <td><c:out value="${ratingValue.steps}"/></td>

            </tr>
        </c:forEach>

        </tbody>
    </table>

</div>

</body>
</html>
