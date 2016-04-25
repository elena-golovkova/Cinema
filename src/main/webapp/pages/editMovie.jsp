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
<form id = "editMovie" method="get" action="${pageContext.request.contextPath}/createMovie">
  <input type="submit" value="Create Movie" />
</form>
<c:if test="${not empty movies}">
  <c:forEach items="${sessionScope.movies}" var="movie">
    <h2><c:out value="${movie.title}"></c:out></h2>

    <p> Duration: <c:out value="${movie.duration}"></c:out> mins</p>

    <p><c:out value="${movie.description}"></c:out></p>

    <a href="/movie-sessions?id=${movie.id}">Show sessions</a>
    <hr/>

  </c:forEach>
</c:if>

<c:if test="${empty movies}">
  <h2> No movie available</h2>
</c:if>

</body>
</html>