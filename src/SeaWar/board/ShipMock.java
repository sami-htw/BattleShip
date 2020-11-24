package SeaWar.board;


public class ShipMock implements Ship {

  public ShipMock(int length) {
  }

  @Override
  public ShipStatus getStatus() {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean isSet() {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void putShip(int column, int row, boolean horizontal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Coordinates[] getCoordinates() throws BoardException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public ShotResults shot(int column, int row) {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getLength() {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
