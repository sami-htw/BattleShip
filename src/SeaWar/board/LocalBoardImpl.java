package SeaWar.board;

import java.util.ArrayList;
import java.util.List;
import SeaWar.GameStatusException;


public class LocalBoardImpl extends BoardImpl implements LocalBoard {

  static final int MIN_SHIP_LENGTH = 2;

  /**
   * object references of each ship is stored in that matrix
   */
  private final Ship[][] shipsOnBoard;

  /**
   * Ships belonging to that board ships[0] contains 4 ships length 2 ships[1] contains 3 ships
   * length 3 ships[2] contains 2 ships length 4 ships[3] contains 1 ship length 5
   */
  private final Ship[][] ships;

  public LocalBoardImpl() {
    super(FieldStatus.WATER);
    this.shipsOnBoard =
        new Ship[Board.MAX_COLUMN_INDEX + 1][Board.MAX_ROW_INDEX + 1];

    this.ships = new Ship[LocalBoard.MAX_SHIP_LENGTH - 1][];

    // create ships.. start with little ones
    for (int length = MIN_SHIP_LENGTH; length <= MAX_SHIP_LENGTH; length++) {
      // create array holding ships with same length
      Ship[] shipArray = new Ship[this.getMaxShipNumber(length)];

      // add actual ship objects with that length
      for (int i = 0; i < this.getMaxShipNumber(length); i++) {
        shipArray[i] = new ShipImpl(length);
      }

      // keep that new list in overall ships list
      this.ships[length - MIN_SHIP_LENGTH] = shipArray;
    }
  }

  /**
   * Return maximal number of ships with that length
   *
   * @param length
   * @return
   */
  private int getMaxShipNumber(int length) {
    return MAX_SHIP_LENGTH + 1 - length;
  }

  @Override
  public Ship getUnsetShip(int length) throws BoardException {
    if (length > MAX_SHIP_LENGTH || length < MIN_SHIP_LENGTH) {
      throw new BoardException("length outside allowed range: " + length);
    }

    Ship[] shipsArray = this.ships[length - MIN_SHIP_LENGTH];

    for (int i = 0; i < this.getMaxShipNumber(length); i++) {
      if (!shipsArray[i].isSet()) {
        return shipsArray[i];
      }
    }

    throw new BoardException("no ship left with length: " + length);
  }

  @Override
  public Ship getShip(int column, int row) throws BoardException {
    this.check4BoardException(column, row);
    return this.shipsOnBoard[column][row];
  }

  /**
   * Keeps all field in sync with ship movement.
   *
   * @param ship on which something happens
   * @param put  true: ship is placed on those coordinates. false removed
   * @throws GameStatusException
   * @throws BoardException
   */
  private void moveShipOnFields(Ship ship, boolean put)
      throws GameStatusException, BoardException {

    if (ship == null) {
      return;
    }
    Coordinates[] coordinates = ship.getCoordinates();

    // ship is put - we set SHIP on the field, otherwise WATER
    FieldStatus status = put ? FieldStatus.SHIP : FieldStatus.WATER;

    // if put we remember object reference - otherwise remove it
    Ship shipToSet = put ? ship : null;

    for (Coordinates coordinate : coordinates) {
      // sync field status
      this.setFieldStatus(
          coordinate.getColumn(),
          coordinate.getRow(),
          status);

      // sync ships on board
      this.shipsOnBoard[coordinate.getColumn()][coordinate.getRow()] =
          shipToSet;
    }
  }

  @Override
  public void removeShip(Ship ship) throws GameStatusException {
    try {
      this.moveShipOnFields(ship, false);
    } catch (BoardException ex) {
      // cannot occure - tested when ship was placed
      throw new GameStatusException("serious internal error: ship was placed"
          + "on wrong coordinates");
    }

    ship.remove();
  }


