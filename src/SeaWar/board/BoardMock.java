package SeaWar.board;

import SeaWar.GameStatusException;


public class BoardMock implements Board {

  @Override
  public ShotResults shot(int column, int row) throws GameStatusException, BoardException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public FieldStatus getFieldStatus(int column, int row) throws BoardException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void setFieldStatus(int column, int row, FieldStatus newStatus)
      throws GameStatusException, BoardException {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
