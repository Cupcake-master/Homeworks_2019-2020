package ru.bulat.servlets;

import org.apache.commons.codec.digest.DigestUtils;
import ru.bulat.CSVFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static String help = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/jsp/Registration.jsp").forward(request,response);
        HttpSession session = request.getSession();
        session.setAttribute("help", help);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String email = request.getParameter("e-mail");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re-password");
        String phone = request.getParameter("phone");
        String dateOfBirth = request.getParameter("date of birth");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String aboutMyself = request.getParameter("about myself");

        dataChecking(email, password, rePassword, phone, dateOfBirth, gender, country, aboutMyself, resp, request, help);
    }

    private static void dataChecking(String email, String password, String rePassword, String phone, String dateOfBirth,
                                     String gender, String country, String aboutMyself, HttpServletResponse response,
                                     HttpServletRequest request, String help) throws ServletException, IOException {
        Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherEmail = patternEmail.matcher(email);
        help+="Conditions not met: ";
        if (!matcherEmail.matches()) help+="Email is entered incorrectly, ";
        if (!password.equals(rePassword)) help+="Passwords do not match, ";
        if (!(password.length() > 6 && password.length() < 20)) help+="Password must be between 6 and 20 characters, ";
        Pattern patternPhone = Pattern.compile("\\+\\d(-\\d{3}){2}-\\d{4}");
        Matcher matcherPhone = patternPhone.matcher(phone);
        if (!matcherPhone.matches()) help+= "Phone entered incorrectly, ";
        Pattern patternDate = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
        Matcher matcherDate = patternDate.matcher(dateOfBirth);
        if (!matcherDate.matches()) help+= "Date entered is incorrect, ";
        if (aboutMyself.length() > 1000) help+="Too much is written in aboutMyself, ";
        if (help.equals("Conditions not met: ")){
            response.sendRedirect(request.getContextPath() + "/newMain");
            writeToCSVFile(email, password, phone, dateOfBirth, gender, country, aboutMyself, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/registration");
            HttpSession session = request.getSession();
            session.setAttribute("help", help);
        }
    }

    private static void writeToCSVFile(String email, String password, String phone, String dateOfBirth, String gender,
                                       String country, String aboutMyself, HttpServletResponse resp){
        String hashedPassword = DigestUtils.md5Hex(password);
        String hashedPhone = DigestUtils.md5Hex(phone);
        CSVFile.writeToCSVFile(email, hashedPassword, hashedPhone, dateOfBirth, gender, country, aboutMyself);
    }
}
