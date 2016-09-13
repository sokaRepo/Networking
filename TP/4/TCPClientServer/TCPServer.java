import java.io.*;
import java.net.*;

public class TCPServer {
	private InetAddress ip;
	private Integer port;
	private ServerSocket serverSocket;
	private Socket conn;
	private BufferedReader in;
	private DataOutputStream out;

	public TCPServer(InetAddress ip, Integer port) {
		this.ip = ip;
		this.port = port;
		System.out.println("Start TCP server at: " + ip + ':' + port);
		start();
		System.out.println("Waiting for clients...");
		handleClients();
	}

	public void start() {
		try {
			this.serverSocket = new ServerSocket(port, 5, ip);
		}
		catch(IOException e) {
			System.out.println("Can't bind at " + ip + ':' + port);
		}
	}

	public void handleClients() {
		try {
			while (true) {
				this.conn = serverSocket.accept();
				this.in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				this.out = new DataOutputStream(conn.getOutputStream());
				System.out.println("Rcv: " + in.readLine());
				out.writeBytes("OK\n");
			}
		} catch(IOException e) {
			System.out.println(e);
		}
	}




}