package ru.bulat;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpManager {
    private static BufferedWriter writer;
    private static BufferedReader reader;

    public static void start(){
        String url = JOptionPane.showInputDialog("Write url: ");
        String regexForUrl = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        while (!url.matches(regexForUrl)){
            url = JOptionPane.showInputDialog("Try again. Write url: ");
        }
        String method = JOptionPane.showInputDialog("Method (GET/POST): ");
        while (!(method.equals("GET") || method.equals("POST"))){
            method = JOptionPane.showInputDialog("Try again. Method (GET/POST): ");
        }
        String data = "";
        if (method.equals("POST")){
            data = JOptionPane.showInputDialog("Enter POST Method data:");
        }
        String version = JOptionPane.showInputDialog("Version (1.0/1.1/2.0): ");
        while (!(version.equals("1.0") || version.equals("1.1") || version.equals("2.0"))){
            version = JOptionPane.showInputDialog("Try again. Version (1.0/1.1/2.0): ");
        }
        String parameters = JOptionPane.showInputDialog("Parameters: ");
        run(url, method, version, parameters, data);
    }

    private static void run(String url, String method, String version, String parameters, String data){
        StringBuilder builder = new StringBuilder();
        Pattern patternForHost = Pattern.compile("/{2}.+?/");
        Matcher matcher = patternForHost.matcher(url);
        int hostEnd = 0;
        String host = "";
        if (matcher.find()){
            hostEnd = matcher.end()-1;
            host = url.substring(matcher.start()+2, hostEnd);
        }
        try {
            Socket socket = new Socket(host, 80);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.err.println("Failed to read because problems with internet connection!");
        }
        builder.append(method);
        if (parameters == null)builder.append(url.substring(hostEnd));
        else {
            if (url.contains("?")) builder.append(url, hostEnd, url.indexOf('?')).append(parameters);
            else builder.append(url.substring(hostEnd)).append("?").append(parameters);
        }
        builder.append("HTTP/").append(version).append("\n");
        builder.append("Host:").append(host).append("\n\n");
        if (!data.equals("")) builder.append(data);
        System.out.println(builder);
        StringBuilder builder1 = new StringBuilder();
        try {
            writer.write(builder.toString());
            writer.flush();
            String out;
            while ((out = reader.readLine()) != null){
                builder1.append(out).append("\n");
                if (out.equals("</html>")){
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read!");
        }
        System.out.println(builder1);
    }
}