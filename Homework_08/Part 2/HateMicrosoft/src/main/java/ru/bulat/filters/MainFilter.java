package ru.bulat.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/main")
public class MainFilter implements Filter {
    private static String bad = "Please do not access from this terrible browser. Download chrome!";
    private static String good = "You're good!";

    @Override
    public void init(FilterConfig filterConfig){
        System.out.println("LogFilter init!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userAgent = request.getHeader("User-Agent");
        Pattern pattern = Pattern.compile(".+Edge.+");
        Matcher matcher = pattern.matcher(userAgent);
        HttpSession session = request.getSession();
        if (matcher.matches())session.setAttribute("something", bad);
        else session.setAttribute("something", good);
        System.out.println(userAgent);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy!");
    }
}
