package ru.bulat;

public class Main {
    public static void main(String[] args) {
        MyURL url = new MyURL("foo://username:password@www.example.com:8080/hello/index.html" +
                "?arg=val&arg2=val2#fragment");
        System.out.println(url.getScheme());
        System.out.println(url.getUserInfo());
        System.out.println(url.getHost());
        System.out.println(url.getPath());
        System.out.println(url.getQuery());
        System.out.println(url.getFragment());
    }
}
