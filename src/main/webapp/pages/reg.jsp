<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
          <title>Registration</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/color.css"/>
      </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/registration">

              <center>
              <table border="1" width="30%" cellpadding="5">
                  <thead>
                    <tr>
                          <th colspan="2">Enter Information Here</th>
                      </tr>
                </thead>
                  <tbody>
                    <tr>
                          <td>First Name *</td>
                          <td><input type="text" name="fname"  value="${fn:escapeXml(param.fname)}" /></td>
                      </tr>
                    <tr>
                          <td>Last Name *</td>
                          <td><input type="text" name="lname" value="${fn:escapeXml(param.lname)}" /></td>
                      </tr>
                    <tr>
                          <td>Email</td>
                          <td><input type="text" name="email" value="${fn:escapeXml(param.email)}" /></td>
                      </tr>
                    <tr>
                          <td>Birthday</td>
                          <td><input type="date" name="date" value="" /></td>
                      </tr>
                    <tr>
                          <td>Login *</td>
                          <td><input type="text" name="login" value="${fn:escapeXml(param.login)}" /></td>
                      </tr>
                    <tr>
                          <td>Password *</td>
                          <td><input type="password" name="pass" value="${fn:escapeXml(param.pass)}" /></td>
                      </tr>
                    <tr>
                          <td><input type="submit" value="Submit" /></td>
                          <td><input type="reset" value="Reset" /></td>
                      </tr>
                    <tr>
                          <td colspan="2">Already registered? <a href="/login">Login Here</a></td>
                      </tr>
                </tbody>
              </table>
  <br/>
  <span class="error">${messages.fname}</span>
  <span class="error">${messages.lname}</span>
  <span class="error">${messages.login}</span>
  <span class="error">${messages.pass}</span>
  <span class="error">${messages.invalidLogin}</span>
  <span class="error">${messages.email}</span>
              </center>
          </form>
    </body>
</html>