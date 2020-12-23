package seaWar;

/**
 * this Enumeration contains some static elements , that are used in this Game
 */
public enum GameStatus {
  /**
   * preparation phase - ships are set, anything can be changed, but shooting is not allowed.
   */
  PREPARE,

  /**
   * play status active. Shooting allowed but no further movement of ships is possible
   */
  PLAY_ACTIVE,

  /**
   * play status passive. Shooting is no more allowed until the enemy also shot,but no further
   * movement of ships is accepted
   */
  PLAY_PASSIVE,

  /**
   * Game ended. Someone won
   */
  END
}
