package SeaWar.board;

/**
 * A shot onto a board can produce different results.
 */
public enum ShotResults {
  HIT, // A ship was hit but not completly destroyed
  HIT_DESTROYED, // A ship was hit an destroy
  HIT_FINISHED, // The final ship was hit and destroyed - game ended
  HIT_WATER // Nothing but water was hit
}
