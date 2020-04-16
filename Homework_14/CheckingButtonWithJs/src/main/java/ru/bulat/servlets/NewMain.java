package ru.bulat.servlets;

import ru.bulat.data.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/newMain")
public class NewMain extends HttpServlet {
    private int id;
    private String[] groups;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionForId = request.getSession();
        id = (int) sessionForId.getAttribute("id");
        List<Integer> idGroups = DatabaseConnection.gettingAllIdGroups(id);
        List<String> namesGroups = new ArrayList<>();
        for (Integer idGroup : idGroups) {
            String group = DatabaseConnection.gettingNameGroup(idGroup);
            namesGroups.add(group);
        }
        request.setAttribute("joinedTheGroup", namesGroups);

        List<Integer> allExistingId = DatabaseConnection.gettingAllExistingIdGroups();
        assert allExistingId != null;
        allExistingId.removeIf(idGroups::contains);
        List<String> group = new ArrayList<>();
        for (Integer integer : allExistingId) {
            String someGroup = DatabaseConnection.gettingNameGroup(integer);
            group.add(someGroup);
        }
        request.setAttribute("didNotJoinTheGroup", group);

        request.getServletContext().getRequestDispatcher("/jsp/NewMain.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("enter") != null) {
            groups = request.getParameterValues("groups");
        }
        for (String group : groups) DatabaseConnection.recordGroupsForUsers(id, group);
        response.sendRedirect(request.getContextPath() + "/newMain");
    }
}
