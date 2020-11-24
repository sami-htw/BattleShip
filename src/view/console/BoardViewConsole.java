package view.console;

import java.io.PrintStream;
import SeaWar.board.BoardViewAccess;

/**
 * visualisation of seawar Games
 */
public interface BoardViewConsole {

  void printBoard(BoardViewAccess board,
      PrintStream screen);
}
