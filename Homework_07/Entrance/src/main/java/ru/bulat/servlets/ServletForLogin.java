package ru.bulat.servlets;

import org.apache.commons.codec.digest.DigestUtils;
import ru.bulat.CSVFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entrance")
public class ServletForLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/Entrance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("e-mail");
        String password = request.getParameter("password");

        boolean check = CSVFile.userVerification(email, DigestUtils.md5Hex(password));
        if (check) request.getServletContext().getRequestDispatcher("/NewMain").forward(request, response);
        else response.sendRedirect(request.getContextPath() + "/entrance");
    }
}
