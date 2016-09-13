import java.net.*;
import java.io.*;


public class Scanner {
    public static void main(String[] args) throws Exception {
    	String ip = "localhost";
        for (int i = 0; i < 65000; i++) {
            //System.out.print(ip + ':' + Integer.toString(i));
            if (new TCPScanner().isUp(ip, i)) {
                //System.out.println(" UP");
                System.out.println(ip + ':' + Integer.toString(i) + " UP");
            }
            else {
                //System.out.println(" DOWN");
            }
        }
    }
}

