package ru.bulat.servlets;

import ru.bulat.data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static List<String> emails = new ArrayList<>();
    private static Map<String, String> descriptionOfAllErrors = new HashMap<>();
    private static String error = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/jsp/Registration.jsp").forward(request,response);
        HttpSession session = request.getSession();
        session.setAttribute("help", error);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("e-mail");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re-password");
        String phone = request.getParameter("phone");
        String dateOfBirth = request.getParameter("date of birth");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String aboutMyself = request.getParameter("about myself");
        String[] groups = request.getParameterValues("groups");
        String newGroup = request.getParameter("newGroup");
        String checkRemember = request.getParameter("remember");

        fillingOutADescriptionOfAllErrors();

        emailChecking(email);
        passwordChecking(password, rePassword);
        phoneChecking(phone);
        dateOfBirthChecking(dateOfBirth);
        aboutMyselfChecking(aboutMyself);
        newGroupChecking(newGroup);

        workWithTheDatabase(email, password, phone, dateOfBirth, gender, country, aboutMyself, groups, newGroup, checkRemember, request, response);
    }

    private static void fillingOutADescriptionOfAllErrors(){
        descriptionOfAllErrors.put("Registered email", "This email is already registered, ");
        descriptionOfAllErrors.put("Incorrectly email", "Email is entered incorrectly, ");
        descriptionOfAllErrors.put("Mismatched passwords", "Passwords do not match, ");
        descriptionOfAllErrors.put("Invalid password", "Password must be between 6 and 20 characters, ");
        descriptionOfAllErrors.put("Incorrectly phone", "Phone entered incorrectly, ");
        descriptionOfAllErrors.put("Incorrectly date", "Date entered is incorrect, ");
        descriptionOfAllErrors.put("Incorrectly aboutMyself", "Too much is written in aboutMyself, ");
        descriptionOfAllErrors.put("Incorrectly newGroup", "newGroup must be between 6 and 20 characters, ");
    }

    private static void emailChecking(String email){
        Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherEmail = patternEmail.matcher(email);
        error +="Conditions not met: ";
        for (String email1 : emails) if (email.equals(email1)) error +=descriptionOfAllErrors.get("Registered email");
        emails.add(email);
        if (!matcherEmail.matches()) error +=descriptionOfAllErrors.get("Incorrectly email");
    }

    private static void passwordChecking(String password, String rePassword){
        if (!password.equals(rePassword)) error +=descriptionOfAllErrors.get("Mismatched passwords");
        if (!(password.length() > 6 && password.length() < 20)) error +=descriptionOfAllErrors.get("Invalid password");
    }

    private static void phoneChecking(String phone){
        Pattern patternPhone = Pattern.compile("\\+\\d(-\\d{3}){2}-\\d{4}");
        Matcher matcherPhone = patternPhone.matcher(phone);
        if (!matcherPhone.matches()) error +=descriptionOfAllErrors.get("Incorrectly phone");
    }

    private static void dateOfBirthChecking(String dateOfBirth){
        Pattern patternDate = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
        Matcher matcherDate = patternDate.matcher(dateOfBirth);
        if (!matcherDate.matches()) error +=descriptionOfAllErrors.get("Incorrectly date");
    }

    private static void aboutMyselfChecking(String aboutMyself){
        if (aboutMyself.length() > 1000) error +=descriptionOfAllErrors.get("Incorrectly aboutMyself");
    }

    private static void newGroupChecking(String newGroup){
        if (!(newGroup.length() > 2 && newGroup.length() < 31)){
            error +=descriptionOfAllErrors.get("Incorrectly newGroup");
        }
    }


    private static void workWithTheDatabase(String email, String password, String phone, String dateOfBirth, String gender,
                                            String country, String aboutMyself, String[] groups, String newGroup, String checkRemember,
                                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (error.equals("Conditions not met: ")){
            int id = DatabaseConnection.writeToDatabaseNewUser(email, password, phone, dateOfBirth, gender, country, aboutMyself);
            if (id == -1) new IllegalStateException();
            List<String> allGroups = new ArrayList<>(Arrays.asList(groups));
            if (!newGroup.equals("")){
                allGroups.add(newGroup);
                DatabaseConnection.writeNewGroup(newGroup);
            }
            for (String group : allGroups) DatabaseConnection.recordGroupsForUsers(id, group);

            if (checkRemember != null){
                String symbols = "abcdefghijklmnopqrstuvwxyz";
                String random = new Random().ints(200, 0, symbols.length())
                        .mapToObj(symbols::charAt)
                        .map(Object::toString)
                        .collect(Collectors.joining());
                Cookie cookie = new Cookie("remember", random);
                DatabaseConnection.writingCookiesToTheDatabase(random);
                response.addCookie(cookie);
            }

            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            response.sendRedirect(request.getContextPath() + "/newMain");
        }
        else{
            response.sendRedirect(request.getContextPath() + "/registration");
            HttpSession session = request.getSession();
            session.setAttribute("help", error);
        }
    }
}
