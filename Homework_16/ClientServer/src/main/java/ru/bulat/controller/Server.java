package ru.bulat.controller;

import ru.bulat.MyResponse;
import ru.bulat.MySocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static boolean check = true;
    private String stopWorld = "<?break?>";

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8000);
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        work(out, in);

    }

    public static void work(BufferedWriter out, BufferedReader in) throws IOException {
        while (true) {
            String mess = getMessage(in);
            System.out.println(mess);

            MySocket firstSocket = new MySocket();
            MyResponse response = firstSocket.request(mess);
            write(out, response.toString());
            System.out.println(mess);
        }
    }

    public static String getMessage(BufferedReader reader) throws IOException {
        StringBuilder message = new StringBuilder();
        String next = "";
        while (!next.contains("<?break?>")){
            next = reader.readLine();
            message.append(next).append("\n");
        }
        message = new StringBuilder(message.toString().replace("<?break?>", ""));
        return message.toString();
    }

    public static void write(BufferedWriter out, String mess) throws IOException {
        out.write(mess);
        out.write("<?break?>\n");
        out.flush();
    }
}
