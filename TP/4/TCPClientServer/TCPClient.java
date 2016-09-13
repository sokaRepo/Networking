import java.io.*;
import java.net.*;

public class TCPClient {

	private String ip;
	private int port;
	private Socket client;
	private BufferedReader in;
	private BufferedReader outMsg;
	private DataOutputStream out;
	private String inMsg;
	private String msgToSend;

	public TCPClient(String ip, int port) {
		this.ip = ip;
		this.port = port;

		if (startClient())
			communicate();
	}

	public boolean startClient() {
		try {
			this.client = new Socket(ip, port);
			System.out.println("Connected to " + ip + ':' + port);
			return true;
		}catch(IOException e) {
			System.out.println("Can't connect to " + ip + ':' + port);
		}
		return false;
	}

	public void communicate() {
		try {
				System.out.print("> ");
				this.outMsg = new BufferedReader(new InputStreamReader(System.in));
				//System.out.flush();
				this.out = new DataOutputStream(client.getOutputStream());
				this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				this.msgToSend = outMsg.readLine();
				if (msgToSend.equals("exit")) {
					client.close();
					System.out.println("Exiting");
					return;
				}
				out.writeBytes(msgToSend + '\n');
				inMsg = in.readLine();
				System.out.println("Server said: " + inMsg);
			} catch(IOException e) {
				System.out.println(e);
		}
	}

	public void communicate2() {
		while (true) {
			try {
				System.out.print("> ");
				this.outMsg = new BufferedReader(new InputStreamReader(System.in));
				//System.out.flush();
				this.out = new DataOutputStream(client.getOutputStream());
				this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				this.msgToSend = outMsg.readLine();
				if (msgToSend.equals("exit")) {
					client.close();
					System.out.println("Exiting");
					return;
				}
				out.writeBytes(msgToSend + '\n');
				System.out.println("bla");
				inMsg = in.readLine();
				System.out.println("Server said: " + inMsg);
			} catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}