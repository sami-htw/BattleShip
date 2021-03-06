package BattleShipTesting;

import seaWar.GameImpl;
import seaWar.board.BoardException;
import seaWar.board.BoardViewAccess;
import seaWar.board.LocalBoard;
import seaWar.board.LocalBoardImpl;
import seaWar.board.RemoteBoardImpl;
import seaWar.board.Ship;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;

import static org.junit.Assert.*;
import static view.console.BoardViewConsoleImpl.WATER_SYMBOL;

import seaWar.GameStatusException;
import view.console.BoardViewConsole;
import view.console.BoardViewConsoleImpl;


public class ViewTests {

  public ViewTests() {
  }

  final LocalBoardImpl getLocalBoard() {
    return new LocalBoardImpl(GameImpl.createGame());
  }

  final RemoteBoardImpl getRemoteBoard() {
    return new RemoteBoardImpl(GameImpl.createGame());
  }

  final BoardViewConsole getBoardView() {
    return new BoardViewConsoleImpl();
  }


  @Test
  public void testOutputVisually() throws BoardException, GameStatusException {
    LocalBoardImpl localBoardImpl = this.getLocalBoard();
    RemoteBoardImpl remoteBoardImpl = this.getRemoteBoard();

    BoardViewAccess localBoard = localBoardImpl;
    BoardViewAccess remoteBoard = remoteBoardImpl;

    BoardViewConsole boardView = this.getBoardView();

    System.out.println("local board");
    boardView.printBoard(localBoard, System.out);

    System.out.println("remote board");
    boardView.printBoard(remoteBoard, System.out);

    // set a ship
    LocalBoard local = localBoardImpl;
    Ship ship5 = local.getUnsetShip(5);

    local.putShip(ship5, 3, 4, true);

    System.out.println("local board again");
    boardView.printBoard(localBoard, System.out);



  }

  //@Test
  public void checkAllWaterNoShips() {
    BoardViewAccess localBoard = this.getLocalBoard();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    BoardViewConsole boardView = this.getBoardView();
    boardView.printBoard(localBoard, new PrintStream(baos));
    System.out.print(baos);

    // check weather a ship was set - which shouldn't be the case
    byte[] byteArray = baos.toByteArray();

        /* Implementation might change: headers added and so forth
        at some point characters and white spaces alternate. Whenever
        we have reached that point when finding first newline (\n).
        Then we are in sync with that stream.
        */
    boolean spaceExpected = true;
    boolean synced = false;

    for(byte aByte : byteArray) {
      if(!synced) {
        if(aByte == '\n') synced = true;
        spaceExpected = false;
      } else {
        if(spaceExpected) {
          if( aByte != ' ' )
            fail("space excpected");
          spaceExpected = false;
        } else {
          if(aByte == '\n') continue; // newline allowed anytime
          if (aByte != WATER_SYMBOL)
            fail("should be water: " + aByte);
          spaceExpected = true;
        }
      }
    }
  }

  @Test
  public void inputTests() throws IOException {
    // expected: print board
    String inputString = "print";

    byte[] buffer = new byte[1000];
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    DataOutputStream dos = new DataOutputStream(baos);

    dos.writeUTF(inputString);

    // commands in byte array
    byte[] byteCommands = baos.toByteArray();

    ByteArrayInputStream bais = new ByteArrayInputStream(byteCommands);

    DataInputStream dis = new DataInputStream(bais);

    // read input from dis...
    String cmd = dis.readUTF();

    switch(cmd) {
      // ...
    }
  }
}