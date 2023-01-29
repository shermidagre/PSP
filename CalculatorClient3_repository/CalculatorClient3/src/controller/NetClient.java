package controller;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetClient implements AutoCloseable {
	private Socket socketClient;
	private String serverAddress;
	private int portNumber;
	private InputStream inputStream;
	private OutputStream outputStream;

	public NetClient(String serverAddress, int portNumber) throws UnknownHostException, IOException {
		// we try to establish a connection
		// every time we have to create a new socket with the given information( cause
		// AutoCloseable is closed)
		this.serverAddress = serverAddress;
		this.portNumber = portNumber;
		System.out.println(" Client try to connect.");
	}

// sending expression to Server
	public String sendWritingToStream(String expression) throws UnknownHostException, IOException {
		System.out
				.println("Before creating socketClient: " + "Port: " + portNumber + " serverAddress: " + serverAddress);
		socketClient = new Socket(serverAddress, portNumber);
		System.out.println("Client accepted ");
		InputStream inputStream = socketClient.getInputStream();
		// sending expression to proceed to NetServer
		System.out.println("NetClient is sending: " + expression);
		PrintWriter response = new PrintWriter(socketClient.getOutputStream());
		System.out.println("NetClient opened Print Writer");
		response.println(expression);
		response.flush();

		// read expression from Server
		String responseFromServer = "0";
		try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			responseFromServer = bufferedReader.readLine();
			System.out.println("REsponse from Server(on Serversite): " + responseFromServer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseFromServer;
	}
	@Override
	public void close() throws Exception {
		if (socketClient != null)
			socketClient.close();
	}
}
