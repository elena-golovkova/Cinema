<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tickets</title>

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

    <p><c:out value="${session.hall.name}"></c:out> hall</p>
    <c:out value="${session.hall.columnCount}"></c:out> column
    <c:out value="${session.hall.rowCount}"></c:out> row
<form name="buy" method="post" action="/purchase">
    <table border="1" cellpadding="3">

        <c:forEach var = "i" begin="1" end="${session.hall.rowCount}">
            <tr>
                <c:forEach var = "j" begin="1" end="${session.hall.columnCount}">
                    <%--<td>  row <c:out value="${i}"/> place <c:out value="${j}"/></td>--%>
                    <td>  <input id="checkboxid" type="checkbox" name="ticket${i}${j}" />
                        <label for="checkboxid">row <c:out value="${i}"/> place <c:out value="${j}"></c:out></label>
                        </td>

                </c:forEach>
            </tr>
        </c:forEach>

    </table>
    <input type="hidden"  name="sessionid" value="${session.id}"/>
    <p><input type="submit" value="Buy ticket">
    </form>

</c:if>
<c:if test="${empty session}">
    <h2> There is no such session</h2>
</c:if>

</body>
</html>