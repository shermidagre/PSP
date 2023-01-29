package netserver;

public class Main {

	public static void main(String args[]) throws Exception {
		try (NetServer server = new NetServer()) {
			server.run();
		}
				
		}
	}


