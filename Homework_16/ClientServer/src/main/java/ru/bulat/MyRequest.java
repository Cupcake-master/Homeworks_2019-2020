package ru.bulat;

public class MyRequest {
    private String path = "";
    private String method = "";
    private String data = "";
    private String version = "";

    MyRequest(String request){
        String[] array1 = request.split("\n");
        String[] array2 = request.split(" ");

        method = array2[0].toLowerCase();
        path = array2[1].toLowerCase();
        version = array2[2];

        try {
            data = request.split("\n\n")[1];
        }catch (ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
