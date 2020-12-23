package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends NetworkImp {

  ServerSocket serverSocket;
  Socket socket;


  public Server(int port) throws IOException {
    this.serverSocket = new ServerSocket(port);
  }

  public void accept() throws IOException {
    this.socket = this.serverSocket.accept();

    this.setupStream(this.socket);
  }
}
