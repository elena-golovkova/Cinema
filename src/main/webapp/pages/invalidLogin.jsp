<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>Login</title>
      </head>
    <body>
<p align="center" style="color:red"> <%=request.getAttribute("noSuchUser")%>
        <form method="post" action="/login">
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
                          <td><input type="text" name="login" value="" /></td>
                      </tr>
                    <tr>
                          <td>Password</td>
                          <td><input type="password" name="pass" value="" /></td>
                      </tr>
                    <tr>
                          <th colspan="2" align="centre"> <input type="submit" value="Login" /></th>
                          
                      </tr>
                    <tr>
                          <td colspan="2">Not Registered Yet? <a href="/registration">Register Here</a></td>
                      </tr>
                </tbody>
              </table>
              </center>
          </form>

    </body>
</html>