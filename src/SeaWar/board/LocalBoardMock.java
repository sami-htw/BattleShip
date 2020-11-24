package SeaWar.board;

import SeaWar.GameStatusException;


public class LocalBoardMock extends BoardMock implements LocalBoard {

  @Override
  public Ship getShip(int coloum, int row) throws BoardException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void putShip(Ship ship, int column, int row, boolean horizontal)
      throws BoardException, GameStatusException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void removeShip(Ship ship) throws GameStatusException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Ship getUnsetShip(int length) throws BoardException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean allShipsSet() {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
