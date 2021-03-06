package seaWar.board;

/**
 * Each Field can have a status which are described here. Field status can change:
 * WATER - can change to (SHIP) when vessels are placed on the board , SHIP - can
 * change to (HIT) when hit ,HIT - cannot change ,SHOT_ON_WATER - cannot change
 *
 */
public enum FieldStatus {
  /**
   * an intact (part of a) vessel is on that field
   */
  SHIP,
  /**
   * water
   */
  WATER,
  /**
   * was a ship but hit
   */
  HIT,
  /**
   * already fired on that field
   */
  SHOT_ON_WATER,
  /**
   * unknown - works only on remote board
   */
  UNKNOWN
}
