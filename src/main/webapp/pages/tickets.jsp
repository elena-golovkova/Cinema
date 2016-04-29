<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tickets</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/color.css"/>
    <style type="text/css">
        label.ex {
            color: darkgrey;
        }
    </style>

</head>
<c:if test="${not empty sessionScope.user}">
    <div align="right">
        Hello <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"></c:out>
        <a href="/logout">Logout</a>
    </div>
    <hr/>
</c:if>

<c:if test="${not empty session}">
    <h2><c:out value="${session.movie.title}"></c:out></h2>

    <p><fmt:formatDate value="${session.date}" type="both" dateStyle="short" timeStyle="short"/></p>

    <p><c:out value="${session.hall.name}"></c:out> hall</p>
    <span class="error">${messages.errorticket}</span>

    <form name="buy" method="post" action="${pageContext.request.contextPath}/purchase">

        <table border="1" cellpadding="3">

            <c:forEach var="i" begin="1" end="${session.hall.rowCount}">
                <tr>
                    <c:forEach var="j" begin="1" end="${session.hall.columnCount}">
                        <c:forEach items="${sessionScope.purchasedTickets}" var="ticket">
                            <c:if test="${ticket.row == i}">
                                <c:if test="${ticket.column == j}">
                                    <c:set var="flag" value="1"/>
                                </c:if>
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${flag == 1}">
                                <td><input id="ticket${i}${j}" type="checkbox" name="ticket${i}${j}" disabled/>
                                <label class="ex" for="ticket${i}${j}">row <c:out value="${i}"/> place <c:out
                                        value="${j}"></c:out> </label>
                                <c:set var="flag" value="0"/>
                            </c:when>
                            <c:otherwise>
                                <td><input id="ticket${i}${j}" type="checkbox" name="ticket${i}${j}"/>
                                    <label for="ticket${i}${j}">row <c:out value="${i}"/> place <c:out
                                            value="${j}"></c:out> </label>
                                </td>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
        <input type="hidden" name="sessionid" value="${session.id}"/>

        <p><input type="submit" value="Buy ticket"><br/>

    </form>

</c:if>
<c:if test="${empty session}">
    <h2> There is no such session</h2>
</c:if>

</body>
</html>