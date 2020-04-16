package ru.bulat;

public interface MyServlet {

    String getPath();

    void doGet(MyRequest request, MyResponse response);

    void doPost(MyRequest request, MyResponse response);
}
