import java.io.*;
import java.net.*;
import java.util.StringTokenizer;


public class HttpServer implements Runnable {

  ServerSocket serverSocket; // server Socket
	public static int PORT = 8080; // default port
	public static String WEB_ROOT = "./";

	/**
	 * Start HTTP Server
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("Here is my web server，default port is: 8080.");
			} else if (args.length == 1) {
				PORT = Integer.parseInt(args[0]);
			}
		} catch (Exception ex) {
			System.err.println("Server initialize error" + ex.getMessage());
		}

		new HttpServer();
	}

	/**
	 * start Server Socket.
	 */
	public HttpServer() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (Exception e) {
			System.out.println("Can not start HTTP server:" + e.getMessage());
		}
		if (serverSocket == null)
			System.exit(1);

		new Thread(this).start();
		System.out.println("HTTP server is running, port:" + PORT);
	}

	/**
	 * run server
	 */
	public void run() {
		while (true) {
			try {
				Socket client = null;// Client Socket
				client = serverSocket.accept();
				if (client != null) {
					System.out.println("The clinet has connected to Server is:"
							+ client);
					try {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(client.getInputStream()));
						String line = in.readLine();
						String resource = line.substring(line.indexOf('/'),
								line.lastIndexOf('/') - 5);
						resource = URLDecoder.decode(resource, "utf-8");
						String method = new StringTokenizer(line).nextElement()
								.toString();
						while ((line = in.readLine()) != null) {
							// System.out.println(line);

							if (line.equals(""))
								break;
						}

						System.out.println("The clinet requested URL is: 127.0.0.1:8080"
								+ resource);
						System.out.println("The clinet requested resource is:"
								+ resource);

						if (resource.indexOf("?") > -1) {
							resource = resource.substring(0,
									resource.indexOf("?"));
						}

						if (!resource.contains("index")) {
							PrintStream out = new PrintStream(client.getOutputStream(), true);
							out.println(resource);
							out.println("http://127.0.0.1:8080/" + resource);
							out.close();
							
						} else {
							ReadFileAndReturn(resource, client);
						}

						client.close();
					} catch (Exception e) {
						System.out.println("HTTP Server error:"
								+ e.getMessage());
					}
				}

			} catch (Exception e) {
				System.out.println("HTTP Server error:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param fileName
	 * 
	 * @param socket
	 * 
	 * @throws IOException
	 */
	void ReadFileAndReturn(String fileName, Socket socket) throws IOException {
		PrintStream out = new PrintStream(socket.getOutputStream(), true);
		fileName = WEB_ROOT;

		File file = new File(fileName);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				System.out.println(files[i]);
				File s = files[i];
				String filesName = s.getName();
				out.println(filesName);
			}
		}
		out.close();
	}
}
