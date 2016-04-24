<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
          <title>Login</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/color.css"/>
      </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/login">
              <center>
              <table border="1" width="30%" cellpadding="3">
                  <thead>
                    <tr>
                          <th colspan="2">Login Here</th>
                      </tr>
                </thead>
                  <tbody>
                    <tr>
                          <td>Login</td>
                          <td><input type="text" name="login" value="${fn:escapeXml(param.login)}" /></td>
                      </tr>
                    <tr>
                          <td>Password</td>
                          <td><input type="password" name="pass" value="${fn:escapeXml(param.pass)}" /></td>

                      </tr>
                    <tr>
                          <th colspan="2" align="centre"> <input type="submit" value="Login" /></th>
                          
                      </tr>
                    <tr>
                          <td colspan="2">Not Registered Yet? <a href="/registration">Register Here</a></td>
                      </tr>
                </tbody>

              </table>
  <br/>
  <span class="error">${messages.login}</span>
  <span class="error">${messages.pass}</span>
  <span class="error">${messages.find}</span>
  <span class="success"><c:out value="${success}"></c:out></span>

              </center>
          </form>

    </body>
</html>