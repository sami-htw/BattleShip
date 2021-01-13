package BattleShipTesting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.Test;
import seaWar.Game;
import seaWar.GameImpl;
import seaWar.board.LocalBoardImpl;
import seaWar.board.RemoteBoardImpl;
import view.console.BoardCommands;
import view.console.BoardCommandsImp;

public class InputTests {

  public InputTests() {
  }

  final LocalBoardImpl getLocalBoard() {
    return new LocalBoardImpl(GameImpl.createGame());
  }

  final RemoteBoardImpl getRemoteBoard() {
    return new RemoteBoardImpl(GameImpl.createGame());
  }

  final Game getGame() {
    return GameImpl.createGame();
  }

  private void runTest(String commands) throws IOException {
    byte[] buffer = new byte[1000];
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    DataOutputStream dos = new DataOutputStream(byteArrayOutputStream);

    dos.writeUTF(commands);

    // commands in byte array
    byte[] byteCommands = byteArrayOutputStream.toByteArray();

    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteCommands);

    Game game = this.getGame();

    BoardCommands commandInput = new BoardCommandsImp(
        game, System.out, byteArrayInputStream);

    commandInput.runGame();

  }


  @Test
  public void basicVisualTests() throws IOException {
    Game game = this.getGame();
    BoardCommands cmd = new BoardCommandsImp(game, System.out, System.in);

    cmd.printUsage();

  }

  @Test
  public void basicVisualTests2() throws IOException {
    // expected: print board
    StringBuilder b = new StringBuilder();
    b.append(BoardCommands.PRINT_LOCAL);
    b.append("\n");

    b.append(BoardCommands.PRINT_REMOTE);
    b.append("\n");

    String inputString = b.toString();

    this.runTest(inputString);
  }

  @Test
  public void inputTestsDebug() throws IOException {
    // expected: print board
    StringBuilder b = new StringBuilder();
    b.append(BoardCommands.REMOVE_SHIP);
    b.append(" B 4"); // failure
    b.append("\n");

    String inputString = b.toString();

    this.runTest(inputString);
  }

  @Test
  public void inputTestsDebug2() throws IOException {
    // expected: print board
    StringBuilder b = new StringBuilder();
    b.append(BoardCommands.GET_STATUS);
    b.append("\n");

    String inputString = b.toString();

    this.runTest(inputString);
  }

}
