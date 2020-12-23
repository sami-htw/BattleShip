package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public interface Network {

  /**
   * to receive Data from Stream
   *
   * @return
   * @throws IOException
   */
  public DataInputStream getDataInputStream() throws IOException;

  /**
   * to send Data through stream
   *
   * @return
   * @throws IOException
   */
  public DataOutputStream getDataOutputStream() throws IOException;

}
