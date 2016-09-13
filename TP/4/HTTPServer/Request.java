import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Request {

	private String request;
	private String method;
	private String file_requested;
	private String http_version;
	private String host;

	public Request(String request) {
		this.request = request;
	}	

	public boolean load() {
		Pattern p = Pattern.compile("(GET|POST) \\/(\\w+.\\w+) HTTP\\/(\\d.\\d)\nHost: (.*)\n(.*)");
		Matcher m = p.matcher(request);
		if (m.find()) {
			this.method = m.group(1);
			this.file_requested = m.group(2);
			this.http_version = m.group(3);
			this.host = m.group(4);
			return true;
		}
		return false;
	}

	public String toString() {
		return "Method: " + method + "; FileRequested: " + file_requested + "; HTTPv: " + http_version + " Host: " + host;
	}

	public String getMethod() {
		return method;
	}
	public String getFile() {
		return file_requested;
	}
	public String getHTTPversion() {
		return http_version;
	}
	public String getHost() {
		return host;
	}
	public String getRequest() {
		return this.request;
	}

}