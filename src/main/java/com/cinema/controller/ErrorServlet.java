package com.cinema.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error-handler")
public class ErrorServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Exception exception = (Exception)req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)req.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String)req.getAttribute("javax.servlet.error.request_uri");


        resp.setContentType("text/html");


        PrintWriter out = resp.getWriter();
        out.write("<html><head><title>Error</title></head><body>");
        if (statusCode != 500){
            out.write("<h3>Error</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
        } else {
            out.write("<h3>Error</h3>");
            out.write("<ul><li>Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + exception.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + exception.getMessage() + "</li>");
            out.write("</ul>");
        }
        out.write("</body></html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}