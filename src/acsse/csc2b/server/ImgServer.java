package acsse.csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**ImgServer class retrieves image wanted by client while running
 * @author Nogantsho, Y 201602466
 * @version P04
 *
 */
public class ImgServer {
//attributes
	private ServerSocket server;
	private boolean running;
	/**
	 * Constructor with parameter initializes variables
	 * @param port is to specify the port number when constructor is used in pane class
	 */
	public ImgServer(int port) {
		try {
			server = new ServerSocket(port);
			running = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * startServer runs the connection accepted in threads
	 */
	private  void startServer() {
		while(running) {
			try {
				Socket connection = server.accept();   //accept connection
						System.out.println("Client connection accepted!");
						ImgHandler imghandler = new ImgHandler(connection);
						Thread thread = new Thread(imghandler);
						thread.start();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
