package ru.bulat;

import ru.bulat.dao.DatabaseConnection;

public class ClientServlet implements MyServlet {
    @Override
    public String getPath() {
        return "/get_users";
    }

    @Override
    public void doGet(MyRequest request, MyResponse response) {
        response.updateData(DatabaseConnection.gettingUsers().toString());
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        response.setStatus("405");
        response.updateData("use get method");
    }
}
