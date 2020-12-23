package seaWar.board;

/**
 * A shot onto a board can produce different results.
 */
public enum ShotResults {
  HIT, // A ship was hit but not completely destroyed
  HIT_DESTROYED, // A ship was hit and destroyed
  HIT_FINISHED, // The final ship was hit and destroyed - game ended
  HIT_WATER // Nothing but water was hit
}
