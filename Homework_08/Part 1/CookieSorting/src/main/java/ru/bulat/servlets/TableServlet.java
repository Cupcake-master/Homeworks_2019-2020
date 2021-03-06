package ru.bulat.servlets;

import ru.bulat.comparators.*;
import ru.bulat.Line;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

@WebServlet("/table")
public class TableServlet extends HttpServlet {
    private static int check = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if (name.equals("sort") && value.equals("name")) {
                TableServlet.sortName(request, response);
            }
            if (name.equals("sort") && value.equals("country"))
                TableServlet.sortCountry(request, response);
        }
        LineIdComparator comparator = new LineIdComparator();
        TreeSet<Line> lines = new TreeSet<>(comparator);
        workWithComparator(lines, request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("sortName") != null){
            sortName(request, response);
        }
        else if (request.getParameter("sortCountry") != null){
            sortCountry(request, response);
        }
        else if (request.getParameter("saveCookies") != null) {
            if (check == 1) sortName(request, response);
            if (check == 2) sortCountry(request, response);
        }
    }

    private static void sortName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LineNameComparator comparator = new LineNameComparator();
        TreeSet<Line> treeSet = new TreeSet<>(comparator);
        if (check == 1){
            Cookie cookie = new Cookie("sort", "name");
            response.addCookie(cookie);
        }
        check = 1;
        workWithComparator(treeSet, request, response);
    }

    private static void sortCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LineCountryComparator comparator = new LineCountryComparator();
        TreeSet<Line> treeSet = new TreeSet<>(comparator);
        if (check == 2){
            Cookie cookie = new Cookie("sort", "country");
            response.addCookie(cookie);
        }
        check = 2;
        workWithComparator(treeSet, request, response);
    }

    private static void workWithComparator(TreeSet<Line> lines, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lines.add(new Line(1, "Lee", "Indonesia"));
        lines.add(new Line(2, "Doria", "Argentina"));
        lines.add(new Line(3, "Tamqrah", "China"));
        lines.add(new Line(4, "Ailbert", "Chile"));
        lines.add(new Line(5, "Darya", "Somalia"));
        lines.add(new Line(6, "Adelle", "Malawi"));
        lines.add(new Line(7, "Gillie", "Cameroon"));
        lines.add(new Line(8, "Matilde", "Bangladesh"));
        request.setAttribute("linesForTable", lines);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/table.jsp");
        dispatcher.forward(request, response);
    }
}
