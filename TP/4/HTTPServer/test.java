import java.io.*;
import java.net.*;


public class test {
	public static void main(String argv[]) {
		Request r = new Request("GET /index.html HTTP/1.1\nHost: www.bla.com\n");
		if (r.load())
			System.out.println(r);
		else
			System.out.println("Invalid request");
	}
}