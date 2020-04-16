package ru.bulat;

import ru.bulat.dao.DatabaseConnection;
import ru.bulat.model.User;

import java.sql.SQLException;
import java.util.HashMap;

public class RequestServlet implements MyServlet{
    private String path = "/req";

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void doGet(MyRequest request, MyResponse response) {
        response.setStatus("405");
        response.updateData("Use Post method");
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        HashMap<String, String> data = getData(request);
        String name = null;
        String birthday = null;
        if (data.containsKey("name") & data.containsKey("birthday")) {
            name = data.get("name");
            birthday = data.get("birthday");
        }
        if (name != null & birthday != null) {
            User user = new User(name, birthday);
            DatabaseConnection.recordNewData(user);
            response.setStatus("200");
            response.updateData("successful");
        } else {
            response.setStatus("400");
            response.updateData("name or birthday not found");
        }
    }

    private HashMap<String, String> getData(MyRequest request) {
        String data = request.getData();
        HashMap<String, String> hashData = new HashMap<>();
        String[] arr = data.split("\n");
        for (String s : arr) {
            try {
                String[] line = s.split("=");
                hashData.put(line[0], line[1]);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        }
        return hashData;
    }
}
