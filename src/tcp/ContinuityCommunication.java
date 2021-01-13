package tcp;

import java.io.IOException;

public class ContinuityCommunication implements Runnable {

  private Network network = null;
  private String hostname = null;
  public final static int PORT = 9876;
  private Thread findClientThread = null;

  ContinuityCommunication(String hostname) {
    this.hostname = hostname;

    Thread findClientThread = new Thread(this);
    findClientThread.start();

    this.connect();
  }

  private void connect() {
    // try as client
    while (this.network == null) {
      try {
        Client client = new Client(this.hostname, PORT);
        this.establishNetwork(client);
        this.stopSearchingClient();
      } catch (IOException ex) {
        try {
          // server not yet activ
          Thread.sleep(10000);
        } catch (InterruptedException ex1) {
          // ignore
        }
      }
    }
  }

  synchronized void establishNetwork(Network network) {
    if (this.network == null) {
      this.network = network;
    }
  }

  void connectAsServer() throws IOException {
    Server server = new Server(PORT);

    server.accept();

    this.establishNetwork(server);
  }

  private void stopSearchingClient() {
    this.findClientThread.stop();
  }


  // TODO
  @Override
  public void run() {

    try {
      this.connectAsServer();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
