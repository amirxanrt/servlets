package org.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.attribute.ContextAttributes;
import org.example.handler.WebHandler;

import java.io.IOException;
import java.util.Map;

@Slf4j
@WebServlet(value = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private Map<String, WebHandler> handlers;


    @Override
    public void init() throws ServletException {
        handlers = (Map<String, WebHandler>) getServletContext()
                .getAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR);
    }


    //  @Override
 //   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
   //     res.getWriter().write("Hello");


   // }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String path = req.getRequestURI().substring(
                req.getContextPath().length()
        );

       final WebHandler handler = handlers.get(path);
        if (handler == null) {
            resp.setStatus(404);
            resp.getWriter().write("Not Found");
            return;


        }
        handler.handle(req, resp);

    }



}
