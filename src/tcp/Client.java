package tcp;

import java.io.IOException;
import java.net.Socket;

public class Client extends NetworkImp {

  public Client(String hostName, int port) throws IOException {

    Socket clientSocket = new Socket(hostName, port);
    this.setupStream(clientSocket);
  }
}
