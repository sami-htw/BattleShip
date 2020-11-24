package BattleShipTesting;

import SeaWar.board.Board;
import SeaWar.board.BoardException;
import SeaWar.board.Coordinates;
import SeaWar.board.FieldStatus;
import SeaWar.board.LocalBoard;
import SeaWar.board.LocalBoardImpl;
import SeaWar.board.RemoteBoard;
import SeaWar.board.RemoteBoardImpl;
import SeaWar.board.Ship;
import SeaWar.board.ShipStatus;
import SeaWar.board.ShotResults;
import org.junit.Assert;
import org.junit.Test;
import SeaWar.SeaWarException;


public class BoardTests {

  public BoardTests() {
  }

  final LocalBoard getLocalBoard() {
//        return new LocalBoardMock();
    return new LocalBoardImpl();
  }

  final RemoteBoard getRemoteBoard() {
    return new RemoteBoardImpl();
//        return new RemoteBoardMock();
  }

  /////////////////////////////////////////////////////////////////
  //     "good" test for both board can be done like this        //
  /////////////////////////////////////////////////////////////////
  @Test
  public void niceShotToBoardTest() throws SeaWarException {
    // local test
    this.niceShotToBoard(this.getLocalBoard());
    // remote test
    this.niceShotToBoard(this.getRemoteBoard());
  }

  @Test
  public void edgeBoardShotTest() throws SeaWarException {
    // local test
    this.edgeBoardShot1(this.getLocalBoard());
    this.edgeBoardShot2(this.getLocalBoard());
    this.edgeBoardShot3(this.getLocalBoard());
    this.edgeBoardShot4(this.getLocalBoard());
    // remote test
    this.edgeBoardShot1(this.getRemoteBoard());
    this.edgeBoardShot2(this.getRemoteBoard());
    this.edgeBoardShot3(this.getRemoteBoard());
    this.edgeBoardShot4(this.getRemoteBoard());
  }

  @Test
  public void fieldStatusTest() throws SeaWarException {
    // local test
    this.fieldStatus(this.getLocalBoard());
    // remote test
    this.fieldStatus(this.getRemoteBoard());
  }

  @Test
  public void fieldStatusInitTest() throws SeaWarException {
    // local test
    this.fieldStatusInit(this.getLocalBoard());
    // remote test
    this.fieldStatusInit(this.getRemoteBoard());
  }

  ///////// double good tests

  /**
   * shot inside board
   */
  public void niceShotToBoard(Board board) throws SeaWarException {
    board.shot(4, 3);
    // must not produce an exception
  }

  /**
   * shot to edges
   */
  public void edgeBoardShot1(Board board) throws SeaWarException {
    board.shot(Board.MIN_COLUMN_INDEX, Board.MIN_ROW_INDEX);
    // must not produce an exception
  }

  /**
   * shot to edges
   */
  public void edgeBoardShot2(Board board) throws SeaWarException {
    board.shot(Board.MAX_COLUMN_INDEX, Board.MIN_ROW_INDEX);
    // must not produce an exception
  }

  /**
   * shot to edges
   */
  public void edgeBoardShot3(Board board) throws SeaWarException {
    board.shot(Board.MIN_COLUMN_INDEX, Board.MAX_ROW_INDEX);
    // must not produce an exception
  }

  /**
   * shot to edges
   */
  public void edgeBoardShot4(Board board) throws SeaWarException {
    board.shot(Board.MAX_COLUMN_INDEX, Board.MAX_ROW_INDEX);
    // must not produce an exception
  }

  /**
   * a shot make a field water or hit
   */
  public void fieldStatus(Board board) throws SeaWarException {
    board.shot(0, 0);
    FieldStatus fieldStatus = board.getFieldStatus(0, 0);

    Assert.assertTrue(fieldStatus == FieldStatus.WATER
        || fieldStatus == FieldStatus.HIT
        || fieldStatus == FieldStatus.SHOT_ON_WATER
        || fieldStatus == FieldStatus.UNKNOWN
    );
  }

  /**
   * a field cannot be hit in a fresh board
   */
  public void fieldStatusInit(Board board) throws SeaWarException {
    for (int c = 0; c < Board.MAX_COLUMN_INDEX; c++) {
      for (int r = 0; r < Board.MAX_ROW_INDEX; r++) {
        Assert.assertFalse(board.getFieldStatus(c, r) ==
            FieldStatus.HIT);
      }
    }
  }
  ///////// END: double good tests

  //////////////////////////////////////////////////////////////////////
  //                        structural tests:
  //                       shot outside board                        //
  //                       expect an exception                        //
  //////////////////////////////////////////////////////////////////////

  ///////////////// Local Board
  @Test(expected = BoardException.class)
  public void shotOutsideBoard1() throws SeaWarException {
    Board localBoard = this.getLocalBoard();
    localBoard.shot(-1, -1);
    // must not produce an exception
  }

  @Test(expected = BoardException.class)
  public void shotOutsideBoard2() throws SeaWarException {
    Board localBoard = this.getLocalBoard();
    localBoard.shot(0, 10);
    // must not produce an exception
  }

  //////////////////////////////////////////////////////////////////////
  //                        scenario tests                            //
  //////////////////////////////////////////////////////////////////////

