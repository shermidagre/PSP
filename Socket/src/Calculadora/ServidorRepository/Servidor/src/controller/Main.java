package controller;

public class Main {

	public static void main(String args[]) throws Exception {
		try (Servidor servidor = new Servidor()) {
			servidor.run();
		}
		}
	}


