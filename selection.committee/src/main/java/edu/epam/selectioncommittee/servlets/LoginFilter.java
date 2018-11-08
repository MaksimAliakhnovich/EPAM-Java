package main.java.edu.epam.selectioncommittee.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();
        if (!(url.compareTo("/") == 0 || url.compareTo("/login") == 0 || url.compareTo("/css/style.css") == 0)) {
            Boolean result = (Boolean) httpServletRequest.getSession().getAttribute("auth");
            if (!result) {
                request.getServletContext().getRequestDispatcher("/jsp/login.jsp")
                        .forward(httpServletRequest, httpServletResponse);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
