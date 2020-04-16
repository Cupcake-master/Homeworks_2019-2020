package ru.bulat.servlets;


import ru.bulat.data.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/entrance")
public class ServletForLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Registration") != null) {
            request.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        }
        request.getServletContext().getRequestDispatcher("/jsp/Entrance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("e-mail");
        String password = request.getParameter("password");
        int check = DatabaseConnection.userVerification(email, password);
        if (check != -1) {
            HttpSession session = request.getSession();
            session.setAttribute("id", check);
            response.sendRedirect(request.getContextPath() + "/newMain");
        }else{
            response.sendRedirect(request.getContextPath() + "/entrance");
        }
    }
}
