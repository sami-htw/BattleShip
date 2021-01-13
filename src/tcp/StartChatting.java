package tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartChatting {

  private static final String LOCALPROMPT = " local > ";
  private static final String REMOTEPROMPT = " remote > ";
  private static final String EXIT = "exit";


  public static void main(String[] args) throws IOException {
    if (args.length < 1 || args.length > 2) {
      StartChatting.print_HelpInfo();
      System.exit(1);
    }

    boolean isClient = args.length == 2;
    Network network = null;

    if (isClient) {
      int port = Integer.parseInt(args[1]);
      Client client = new Client(args[0], port);
      network = client;
    } else {
      int port = Integer.parseInt(args[0]);
      Server server = new Server(port);
      System.out.println("Server waits for client");
      server.accept(); // waits for client
      System.out.println("client connected");
      network = server;
    }

    boolean stopped = false;
    boolean active = isClient;

    DataInputStream dis = network.getDataInputStream();
    DataOutputStream dos = network.getDataOutputStream();

    // get stream from console
    BufferedReader userInput = new BufferedReader(
        new InputStreamReader(System.in));

    while (!stopped) {
      if (active) {
        System.out.print(LOCALPROMPT);

        String line = userInput.readLine();
        if (line.equalsIgnoreCase(EXIT)) {
          stopped = true;
          break;
        }

        dos.writeUTF(line); // The End!
      } else {
        System.out.print(REMOTEPROMPT);

        String line = dis.readUTF();

        System.out.println(line);
      }

      active = !active;
    }


  }


  public static void print_HelpInfo() {
    System.out.println("To use Client, input : hostname, port");
    System.out.println("To use Server, input : port ");
  }


}
