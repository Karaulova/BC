<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Игра</title>
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
<body class="bg-light">
<div class="container">

    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="text-muted" href="rating">Рейтинг</a>
            </div>

            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" href="#">Быки и коровы</a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <a class="btn btn-sm btn-outline-secondary" href="logOut">Выйти</a>
            </div>
        </div>
    </header>
    <div class="container">

        <div class="py-5 text-center">
            <h2>Правила</h2>
            <p class="lead">Компьютер загадывает 4-х значное число. Цифры загаданного числа не
                повторяются. Задача пользователя угадать загаданное число. У
                пользователя неограниченное число попыток. В каждую попытку
                пользователь дает компьютеру свой вариант числа. Компьютер сообщает
                сколько цифр точно угадано (бык) и сколько цифр угадано без учета
                позиции (корова). По ответу компьютера пользователь должен за
                несколько ходов угадать число.</p>
        </div>


        <div class="row">
            <div class="col-md-4 order-md-2 mb-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Попытки</span>
                    <span class="badge badge-secondary badge-pill"><c:out value="${countTry}"/></span>
                </h4>
                <table id="try" class="display" style="width:100%">
                    <thead>
                    <tr>
                        <th>Число</th>
                        <th>Быки</th>
                        <th>Коровы</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="checkValue" items="${listCheckValue}">
                        <tr>
                            <td><c:out value="${checkValue.enterValue}"/></td>
                            <td><c:out value="${checkValue.bulls}"/></td>
                            <td><c:out value="${checkValue.cows}"/></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Игровое поле</h4>
                <form class="needs-validation" novalidate action="checkGame" method="post">

                    <div class="col-md-12 mb-3">
                        <label for="enterValue">Введите число: </label>
                        <input type="number" class="form-control" id="enterValue" placeholder="" value="" required=""
                               name="enterValue" maxlength="4" minlength="4" size="4" pattern="^[0-9]{4}">
                        <div class="row">
                            <div class="col-md-4 ">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="7" name="btn7"
                                       onclick="onclickBtn('btn7')">
                            </div>
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="8" name="btn8"
                                       onclick="onclickBtn('btn8')">
                            </div>
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="9" name="btn9"
                                       onclick="onclickBtn('btn9')">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="4" name="btn4"
                                       onclick="onclickBtn('btn4')">
                            </div>
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="5" name="btn5"
                                       onclick="onclickBtn('btn5')">
                            </div>
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="6" name="btn6"
                                       onclick="onclickBtn('btn6')">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="1" name="btn1"
                                       onclick="onclickBtn('btn1')">
                            </div>
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="2" name="btn2"
                                       onclick="onclickBtn('btn2')">
                            </div>
                            <div class="col-md-4">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="3" name="btn3"
                                       onclick="onclickBtn('btn3')">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <input type="button" class="btn btn-secondary my-2 btn-block" value="0" name="btn0"
                                       onclick="onclickBtn('btn0')">
                            </div>

                        </div>
                    </div>

                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Проверить</button>
                </form>
            </div>
        </div>

    </div>
</div>
<script>
    function onclickBtn(text) {
        //var num = document.getElementById('enterValue');

        switch (text) {
            case 'btn0': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "0";
                break;
            }

            case 'btn1': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "1";
                break;
            }

            case 'btn2': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "2";
                break;
            }

            case 'btn3': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "3";
                break;
            }

            case 'btn4': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "4";
                break;
            }

            case 'btn5': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "5";
                break;
            }

            case 'btn6': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "6";
                break;
            }

            case 'btn7': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "7";
                break;
            }

            case 'btn8': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "8";
                break;
            }

            case 'btn9': {
                document.getElementById('enterValue').value = document.getElementById('enterValue').value + "9";
                break;
            }

            default: {
                break;
            }


        }
    }
</script>

</body>
</html>
