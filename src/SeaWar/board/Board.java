package SeaWar.board;

import SeaWar.GameStatusException;

/**
 * A board is 10 x 10 square. Coloumn and row count starts with 0. Final index in 9. User interface
 * should use alpahetic letters A, B etc. as coloumn index and number beginning with 1 for rows.
 */
public interface Board {

  public static final int MIN_COLUMN_INDEX = 0;
  public static final int MIN_ROW_INDEX = 0;
  public static final int MAX_COLUMN_INDEX = 9;
  public static final int MAX_ROW_INDEX = 9;

  /**
   * A shot was issued on that board.
   *
   * @param column coloum index of the shot
   * @param row    row index of that shot
   * @return result of that shot (note: a subsequent shot on a field that was already HIT results in
   * a HIT_WATER)
   * @throws GameStatusException if not in play status
   * @throws BoardException      if shot in not within the board
   */
  ShotResults shot(int column, int row) throws GameStatusException, BoardException;

  /**
   * Get status of that field
   *
   * @param column
   * @param row
   * @return status
   * @throws BoardException if coloumn/row not inside board
   * @see FieldStatus
   */
  FieldStatus getFieldStatus(int column, int row) throws BoardException;

  /**
   * change status of a field.This method can only be called when game has not yet started.
   *
   * @param column
   * @param row
   * @param newStatus
   * @throws GameStatusException wrong game status
   * @throws BoardException      coordinates outside board
   */
  void setFieldStatus(int column, int row, FieldStatus newStatus)
      throws GameStatusException, BoardException;
}
