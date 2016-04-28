<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movie sessions</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/color.css"/>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <div align="right">
        Hello <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"></c:out>
        <a href="/logout">Logout</a>
        <hr/>
    </div>
</c:if>
<h2><c:out value="${sessionScope.movie.title}"></c:out></h2>

<h3>Create new session:</h3>

<form id="createform" method="post" action="${pageContext.request.contextPath}/createSession">
    <table>
        <tr>
            <td>
                Date:<br/>
                <input style="height:25px;" type="date" name="date" value="${fn:escapeXml(param.date)}"/><br/></td>
            <td>Time:<br/>
                <input style="height:25px;" type="time" name="time" value="${fn:escapeXml(param.date)}"/><br/></td>
            <td> Hall:<br/>
                <select style="height:25px;" name="hallId">
                    <c:forEach items="${sessionScope.halls}" var="hall">
                        <option value="${hall.id}"><c:out value="${hall.name}"></c:out></option>
                    </c:forEach>
                </select></td>
        </tr>
    </table>
    <input type="hidden" name="movieId" value="${movie.id}"/>
    <input type="submit" value="Create">
</form>
<span class="error">${messages.date}</span>
<span class="error">${messages.time}</span>
<span class="error">${messages.hallId}</span>


<c:if test="${not empty sessionScope.allSessions}">
    <h3>Delete existing session:</h3>
    <hr/>
    <form name="deleteSession" method="post" action="/deleteSession">

        <c:forEach items="${sessionScope.allSessions}" var="session">
            <input id="${session.id}" type="radio" name="sessionid" value="${session.id}">
            <label for="${session.id}"> <fmt:formatDate value="${session.date}" type="both" dateStyle="short"
                                                        timeStyle="short"/>,
                <c:out value="${session.hall.name}"></c:out> hall</label><br/>
        </c:forEach>

        <p><input type="submit" value="Delete Session">
    </form>

</c:if>

</body>
</html>