  private boolean isShipOnCoordinates(List<Coordinates> coos) throws BoardException {
    for (Coordinates coo : coos) {
      FieldStatus status =
          this.getFieldStatus(coo.getColumn(), coo.getRow());

      if (status == FieldStatus.SHIP) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void putShip(Ship ship, int column, int row, boolean horizontal)
      throws BoardException, GameStatusException {

    this.check4BoardException(column, row);

    // check for existing ships too close neighbours.
    List<Coordinates> cooList = new ArrayList<>();

    // first - put future coordinates into that list

    // BoardException would lead to method abortion - ok: vessel would be
    // on board.
    Coordinates coo = new CoordinatesImpl(column, row);
    cooList.add(coo);
    // start with index 1: one coordinate is already handled
    for (int i = 1; i < ship.getLength(); i++) {
      coo = BoardImpl.getNextCoordinate(coo, horizontal);
      cooList.add(coo);
    }

    int columnBow = horizontal ? column - 1 : column;
    int rowBow = horizontal ? row : row - 1;

    // if those coordinates are still on board (wordplay :-)) :/) - add it.
    if (this.coordinatesOnBoard(columnBow, rowBow)) {
      cooList.add(new CoordinatesImpl(columnBow, rowBow));
    }

    // stern (S) Heck
    int columnStern = horizontal ? column + ship.getLength() : column;
    int rowStern = horizontal ? row : row + ship.getLength();

    // if those coordinates are on board - add it.
    if (this.coordinatesOnBoard(columnStern, rowStern)) {
      cooList.add(new CoordinatesImpl(columnStern, rowStern));
    }

    // add startbord and portside

    // horizontal
    if (horizontal) {
      // starboard
      if (row > Board.MIN_ROW_INDEX) {
        this.addCoordinates(cooList, column, row - 1,
            ship.getLength(), horizontal);

      }
      // portside
      if (row < Board.MAX_ROW_INDEX) {
        this.addCoordinates(cooList, column, row + 1,
            ship.getLength(), horizontal);
      }
      // vertical
    } else {
      // starboard
      if (column < Board.MAX_COLUMN_INDEX) {
        this.addCoordinates(cooList, column + 1, row,
            ship.getLength(), horizontal);
      }

      // portside
      if (column > Board.MIN_COLUMN_INDEX) {
        this.addCoordinates(cooList, column - 1, row,
            ship.getLength(), horizontal);
      }
    }

    if (this.isShipOnCoordinates(cooList)) {
      throw new BoardException("vessel too close to or ontop of another");
    }

    // there are no other ships - put ship
    ship.putShip(column, row, horizontal);

    // adjust fields status
    this.moveShipOnFields(ship, true);
  }

  /**
   * Add coordinate to list. Add also all following number fields.
   *
   * @param list
   * @param column
   * @param row
   * @param number
   * @param horizontal
   */
  private void addCoordinates(List<Coordinates> list, int column, int row,
      int number, boolean horizontal) {

    Coordinates coo = new CoordinatesImpl(column, row);
    list.add(coo);
    // start with 1 - one coordinate is already stored
    for (int i = 1; i < number; i++) {
      try {
        coo = BoardImpl.getNextCoordinate(coo, horizontal);
      } catch (BoardException ex) {
        // can happen in last call - ok
        return;
      }
      list.add(coo);
    }
  }

  /**
   * We follow the decorator pattern: Add functionality to board management
   *
   * @param column
   * @param row
   * @return
   * @throws GameStatusException
   * @throws BoardException
   */
  @Override
  public ShotResults shot(int column, int row) throws GameStatusException,
      BoardException {

    FieldStatus fieldStatus = this.getFieldStatus(column, row);

    // lets handle superclass the board first
    ShotResults shotResult = super.shot(column, row);

    if (fieldStatus != FieldStatus.SHIP) {
      // there was no ship on that field - we are done here
      return shotResult;
    }

    // there was a ship
    Ship ship = this.shipsOnBoard[column][row];
    return ship.shot(column, row);
  }

  @Override
  public boolean allShipsSet() {
    // is there any unset ship?
    for (Ship[] shipArray : this.ships) {
      for (int i = 0; i < shipArray.length; i++) {
        if (!shipArray[i].isSet()) {
          // yes
          return false; // not all ships set
        }
      }
    }

    // no
    return true; // all ships set
  }
}
