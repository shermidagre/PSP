package netserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.Snippet;

//connects the server and connect to the given port

public class NetServer implements AutoCloseable {
	// initialize the socket and the port number;
	public static final int portNumber = 5555;
	private final ServerSocket socketServer;
	private static final String ERROR = "Error in expression procceding.";
	private JShell jshell;

	// creates a Server and connects to the given port
	public NetServer() throws IOException {

		socketServer = new ServerSocket(portNumber);// we start our server with the given port
		jshell = JShell.create();

		System.out.println("Server has started.");
		System.out.println("Waiting for a Client...");
	}

	public void run() {
		while (true) {
			try 
			(Socket socketAccepted = socketServer.accept()) {
				System.out.println("Client accepted.");
				processingTheReceivedExpression(socketAccepted);
			}catch (IOException e) {e.printStackTrace();}
		}
	}
	private void processingTheReceivedExpression(Socket socket) {
		boolean isAutoFlush = true; //A value of true (default) indicates automatic buffer flushing
		// InputStream inputStream=socket.getInputStream();
		System.out.println("Server has established a connection ");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			// got expression from Client and processing
			String taskFromClient = reader.readLine();
			System.out.println("Server: calculate  " + taskFromClient);
			String result = snipperTransferedToJShell(taskFromClient);
			System.out.print("Server: task= " + taskFromClient + " result: " + result);
			// Sending answer to Client:
			PrintWriter responseToClient = new PrintWriter(socket.getOutputStream(), isAutoFlush);
			if (result == null) {
				System.out.print("Fatal error. Calculations wrong." + taskFromClient);
				result = ERROR;
			}
			responseToClient.println(result);
		} catch (IOException e) {
			System.out.print("Fatal error. Calculations wrong.");
			e.printStackTrace();
		}
	}
	private String snipperTransferedToJShell(String snippet) {	
		System.out.println("Server got:" + snippet);
		//Evaluate the input String:
		List<SnippetEvent> event = jshell.eval(snippet);
		System.out.println("Server work on the expression from Client and got event :" + event.size());		
//		Returns:the Snippet which caused this change or null if directly caused by an API action.
		if (event.get(0).causeSnippet() == null) {
			SnippetEvent snippetEvent = event.get(0);
			if (snippetEvent.status() == Snippet.Status.VALID && snippetEvent.value() != null) {
				return snippetEvent.value();
			}
		}
		return (ERROR);
	}				
  @Override
	public void close() throws Exception {
		if (socketServer != null) {
			socketServer.close();
		}
	}
}
