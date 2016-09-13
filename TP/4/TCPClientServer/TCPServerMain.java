import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TCPServerMain {

	public static void main(String argv[]) throws Exception {
		
		if (argv.length != 2) {
			System.out.println("Usage: ./TCPServerMain ip port");
		}
		else {
			String ip = argv[0];
			if (isInt(argv[1])) {
				Integer port = Integer.parseInt(argv[1]);
				if (isValidIp(ip)) {
					if (port > 0) {
						new TCPServer( InetAddress.getByName(ip), port);
					}
					else {
						System.out.println("Incorect port: " + port);
					}
				}
				else {
					System.out.println("Invalid ip adress: " + ip);
				}
			}else {
				System.out.println("Port Error: invalid integer");
			}
		}
	}

	public static boolean isValidIp(String ip) {
		Pattern p = Pattern.compile("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$");
		Matcher m = p.matcher(ip);
		return m.matches();
	}

	public static boolean isInt(String n) {
		Pattern p = Pattern.compile("^\\d+$");
		Matcher m = p.matcher(n);
		return m.matches();
	}



}