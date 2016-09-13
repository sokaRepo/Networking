import java.net.*;
import java.io.*;


public class Bruteforce {
    public static void main(String[] args) throws Exception {
    	String url = "http://192.168.1.42/cgi-bin/";
        for (int i = 0; i < 1000; i++) {
            System.out.println("Test: "+url+i+".cgi");
            if (new Request().getCode(url+i+".cgi") == 200) {
                System.out.println("Find: "+Integer.toString(i));
                break;
            }
        }
    }
}

