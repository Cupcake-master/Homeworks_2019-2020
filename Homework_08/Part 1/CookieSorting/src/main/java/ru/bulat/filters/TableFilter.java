package ru.bulat.filters;

import ru.bulat.servlets.*;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TableFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            String name = cookies[i].getName();
//            String value = cookies[i].getValue();
//            if (name.equals("sort") && value.equals("name")){
//                TableServlet.sortName(request, response);
//            }
//            if (name.equals("sort") && value.equals("country"))
//                TableServlet.sortCountry(request, response);
//        }
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy!");
    }
}
