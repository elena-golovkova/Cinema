<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movie sessions</title>

</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <div align="right">
        Hello <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"></c:out>
        <a href="/logout">Logout</a>
        <hr/>
    </div>
</c:if>


<h2><c:out value="${sessionScope.sessions[0].movie.title}"></c:out></h2>
<c:if test="${not empty sessions}">
    <c:forEach items="${sessionScope.sessions}" var="session">
        <p><fmt:formatDate value="${session.date}" type="both" dateStyle="short" timeStyle="short"/>
            hall: <c:out value="${session.hall.name}"></c:out></p>

    </c:forEach>
</c:if>
<c:if test="${empty sessions}">
    <h2> No session available for this movie</h2>
    <br/>
    <a href="/movie">Please choose another one</a></td>
</c:if>
</body>
</html>


