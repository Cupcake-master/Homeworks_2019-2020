package ru.bulat.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bilalov_Bulat
 * @date 21.10.19
 * @version 1.1
 */

public class CheckingEmailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        BufferedReader reader = new BufferedReader
                (new FileReader("C:\\Users\\sereg\\Desktop\\My_work\\Homework_on_2_course-practice\\Homework_6" +
                        "\\WorkingWithServlets\\src\\main\\resources\\htmls\\startHTML.html"));
        String s;
        while ((s = reader.readLine())!= null){
            writer.write(s);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String e_mail = request.getParameter("E-mail");
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher  = pattern.matcher(e_mail);
        if (matcher.matches()){
            BufferedReader reader = new BufferedReader
                    (new FileReader("C:\\Users\\sereg\\Desktop\\My_work\\Homework_on_2_course-practice\\Homework_6" +
                            "\\WorkingWithServlets\\src\\main\\resources\\htmls\\CorrectEmailHTML.html"));
            String s;
            while ((s = reader.readLine())!= null) {
                writer.write(s);
            }
        }else {
            BufferedReader reader = new BufferedReader
                    (new FileReader("C:\\Users\\sereg\\Desktop\\My_work\\Homework_on_2_course-practice\\Homework_6" +
                            "\\WorkingWithServlets\\src\\main\\resources\\htmls\\InvalidEmailHTML.html"));
            String s;
            while ((s = reader.readLine())!= null) {
                writer.write(s);
            }
        }
    }
}
