package org.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.attribute.RequestAttributes;
import org.example.security.Authentication;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@WebFilter("/*")

//Filter
public class AuthFilter extends HttpFilter {
    private final Map<String, String> users = Map.of(
            "vasya", "secret",
            "petya", "secret"
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String login = req.getHeader("X-Login");
        String password = req.getHeader("X-Password");
        //TODO: credential
        //-> log.trace
        //-> log.debug
        log.debug("login: {}, password: {}", login, password);
        //TODO: 1.DB (map)
        // 2.Hash & Compore
        // 3.User
        if (login == null) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;//чтобы не попало в chain.doFilter
        }
        if (password == null) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }
        if (!Objects.equals(users.get(login), password)) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }
        final Authentication authentication = new Authentication(login);

        req.setAttribute(RequestAttributes.AUTHENTICATION_ATTR, authentication);

        chain.doFilter(req, res);

    }
}
