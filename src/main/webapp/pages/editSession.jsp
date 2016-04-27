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

<c:if test="${not empty sessionScope.allSessions}" >
  <h2><c:out value="${sessionScope.allSessions[0].movie.title}"></c:out></h2>

  <form name="test" method="post" action="/tickets">

    <c:forEach items="${sessionScope.allSessions}" var="session">
      <input id="${session.id}" type="radio" name="sessionid" value="${session.id}">
      <label for="${session.id}"> <fmt:formatDate value="${session.date}" type="both" dateStyle="short"
                                                  timeStyle="short"/>,
        <c:out value="${session.hall.name}"></c:out> hall</label><br/>
    </c:forEach>

    <p><input type="submit" value="Choose your tickets">
  </form>

</c:if>

</body>
</html>

