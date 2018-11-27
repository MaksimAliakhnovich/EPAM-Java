package edu.epam.selectioncommittee.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        String url = httpServletRequest.getRequestURI();
        if (!(url.compareTo("/") == 0
                || url.compareTo("/login") == 0
                || url.compareTo("/static/css/login.css") == 0)) {
            Boolean result = (Boolean) httpServletRequest.getSession().getAttribute("auth");
            if (!result) {
                req.getServletContext().getRequestDispatcher("/login.html")
                        .forward(httpServletRequest, httpServletResponse);
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
