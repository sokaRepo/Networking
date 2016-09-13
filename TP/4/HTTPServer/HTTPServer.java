import java.io.*;
import java.net.*;

public class HTTPServer {
	private InetAddress ip;
	private Integer port;
	private ServerSocket serverSocket;
	private Socket conn;
	private BufferedReader in;
	private DataOutputStream out;
	private Request request;
	private String requestI;

	public HTTPServer(InetAddress ip, Integer port) {
		this.ip = ip;
		this.port = port;
		System.out.println("Start HTTP server at: " + ip + ':' + port);
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
				//System.out.println("Recv request: \n" + in);
				this.out = new DataOutputStream(conn.getOutputStream());
				this.requestI = getRequest(in);
				System.out.println("Recv: \n" + requestI);
				this.request = new Request(requestI);
				if (request.load()) {
					System.out.println(request);
					out.writeBytes("HTTP/1.1 200 OK\n\nDate: Fri, 09 Sep 2016 15:33:21 GMT\n\nServer: Apache\n\nContent-Length: 15\n\nConnection: close\n\nContent-Type: text/html; charset=UTF-8\n\n\n\nindex crashlab\n");
				}
				else {
					System.out.println("Invalid request: ");
					out.writeBytes("HTTP/1.1 200 OK\nDate: Fri, 09 Sep 2016 15:33:21 GMT\nServer: Apache\nContent-Length: 15\nConnection: close\nContent-Type: text/html; charset=UTF-8\n\nindex crashlab\r\n");
					//System.out.println("msg sent");
				}
				conn.close();
				
			}
		} catch(IOException e) {
			System.out.println(e);
		}
	}

	public boolean loadFile(String file) {
		try {
			File f = new File(file);
			if (f.exists() && !f.isDirectory()) {
				return true;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public String readFile(String file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			br.close();
			return sb.toString();
		} catch(Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public String getRequest(BufferedReader in) {
		try {
			StringBuilder sb = new StringBuilder();
			String line = in.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				if (line.equals(""))
					break;
				line = in.readLine();
				
			}
			in.close();
			return sb.toString();
		}catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}


}