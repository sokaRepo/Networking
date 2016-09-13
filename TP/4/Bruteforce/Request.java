import java.net.*;
import java.io.*;


public class Request {
    
    public static int getCode(String target)  throws Exception {
        URL url = new URL(target);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        return connection.getResponseCode();
    }
}