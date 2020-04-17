package ru.bulat;

import java.io.Serializable;

class MyURL implements Serializable{
    private String url;
    private String scheme;
    private String authority;
    private String userInfo;
    private String host;
    private int port;
    private String path;
    private String query;
    private String ssp;
    private String fragment;

    public MyURL(String url) {
        this.url = url;
    }

    String getScheme(){
        return url.substring(0, url.indexOf("://"));
    }

    String getUserInfo() {
        return url.substring(url.indexOf("://")+3, url.indexOf("@"));
    }

    String getHost() {
        int start = url.indexOf("@");
        if (start == -1){
            int newStart = url.indexOf("://");
            int newEnd = url.indexOf("/", newStart);
            return url.substring(newStart, newEnd);
        }else{
            return url.substring(start+1, url.indexOf("/", start));
        }
    }

    String getPath() {
        return url.substring(url.lastIndexOf("/")+1, url.indexOf("?"));
    }

    String getQuery() {
        return url.substring(url.indexOf("?")+1, url.indexOf("#"));
    }

    String getFragment() {
        if (url.substring(url.indexOf("#") + 1).equals("-1")) {
            return null;
        } else {
            return url.substring(url.indexOf("#") + 1);
        }
    }
}