  /**
   * One cannot hit same field twice
   */
  @Test
  public void twoShotsAreWaterLocal() throws SeaWarException {
    Board board = this.getLocalBoard();
    // shot at 0,0
    board.shot(0, 0);
    ShotResults shotResult = board.shot(0, 0);

    // at least second shot must be water
    Assert.assertEquals(ShotResults.HIT_WATER, shotResult);
  }

  @Test
  public void twoShotsAreWaterRemote() throws SeaWarException {
    Board board = this.getRemoteBoard();
    // shot at 0,0
    board.shot(0, 0);
    ShotResults shotResult = board.shot(0, 0);

    // at least second shot must be water
    Assert.assertEquals(ShotResults.HIT_WATER, shotResult);
  }

  /**
   * no peeping: we cannot see ships on the remote board
   */
  @Test
  public void noBoardPeeping() throws SeaWarException {
    Board board = this.getRemoteBoard();
    for (int c = 0; c < Board.MAX_COLUMN_INDEX; c++) {
      for (int r = 0; r < Board.MAX_ROW_INDEX; r++) {
        Assert.assertFalse(board.getFieldStatus(c, r) ==
            FieldStatus.SHIP);
      }
    }
  }

  //////////////////////////////////////////////////////////////////
  //                     local board scenarios                    //
  //////////////////////////////////////////////////////////////////

  /**
   * a brand new ship is ok
   */
  @Test
  public void shipCreation() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(5);
    Assert.assertEquals(ShipStatus.OK, ship.getStatus());
  }

  /**
   * a brand new ship is ok
   */
  @Test(expected = BoardException.class)
  public void shipNotSetCoordinatesFailure() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(5);
    ship.getCoordinates();
  }

  /**
   * place a ship
   */
  @Test
  public void shipHorizontalPlaceOK() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(4);
    // upper left corner - to the right
    localBoard.putShip(ship, 0, 0, true);

    // must occupy field 0,0; 1,0; 2,0 and 3,0
    boolean[] found = new boolean[4];
    for (int i = 0; i < 4; i++) {
      found[i] = false;
    }

    Coordinates[] coordinates = ship.getCoordinates();
    for (Coordinates coo : coordinates) {
      // we are in row 0
      Assert.assertEquals(0, coo.getRow());
      // is column 0 up to 3
      Assert.assertTrue(coo.getColumn() >= 0 && coo.getColumn() <= 3);

      // no duplicates allowed
      Assert.assertFalse(found[coo.getColumn()]);
      found[coo.getColumn()] = true; // found
    }
  }

  /**
   * place a ship
   */
  @Test
  public void shipVerticalPlaceOK() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(4);

    // upper left corner - to the right
    localBoard.putShip(ship, 3, 0, false);

    // must occupy field 3,0 3,1 3,2 3,3
    boolean[] found = new boolean[4];
    for (int i = 0; i < 4; i++) {
      found[i] = false;
    }

    Coordinates[] coordinates = ship.getCoordinates();
    for (Coordinates coo : coordinates) {
      // we are in column 3
      Assert.assertEquals(3, coo.getColumn());

      // is row 0 up to 3
      Assert.assertTrue(coo.getRow() >= 0 && coo.getRow() <= 3);

      // no duplicates allowed
      Assert.assertFalse(found[coo.getRow()]);
      found[coo.getRow()] = true; // found
    }
  }

  /**
   * first field outside board
   */
  @Test(expected = BoardException.class)
  public void shipWrongPlacement1() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(4);
    LocalBoard board = this.getLocalBoard();
    // upper left corner - to the right
    board.putShip(ship, 0, -1, false);
  }

  /**
   * place a ship hanging over
   */
  @Test(expected = BoardException.class)
  public void shipWrongPlacement2() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(4);
    LocalBoard board = this.getLocalBoard();
    // start upper right corner to the right - that's too long
    board.putShip(ship, 9, 0, true);
  }

  /**
   * place a ship hanging over
   */
  @Test(expected = BoardException.class)
  public void shipWrongPlacement3() throws SeaWarException {
    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(4);
    LocalBoard board = this.getLocalBoard();
    // start upper right corner and downwards - that's too long
    board.putShip(ship, 0, 9, false);
  }

  /**
   * place a ship on edge
   */
  @Test
  public void shipEdgePlacement3() throws SeaWarException {
    LocalBoard board = this.getLocalBoard();

    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(2);
    // start upper right corner but only two fields - ok
    board.putShip(ship, 0, 8, false);

    ship = localBoard.getUnsetShip(2);
    // start lower right corner but only tw fields - ok
    board.putShip(ship, 9, 8, false);
  }

  /**
   * place a ship on edge
   */
  @Test
  public void shipEdgePlacement4() throws SeaWarException {
    LocalBoard board = this.getLocalBoard();

    LocalBoard localBoard = this.getLocalBoard();
    Ship ship = localBoard.getUnsetShip(2);
    // start upper right corner but only two fields - ok
    board.putShip(ship, 8, 0, true);

    ship = localBoard.getUnsetShip(2);
    // start lower right corner but only tw fields - ok
    board.putShip(ship, 8, 8, true);
  }
}
