package view.console;

import java.io.PrintStream;
import seaWar.board.BoardViewAccess;

/**
 * visualisation of seawar Games
 */
public interface BoardViewConsole {

  static BoardViewConsole getBoardViewConsole() {
    return new BoardViewConsoleImpl();
  }

  void printBoard(BoardViewAccess board,
      PrintStream screen);
}
