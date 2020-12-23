package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkImp implements Network {

  private InputStream is = null;
  private OutputStream os = null;
  private DataOutputStream dos = null;
  private DataInputStream dis = null;

  @Override
  public DataInputStream getDataInputStream() throws IOException {
    return this.dis;
  }

  @Override
  public DataOutputStream getDataOutputStream() throws IOException {
    return this.dos;
  }


  public void setupStream(Socket socket) throws IOException {
    this.is = socket.getInputStream();
    this.os = socket.getOutputStream();

    this.dis = new DataInputStream(this.is);
    this.dos = new DataOutputStream(this.os);
  }
}
