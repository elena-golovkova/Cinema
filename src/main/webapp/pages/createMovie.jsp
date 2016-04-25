<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/color.css"/>
  <title>Movies</title>
</head>
<c:if test="${not empty sessionScope.user}">
  <div align="right">
    Hello <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"></c:out>
    <a href="/logout">Logout</a>
  </div>

</c:if>
<h2>Create new movie</h2>
<form id = "createform" method="post" action="${pageContext.request.contextPath}/createMovie">
  Movie title:<br/>
  <input type="text" name="title" style="width:500px; height:20px;" value="${fn:escapeXml(param.title)}" /><br/>
  Duration (min):<br/>
  <input type="text" name="duration" style="width:500px; height:20px;"  value="${fn:escapeXml(param.duration)}" /><br/>
  Description:<br/>
  <textarea name="description" style="width:500px; height:100px;resize: none;" value="${fn:escapeXml(param.description)}"></textarea><br/>
  <input type="submit" value="Create">
</form>
<span class="error">${messages.title}</span>
<span class="error">${messages.duration}</span>
<span class="error">${messages.description}</span>
<span class="error">${messages.movieExist}</span>


</body>
</html>