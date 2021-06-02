<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<c:forEach items="${accidents}" var="accident">
    <c:out value="${accident.name}"/></br>
</c:forEach>
</body>--%>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container ">



    <table class="table table-striped">
        <thead>
        <tr>
            <td>ID</td>
            <td>TYPE</td>
            <td>RULES</td>
            <td>NAMES</td>
            <td>TEXT</td>
            <td>ADDRESS</td>
            <td>Действия</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="accident" items="${accidents}">
            <tr>
                <td>${accident.id}</td>
                <td>${accident.type.name}</td>
                <td>
                    <ul>
                        <c:forEach var="rule" items="${accident.rules}">
                            <li> ${rule.name}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>${accident.name}</td>
                <td>${accident.text}</td>
                <td>${accident.address}</td>
                <td>
                    <a href="<c:url value='/update?id=${accident.id}'/>">Редактировать инцидент</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul>
        <li><a href="<c:url value='/create'/>">Добавить инцидент</a></li>
    </ul>
</div>
</body>
</html>
