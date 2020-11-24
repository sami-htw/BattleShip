package SeaWar.board;

import SeaWar.SeaWarException;


public class BoardException extends SeaWarException {

  /**
   * Creates a new instance of <code>BoardException</code> without detail message.
   */
  public BoardException() {
  }

  /**
   * Constructs an instance of <code>BoardException</code> with the specified detail message.
   *
   * @param msg the detail message.
   */
  public BoardException(String msg) {
    super(msg);
  }
}
