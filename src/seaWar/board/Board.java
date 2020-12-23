package seaWar.board;

import seaWar.Game;
import seaWar.GameStatusException;

/**
 * A board is 10 x 10 square. Each Column or row count starts with 0. Final index is 9. User interface
 * should use alphabetic letters A...to J as column index and number beginning with 1 for rows.
 */
public interface Board {
  public int MIN_COLUMN_INDEX = 0;
  public int MIN_ROW_INDEX = 0;
  public int MAX_COLUMN_INDEX = 9;
  public int MAX_ROW_INDEX = 9;

  public char MIN_COLUMN_CHAR = 'A';
  public char MAX_COLUMN_CHAR = 'J';

  public int MAX_SHIP_LENGTH = 5;
  public int MIN_SHIP_LENGTH = 2;

  /**
   * Get status of that field
   * @param column
   * @param row
   * @return status
   * @throws BoardException if column/row not inside board
   * @see FieldStatus
   */
  FieldStatus getFieldStatus(int column, int row) throws BoardException;

  /**
   * change status of a field.This method can only be called when
   * game has not started yet .
   * @param column
   * @param row
   * @param newStatus
   * @throws GameStatusException wrong game status
   * @throws BoardException coordinates outside board
   */
  void setFieldStatus(int column, int row, FieldStatus newStatus)
      throws GameStatusException, BoardException;

  Game getGame();
}
