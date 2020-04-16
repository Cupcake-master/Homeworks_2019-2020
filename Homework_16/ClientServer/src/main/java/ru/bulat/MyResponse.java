package ru.bulat;

import java.util.HashMap;
import java.util.Map;

public class MyResponse {
    private String status = "";
    private Map<String, String> headers = new HashMap<>();
    private String data = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void updateData(String line) {
        data += line + "\n";
    }

    @Override
    public String toString() {
        return "status: " + status + "\n" + data;
    }
}
