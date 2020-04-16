package ru.bulat.controller;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 8000);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String message = "";

        while (true){
            message = getMessage();
            Server.write(writer,message);
            System.out.println(Server.getMessage(reader));
        }
    }

    private static String getMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder mess = new StringBuilder();
        System.out.println("Вводи: ");
        while (true) {
            String line = reader.readLine();
            mess.append(line).append("\n");
            if (line.contains("<?break?>")) {
                break;
            }
        }
        return mess.toString().replace("<?break?>", "");
    }
}
