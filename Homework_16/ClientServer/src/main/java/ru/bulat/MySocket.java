package ru.bulat;


import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;

public class MySocket {
    private String version = "HTTP/1.1";
    private List<MyServlet> servlets = new ArrayList<>();


    public static void main(String[] args) {
        MySocket socket = new MySocket();

        String request = "GET /get_users HTTP/1.1";
        MyResponse response = socket.request(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());

    }

    public MySocket() {
        servlets.add(new RequestServlet());
        servlets.add(new ClientServlet());

    }

    public MyResponse request(String request) {
        MyResponse response = new MyResponse();
        MyRequest req;
        try {
            req = new MyRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("400");
            response.updateData("Bad Request");
            return response;
        }

        if (!req.getVersion().equals(version)) {
            response.setStatus("505");
            response.updateData("HTTP Version Not Supported");
            return response;
        }

        MyServlet servlet;
        try {
            servlet = getServlet(req);
        } catch (NotFound e) {
            response.setStatus("404");
            response.updateData("Not found");
            return response;
        }

        try {
            if (req.getMethod().equals("get")) {
                servlet.doGet(req, response);
            } else if (req.getMethod().equals("post")) {
                servlet.doPost(req, response);
            } else {
                response.setStatus("405");
                return response;
            }
            if (response.getStatus() == null) {
                response.setStatus("200");
            }
            return response;
        } catch (Exception e) {
            response.setStatus("500");
            response.updateData("Internal Server Error");
            return response;
        }

    }

    private MyServlet getServlet(MyRequest request) throws NotFound {
        for (int i = 0; i < servlets.size(); i++) {
            if (request.getPath().toLowerCase().equals(servlets.get(i).getPath())) {
                return servlets.get(i);
            }
        }
        throw new NotFound();
    }
}
