<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/color.css"/>
    <title>Movies</title>

</head>
<c:if test="${not empty sessionScope.user}">
    <div align="right">
        Hello <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"></c:out>
        <a href="/logout">Logout</a>
    </div>
    <hr/>
</c:if>
<span class="success">${messages.createsuccess}</span>

<form id="editMovie" method="get" action="${pageContext.request.contextPath}/createMovie">
    <input type="submit" value="Create Movie"/>
</form>
<c:if test="${not empty movies}">
    <span class="error">${messages.wrongId}</span>
    <span class="error">${messages.wrongpage}</span>
    <span class="success">${messages.wrongpage}</span>
    <span class="success">${messages.createSession}</span>
    <span class="success">${sessionDelete}</span><br/>
    <table border="1" cellpadding="3">
        <c:forEach items="${sessionScope.movies}" var="movie">

            <tr>
                <td width="25%"><c:out value="${movie.title}"></c:out><br/>
                    Duration: <c:out value="${movie.duration}"></c:out> mins
                </td>
                <td width="50%"><c:out value="${movie.description}"></c:out></td>
                <td idth="25%">
                    <form method="post" action="${pageContext.request.contextPath}/deleteMovie">
                        <input type="hidden" name="movieId" value="${movie.id}"/>
                        <input type="submit" value="Delete movie"><br/>

                </form>
                    <form method="post" action="${pageContext.request.contextPath}/editSessions">
                        <input type="hidden" name="movieId" value="${movie.id}"/>
                        <input type="submit" value="Edit sessions"><br/>

                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>

</c:if>

<c:if test="${empty movies}">
    <h2> No movie available</h2>
</c:if>

</body>
</html>