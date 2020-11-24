package SeaWar;

/**
 * this Enumeration contains some static elements , that are used in this Game
 */
public enum GameStatus {
  /**
   * preparation phase - ships are set, anything can be changed, but shooting
   * is not allowed.
   */
  PREPARE,

  /**
   * play status. Shooting allowed but no further movement of ships
   */
  PLAY,

  /**
   * Game ended. Someone won
   */
  END
}
