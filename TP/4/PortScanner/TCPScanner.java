import java.net.*;
import java.io.*;


public class TCPScanner {

	public static boolean isUp(String ip, Integer port) { 
	    try (Socket s = new Socket(ip, port)) {
	        return true;
	    } catch (IOException ex) {
	        //System.out.println("Can't acces: " + ip + ":" + Integer.toString(port));
	    }
	    return false;
	}
}