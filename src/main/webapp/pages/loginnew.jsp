<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 19.04.2016
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
  <label for="login">Login</label>
  <input id="login" name="login" value="${fn:escapeXml(param.login)}">
  <span class="error">${messages.login}</span>
  <br />
  <label for="pass">Password</label>
  <input id="pass" name="pass" value="${fn:escapeXml(param.pass)}">
  <span class="error">${messages.pass}</span>
  <br />
  <input type="submit">
  <span class="success">${messages.success}</span>
</form>
</body>
</html>
