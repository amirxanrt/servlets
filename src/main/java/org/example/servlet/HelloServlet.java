package org.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

  //  @Override
 //   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
   //     res.getWriter().write("Hello");


   // }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello");
    }
}
