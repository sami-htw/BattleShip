package seaWar.board;

import seaWar.GameStatusException;

public interface Shot {

  /**
   * A shot was issued on that board.
   *
   * @param column column index of the shot
   * @param row    row index of that shot
   * @return result of that shot (note: a subsequent shot on a field that was already HIT results in
   * a HIT_WATER)
   * @throws GameStatusException if not in 'play' status,because before play shot is not accepted
   * @throws BoardException      if shot outside the board
   */
  ShotResults shot(int column, int row) throws GameStatusException, BoardException;

}
