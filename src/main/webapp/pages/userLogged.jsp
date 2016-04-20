
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>User Logged Successfully </title>
</head>
<body>
<h1>User Logged Successfully </h1>
Hello <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"></c:out>
<a href="/logout">Logout</a>
</body>
</html>
