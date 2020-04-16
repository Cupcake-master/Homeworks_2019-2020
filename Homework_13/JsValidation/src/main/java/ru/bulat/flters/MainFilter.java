package ru.bulat.flters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/main","/newMain"}
)
public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
        System.out.println("Filter init!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null){
            servletRequest.getServletContext().getRequestDispatcher("/entrance").forward(request, response);
        }else {
            servletRequest.getServletContext().getRequestDispatcher("/newMain").forward(request, response);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy!");
    }
}
